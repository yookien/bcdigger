package com.bcdigger.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcdigger.admin.entity.Admin;
import com.bcdigger.admin.service.AdminService;
import com.bcdigger.common.constant.CacheConstant;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.common.utils.cache.redis.RedisUtils;
import com.bcdigger.common.utils.rsa.MD5;
import com.bcdigger.core.annotation.AdminAuth;

@Controller	
//@RestController  //只返回值，不放回页面渲染
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
	
	@RequestMapping(value ="/userLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam String name, String password, String vrifycode, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();  
		try{
			Integer sessionId=(Integer)request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
			if(sessionId!=null && sessionId>0){
				// 用户已经登录，自动跳转到操作页
				response.sendRedirect("/admin/index");
				return null;
			}
			if(name==null || name.trim().equals("")){
				map.put("result", -2);// 用户名不能为空
				return map;
			}
			
			if(password==null || password.trim().equals("")){
				map.put("result", -3);// 密码不能为空
				return map;
			}
			
			
			String sessionVrifyCode=(String)request.getSession().getAttribute("vrifyCode");
			if(vrifycode==null || vrifycode.trim().equals("") 
					|| sessionVrifyCode==null || !sessionVrifyCode.equals(vrifycode)){
				map.put("result", -4);// 验证码错误
				return map;
			}
			
			Admin admin=new Admin();
			admin.setName(name);
			Admin adminTemp=adminService.getAdmin(admin);
			if(adminTemp==null){
				map.put("result", -5);// 用户名不存在
				return map;
			}else if(adminTemp.getPassword()==null || !adminTemp.getPassword().equals(MD5.getMD5Str(password))){
				map.put("result", -6);// 账户密码不匹配
				return map;
			}
			request.getSession().setAttribute(CacheConstant.ADMIN_SESSION_ID,adminTemp.getAdminId());
			map.put("result", 1);//登录成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value ="/index")
	@AdminAuth
	public String adminIndex(HttpServletRequest request) {
		return "index";
	}
	
	/**
	 * 
	 * @Description: 退出登录
	 * @param request
	 * @return String  
	 * @throws
	 * @author liubei
	 * @date 2018年3月25日 上午1:44:45
	 */
	@RequestMapping(value ="/logout")
	@AdminAuth
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute(CacheConstant.ADMIN_SESSION_ID,null);
		request.getSession().setAttribute(CacheConstant.ADMIN_ID_SESSION_KEY,null);
		return "login";
	}
	
	@RequestMapping(value ="/getAdmin/{id}",method=RequestMethod.GET)
	@AdminAuth
	public String getAdmin(@PathVariable int id) {
		
		System.out.println("aaaa"+ id);
		Admin admin = adminService.getAdmin(id);
		RedisUtils.save("admin", admin);
		Admin admin01 = (Admin)RedisUtils.get("admin");
		System.out.println("bbbb"+ admin.getNickname());
		return "login";
	}
	
	@RequestMapping(value ="/getAdminName/{name}",method=RequestMethod.GET)
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
