package com.bcdigger.goods.service;

import java.util.List;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsInstoreBiz;

public interface GoodsInstoreService {

	public GoodsInstore getGoodsInstore(int id);

	public GoodsInstore getGoodsInstore(GoodsInstore goodsInstore);

	public PageInfo<GoodsInstore> getGoodsInstore(GoodsInstore goodsInstore, PageInfo pageInfo);

	public int addGoodsInstore(GoodsInstore goodsInstore);

	public int updateGoodsInstore(GoodsInstore goodsInstore);
	
	/**
	 * 获取收货审核列表(分页)
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: PageInfo<GoodsInstoreBiz>      
	 * @throws
	 */
	public PageInfo<GoodsInstoreBiz> getGoodsInstoreBizs(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo);

}
