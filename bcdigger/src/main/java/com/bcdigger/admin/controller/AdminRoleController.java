package com.bcdigger.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.admin.service.AdminRoleService;
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
	@RequestMapping(value ="/addRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addRole(@RequestParam String name, String password, String vrifycode){ 
		
		return null;
	}
	
	/**
	 * @Description: 根据id查询角色信息
	 * @param id
	 * @return Map<String,Object>  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午3:03:54
	 */
	@RequestMapping(value ="/getRole/{id}",method=RequestMethod.GET)
	public String getRole(@PathVariable int id) {
		
		return null;
	}
	
	
	
	/**
	 * @Description: 分页查询角色信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @throws
	 * @author yookien
	 * @dateTime 2018年3月25日 下午3:04:38
	 */
	@RequestMapping(value ="/getRoles",method={RequestMethod.GET,RequestMethod.POST})
	public String getRoles(Integer pageNum) {
		
		return null;
	}

}
