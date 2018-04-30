package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.Department;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.dao.BaseDaoImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 部门数据Dao
* <p>Title: DepartmentDao</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年4月29日
 */
@Repository("departmetDao")
public class DepartmentDao extends BaseDaoImpl<Department>{
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<Department> findDepartments(PageInfo pageInfo, Department department) {
		try{
			Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
			List<Department> list= sqlSession.selectList("listDepartmentByCondation", department);
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
	
	
	public List<Department> findSysMenuList(Department department) {
		return sqlSession.selectList("findDepartmentList", department);
	}
	
}
