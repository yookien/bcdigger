package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsPurchaseItemDao;
import com.bcdigger.goods.entity.GoodsPurchaseItem;
import com.bcdigger.goods.service.GoodsPurchaseItemService;
import com.bcdigger.common.page.PageInfo;

@Service("goodsPurchaseItemService")
public class GoodsPurchaseItemServiceImpl implements GoodsPurchaseItemService {

	@Autowired
	private GoodsPurchaseItemDao goodsPurchaseItemDao;

	@Override
	public GoodsPurchaseItem getGoodsPurchaseItem(int id) {
		GoodsPurchaseItem goodsPurchaseItem = goodsPurchaseItemDao.getById(id);
		return goodsPurchaseItem;
	}

	public GoodsPurchaseItem getGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem) {
		try {
			if (goodsPurchaseItem == null) {
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("goodsId", goodsPurchaseItem.getGoodsId());
			goodsPurchaseItem = goodsPurchaseItemDao.getBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsPurchaseItem;
	}

	@Override
	public PageInfo<GoodsPurchaseItem> getGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", goodsPurchaseItem.getGoodsId());
		return goodsPurchaseItemDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作采购单明细
	 */
	public int addGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem) {
		return goodsPurchaseItemDao.insert(goodsPurchaseItem);
	}

	/**
	 * 更新操作采购单明细信息
	 */
	public int updateGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem) {
		return goodsPurchaseItemDao.update(goodsPurchaseItem);
	}

}
