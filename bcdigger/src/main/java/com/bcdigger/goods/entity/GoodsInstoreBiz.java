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
	private Date updateTime;//入库提交时间
	private int orderType;//订货单类型
	private int instoreState;//入库状态
	
	private int goodsInstoreId;//收货入库id
	private String goodsNo;//商品条码
	private String goodsName;//商品名称
	private String unit;//单位
	private String model;//规格型号
	private int orderQuantity;//订货数量
	private int instoreQuantity;//已入库数量
	private Date instoreTime;//要货日期
	private String memo;//备注
	private int goodsOrderItemId;//订货单明细id
	
	private int auditType ;//审核类型
	

	public int getAuditType() {
		return auditType;
	}
	public void setAuditType(int auditType) {
		this.auditType = auditType;
	}
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
	public int getGoodsInstoreId() {
		return goodsInstoreId;
	}
	public void setGoodsInstoreId(int goodsInstoreId) {
		this.goodsInstoreId = goodsInstoreId;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getGoodsOrderItemId() {
		return goodsOrderItemId;
	}
	public void setGoodsOrderItemId(int goodsOrderItemId) {
		this.goodsOrderItemId = goodsOrderItemId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getInstoreState() {
		return instoreState;
	}
	public void setInstoreState(int instoreState) {
		this.instoreState = instoreState;
	}
	
	

}
