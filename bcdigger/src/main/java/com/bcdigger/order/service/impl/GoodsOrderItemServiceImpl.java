package com.bcdigger.order.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.order.dao.GoodsOrderItemDao;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.service.GoodsOrderItemService;


@Service("goodsOrderItemService")
public class GoodsOrderItemServiceImpl implements GoodsOrderItemService {

	@Autowired
	private GoodsOrderItemDao goodsOrderItemDao;
	
	@Override
	public int addGoodsOrderItem(GoodsOrderItem goodsOrderItem) {
		return goodsOrderItemDao.insert(goodsOrderItem);
	}

	@Override
	public int updateGoodsOrderItem(GoodsOrderItem goodsOrderItem) {
		return goodsOrderItemDao.update(goodsOrderItem);
	}

	@Override
	public GoodsOrderItem getGoodsOrderItemById(int id) {
		return goodsOrderItemDao.getById(id);
	}

	@Override
	public PageInfo<GoodsOrderItem> getGoodsOrderItems(GoodsOrderItem goodsOrderItem, PageInfo pageInfo) {
		return goodsOrderItemDao.findGoodsOrderItems(pageInfo, goodsOrderItem);
	}

}
