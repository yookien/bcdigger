package com.bcdigger.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.goods.dao.GoodsInstoreDao;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsInstoreBiz;
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
	
	
	/**
	 * 获取收货审核列表
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: PageInfo<GoodsInstoreBiz>      
	 * @throws
	 */
	public PageInfo<GoodsInstoreBiz> getGoodsInstoreBizs(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo){
		
		return goodsInstoreDao.getGoodsInstoreBizs(goodsInstoreBiz, pageInfo);
	}
	
	
	/**
	 * 根据订货单id查找需审核的收货信息
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: List<GoodsInstoreBiz>      
	 * @throws
	 */
	public List<GoodsInstoreBiz> getGoodsInstoreInfo(GoodsInstoreBiz goodsInstoreBiz) {
		return goodsInstoreDao.getGoodsInstoreInfo(goodsInstoreBiz);
	}
	
	/**
	 * 根据订货单id查找需添加的收货信息
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: List<GoodsInstoreBiz>      
	 * @throws
	 */
	public List<GoodsInstoreBiz> getAddGoodsInstoreInfo(GoodsInstoreBiz goodsInstoreBiz) {
		return goodsInstoreDao.getAddGoodsInstoreInfo(goodsInstoreBiz);
	}
	
	
	/**
	 * 获取待关闭收货单列表
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: PageInfo<GoodsInstoreBiz>      
	 * @throws
	 */
	public PageInfo<GoodsInstoreBiz> getCloseOrders(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo){
		
		return goodsInstoreDao.findCloseOrders(goodsInstoreBiz, pageInfo);
	}
	
	/**
	 * 根据订货单id查找需关闭的收货单详细信息
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: List<GoodsInstoreBiz>      
	 * @throws
	 */
	public List<GoodsInstoreBiz> getCloseOrderInfo(GoodsInstoreBiz goodsInstoreBiz){
		return goodsInstoreDao.findCloseOrderById(goodsInstoreBiz);
	}
}
