package com.bcdigger.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.admin.entity.AdminRole;
import com.bcdigger.admin.service.AdminRoleService;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;

/**
 * 
 * ClassName: SysMenuController
 * @Description: 操作员角色管理
 * @author yookien
 * @date 2018年3月25日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/admin")
@AdminAuth  //操作员角色管理全部需要登录鉴权
public class AdminRoleController {
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	/**
	 * @Description: 添加角色
	 * @param name
	 * @param password
	 * @param vrifycode
	 * @return Map<String,Object>  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午3:03:35
	 */
	@RequestMapping(value ="/addAdminRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addAdminRole(AdminRole adminRole){ 
		Map<String, Object> map = new HashMap<>();  
		try{
			if(adminRole==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			if(adminRole.getRoleName()==null || "".equals(adminRole.getRoleName().trim())){
				map.put("result", -2);// 菜单名称不能为空
				return map;
			}
			Date now=new Date();
			adminRole.setAddTime(now);
			adminRole.setUpdateTime(now);
			adminRoleService.addRole(adminRole);
			map.put("result", 1);//登录成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description: 根据id查询角色信息
	 * @param id
	 * @return Map<String,Object>  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午3:03:54
	 */
	@RequestMapping(value ="/getAdminRole",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAdminRole(AdminRole adminRole) {
		Map<String, Object> map = new HashMap<>();
		try{
			if(adminRole==null || adminRole.getId()<=0){
				return null;
			}
			adminRole = adminRoleService.getRoleById(adminRole.getId());
			map.put("result", 1);//登录成功
			map.put("adminRole", adminRole);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 
	 * @Description: 更新用户角色
	 * @param adminRole
	 * @return Map<String,Object>  
	 * @throws
	 * @author ipui
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/updateAdminRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateAdminRole(AdminRole adminRole){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(adminRole==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			Date now=new Date();
			adminRole.setUpdateTime(now);
			adminRoleService.updateAdminRole(adminRole);
			map.put("result", 1);//更新成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description:打开角色管理首页
	 * @param request
	 * @return
	 * @return String
	 * @author ipui
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/adminRoleIndex")
	public String adminRoleIndex() {
		return "/admin/role_index";
	}
	
	/**
	 * @Description: 分页查询角色信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午3:04:38
	 */
	@RequestMapping(value ="/getAdminRoles",method={RequestMethod.GET,RequestMethod.POST})
	public String getAdminRoles(AdminRole adminRole, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<AdminRole> adminRolePages = adminRoleService.getAdminRoles(adminRole, pageInfo);
			map.addAttribute("pageInfo", adminRolePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/admin/role_list";
	}

}
