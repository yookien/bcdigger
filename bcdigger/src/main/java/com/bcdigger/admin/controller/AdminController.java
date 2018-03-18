package com.bcdigger.admin.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bcdigger.admin.entity.Admin;

@Controller	
//@RestController  只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {
	
	//@Autowired
	//private AdminService adminService;
	
	@RequestMapping(value ="/login",method=RequestMethod.GET)
	public String adminLogin() {
		return "login";
	}
	
	@RequestMapping(value ="/getAdmin/{id}",method=RequestMethod.GET)
	public Admin getAdmin(@PathVariable int id) {
		
		//Admin admin = adminService.getAdmin(1);
		return null;
	}

}
