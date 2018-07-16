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
			List<SysMenu> list= sqlSession.selectList("listByBiz", goodsInstoreBiz);
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

}
