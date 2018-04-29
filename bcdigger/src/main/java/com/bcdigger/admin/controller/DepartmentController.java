package com.bcdigger.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.admin.entity.Department;
import com.bcdigger.admin.service.DepartmentService;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;

/**
 * 
 * ClassName: DepartmentController
 * @Description: 部门管理
 * @author yookien
 * @date 2018年3月25日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/admin")
@AdminAuth  // 部门管理全部需要登录鉴权
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * @Description: 添加部门
	 * @param Department
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/addDepartment",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDepartment(Department department){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(department==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			if(StringUtils.isBlank(department.getName())){
				map.put("result", -2);// 部门名称不能为空
				return map;
			}
			Date now=new Date();
			department.setAddTime(now);
			department.setUpdateTime(now);
			departmentService.addDepartment(department);
			map.put("result", 1);//登录成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Description: 根据id查询部门信息
	 * @param id
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getDepartment",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getDepartment(Department department) {
		Map<String, Object> map = new HashMap<>();
		try{
			if( department==null || department.getId()<=0){
				return null;
			}
			department = departmentService.getDepartmentById(department.getId());
			map.put("result", 1);//登录成功
			map.put("department", department);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 更新部门
	 * @param Department
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateDepartment",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDepartment(Department department){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(department==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			Date now=new Date();
			department.setUpdateTime(now);
			departmentService.updateDepartment(department);
			map.put("result", 1);//更新成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description:打开部门管理首页
	 * @param request
	 * @return
	 * @return String
	 * @author ipui
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/departmentsIndex")
	public String departmentsIndex() {
		return "/admin/department_index";
	}

	/**
	 * @Description: 分页查询部门信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @throws
	 * @author ipui
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getDepartments",method={RequestMethod.GET,RequestMethod.POST})
	public String getDepartments(Department department, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<Department> departmentPages = departmentService.getDepartments(department, pageInfo);
			map.addAttribute("pageInfo", departmentPages);
			map.addAttribute("parentId", department.getParentId());// 传递参数，供新增部门使用
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/admin/department_list";
	}

}
