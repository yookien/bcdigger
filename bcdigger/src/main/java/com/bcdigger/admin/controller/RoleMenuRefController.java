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
import com.bcdigger.admin.entity.RoleMenuRef;
import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.admin.service.AdminRoleService;
import com.bcdigger.admin.service.RoleMenuRefService;
import com.bcdigger.admin.service.SysMenuService;
import com.bcdigger.core.annotation.AdminAuth;

/**
 * 
 * ClassName: RoleMenuRefController
 * @Description: 角色权限管理
 * @author ipui
 * @date 2018年3月31日
 */
@Controller	
@EnableAutoConfiguration
@RequestMapping("/admin")
@AdminAuth  //操作员角色管理全部需要登录鉴权
public class RoleMenuRefController {
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	
	@Autowired
	private RoleMenuRefService roleMenuRefService;
	
	
	
	/**
	 * @Description:打开角色权限管理首页
	 * @param request
	 * @return
	 * @return String
	 * @author ipui
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/roleMenuRefIndex")
	public String RoleMenuRefIndex(ModelMap map) {
		try{
			// 查询所有用户角色信息
			AdminRole role=new AdminRole();
			role.setState(1);// 只查询有效的角色
			List<AdminRole> roleList = adminRoleService.getAdminRoleList(role);
			
			// 查询所有菜单信息
			SysMenu sysMenu = new SysMenu();
			sysMenu.setState(1);// 只查询当前可用的菜单
			List<SysMenu> menuList = sysMenuService.getSysMenuList(sysMenu);
			map.addAttribute("roleList", roleList);
			map.addAttribute("menuList", menuList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/admin/role_menu_ref_index";
	}
	
	@RequestMapping(value ="/getRoleMenuRefByRoleId")
	@ResponseBody
	public Map<String, Object> getRoleMenuRefByRoleId(RoleMenuRef roleMenuRef) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			roleMenuRef = roleMenuRefService.getRoleMenuRef(roleMenuRef);
			if (roleMenuRef != null) {
				String source = roleMenuRef.getMenuJson();
				map.put("infoNo", 1);
				map.put("str", source);
			} else {
				map.put("infoNo", 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("infoNo", 3);
			map.put("str", "获取权限时发生异常");
		}
		return map;
	}
	
	/**
	 * @Description:  修改权限 表中有数据就更新。。表中无数据增加
	 * @param roleMenuRef
	 * @return
	 * @throws Exception Map<String,Object>  
	 * @throws
	 * @author ipui
	 * @date 2018年3月31日
	 */
	@RequestMapping(value ="/saveRoleMenuRef")
	@ResponseBody
	public Map<String, Object> saveRoleMenuRef(RoleMenuRef roleMenuRef) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			RoleMenuRef roleMenuRefTemp=new RoleMenuRef();
			if(roleMenuRef!=null){
				roleMenuRefTemp.setRoleId(roleMenuRef.getRoleId());
			}
			roleMenuRefTemp = roleMenuRefService.getRoleMenuRef(roleMenuRefTemp);
			if (roleMenuRefTemp != null) { // 更新
				Date now=new Date();
				roleMenuRef.setUpdateTime(now);
				roleMenuRef.setId(roleMenuRefTemp.getId());
				roleMenuRefService.updateRoleMenuRef(roleMenuRef);
			} else {// 插入
				Date now=new Date();
				roleMenuRef.setAddTime(now);
				roleMenuRef.setUpdateTime(now);
				roleMenuRefService.addRoleMenuRef(roleMenuRef);
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
