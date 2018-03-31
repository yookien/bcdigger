package com.bcdigger.admin.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * 
 * ClassName: SysMenu
 * 
 * @Description: 系统菜单
 * @author ipui
 * @date 2018年3月25日
 */
public class SysMenu extends BaseEntity {

	private static final long serialVersionUID = 3193400919536163003L;

	private String menuName;

	private String menuUrl;

	private String menuLogoUrl;

	private String menuDesc;

	private Integer parentId;

	private Integer isLeaf;

	private Integer level;

	private Integer state;

	private Integer displayOrder;

	private Date addTime;

	private Date updateTime;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuLogoUrl() {
		return menuLogoUrl;
	}

	public void setMenuLogoUrl(String menuLogoUrl) {
		this.menuLogoUrl = menuLogoUrl;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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