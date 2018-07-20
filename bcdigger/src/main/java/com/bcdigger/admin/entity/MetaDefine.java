package com.bcdigger.admin.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * ClassName: MetaDefine
 * 
 * @Description: 元数据内容
 * @author ipui
 * @date 2018年7月20日
 */
public class MetaDefine extends BaseEntity {
	
	private static final long serialVersionUID = 3408430953234652002L;

	private String defineName;
	private String defineDesc;
	private Integer state;
	private Date addTime;
	private Date updateTime;

	public String getDefineName() {
		return defineName;
	}

	public void setDefineName(String defineName) {
		this.defineName = defineName;
	}

	public String getDefineDesc() {
		return defineDesc;
	}

	public void setDefineDesc(String defineDesc) {
		this.defineDesc = defineDesc;
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