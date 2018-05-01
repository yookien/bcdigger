package com.bcdigger.goods.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsRepository;

public interface GoodsRepositoryService {

	public GoodsRepository getGoodsRepository(int id);

	public GoodsRepository getGoodsRepository(GoodsRepository goodsRepository);

	public PageInfo<GoodsRepository> getGoodsRepository(GoodsRepository goodsRepository, PageInfo pageInfo);

	public int addGoodsRepository(GoodsRepository goodsRepository);

	public int updateGoodsRepository(GoodsRepository goodsRepository);

}
