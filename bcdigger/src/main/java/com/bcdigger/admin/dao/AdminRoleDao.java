package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.AdminRole;
import com.bcdigger.core.dao.BaseDaoImpl;

@Repository("adminRoleDao")
public class AdminRoleDao extends BaseDaoImpl<AdminRole>{
	@Autowired
	private SqlSession sqlSession;
	public List<AdminRole> findAdminRoleList(AdminRole adminRole) {
		return sqlSession.selectList("findAdminRoleList", adminRole);
	}
	
}
