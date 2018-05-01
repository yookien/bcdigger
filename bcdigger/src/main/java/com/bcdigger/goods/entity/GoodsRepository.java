package com.bcdigger.goods.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * 仓库库位表
* <p>Title: GoodsRepository</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsRepository extends BaseEntity {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private String name;//名称
	private int storeId;//所属店铺id
	private int type    ;//类型5601 正常仓,5602 辅料仓,5603 退货仓
	private String    address;//地址
	private int    isUse  ;//是否可用
	private int    capacity;//容量
	private int    quantity;//目前库存数
	private int    operator;//管理人
	private int    state   ;//状态
	private Date    addTime;//添加时间
	private Date    updateTime;//更新时间
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
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
