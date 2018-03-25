package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.AdminRoleDao;
import com.bcdigger.admin.entity.AdminRole;
import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.admin.service.AdminRoleService;
import com.bcdigger.common.page.PageInfo;

/**
 * 
 * ClassName: SysMenuServiceImpl
 * @Description: 菜单service
 * @author yookien
 * @date 2018年3月25日
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	private AdminRoleDao adminRoleDao;

	@Override
	public void addRole(AdminRole role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysMenu getRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<AdminRole> getAdminRoles(AdminRole role, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
