package com.bcdigger.goods.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 商品收货入库业务bean
* <p>Title: GoodsInstore</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsInstoreBiz extends BaseEntity{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int goodsOrderId;//订货单id
	private int storeId;//门店id
	private String storeChineseName;//门店中文名称 
	private String orderNo;//订货单NO
	private int kingdeeCustId;//金蝶销售单内码
	private String kingdeeCustNo;//金蝶销售单No
	private String orderState;//订货单状态
	private int inQuantity;//本次入库数量
	private String operatorName;//入库操作人
	private Date updateTime;
	
	
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getGoodsOrderId() {
		return goodsOrderId;
	}
	public void setGoodsOrderId(int goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreChineseName() {
		return storeChineseName;
	}
	public void setStoreChineseName(String storeChineseName) {
		this.storeChineseName = storeChineseName;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getKingdeeCustId() {
		return kingdeeCustId;
	}
	public void setKingdeeCustId(int kingdeeCustId) {
		this.kingdeeCustId = kingdeeCustId;
	}
	public String getKingdeeCustNo() {
		return kingdeeCustNo;
	}
	public void setKingdeeCustNo(String kingdeeCustNo) {
		this.kingdeeCustNo = kingdeeCustNo;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public int getInQuantity() {
		return inQuantity;
	}
	public void setInQuantity(int inQuantity) {
		this.inQuantity = inQuantity;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
	

}
