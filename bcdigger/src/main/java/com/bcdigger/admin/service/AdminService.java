package com.bcdigger.admin.service;

import com.bcdigger.admin.entity.Admin;
import com.bcdigger.common.page.PageInfo;

public interface AdminService {
	
	public Admin getAdmin(int id);
	
	public Admin getAdmin(Admin admin);
	
	public PageInfo<Admin> getAdmins(String name,PageInfo pageInfo);
	
	public PageInfo<Admin> getAdmins(Admin admin,PageInfo pageInfo);
	
	public int addAdmin(Admin admin);
	
	public int updateAdmin(Admin admin);
	
	public int updateAdminPassword(Admin admin);
	
}
