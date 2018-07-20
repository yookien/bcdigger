package com.bcdigger.goods.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcdigger.admin.entity.Admin;
import com.bcdigger.admin.service.AdminService;
import com.bcdigger.common.constant.CacheConstant;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.common.utils.DateUtils;
import com.bcdigger.core.annotation.AdminAuth;
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsInstoreBiz;
import com.bcdigger.goods.service.GoodsInstoreService;
import com.bcdigger.kingdee.util.AccessToken;
import com.bcdigger.kingdee.util.KingdeeStdLib;
import com.bcdigger.kingdee.util.KingdeeUtil;
import com.bcdigger.order.entity.GoodsOrder;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.service.GoodsOrderItemService;
import com.bcdigger.order.service.GoodsOrderService;

/**
 * 
 * ClassName: GoodsInstoreGoodsInstoreController
 * @Description: 库存Controller
 * @author ipui
 * @date 2018年5月3日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/goods")
@AdminAuth  // 门店管理全部需要登录鉴权
public class GoodsStoreController {
	
	@Autowired
	private GoodsInstoreService goodsInstoreService;
	@Autowired
	private GoodsOrderService goodsOrderService;
	@Autowired
	private GoodsOrderItemService goodsOrderItemService;
	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping(value ="/addGoodsInstore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> addGoodsInstore(GoodsInstoreBiz goodsInstoreBiz,HttpServletRequest request){
		Integer adminId=(Integer)request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
//adminId=1;
		Map<String, Object> map = new HashMap<>();  
		try{
			GoodsOrderItem goodsOrderItem = goodsOrderItemService.getGoodsOrderItemById(goodsInstoreBiz.getGoodsOrderItemId());
			GoodsInstore goodsInstore = new GoodsInstore();
			goodsInstore.setBatchNo("OMSRK"+DateUtils.formatDate(new Date(), "yyyyMMdd")+String.valueOf(RandomUtils.nextInt(10000)+10000));
			goodsInstore.setGoodsId(goodsOrderItem.getGoodsId());
			goodsInstore.setGoodsOrderId(goodsOrderItem.getGoodsOrderId());
			goodsInstore.setGoodsOrderItemId(goodsOrderItem.getId());
			goodsInstore.setInQuantity(goodsInstoreBiz.getInQuantity());
			goodsInstore.setOperator(adminId);
			goodsInstore.setStoreId(goodsInstoreBiz.getStoreId());
			goodsInstore.setRepositoryId(1);
			goodsInstore.setState(0);
			goodsInstore.setType(5000);
			goodsInstore.setVersion(0);
			int result = goodsInstoreService.addGoodsInstore(goodsInstore);
			map.put("result", 1);//插入成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Description: 根据id查询门店信息
	 * @param id
	 * @return Map<String,Object>  
	 * @throws
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsInstore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getGoodsInstore(GoodsInstore GoodsInstore) {
		Map<String, Object> map = new HashMap<>();
		try{
			if( GoodsInstore==null || GoodsInstore.getId()<=0){
				return null;
			}
			GoodsInstore = goodsInstoreService.getGoodsInstore(GoodsInstore.getId());
			map.put("result", 1);//登录成功
			map.put("GoodsInstore", GoodsInstore);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Description:打开门店管理首页
	 * @param request
	 * @return
	 * @return String
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/goodsInstoreGtoreIndex")
	public String goodsInstoreGtoreIndex() {
		return "/GoodsInstore/GoodsInstore_index";
	}

	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsInstores",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsInstores(GoodsInstore GoodsInstore, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<GoodsInstore> GoodsInstorePages = goodsInstoreService.getGoodsInstore(GoodsInstore, pageInfo);
			map.addAttribute("pageInfo", GoodsInstorePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/GoodsInstore/GoodsInstore_list";
	}
	
	@RequestMapping(value ="/goodsInstoreAddIndex")
	public String goodsInstoreAddIndex() {
		return "/goods/instore_add_index";
	}
	
	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsInstoreAdds",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsInstoreAdds(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo,ModelMap map,HttpServletRequest request) {
		try{
			Integer adminId=(Integer)request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
//adminId=1;
			Admin admin=adminService.getAdmin(adminId);
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			goodsInstoreBiz.setInstoreState(-1);
			goodsInstoreBiz.setStoreId(admin.getStoreId());
			PageInfo<GoodsInstoreBiz> GoodsInstorePages = goodsInstoreService.getGoodsInstoreBizs(goodsInstoreBiz, pageInfo);
			map.addAttribute("pageInfo", GoodsInstorePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/goods/instore_add_list";
	}
	
	/**
	 * @Description: 根据goodsOrderId查找具体的收货信息
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getAddGoodsInstoreInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAddGoodsInstoreInfo(GoodsInstoreBiz goodsInstoreBiz) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsInstoreBiz> list = goodsInstoreService.getAddGoodsInstoreInfo(goodsInstoreBiz);
		map.put("list",list);
		map.put("result", 1);
		return map;
	}
	
	
	@RequestMapping(value ="/goodsInstoreAuditIndex")
	public String goodsInstoreAuditIndex() {
		return "/goods/instore_audit_index";
	}
	
	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsInstoreAudits",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsInstoreAudits(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			goodsInstoreBiz.setInstoreState(0);
			PageInfo<GoodsInstoreBiz> GoodsInstorePages = goodsInstoreService.getGoodsInstoreBizs(goodsInstoreBiz, pageInfo);
			map.addAttribute("pageInfo", GoodsInstorePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/goods/instore_audit_list";
	}
	
	/**
	 * @Description: 根据goodsOrderId查找具体的收货信息
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsInstoreInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getGoodsInstoreInfo(GoodsInstoreBiz goodsInstoreBiz) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsInstoreBiz> list = goodsInstoreService.getGoodsInstoreInfo(goodsInstoreBiz);
		map.put("list",list);
		map.put("result", 1);
		return map;
	}
	
	/**
	 * @Description: 根据goodsOrderId查找具体的收货信息
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateInstoreInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateInstoreInfo(GoodsInstoreBiz goodsInstoreBiz) {
		Map<String, Object> map = new HashMap<>();
		synchronized(goodsInstoreBiz) {
			GoodsInstore goodsInstore = goodsInstoreService.getGoodsInstore(goodsInstoreBiz.getGoodsInstoreId());
			if(goodsInstoreBiz.getAuditType()==1) {//审核通过
				//更新订单明细信息
				GoodsOrderItem goodsOrderItem = goodsOrderItemService.getGoodsOrderItemById(goodsInstoreBiz.getGoodsOrderItemId());	
				goodsOrderItem.setInstoreQuantity(goodsOrderItem.getInstoreQuantity()+goodsInstoreBiz.getInQuantity());
				goodsOrderItemService.updateGoodsOrderItem(goodsOrderItem);
				//更新入库信息
				goodsInstore.setState(1);
				goodsInstoreService.updateGoodsInstore(goodsInstore);
				//同步金蝶系统数据（待补充）
				pushGoodsInstore(goodsInstore);
			
			}else {//审核不通过
				goodsInstore.setState(2);
				goodsInstoreService.updateGoodsInstore(goodsInstore);
			}
		}
		map.put("result", 1);
		return map;
	}
	
	/**
	 * 关闭订货单首页
	 * @Title: goodsInstoreAuditIndex   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value ="/closeOrderIndex")
	public String closeOrderIndex() {
		return "/goods/close_order_index";
	}
	
	/**
	 * @Description: 分页查找订货单列表（待关闭）
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getCloseOrders",method={RequestMethod.GET,RequestMethod.POST})
	public String getCloseOrders(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			PageInfo<GoodsInstoreBiz> GoodsInstorePages = goodsInstoreService.getCloseOrders(goodsInstoreBiz, pageInfo);
			map.addAttribute("pageInfo", GoodsInstorePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/goods/close_order_list";
	}
	
	/**
	 * 获取待关闭订货单的详细信息（根据订货单id）
	 * @Title: getCloseOrderInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping(value ="/getCloseOrderInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCloseOrderInfo(GoodsInstoreBiz goodsInstoreBiz) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsInstoreBiz> list = goodsInstoreService.getCloseOrderInfo(goodsInstoreBiz);
		map.put("list",list);
		map.put("result", 1);
		return map;
	}
	/**
	 * 关闭订货单
	 * @Title: updateCloseOrderInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@RequestMapping(value ="/updateCloseOrderInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateCloseOrderInfo(GoodsInstoreBiz goodsInstoreBiz) {
		Map<String, Object> map = new HashMap<>();
		if(goodsInstoreBiz!=null && goodsInstoreBiz.getGoodsOrderId()!=0) {
			GoodsOrder goodsOrder = goodsOrderService.getGoodsOrderById(goodsInstoreBiz.getGoodsOrderId());
			goodsOrder.setState(10040);
			goodsOrderService.updateGoodsOrder(goodsOrder);
		}
		map.put("result", 1);
		return map;
	}
	
	
	/**
	 * 金蝶相关接口
	 */
	private static String sessionValue = "";
	private static String aspnetsessionValue = "";
	
	private static String sessionkey = "kdservice-sessionid";
	private static String aspnetsessionkey = "ASP.NET_SessionId";
	
	/**
	 * 采购订单下推生成采购入库单
	 * 
	 * @param tOrder
	 */
	public String pushGoodsInstore(GoodsInstore goodsInstore) {
		try {
			// 得到登录接口
			AccessToken accessToken = KingdeeUtil.getAccessToken();
			if (accessToken != null) {
				sessionValue = accessToken.getSessionValue();
				aspnetsessionValue = accessToken.getAspnetsessionValue();
			}
			// 定义httpClient的实例
			HttpClient httpclient = new DefaultHttpClient();
			// 订单保存接口
			String save_URL = KingdeeStdLib.KINGDEE_PUSH_URL;
			URI save_uri = new URI(save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("formid", "BD_MATERIAL");

			JSONObject jsonData = new JSONObject();
			JSONObject jsonModel = new JSONObject();
			// 需替换成采购单金蝶内码id、采购单号
			jsonModel.put("Ids", goodsInstore.getGoodsOrderId());
			JSONArray jsArrEntry = new JSONArray();
			jsArrEntry.add("");
			jsonModel.put("Numbers", jsArrEntry);
			
			jsonData.put("Model", jsonModel);
			json.put("data", jsonData);
			// 设置json格式
			StringEntity entity = new StringEntity(json.toString(), "utf-8");
			System.out.println(json.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			// 将登陆信息放入
			method.setHeader(sessionkey, sessionValue);
			method.setHeader(aspnetsessionkey, aspnetsessionValue);
			method.setEntity(entity);
			HttpResponse result = httpclient.execute(method);
			String str = "";
			if (result.getStatusLine().getStatusCode() == 200) {
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				System.out.println("result:"+str);
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
}
