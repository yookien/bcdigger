package com.bcdigger.order.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * ClassName: GoodsOrder
 * @Description: 订单表
 * @author ipui
 * @date 2018年7月15日
 */
public class GoodsOrder extends BaseEntity {
	
	private static final long serialVersionUID = 1743202230250183805L;
	
	private String orderNo;
	
	private int orderUserId;
	
	private int storeId;
	
	private int orderType;

	private Date addTime;
	
	private Date updateTime;
	
	private int state;
	
	private String memo;
	
	// 金蝶内码id
	private int kingdeeCustId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(int orderUserId) {
		this.orderUserId = orderUserId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getKingdeeCustId() {
		return kingdeeCustId;
	}

	public void setKingdeeCustId(int kingdeeCustId) {
		this.kingdeeCustId = kingdeeCustId;
	}
	
}
