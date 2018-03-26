package com.bcdigger.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.admin.service.SysMenuService;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;

/**
 * 
 * ClassName: SysMenuController
 * @Description: 菜单管理
 * @author liubei
 * @date 2018年3月25日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/admin")
@AdminAuth  // 菜单管理全部需要登录鉴权
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * @Description: 添加菜单
	 * @param sysMenu
	 * @return Map<String,Object>  
	 * @throws
	 * @author liubei
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/addMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSysMenu(SysMenu sysMenu){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(sysMenu==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			if(sysMenu.getMenuName()==null || "".equals(sysMenu.getMenuName().trim())){
				map.put("result", -2);// 菜单名称不能为空
				return map;
			}
			Date now=new Date();
			sysMenu.setAddTime(now);
			sysMenu.setUpdateTime(now);
			sysMenu.setLevel(1);
			sysMenuService.addSysMenu(sysMenu);
			map.put("result", 1);//登录成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Description: 根据id查询菜单信息
	 * @param id
	 * @return Map<String,Object>  
	 * @throws
	 * @author liubei
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getSysMenu",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getSysMenu(SysMenu sysMenu) {
		Map<String, Object> map = new HashMap<>();
		try{
			if( sysMenu==null || sysMenu.getId()<=0){
				return null;
			}
			sysMenu = sysMenuService.getSysMenuById(sysMenu.getId());
			map.put("result", 1);//登录成功
			map.put("sysMenu", sysMenu);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 更新菜单
	 * @param sysMenu
	 * @return Map<String,Object>  
	 * @throws
	 * @author liubei
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSysMenu(SysMenu sysMenu){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(sysMenu==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			
			sysMenuService.updateSysMenu(sysMenu);
			map.put("result", 1);//更新成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description:打开菜单管理首页
	 * @param request
	 * @return
	 * @return String
	 * @author liubei
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/sysMenusIndex")
	public String sysMenusIndex() {
		return "/admin/menu_index";
	}

	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @throws
	 * @author liubei
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getSysMenus",method={RequestMethod.GET,RequestMethod.POST})
	public String getSysMenus(SysMenu sysMenu, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<SysMenu> sysMenuPages = sysMenuService.getSysMenus(sysMenu, pageInfo);
			map.addAttribute("pageInfo", sysMenuPages);
			map.addAttribute("parentId", sysMenu.getParentId());// 传递参数，供新增菜单使用
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/admin/menu_list";
	}

}
