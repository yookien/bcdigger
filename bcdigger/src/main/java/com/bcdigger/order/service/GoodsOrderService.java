package com.bcdigger.order.service;

import com.alibaba.fastjson.JSONObject;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.order.entity.GoodsOrder;
import com.bcdigger.order.entity.GoodsOrderItemModel;


public interface GoodsOrderService {
	
	/**
	 * 添加订单表
	 * <p>Title: addGoodsOrder</p>  
	 * <p>Description: </p>  
	 * @param GoodsOrder
	 * @return
	 */
	public JSONObject addGoodsOrder(GoodsOrder goodsOrder,GoodsOrderItemModel orderItemModel);
	
	/**
	 * 更新订单信息
	 * <p>Title: updateGoodsOrder</p>  
	 * <p>Description: </p>  
	 * @param GoodsOrder
	 * @return
	 */
	public JSONObject updateGoodsOrder(GoodsOrder goodsOrder,GoodsOrderItemModel orderItemModel);
	
	/**
	 * @Description: 审核订单
	 * @param goodsOrder
	 * @return int  
	 * @throws
	 * @author ipui
	 * @date 2018年7月17日
	 */
	public int auditingGoodsOrder(GoodsOrder goodsOrder);
	
	
	/**
	 * 根据id查找订单信息
	 * <p>Title: getGoodsOrderById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public GoodsOrder getGoodsOrderById(int id);
	/**
	 * 根据订单信息分页查找订单
	 * <p>Title: getGoodsOrders</p>  
	 * <p>Description: </p>  
	 * @param GoodsOrder
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<GoodsOrder> getGoodsOrders(GoodsOrder goodsOrder,PageInfo pageInfo);
	
	
}
