package com.bcdigger.goods.service;

import com.bcdigger.goods.entity.Goods;
import com.bcdigger.common.page.PageInfo;

public interface GoodsService {
	
	public Goods getGoods(int id);
	
	public Goods getGoods(Goods goods);
	
	public PageInfo<Goods> getGoods(Goods goods,PageInfo pageInfo);
	
	public int addGoods(Goods goods);
	
	public int updateGoods(Goods goods);
	
	public void addOrUpdateGoods(Goods goods);
	
}
