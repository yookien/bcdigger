package com.bcdigger.order.entity;

import java.util.Date;
import java.util.List;

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
	
	/**
	 * 金蝶内码id
	 */
	private int kingdeeCustId;
	
	private String kingdeeCustNo;
	
	private int kingdeePurchaseCustId;// 采购单id
	
	private String kingdeePurchaseCustNo;// 采购单号
	
	private int kingdeePurchaseInstoreCustId;// 采购入库单id
	
	private String kingdeePurchaseInstoreCustNo;// 采购入库单号
	
	// 非数据库字段
	/**
	 * 门店名称
	 */
	private String storeName;
	
	private int storeKingdeeCustId;
	
	private String storeKingdeeCustNo;
	
	private List<GoodsOrderItem> orderItemList;
	
	private String orderUserName;

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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getKingdeeCustNo() {
		return kingdeeCustNo;
	}

	public void setKingdeeCustNo(String kingdeeCustNo) {
		this.kingdeeCustNo = kingdeeCustNo;
	}

	public List<GoodsOrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<GoodsOrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public int getStoreKingdeeCustId() {
		return storeKingdeeCustId;
	}

	public void setStoreKingdeeCustId(int storeKingdeeCustId) {
		this.storeKingdeeCustId = storeKingdeeCustId;
	}

	public String getStoreKingdeeCustNo() {
		return storeKingdeeCustNo;
	}

	public void setStoreKingdeeCustNo(String storeKingdeeCustNo) {
		this.storeKingdeeCustNo = storeKingdeeCustNo;
	}

	public String getOrderUserName() {
		return orderUserName;
	}

	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName;
	}

	public int getKingdeePurchaseCustId() {
		return kingdeePurchaseCustId;
	}

	public void setKingdeePurchaseCustId(int kingdeePurchaseCustId) {
		this.kingdeePurchaseCustId = kingdeePurchaseCustId;
	}

	public String getKingdeePurchaseCustNo() {
		return kingdeePurchaseCustNo;
	}

	public void setKingdeePurchaseCustNo(String kingdeePurchaseCustNo) {
		this.kingdeePurchaseCustNo = kingdeePurchaseCustNo;
	}

	public int getKingdeePurchaseInstoreCustId() {
		return kingdeePurchaseInstoreCustId;
	}

	public void setKingdeePurchaseInstoreCustId(int kingdeePurchaseInstoreCustId) {
		this.kingdeePurchaseInstoreCustId = kingdeePurchaseInstoreCustId;
	}

	public String getKingdeePurchaseInstoreCustNo() {
		return kingdeePurchaseInstoreCustNo;
	}

	public void setKingdeePurchaseInstoreCustNo(String kingdeePurchaseInstoreCustNo) {
		this.kingdeePurchaseInstoreCustNo = kingdeePurchaseInstoreCustNo;
	}
	
}
