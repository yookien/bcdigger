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
import com.bcdigger.goods.service.GoodsService;
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
	
	@Autowired
	private GoodsService goodsService;
	
	
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
				updateInstoreInfo(goodsInstore);
			
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
     * 保存采购入库单
     */
	@RequestMapping(value ="/t_updateInstoreInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateInstoreInfo(
			GoodsInstore goodsInstore){
		Map<String, Object> map = new HashMap<>(); 
		try {
			// ***********参数处理 开始*********
			// 得到登录接口
			AccessToken accessToken = KingdeeUtil.getAccessToken();
			if (accessToken != null) {
				sessionValue = accessToken.getSessionValue();
				aspnetsessionValue = accessToken.getAspnetsessionValue();
			}
			// 定义httpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 入库单保存接口地址
			String save_URL = KingdeeStdLib.KINGDEE_BATCH_SAVE_URL;// KINGDEE_SAVE_URL
			URI save_uri = new URI(save_URL);
			HttpPost method = new HttpPost(save_uri);
			// 拼接json字符串
			JSONObject json = new JSONObject();
			json.put("formid", "STK_InStock");
			json.put("ValidateFlag", false);
			json.put("NeedUpDateFields", "['FRealQty','FStockID']");
			
			JSONObject jsonData = new JSONObject();
			JSONObject jsonModel = new JSONObject();
			jsonModel.put("FID", 100470);
			// 直配采购：RKD01_CUST  标准采购：RKD01_SYS 
			jsonModel.put("FBillTypeID", KingdeeUtil.getFNumber("RKD01_CUST")); // 入库单据类型 
			jsonModel.put("FDate", KingdeeUtil.getDateForString(new Date())); // 日期
			jsonModel.put("FBillNo", "CGRK1807210001"); 
			//jsonModel.put("FSRCBillNo", "CGDD1807140172");
			
			// 收料组织、需求组织和采购组织
			jsonModel.put("FStockOrgId", KingdeeUtil
					.getFNumber(KingdeeStdLib.saleOrgId));
			jsonModel.put("FDemandOrgId", KingdeeUtil
					.getFNumber(KingdeeStdLib.saleOrgId));
			jsonModel.put("FPurchaseOrgId", KingdeeUtil
					.getFNumber(KingdeeStdLib.saleOrgId));
			// 得到供应商编码(从货号关联种获取)
			String purchaseKindeeNo = "YLBC0044";
			jsonModel.put("FSupplierId", KingdeeUtil
					.getFNumber(purchaseKindeeNo));
			// 货主类型和货主
			jsonModel.put("FOwnerTypeIdHead", "BD_OwnerOrg");
			jsonModel.put("FOwnerIdHead", KingdeeUtil
					.getFNumber(KingdeeStdLib.saleOrgId));

			jsonModel.put("FOwnerIdHead", KingdeeUtil.getFNumber(KingdeeStdLib.saleOrgId));
			
			// 支付详情
			JSONObject jsFinance = new JSONObject();
			jsFinance.put("FSettleOrgId", KingdeeUtil
					.getFNumber(KingdeeStdLib.saleOrgId));
			jsFinance.put("FSettleCurrId", KingdeeUtil.getFNumber("PRE001"));// 结算币别
			jsFinance.put("FExchangeTypeId", KingdeeUtil
					.getFNumber("HLTX01_SYS"));
			jsFinance.put("FExchangeRate", 1); // 汇率
			jsFinance.put("FPriceTimePoint", 2); // 单据日期
			jsonModel.put("FInStockFin", jsFinance);
			// 入库详情 库存入库表集合
			JSONArray jsArrEntry = new JSONArray();
			// 循环封装明细
			Goods goods;
			for (int i = 0; i < 1; i++) {
				JSONArray jsArrEntry1 = new JSONArray();
				int inQuantity = 20;
				// 测试商品 对应物料4030300801
				goods = this.goodsService.getGoods(545);//goodsInstore.getGoodsId()
				if(goods == null){
					continue;
				}
				String unitID=String.valueOf(goods.getUnitCustId());
				// 入库详情 库存入库表集合
				JSONObject jsEntry = new JSONObject();
				// 关联实体
				JSONObject jsEntry1 = new JSONObject();
				jsEntry.put("FUnitID", KingdeeUtil.getFNumber(unitID));
				jsEntry
						.put("FMaterialId", KingdeeUtil
								.getFNumber("4030300801")); // 物料
				// jsEntry.put("FUnitID",KingdeeUtil.getFNumber("Pcs"));//单位
				jsEntry.put("FRealQty", inQuantity);// 实收数量
				jsEntry.put("FTaxPrice", 0);// 含税单价
				jsEntry.put("FPrice", 0);// 未税单价
				
				jsEntry.put("FID", 101428);// 分录id
				 
				// 税额
				// jsEntry.put("FEntryTaxAmount",inQuantity*Integer.parseInt(taxPrices[i])/100);
				// 税率
				jsEntry.put("FEntryTaxRate",16);// 税率
				// 仓库id
				jsEntry.put("FStockID", KingdeeUtil
						.getFNumber("HNSZ91"));// 仓库id 先写死
				// 计价单位  从商品中获取
				jsEntry.put("FPriceUnitID", KingdeeUtil.getFNumber(unitID));

				jsEntry.put("FRemainInStockUnitId", KingdeeUtil
						.getFNumber(unitID));
				// 原单号 采购单单号？
				/**jsEntry.put("FSRCBillNo", "CGDD1807140172");
				// 原单类型
				jsEntry.put("FSRCBILLTYPEID", "SCP_PurchaseOrder");
				jsEntry.put("FPOORDERENTRYID", "");
				jsEntry.put("FPOOrderNo", "");
				// 关联表关系
				jsEntry1.put("FInStockEntry_Link_FRuleId",
						"PUR_ReceiveBill-STK_InStock");
				jsEntry1.put("FInStockEntry_Link_FSTableName",
						"T_PUR_ReceiveEntry");
				jsEntry1.put("FInStockEntry_Link_FSBillId", Integer
						.parseInt("1"));// 待修改
				jsEntry1.put("FInStockEntry_Link_FSId", Integer
						.parseInt("1"));// 待修改
				jsEntry1.put("FInStockEntry_Link_FBaseUnitQtyOld", inQuantity);
				jsEntry1.put("FInStockEntry_Link_FBaseUnitQty", inQuantity);
				jsArrEntry1.add(jsEntry1);
				jsEntry.put("FInStockEntry_Link", jsArrEntry1);*/
				jsArrEntry.add(jsEntry);
			}
			jsonModel.put("FInStockEntry", jsArrEntry);
			
			JSONArray modelArray = new JSONArray();
			modelArray.add(jsonModel);
			jsonData.put("Model", modelArray);
			json.put("data", jsonData);
			System.out.println(json.toString());
			StringEntity entity = new StringEntity(json.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			// 将登陆信息放入
			method.setHeader(sessionkey, sessionValue);
			method.setHeader(aspnetsessionkey, aspnetsessionValue);
			method.setEntity(entity);
			HttpResponse result = httpclient.execute(method);
			if (result.getStatusLine().getStatusCode() == 200) {
				System.out.println("请求成功");
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				System.out.println(str);
				// 根据返回结果成功与否决定入库
				JSONObject jsonObject1 = JSONObject.parseObject(str);
				JSONObject resultjson = jsonObject1.getJSONObject("Result");

				JSONObject jsResponse = resultjson
						.getJSONObject("ResponseStatus");
				boolean jsError = jsResponse.getBoolean("IsSuccess");
				if (jsError != true) {
					//金蝶保存失败
				} else {
					String successNumber = resultjson.getString("Number");
					String formId = "STK_InStock";
					String url = KingdeeStdLib.KINGDEE_SUBMIT_URL;
					String checkUrl = KingdeeStdLib.KINGDEE_AUDIT_URL;
					// String FBillNo = "CGRK00066";
					Boolean f = SingleKingSubmitAndCheck(formId,
							url, successNumber);
					System.out.println("状态：" + f);
					if (f == true) { // 提交成功
						System.out.println("提交成功");
						Boolean e = SingleKingSubmitAndCheck(
								formId, checkUrl, successNumber);
						if (e == true) { // 审核成功
							System.out.println("审核成功！！");
						} else {
							// 金蝶审核失败
						}
					} else {
						// 金蝶提交失败
					}
				}
			} else {
				//金蝶接口返回失败
			}
			map.put("result", 1);// 同步成功
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", 0);// 系统异常
		}
		return map;
	}
	
	
   //单个提交
   public  static Boolean SingleKingSubmitAndCheck(String formId,String url,String FBillNo){
	   	boolean jsError =false;
	   	JSONObject json=new JSONObject();
		JSONObject jsonData=new JSONObject();
	   try {
			AccessToken accessToken=KingdeeUtil.getAccessToken();
			if(accessToken!=null){
				sessionValue =accessToken.getSessionValue();
				aspnetsessionValue=accessToken.getAspnetsessionValue();
			}
			// 定义httpClient的实例
			HttpClient httpclient = new DefaultHttpClient();
			//String save_URL = 
			URI save_uri = new URI(url);
			HttpPost method = new HttpPost(save_uri);
			//设置参数
			json.put("formid",formId);
			jsonData.put("Numbers",FBillNo);
			json.put("data",jsonData);
			
			//设置json格式
			StringEntity entity = new StringEntity(json.toString(), "utf-8");
			System.out.println(json.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			//将登陆信息放入
			method.setHeader(sessionkey, sessionValue);
			method.setHeader(aspnetsessionkey, aspnetsessionValue);
			method.setEntity(entity);
			HttpResponse result =httpclient.execute(method);
			
			if(result.getStatusLine().getStatusCode()==200){
				String str ="";
				str = EntityUtils.toString(result.getEntity());
				JSONObject jsonObject1=JSONObject.parseObject(str);
				JSONObject resultjson=jsonObject1.getJSONObject("Result");
				JSONObject jsResponse=resultjson.getJSONObject("ResponseStatus");
				jsError=jsResponse.getBoolean("IsSuccess");
				System.out.println("提交或审核结果:"+jsonObject1.toString());
			}
	   }catch (Exception e) {
			e.printStackTrace();
		}
	   return jsError;
   }

	
}
