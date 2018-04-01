package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.RoleMenuRefDao;
import com.bcdigger.admin.entity.RoleMenuRef;
import com.bcdigger.admin.service.RoleMenuRefService;
import com.bcdigger.common.page.PageInfo;

/**
 * 
 * ClassName: RoleMenuRefServiceImpl
 * 
 * @Description: 角色权限service
 * @author ipui
 * @date 2018年3月31日
 */
@Service("roleMenuRefService")
public class RoleMenuRefServiceImpl implements RoleMenuRefService {

	@Autowired
	private RoleMenuRefDao roleMenuRefDao;

	@Override
	public void addRoleMenuRef(RoleMenuRef roleMenuRef) {
		try {
			roleMenuRefDao.insert(roleMenuRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRoleMenuRef(RoleMenuRef roleMenuRef) {
		try {
			roleMenuRefDao.update(roleMenuRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RoleMenuRef getRoleMenuRefById(int id) {
		try {
			if (id <= 0) {
				return null;
			}
			return roleMenuRefDao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public RoleMenuRef getRoleMenuRef(RoleMenuRef roleMenuRef) {
		try {
			return roleMenuRefDao.getRoleMenuRef(roleMenuRef);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<RoleMenuRef> getRoleMenuRefs(RoleMenuRef roleMenuRef, PageInfo<RoleMenuRef> pageInfo) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			return roleMenuRefDao.listPage(pageInfo, params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<RoleMenuRef> getRoleMenuRefs(RoleMenuRef roleMenuRef){
		try {
			return roleMenuRefDao.getRoleMenuRefs(roleMenuRef);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
