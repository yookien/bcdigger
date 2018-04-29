package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.DepartmentDao;
import com.bcdigger.admin.entity.Department;
import com.bcdigger.admin.service.DepartmentService;
import com.bcdigger.common.page.PageInfo;

/**
 * 
 * ClassName: DepartmentServiceImpl
 * @Description: 部门service
 * @author yookien
 * @date 2018年3月25日
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public int addDepartment(Department department) {
		
		return departmentDao.insert(department);
	}

	@Override
	public int updateDepartment(Department department) {
		return departmentDao.update(department);
	}

	@Override
	public Department getDepartmentById(int id) {
		
		return departmentDao.getById(id);
	}

	@Override
	public PageInfo<Department> getDepartments(Department department, PageInfo pageInfo) {
		return departmentDao.findDepartments(pageInfo, department);
	}

	@Override
	public List<Department> getDepartmentList(Department department) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
