package com.bcdigger.store.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.store.entity.Store;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 门店数据层Dao
* <p>Title: StoreDao</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年4月29日
 */
@Repository("storeDao")
public class StoreDao extends BaseDaoImpl<Store>{
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<Store> findStores(PageInfo pageInfo, Store store) {
		try{
			Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
			List<Store> list= sqlSession.selectList("listPageByCondation", store);
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
	
	
	public List<Store> findStoreList(Store store) {
		return sqlSession.selectList("findStoreList", store);
	}
	
}
