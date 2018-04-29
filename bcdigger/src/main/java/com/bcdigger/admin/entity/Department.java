package com.bcdigger.admin.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 部门类
* <p>Title: Department</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年4月29日
 */
public class Department extends BaseEntity{
	
	private String name;
	private int  adminId;
	private int parentId;
	private int  leftId;
	private int  rightId;
	private int  displayOrder;
	private Date  createTime;
	private Date  updateTime;
	private int  state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getLeftId() {
		return leftId;
	}
	public void setLeftId(int leftId) {
		this.leftId = leftId;
	}
	public int getRightId() {
		return rightId;
	}
	public void setRightId(int rightId) {
		this.rightId = rightId;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
