package com.bcdigger.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.order.dao.GoodsOrderDao;
import com.bcdigger.order.entity.GoodsOrder;
import com.bcdigger.order.service.GoodsOrderService;


@Service("goodsOrderService")
public class GoodsOrderServiceImpl implements GoodsOrderService {

	@Autowired
	private GoodsOrderDao goodsOrderDao;
	
	@Override
	public int addGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderDao.insert(goodsOrder);
	}

	@Override
	public int updateGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderDao.update(goodsOrder);
	}

	@Override
	public GoodsOrder getGoodsOrderById(int id) {
		return goodsOrderDao.getById(id);
	}

	@Override
	public PageInfo<GoodsOrder> getGoodsOrders(GoodsOrder goodsOrder, PageInfo pageInfo) {
		return goodsOrderDao.findGoodsOrders(pageInfo, goodsOrder);
	}

}
