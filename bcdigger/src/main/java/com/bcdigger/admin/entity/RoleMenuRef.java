// default package
package com.bcdigger.admin.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * 
 * ClassName: RoleMenuRef
 * 
 * @Description: 角色权限分配
 * @author ipui
 * @date 2018年3月25日
 */
public class RoleMenuRef extends BaseEntity {

	private static final long serialVersionUID = -3352877790316669217L;
	private Integer roleId;
	private String menuIds;
	private Integer state;
	private Date addTime;
	private Date updateTime;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
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
	
	public String getMenuJson(){
		StringBuffer str=new StringBuffer("({funcs:[{menudm:'0'}");
		if(menuIds != null && menuIds.split(",").length>0){
			String[] arr=menuIds.split(",");
			for (String s : arr) {
				str.append(",{menudm:'"+s+"'}");
			}
			str.append("]})");
		}
		return str.toString();
	}

}