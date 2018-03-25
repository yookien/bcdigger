package com.bcdigger.admin.service;

import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;

public interface SysMenuService {
	
	/**
	 * @Description: 添加系统菜单
	 * @param sysMenu void  
	 * @throws
	 * @author liubei
	 * @dateTime 2018年3月25日 下午2:44:38
	 */
	public void addSysMenu(SysMenu sysMenu);
	
	/**
	 * @Description: 根据id查询菜单信息
	 * @param id
	 * @return SysMenu  
	 * @throws
	 * @author liubei
	 * @dateTime 2018年3月25日 下午2:38:34
	 */
	public SysMenu getSysMenuById(int id);
	
	
	/**
	 * @Description: 分页查询系统菜单
	 * @param name
	 * @param pageInfo
	 * @return PageInfo<SysMenu>  
	 * @throws
	 * @author liubei
	 * @dateTime 2018年3月25日 下午2:37:23
	 */
	public PageInfo<SysMenu> getSysMenus(SysMenu sysMenu,PageInfo pageInfo);
}
