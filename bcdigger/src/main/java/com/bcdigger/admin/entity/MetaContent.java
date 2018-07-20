package com.bcdigger.admin.entity;
// default package

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * 
 * ClassName: MetaContent
 * 
 * @Description: 元数据类型表
 * @author ipui
 * @date 2018年7月20日
 */
public class MetaContent extends BaseEntity {

	private static final long serialVersionUID = 5111788191533091092L;

	private Integer defineId;
	private String contentDesc;
	private String name;
	private String value;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	private Integer kingdeeCustId;// 金蝶内码id
	private Integer state;
	private Date addTime;
	private Date updateTime;

	public Integer getDefineId() {
		return defineId;
	}

	public void setDefineId(Integer defineId) {
		this.defineId = defineId;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public Integer getKingdeeCustId() {
		return kingdeeCustId;
	}

	public void setKingdeeCustId(Integer kingdeeCustId) {
		this.kingdeeCustId = kingdeeCustId;
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