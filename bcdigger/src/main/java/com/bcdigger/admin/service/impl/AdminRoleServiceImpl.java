package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.AdminRoleDao;
import com.bcdigger.admin.entity.AdminRole;
import com.bcdigger.admin.service.AdminRoleService;
import com.bcdigger.common.page.PageInfo;

/**
 * 
 * ClassName: SysMenuServiceImpl
 * 
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
		try {
			adminRoleDao.insert(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdminRole(AdminRole role) {
		try {
			adminRoleDao.update(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public AdminRole getRoleById(int id) {
		try {
			if (id <= 0) {
				return null;
			}
			return adminRoleDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<AdminRole> getAdminRoles(AdminRole role, PageInfo pageInfo) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			return adminRoleDao.listPage(pageInfo, params);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<AdminRole> getAdminRoleList(AdminRole role){
		try{
			return (List<AdminRole>)adminRoleDao.findAdminRoleList(role);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
