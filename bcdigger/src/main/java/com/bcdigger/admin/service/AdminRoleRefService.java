package com.bcdigger.admin.service;

import com.bcdigger.admin.entity.AdminRoleRef;

public interface AdminRoleRefService {
	
	/**
	 * @Description: 添加角色权限
	 * @param AdminRoleRef void  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public void addAdminRoleRef(AdminRoleRef adminRoleRef);
	
	/**
	 * @Description: 更新角色权限
	 * @param AdminRoleRef void  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public void updateAdminRoleRef(AdminRoleRef adminRoleRef);
	
	/**
	 * @Description: 根据id查询角色权限信息
	 * @param id
	 * @return AdminRoleRef  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public AdminRoleRef getAdminRoleRefById(int id);
	
	/**
	 * @Description: 根据条件查询角色权限菜单
	 * @param AdminRoleRef
	 * @return AdminRoleRef  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public AdminRoleRef getAdminRoleRef(AdminRoleRef adminRoleRef);

	
}
