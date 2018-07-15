package com.bcdigger.order.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * ClassName: GoodsOrderItemDao
 * @Description: 订单明细dao
 * @author ipui
 * @date 2018年7月15日
 */
@Repository("goodsOrderItemDao")
public class GoodsOrderItemDao extends BaseDaoImpl<GoodsOrderItem>{
	
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<GoodsOrderItem> findGoodsOrderItems(PageInfo pageInfo, GoodsOrderItem goodsOrderItem) {
		try{
			Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
			List<GoodsOrderItem> list= sqlSession.selectList("listGoodsOrderItemByCondation", goodsOrderItem);
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
