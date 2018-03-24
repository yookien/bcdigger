package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

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
		
		
		
		Admin admin = adminDao.findAdminById(id);
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adminId", id);
		
		admin = adminDao.getById(params);
		
		return admin;
	}
	@Override
	public PageInfo<Admin> getAdmins(String name,PageInfo pageInfo) {
		
		PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
		Page<Admin> admins = adminDao.findAdminByPage();
		pageInfo.setList(admins);
		return pageInfo;
	}

	
	
}
