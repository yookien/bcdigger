package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.AdminRoleRef;
import com.bcdigger.core.dao.BaseDaoImpl;

@Repository("adminRoleRefDao")
public class AdminRoleRefDao extends BaseDaoImpl<AdminRoleRef>{
	@Autowired
	private SqlSession sqlSession;
	public AdminRoleRef getRoleMenuRef(AdminRoleRef adminRoleRef) {
		try{
			List<AdminRoleRef> list= sqlSession.selectList("getAdminRoleRef", adminRoleRef);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
