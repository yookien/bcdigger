package com.bcdigger.admin.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 
 * ClassName: AdminRoleRef
 * @Description: 用户角色分配
 * @author ipui
 * @date 2018年3月25日
 */
public class AdminRoleRef extends BaseEntity {

	private static final long serialVersionUID = -2539244210955086805L;
	private String adminId;
	private String roleIds;
	private Integer state;
	private Date addTime;
	private Date updateTime;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getAddTime() {
		return addTime;
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