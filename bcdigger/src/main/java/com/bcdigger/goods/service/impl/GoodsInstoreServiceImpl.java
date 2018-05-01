package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsInstoreDao;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.service.GoodsInstoreService;
import com.bcdigger.common.page.PageInfo;

@Service("GoodsInstoreService")
public class GoodsInstoreServiceImpl implements GoodsInstoreService {

	@Autowired
	private GoodsInstoreDao goodsInstoreDao;

	@Override
	public GoodsInstore getGoodsInstore(int id) {
		GoodsInstore GoodsInstore = goodsInstoreDao.getById(id);
		return GoodsInstore;
	}

	public GoodsInstore getGoodsInstore(GoodsInstore goodsInstore) {
		try {
			if (goodsInstore == null) {
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("goodsId", goodsInstore.getGoodsId());
			params.put("repositoryId", goodsInstore.getRepositoryId());
			goodsInstore = goodsInstoreDao.getBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsInstore;
	}

	@Override
	public PageInfo<GoodsInstore> getGoodsInstore(GoodsInstore goodsInstore, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("goodsId", goodsInstore.getGoodsId());
		return goodsInstoreDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作入库
	 */
	public int addGoodsInstore(GoodsInstore goodsInstore) {
		return goodsInstoreDao.insert(goodsInstore);
	}

	/**
	 * 更新操作入库信息
	 */
	public int updateGoodsInstore(GoodsInstore goodsInstore) {
		return goodsInstoreDao.update(goodsInstore);
	}

}
