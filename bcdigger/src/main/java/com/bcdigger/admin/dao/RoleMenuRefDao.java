package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.RoleMenuRef;
import com.bcdigger.core.dao.BaseDaoImpl;

@Repository("roleMenuRefDao")
public class RoleMenuRefDao extends BaseDaoImpl<RoleMenuRef>{
	@Autowired
	private SqlSession sqlSession;
	public RoleMenuRef getRoleMenuRef(RoleMenuRef roleMenuRef) {
		try{
			List<RoleMenuRef> list= sqlSession.selectList("getRoleMenuRef", roleMenuRef);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
