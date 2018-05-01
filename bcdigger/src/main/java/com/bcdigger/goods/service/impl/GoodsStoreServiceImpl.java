package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsStoreDao;
import com.bcdigger.goods.entity.GoodsStore;
import com.bcdigger.goods.service.GoodsStoreService;
import com.bcdigger.common.page.PageInfo;

@Service("goodsStoreService")
public class GoodsStoreServiceImpl implements GoodsStoreService {

	@Autowired
	private GoodsStoreDao goodsStoreDao;

	@Override
	public GoodsStore getGoodsStore(int id) {

		GoodsStore goodsStore = goodsStoreDao.getById(id);
		return goodsStore;
	}

	public GoodsStore getGoodsStore(GoodsStore goodsStore) {
		try {
			if (goodsStore == null) {
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("goodsId", goodsStore.getGoodsId());
			params.put("repositoryId", goodsStore.getRepositoryId());
			goodsStore = goodsStoreDao.getBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsStore;
	}

	@Override
	public PageInfo<GoodsStore> getGoodsStore(GoodsStore goodsStore, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", goodsStore.getGoodsId());
		return goodsStoreDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作库存
	 */
	public int addGoodsStore(GoodsStore goodsStore) {
		return goodsStoreDao.insert(goodsStore);
	}

	/**
	 * 更新操作库存信息
	 */
	public int updateGoodsStore(GoodsStore goodsStore) {
		return goodsStoreDao.update(goodsStore);
	}

}
