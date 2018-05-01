package com.bcdigger.goods.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsStore;

public interface GoodsStoreService {

	public GoodsStore getGoodsStore(int id);

	public GoodsStore getGoodsStore(GoodsStore GoodsStore);

	public PageInfo<GoodsStore> getGoodsStore(GoodsStore GoodsStore, PageInfo pageInfo);

	public int addGoodsStore(GoodsStore GoodsStore);

	public int updateGoodsStore(GoodsStore GoodsStore);

}
