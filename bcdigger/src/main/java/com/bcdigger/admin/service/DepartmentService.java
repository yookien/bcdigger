package com.bcdigger.admin.service;

import java.util.List;

import com.bcdigger.admin.entity.Department;
import com.bcdigger.common.page.PageInfo;

public interface DepartmentService {
	
	/**
	 * @Description: 添加系统部门
	 * @param Department void  
	 * @dateTime 2018年3月25日
	 */
	public int addDepartment(Department department);
	
	/**
	 * @Description: 更新系统部门
	 * @param Department void  
	 * @date 2018年3月25日
	 */
	public int updateDepartment(Department department);
	
	/**
	 * @Description: 根据id查询部门信息
	 * @param id
	 * @return Department  
	 * @dateTime 2018年3月25日
	 */
	public Department getDepartmentById(int id);
	
	
	/**
	 * @Description: 分页查询系统部门
	 * @param name
	 * @param pageInfo
	 * @return PageInfo<Department>  
	 * @dateTime 2018年3月25日
	 */
	public PageInfo<Department> getDepartments(Department department,PageInfo pageInfo);
	
	/**
	 * @Description: 查询系统部门列表
	 * @param Department
	 * @return List<Department>  
	 * @date 2018年3月31日
	 */
	public List<Department> getDepartmentList(Department department);
}
