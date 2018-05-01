package com.bcdigger.goods.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsInstore;

public interface GoodsInstoreService {

	public GoodsInstore getGoodsInstore(int id);

	public GoodsInstore getGoodsInstore(GoodsInstore goodsInstore);

	public PageInfo<GoodsInstore> getGoodsInstore(GoodsInstore goodsInstore, PageInfo pageInfo);

	public int addGoodsInstore(GoodsInstore goodsInstore);

	public int updateGoodsInstore(GoodsInstore goodsInstore);

}
