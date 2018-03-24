package com.bcdigger.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcdigger.admin.entity.Admin;
import com.bcdigger.admin.service.AdminService;
import com.bcdigger.common.constant.CacheConstant;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.common.utils.cache.redis.RedisUtils;
import com.bcdigger.core.annotation.AdminAuth;

@Controller	
//@RestController  只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value ="/login",method=RequestMethod.GET)
	public String adminLogin(HttpServletRequest request) {
		
		//request.getSession().setAttribute(CacheConstant.ADMIN_SESSION_ID,"1");
		return "login";
	}
	
	@RequestMapping(value ="/getAdmin/{id}",method=RequestMethod.GET)
	public String getAdmin(@PathVariable int id) {
		
		System.out.println("aaaa"+ id);
		Admin admin = adminService.getAdmin(id);
		RedisUtils.save("admin", admin);
		Admin admin01 = (Admin)RedisUtils.get("admin");
		System.out.println("bbbb"+ admin.getNickname());
		return "login";
	}
	
	@RequestMapping(value ="/getAdminName/{name}",method=RequestMethod.GET)
	@AdminAuth
	public String getAdminByName(@PathVariable String name) {
		
		System.out.println("aaaa"+ name);
		PageInfo pageInfo =new PageInfo();
		//设置每页显示个数
		pageInfo.setPageSize(3);
		//设置显示哪一页
		pageInfo.setPageNum(2);
		PageInfo<Admin> adminPages = adminService.getAdmins(name, pageInfo);
		List<Admin> admins = adminPages.getList();
		for(Admin admin : admins) {
			System.out.println("admin name:"+admin.getName());
		}
		System.out.println("bbb"+ admins.size());
		return "login";
	}

}
