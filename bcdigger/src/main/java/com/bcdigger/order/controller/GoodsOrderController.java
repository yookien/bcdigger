package com.bcdigger.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bcdigger.admin.entity.Admin;
import com.bcdigger.admin.service.AdminService;
import com.bcdigger.common.constant.CacheConstant;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;
import com.bcdigger.order.entity.GoodsOrder;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.entity.GoodsOrderItemModel;
import com.bcdigger.order.service.GoodsOrderService;


@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/order")
@AdminAuth  // 订货单管理全部需要登录鉴权
public class GoodsOrderController {
	
	@Autowired
	private GoodsOrderService goodsOrderService;
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value ="/addGoodsOrder",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> addGoodsOrder(HttpServletRequest request, GoodsOrder goodsOrder,
			GoodsOrderItemModel orderItemModel,GoodsOrderItem goodsOrderItem){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 获取用户登录信息
			Integer adminId=(Integer)request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
			if(adminId == null || adminId < 1){
				map.put("result", 10001);// 用户未登录
				return map;
			}
			Admin admin=adminService.getAdmin(adminId);
			if( admin == null || admin.getId() < 1){
				map.put("result", 10001);// 用户未登录
				return map;
			}
			if( admin.getStoreId() < 1){
				map.put("result", 10002);// 用户未绑定订货单
				return map;
			}
			// 参数校验，待完善
			if(goodsOrder == null){
				map.put("result", 10003);// 参数为空
				return map;
			}
			goodsOrder.setOrderUserId(admin.getId());
			goodsOrder.setStoreId(admin.getStoreId());
			/**if(StringUtils.isBlank(goodsOrder.getOrderNo())){
				map.put("result", -2);// 菜单名称不能为空
				return map;
			}*/
			
			if( orderItemModel != null ){
				// 货号列表
				List<String> goodsNos = orderItemModel.getGoodsNo();
				// 数量列表
				List<Integer> quantitys = orderItemModel.getQuantity();
				// 要货时间列表
				List<String> instoreTimes = orderItemModel.getInstoreTime();
				// 备注列表
				List<String> memos = orderItemModel.getMemo();
				
				// 参数不合法
				if(goodsNos==null || goodsNos.size()<1 || quantitys == null 
					|| instoreTimes == null || memos == null
					|| goodsNos.size() != quantitys.size()
					|| goodsNos.size() != instoreTimes.size() 
					|| goodsNos.size() != memos.size() ){
					map.put("result", 10004);// 参数校验失败
					return map;
				}
				
			} else {
				map.put("result", 10005);// 订单明细数据为空
				return map;
			}
			
			JSONObject json = goodsOrderService.addGoodsOrder(goodsOrder,orderItemModel);
			if(json ==null ){
				map.put("result", 0);
			} else {
				map.put("result", json.get("result"));
			}
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Description: 根据id查询订货单信息
	 * @param id
	 * @return Map<String,Object>  
	 * @throws
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsOrder",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getGoodsOrder(GoodsOrder goodsOrder) {
		
		Map<String, Object> map = new HashMap<>();
		try{
			if( goodsOrder==null || goodsOrder.getId()<=0){
				return null;
			}
			goodsOrder = goodsOrderService.getGoodsOrderById(goodsOrder.getId());
			map.put("result", 1);//登录成功
			map.put("goodsOrder", goodsOrder);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 更新订货单信息
	 * @param GoodsOrder
	 * @return Map<String,Object>
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateGoodsOrder(GoodsOrder goodsOrder){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(goodsOrder == null){
				map.put("result", -1);// 参数为空
				return map;
			}
			Date now=new Date();
			goodsOrder.setUpdateTime(now);
			int result = goodsOrderService.updateGoodsOrder(goodsOrder);
			map.put("result", result);//更新成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description:打开订货单管理首页
	 * @param request
	 * @return
	 * @return String
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/goodsOrderIndex")
	public String sysMenusIndex() {
		return "/order/order_index";
	}

	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsOrders",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsOrders(GoodsOrder goodsOrder, PageInfo pageInfo,ModelMap map,HttpServletRequest request) {
		Integer adminId=(Integer)request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
		Admin admin = adminService.getAdmin(adminId);
		if(admin!=null) {
			goodsOrder.setOrderUserId(adminId);
			goodsOrder.setStoreId(admin.getStoreId());
		}
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<GoodsOrder> goodsOrderPages = goodsOrderService.getGoodsOrders(goodsOrder, pageInfo);
			map.addAttribute("pageInfo", goodsOrderPages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/order/order_list";
	}
	
	
	
	@RequestMapping(value ="/goodsOrderAuditingIndex")
	public String goodsOrderAuditingIndex() {
		return "/order/order_auditing_index";
	}

	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsOrdersForAuditing",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsOrdersForAuditing(GoodsOrder goodsOrder, PageInfo pageInfo,ModelMap map,HttpServletRequest request) {
		Integer adminId=(Integer)request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
		Admin admin = adminService.getAdmin(adminId);
		if(admin!=null) {
			goodsOrder.setOrderUserId(adminId);
			goodsOrder.setStoreId(admin.getStoreId());
		}
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<GoodsOrder> goodsOrderPages = goodsOrderService.getGoodsOrders(goodsOrder, pageInfo);
			map.addAttribute("pageInfo", goodsOrderPages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/order/order_auditing_list";
	}

}
