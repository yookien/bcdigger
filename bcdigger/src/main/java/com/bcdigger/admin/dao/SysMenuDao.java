package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.dao.BaseDaoImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Repository("sysMenuDao")
public class SysMenuDao extends BaseDaoImpl<SysMenu>{
	@Autowired
	private SqlSession sqlSession;
	
	public PageInfo findSysMenus(PageInfo pageInfo, SysMenu sysMenu) {
		try{
			Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
			List<SysMenu> list= sqlSession.selectList("listPageByCondation", sysMenu);
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
