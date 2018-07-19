package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.AdminDao;
import com.bcdigger.admin.entity.Admin;
import com.bcdigger.admin.service.AdminService;
import com.bcdigger.common.page.PageInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin getAdmin(int id) {

		Admin admin = adminDao.getById(id);
		return admin;
	}

	public Admin getAdmin(Admin admin) {
		try{
			if(admin==null || admin.getName()==null || admin.getName().equals("")){
				return null;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", admin.getName());
			admin = adminDao.getBy(params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return admin;
	}
	
	public Admin getAdminInfoById(Admin admin){
		try{
			if(admin==null || admin.getId() <= 0 ){
				return null;
			}
			admin = adminDao.getAdminInfoById(admin);
		}catch(Exception e){
			e.printStackTrace();
			admin = null;
		}
		return admin;
	}
	
	@Override
	public PageInfo<Admin> getAdmins(String name, PageInfo pageInfo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("name", name);
		return adminDao.listPage(pageInfo, params);
	}
	
	@Override
	public PageInfo<Admin> getAdmins(Admin admin, PageInfo pageInfo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", admin.getName());
		params.put("startTime", admin.getStartTime());
		params.put("endTime", admin.getEndTime());
		if(StringUtils.isBlank(admin.getName()))
			params.put("like", 0);
		else 
			params.put("like", 1);
		return adminDao.listPage(pageInfo, params);
	}
	/**
	 * 插入操作 用户
	 */
	public int addAdmin(Admin admin) {
		return adminDao.insert(admin);
	}
	
	/**
	 * 更新操作 用户信息
	 */
	public int updateAdmin(Admin admin) {
		return adminDao.update(admin);
	}
	
	public int updateAdminPassword(Admin admin) {
		return adminDao.updatePassword(admin);
	}

}
