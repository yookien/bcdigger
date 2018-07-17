package com.bcdigger.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsInstoreBiz;
import com.bcdigger.goods.service.GoodsInstoreService;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.service.GoodsOrderItemService;

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
	private GoodsOrderItemService goodsOrderItemService;
	
	
	@RequestMapping(value ="/addGoodsInstore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> addGoodsInstore(GoodsInstore GoodsInstore){
		Map<String, Object> map = new HashMap<>();  
		try{
			int result = goodsInstoreService.addGoodsInstore(GoodsInstore);
			map.put("result", result);//登录成功
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
	 * 
	 * @Description: 更新门店信息
	 * @param GoodsInstore
	 * @return Map<String,Object>
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateGoodsInstore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateGoodsInstore(GoodsInstore GoodsInstore){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(GoodsInstore==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			int result = goodsInstoreService.updateGoodsInstore(GoodsInstore);
			map.put("result", result);//更新成功
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
	@RequestMapping(value ="/GoodsInstoreGtoreIndex")
	public String sysMenusIndex() {
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
	public Map<String, Object> getGoodsInstoreAudits(GoodsInstoreBiz goodsInstoreBiz) {
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
			
			
		}else {//审核不通过
			goodsInstore.setState(2);
			goodsInstoreService.updateGoodsInstore(goodsInstore);
		}
		map.put("result", 1);
		return map;
	}
}
