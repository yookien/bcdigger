package com.bcdigger.admin.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.bcdigger.admin.entity.Admin;
import com.github.pagehelper.Page;

@Component
public class AdminDao {
	
	private final SqlSession sqlSession;
	public AdminDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
}
	/**
	 * 根据管理员ID查找管理员
	 * @author yookien
	 * @param id
	 * @return
	 */
	public Admin findAdminById(Integer adminId){
		return this.sqlSession.selectOne("findAdminById", adminId);
	}
	
	public Admin getById(Map params){
		return this.sqlSession.selectOne("getById", params);
	}
	
	public Admin getBycondition(Map params){
		return this.sqlSession.selectOne("getBycondition", params);
	}
	
	public Page<Admin> findAdminByPage(){
		return this.sqlSession.selectOne("findAdminByPage");
	}
	
	
}
