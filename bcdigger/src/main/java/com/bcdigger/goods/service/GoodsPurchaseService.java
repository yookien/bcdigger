package com.bcdigger.goods.service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsPurchase;

public interface GoodsPurchaseService {

	public GoodsPurchase getGoodsPurchase(int id);

	public GoodsPurchase getGoodsPurchase(GoodsPurchase goodsPurchase);

	public PageInfo<GoodsPurchase> getGoodsPurchase(GoodsPurchase goodsPurchase, PageInfo pageInfo);

	public int addGoodsPurchase(GoodsPurchase goodsPurchase);

	public int updateGoodsPurchase(GoodsPurchase goodsPurchase);

}
