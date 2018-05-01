package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsOutstoreDao;
import com.bcdigger.goods.entity.GoodsOutstore;
import com.bcdigger.goods.service.GoodsOutstoreService;
import com.bcdigger.common.page.PageInfo;

@Service("goodsOutstoreService")
public class GoodsOutstoreServiceImpl implements GoodsOutstoreService {

	@Autowired
	private GoodsOutstoreDao goodsOutstoreDao;

	@Override
	public GoodsOutstore getGoodsOutstore(int id) {
		GoodsOutstore goodsOutstore = goodsOutstoreDao.getById(id);
		return goodsOutstore;
	}

	public GoodsOutstore getGoodsOutstore(GoodsOutstore goodsOutstore) {
		try {
			if (goodsOutstore == null) {
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("goodsId", goodsOutstore.getGoodsId());
			params.put("repositoryId", goodsOutstore.getRepositoryId());
			goodsOutstore = goodsOutstoreDao.getBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsOutstore;
	}

	@Override
	public PageInfo<GoodsOutstore> getGoodsOutstore(GoodsOutstore goodsOutstore, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", goodsOutstore.getGoodsId());
		return goodsOutstoreDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作出库
	 */
	public int addGoodsOutstore(GoodsOutstore goodsOutstore) {
		return goodsOutstoreDao.insert(goodsOutstore);
	}

	/**
	 * 更新操作出库信息
	 */
	public int updateGoodsOutstore(GoodsOutstore goodsOutstore) {
		return goodsOutstoreDao.update(goodsOutstore);
	}

}
