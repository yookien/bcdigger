package com.bcdigger.admin.service;

import com.bcdigger.admin.entity.AdminRole;
import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;

public interface AdminRoleService {
	
	/**
	 * @Description: 添加角色
	 * @param role void  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午2:44:38
	 */
	public void addRole(AdminRole role);
	
	/**
	 * @Description: 修改角色
	 * @param role void  
	 * @throws
	 * @author liubei
	 * @date 2018年3月26日
	 */
	public void updateAdminRole(AdminRole role);
	
	/**
	 * @Description: 添加角色
	 * @param id
	 * @return AdminRole  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午2:38:34
	 */
	public AdminRole getRoleById(int id);
	
	
	/**
	 * @Description: 分页查询角色
	 * @param name
	 * @param pageInfo
	 * @return PageInfo<SysMenu>  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午2:37:23
	 */
	public PageInfo<AdminRole> getAdminRoles(AdminRole role,PageInfo pageInfo);
}
