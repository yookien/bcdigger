package com.bcdigger.goods.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsPurchaseItem;

public interface GoodsPurchaseItemService {

	public GoodsPurchaseItem getGoodsPurchaseItem(int id);

	public GoodsPurchaseItem getGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem);

	public PageInfo<GoodsPurchaseItem> getGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem, PageInfo pageInfo);

	public int addGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem);

	public int updateGoodsPurchaseItem(GoodsPurchaseItem goodsPurchaseItem);

}
