package com.bcdigger.order.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;

/**
 * ClassName: GoodsOrderItem
 * @Description: 订单明细表
 * @author ipui
 * @date 2018年7月15日
 */
public class GoodsOrderItem extends BaseEntity {
	
	private static final long serialVersionUID = 1821765353504460055L;

	private int orderId;
	
	private String orderNo;
	
	private int goodsId;
	
	private String goodsNo;
	
	private int orderQuantity;
	
	private int instoreQuantity;
	
	private int instoreTime;
	
	private Date addTime;
	
	private Date updateTime;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getInstoreQuantity() {
		return instoreQuantity;
	}

	public void setInstoreQuantity(int instoreQuantity) {
		this.instoreQuantity = instoreQuantity;
	}

	public int getInstoreTime() {
		return instoreTime;
	}

	public void setInstoreTime(int instoreTime) {
		this.instoreTime = instoreTime;
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
