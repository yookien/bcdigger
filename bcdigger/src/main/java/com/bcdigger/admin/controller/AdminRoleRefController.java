package com.bcdigger.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.admin.entity.AdminRole;
import com.bcdigger.admin.entity.AdminRoleRef;
import com.bcdigger.admin.entity.RoleMenuRef;
import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.admin.service.AdminRoleRefService;
import com.bcdigger.admin.service.AdminRoleService;
import com.bcdigger.admin.service.RoleMenuRefService;
import com.bcdigger.admin.service.SysMenuService;
import com.bcdigger.core.annotation.AdminAuth;

/**
 * 
 * ClassName: AdminRoleRefController
 * @Description: 角色权限管理
 * @author ipui
 * @date 2018年3月31日
 */
@Controller	
@EnableAutoConfiguration
@RequestMapping("/admin")
@AdminAuth  //操作员角色管理全部需要登录鉴权
public class AdminRoleRefController {
	
	@Autowired
	private AdminRoleRefService adminRoleRefService;

	
	@RequestMapping(value ="/getAdminRoleRefByRoleId")
	@ResponseBody
	public Map<String, Object> getAdminRoleRefByRoleId(AdminRoleRef adminRoleRef) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			adminRoleRef = adminRoleRefService.getAdminRoleRef(adminRoleRef);
			if (adminRoleRef != null) {
				String source = adminRoleRef.getRoleJson();
				map.put("infoNo", 1);
				map.put("roles", source);
			} else {
				map.put("infoNo", 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("infoNo", 3);
			map.put("roles", "获取角色时发生异常");
		}
		return map;
	}
	
	/**
	 * @Description:  修改用户角色分配  表中有数据就更新。。表中无数据增加
	 * @param adminRoleRef
	 * @return
	 * @throws Exception Map<String,Object>  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	@RequestMapping(value ="/saveAdminRoleRef")
	@ResponseBody
	public Map<String, Object> saveAdminRoleRef(AdminRoleRef adminRoleRef) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			AdminRoleRef adminRoleRefTemp=new AdminRoleRef();
			if(adminRoleRef!=null){
				adminRoleRefTemp.setAdminId(adminRoleRef.getAdminId());
			}
			adminRoleRefTemp = adminRoleRefService.getAdminRoleRef(adminRoleRefTemp);
			if (adminRoleRefTemp != null) { // 更新
				Date now=new Date();
				adminRoleRef.setUpdateTime(now);
				adminRoleRef.setId(adminRoleRefTemp.getId());
				adminRoleRefService.updateAdminRoleRef(adminRoleRef);
			} else {// 插入
				Date now=new Date();
				adminRoleRef.setAddTime(now);
				adminRoleRef.setUpdateTime(now);
				adminRoleRefService.addAdminRoleRef(adminRoleRef);
			}
			map.put("infoNo", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("infoNo", 3);
			map.put("str", "修改权限时发生错误");
		}
		return map;
	}

}
