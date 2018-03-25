package com.bcdigger.admin.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bcdigger.admin.entity.Admin;
import com.bcdigger.admin.service.AdminService;
import com.bcdigger.common.constant.CacheConstant;
import com.bcdigger.common.utils.cache.redis.RedisUtils;
import com.bcdigger.core.annotation.AdminAuth;


/**
 * 实现管理员登录权限控制接口
 * @author yookien
 *
 */
@Component
public class AdminLoginInterceptor extends  HandlerInterceptorAdapter {
	
	/*@Autowired
	private RedisUtils redisUtils; */
	private final static String SESSION_KEY_PREFIX = "session:";
	
	@Autowired
	private AdminService adminService;
	 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) { 
			return true; 
		}
		//处理request Session中的Admin信息
		handlerSession(request); 
		
		final HandlerMethod handlerMethod = (HandlerMethod) handler; 
	    final Method method = handlerMethod.getMethod(); 
		final Class<?> clazz = method.getDeclaringClass(); 
		if (clazz.isAnnotationPresent(AdminAuth.class) || method.isAnnotationPresent(AdminAuth.class)) { 
			if(request.getAttribute(CacheConstant.ADMIN_ID_SESSION_KEY) == null){ 
				//throw new Exception(); 
				// 跳转登录
		        String url = "/admin/login";
		        response.sendRedirect(url);
		        return false;
			}else{ 
				return true; 
			} 
		} 
		return true; 
	}
	
	/**
	 * 获取请求中的管理员的session信息
	 * @param request
	 */
	public void handlerSession(HttpServletRequest request) { 
		String sessionId = request.getHeader(CacheConstant.ADMIN_SESSION_ID); 
	    if(org.apache.commons.lang3.StringUtils.isBlank(sessionId)){
	    	Integer sessionIdTemp=(Integer) request.getSession().getAttribute(CacheConstant.ADMIN_SESSION_ID);
	    	if(sessionIdTemp!=null){
	    		sessionId=String.valueOf(sessionIdTemp);
	    	}
	    } 
	    if (org.apache.commons.lang3.StringUtils.isNotBlank(sessionId)) { 
	    	//从Redis中取用户数据
	    	//Admin model = (Admin) RedisUtils.get(SESSION_KEY_PREFIX+sessionId); 
	    	//从数据库中取
	    	Admin model = (Admin) adminService.getAdmin(Integer.parseInt(sessionId));
	    	if (model == null) { 
	    		return ; 
	    	} 
	    	/**request.setAttribute(CacheConstant.ADMIN_SESSION_ID,sessionId); */
	    	Integer adminId = model.getId();
	    	if (adminId != null) { 
	    		request.setAttribute(CacheConstant.ADMIN_ID_SESSION_KEY, Long.valueOf(adminId)); 
	    	} 
	    	String mobile = model.getMobile();
	    	if (mobile != null && !"".equals(mobile)) { 
	    		request.setAttribute(CacheConstant.ADMIN_MOBILE_SESSION_KEY, mobile); 
	    	} 
	    } 
	    return ; 
	} 
	
	

}
