package com.bcdigger.admin.service;

import java.util.List;

import com.bcdigger.admin.entity.RoleMenuRef;
import com.bcdigger.common.page.PageInfo;

public interface RoleMenuRefService {
	
	/**
	 * @Description: 添加角色权限
	 * @param roleMenuRef void  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public void addRoleMenuRef(RoleMenuRef roleMenuRef);
	
	/**
	 * @Description: 更新角色权限
	 * @param roleMenuRef void  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public void updateRoleMenuRef(RoleMenuRef roleMenuRef);
	
	/**
	 * @Description: 根据id查询角色权限信息
	 * @param id
	 * @return RoleMenuRef  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public RoleMenuRef getRoleMenuRefById(int id);
	
	/**
	 * @Description: 根据条件查询角色权限菜单
	 * @param roleMenuRef
	 * @return RoleMenuRef  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public RoleMenuRef getRoleMenuRef(RoleMenuRef roleMenuRef);
	
	/**
	 * @Description: 分页查询角色权限信息
	 * @param roleMenuRef
	 * @param pageInfo
	 * @return PageInfo<RoleMenuRef>  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public PageInfo<RoleMenuRef> getRoleMenuRefs(RoleMenuRef roleMenuRef,PageInfo<RoleMenuRef> pageInfo);
	
	/**
	 * 
	 * @Description: 查询角色权限信息
	 * @param roleMenuRef
	 * @return List<RoleMenuRef>  
	 * @throws
	 * @author ipui
	 * @date 2018年4月1日
	 */
	public List<RoleMenuRef> getRoleMenuRefs(RoleMenuRef roleMenuRef);
	
}
