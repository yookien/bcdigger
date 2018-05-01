package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsPurchaseDao;
import com.bcdigger.goods.entity.GoodsPurchase;
import com.bcdigger.goods.service.GoodsPurchaseService;
import com.bcdigger.common.page.PageInfo;

@Service("goodsPurchaseService")
public class GoodsPurchaseServiceImpl implements GoodsPurchaseService {

	@Autowired
	private GoodsPurchaseDao goodsPurchaseDao;

	@Override
	public GoodsPurchase getGoodsPurchase(int id) {
		GoodsPurchase goodsPurchase = goodsPurchaseDao.getById(id);
		return goodsPurchase;
	}

	public GoodsPurchase getGoodsPurchase(GoodsPurchase goodsPurchase) {
		try {
			if (goodsPurchase == null) {
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("batchNo", goodsPurchase.getBatchNo());
			goodsPurchase = goodsPurchaseDao.getBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsPurchase;
	}

	@Override
	public PageInfo<GoodsPurchase> getGoodsPurchase(GoodsPurchase goodsPurchase, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("batchNo", goodsPurchase.getBatchNo());
		return goodsPurchaseDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作采购单
	 */
	public int addGoodsPurchase(GoodsPurchase goodsPurchase) {
		return goodsPurchaseDao.insert(goodsPurchase);
	}

	/**
	 * 更新操作采购单信息
	 */
	public int updateGoodsPurchase(GoodsPurchase goodsPurchase) {
		return goodsPurchaseDao.update(goodsPurchase);
	}

}
