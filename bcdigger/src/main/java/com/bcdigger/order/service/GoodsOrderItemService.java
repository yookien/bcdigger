package com.bcdigger.order.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.order.entity.GoodsOrderItem;


public interface GoodsOrderItemService {
	
	/**
	 * 添加订单表
	 * <p>Title: addGoodsOrderItem</p>  
	 * <p>Description: </p>  
	 * @param GoodsOrderItem
	 * @return
	 */
	public int addGoodsOrderItem(GoodsOrderItem goodsOrderItem);
	/**
	 * 更新订单信息
	 * <p>Title: updateGoodsOrderItem</p>  
	 * <p>Description: </p>  
	 * @param GoodsOrderItem
	 * @return
	 */
	public int updateGoodsOrderItem(GoodsOrderItem goodsOrderItem);
	/**
	 * 根据id查找订单信息
	 * <p>Title: getGoodsOrderItemById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public GoodsOrderItem getGoodsOrderItemById(int id);
	/**
	 * 根据订单信息分页查找订单
	 * <p>Title: getGoodsOrderItems</p>  
	 * <p>Description: </p>  
	 * @param GoodsOrderItem
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<GoodsOrderItem> getGoodsOrderItems(GoodsOrderItem goodsOrderItem,PageInfo pageInfo);
	
	
}
