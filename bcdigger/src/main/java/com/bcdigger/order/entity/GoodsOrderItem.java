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

	private int goodsOrderId;
	
	private String orderNo;
	
	private int goodsId;
	
	private String goodsNo;
	
	private int orderQuantity;
	
	private int instoreQuantity;
	
	private Date instoreTime;
	
	private Date addTime;
	
	private Date updateTime;
	
	private String memo;
	
	// 非数据库字段
	/**
	 * 商品金蝶内码
	 */
	private String goodsKingDeeCustId;
	
	private String goodsName;
	
	private String goodsModel;// 商品规格型号
	
	private String goodsUnit;// 商品单位
	
	public int getGoodsOrderId() {
		return goodsOrderId;
	}

	public void setGoodsOrderId(int goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
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

	public Date getInstoreTime() {
		return instoreTime;
	}

	public void setInstoreTime(Date instoreTime) {
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

	public String getMemo() {
		if(memo == null || "无".equals(memo)){
			memo = "";
		}
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGoodsKingDeeCustId() {
		return goodsKingDeeCustId;
	}

	public void setGoodsKingDeeCustId(String goodsKingDeeCustId) {
		this.goodsKingDeeCustId = goodsKingDeeCustId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	
}
