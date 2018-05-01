package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsRepositoryDao;
import com.bcdigger.goods.entity.GoodsRepository;
import com.bcdigger.goods.service.GoodsRepositoryService;
import com.bcdigger.common.page.PageInfo;

@Service("goodsRepositoryService")
public class GoodsRepositoryServiceImpl implements GoodsRepositoryService {

	@Autowired
	private GoodsRepositoryDao goodsRepositoryDao;

	@Override
	public GoodsRepository getGoodsRepository(int id) {
		GoodsRepository goodsRepository = goodsRepositoryDao.getById(id);
		return goodsRepository;
	}

	public GoodsRepository getGoodsRepository(GoodsRepository goodsRepository) {
		try {
			if (goodsRepository == null) {
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", goodsRepository.getName());
			params.put("type", goodsRepository.getType());
			goodsRepository = goodsRepositoryDao.getBy(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsRepository;
	}

	@Override
	public PageInfo<GoodsRepository> getGoodsRepository(GoodsRepository goodsRepository, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", goodsRepository.getName());
		params.put("type", goodsRepository.getType());
		return goodsRepositoryDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作仓位
	 */
	public int addGoodsRepository(GoodsRepository goodsRepository) {
		return goodsRepositoryDao.insert(goodsRepository);
	}

	/**
	 * 更新操作仓位信息
	 */
	public int updateGoodsRepository(GoodsRepository goodsRepository) {
		return goodsRepositoryDao.update(goodsRepository);
	}

}
