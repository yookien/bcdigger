package com.bcdigger.goods.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsOutstore;

public interface GoodsOutstoreService {

	public GoodsOutstore getGoodsOutstore(int id);

	public GoodsOutstore getGoodsOutstore(GoodsOutstore goodsOutstore);

	public PageInfo<GoodsOutstore> getGoodsOutstore(GoodsOutstore goodsOutstore, PageInfo pageInfo);

	public int addGoodsOutstore(GoodsOutstore goodsOutstore);

	public int updateGoodsOutstore(GoodsOutstore goodsOutstore);

}
