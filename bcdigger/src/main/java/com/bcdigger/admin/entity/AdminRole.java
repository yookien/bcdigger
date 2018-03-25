package com.bcdigger.admin.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * 
 * ClassName: AdminRole
 * 
 * @Description: 用户角色
 * @author liubei
 * @date 2018年3月25日
 */
public class AdminRole extends BaseEntity {

	private static final long serialVersionUID = -5758794021224795070L;
	
	private String roleName;
	private String roleDesc;
	private Integer state;
	private Date addTime;
	private Date updateTime;

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}