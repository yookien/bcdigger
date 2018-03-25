package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.SysMenuDao;
import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.admin.service.SysMenuService;
import com.bcdigger.common.page.PageInfo;

/**
 * 
 * ClassName: SysMenuServiceImpl
 * @Description: 菜单service
 * @author liubei
 * @date 2018年3月25日
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;
	
	public void addSysMenu(SysMenu sysMenu){
		try{
			sysMenuDao.insert(sysMenu);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public SysMenu getSysMenuById(int id) {
		try{
			if(id<=0){
				return null;
			}
			return sysMenuDao.getById(id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<SysMenu> getSysMenus(SysMenu sysMenu, PageInfo pageInfo) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("menuName", sysMenu.getMenuName());
			return sysMenuDao.listPage(pageInfo, params);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
