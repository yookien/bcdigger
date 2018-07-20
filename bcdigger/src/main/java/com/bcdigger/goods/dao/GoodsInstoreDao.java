package com.bcdigger.goods.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.AdminRoleRef;
import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsInstoreBiz;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Repository("goodsInstoreDao")
public class GoodsInstoreDao extends BaseDaoImpl<GoodsInstore> {

	@Autowired
	private SqlSession sqlSession;
	/**
	 * 获取收货审核列表
	 * @Title: getGoodsInstoreBizs   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param goodsInstoreBiz
	 * @param: @return      
	 * @return: PageInfo<GoodsInstoreBiz>      
	 * @throws
	 */
	public PageInfo<GoodsInstoreBiz> getGoodsInstoreBizs(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo) {
		
		try{
			Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
			List<GoodsInstoreBiz> list= sqlSession.selectList("listBizs", goodsInstoreBiz);
			pageInfo.setTotal(page.getTotal());
			pageInfo.setPages(page.getPages());
			pageInfo.setIsFirstPage(page.getPageNum()==1?true:false);
			pageInfo.setIsLastPage(page.getPageNum()==page.getPages()?true:false);
			pageInfo.setList(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageInfo;
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
		List<GoodsInstoreBiz> list = null;
		try{
			list= sqlSession.selectList("listBizByOrderId", goodsInstoreBiz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
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
		List<GoodsInstoreBiz> list = null;
		try{
			list= sqlSession.selectList("listAddBizByOrderId", goodsInstoreBiz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
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
	public PageInfo<GoodsInstoreBiz> findCloseOrders(GoodsInstoreBiz goodsInstoreBiz, PageInfo pageInfo) {
		
		try{
			Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
			List<GoodsInstoreBiz> list= sqlSession.selectList("listCloseOrders", goodsInstoreBiz);
			pageInfo.setTotal(page.getTotal());
			pageInfo.setPages(page.getPages());
			pageInfo.setIsFirstPage(page.getPageNum()==1?true:false);
			pageInfo.setIsLastPage(page.getPageNum()==page.getPages()?true:false);
			pageInfo.setList(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageInfo;
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
	public List<GoodsInstoreBiz> findCloseOrderById(GoodsInstoreBiz goodsInstoreBiz){
		
		List<GoodsInstoreBiz> list = null;
		try{
			list= sqlSession.selectList("listCloseOrderById", goodsInstoreBiz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
