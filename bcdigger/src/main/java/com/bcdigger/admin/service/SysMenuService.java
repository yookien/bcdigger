package com.bcdigger.admin.service;

import java.util.List;

import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;

public interface SysMenuService {
	
	/**
	 * @Description: 添加系统菜单
	 * @param sysMenu void  
	 * @throws
	 * @author ipui
	 * @dateTime 2018年3月25日
	 */
	public void addSysMenu(SysMenu sysMenu);
	
	/**
	 * @Description: 更新系统菜单
	 * @param sysMenu void  
	 * @throws
	 * @author ipui
	 * @date 2018年3月25日
	 */
	public void updateSysMenu(SysMenu sysMenu);
	
	/**
	 * @Description: 根据id查询菜单信息
	 * @param id
	 * @return SysMenu  
	 * @throws
	 * @author ipui
	 * @dateTime 2018年3月25日
	 */
	public SysMenu getSysMenuById(int id);
	
	
	/**
	 * @Description: 分页查询系统菜单
	 * @param name
	 * @param pageInfo
	 * @return PageInfo<SysMenu>  
	 * @throws
	 * @author ipui
	 * @dateTime 2018年3月25日
	 */
	public PageInfo<SysMenu> getSysMenus(SysMenu sysMenu,PageInfo pageInfo);
	
	/**
	 * @Description: 查询系统菜单列表
	 * @param sysMenu
	 * @return List<SysMenu>  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	public List<SysMenu> getSysMenuList(SysMenu sysMenu);
}
