package com.bcdigger.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.AdminRoleRefDao;
import com.bcdigger.admin.entity.AdminRoleRef;
import com.bcdigger.admin.service.AdminRoleRefService;

/**
 * 
 * ClassName: AdminRoleRefServiceImpl
 * @Description: 用户角色关联service
 * @author ipui
 * @date 2018年3月31日
 */
@Service("adminRoleRefService")
public class AdminRoleRefServiceImpl implements AdminRoleRefService {

	@Autowired
	private AdminRoleRefDao adminRoleRefDao;

	@Override
	public void addAdminRoleRef(AdminRoleRef adminRoleRef) {
		try {
			adminRoleRefDao.insert(adminRoleRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdminRoleRef(AdminRoleRef adminRoleRef) {
		try {
			adminRoleRefDao.update(adminRoleRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdminRoleRef getAdminRoleRefById(int id) {
		try {
			if (id <= 0) {
				return null;
			}
			return adminRoleRefDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AdminRoleRef getAdminRoleRef(AdminRoleRef adminRoleRef) {
		try {
			return adminRoleRefDao.getRoleMenuRef(adminRoleRef);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
