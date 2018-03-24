package com.bcdigger.admin.dao;

import java.util.Map;

import com.bcdigger.admin.entity.Admin;
import com.github.pagehelper.Page;

public interface AdminDao {
	/**
	 * 根据管理员ID查找管理员
	 * @author yookien
	 * @param id
	 * @return
	 */
	public Admin findAdminById(Integer adminId);
	
	public Admin getById(Map params);
	
	public Page<Admin> findAdminByPage();
	
	
}
