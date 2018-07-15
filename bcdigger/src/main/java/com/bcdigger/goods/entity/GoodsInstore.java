package com.bcdigger.goods.entity;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 商品入库表
* <p>Title: GoodsInstore</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsInstore extends BaseEntity{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int goodsId;//商品id
	private int goodsOrderId;//订货单id
	private int goodsOrderItemId;//订货明细id
	private String batchNo;//批次号
	private int state   ;//状态
	private int purPrice;//采购价
	private int type    ;//入库类型 5000正常采购入库 5001盘点入库 5002退货入库 5003调仓入库
	private int inQuantity;//入库数量
	private int inStoreTime;//入库时间
	private int operator;//操作人
	private String memo;//备注
	private int sourceId;//入库来源 0pc端 1手机端 2paid 3其他
	private int storeId;//店铺id
	private int repositoryId;//仓库id
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPurPrice() {
		return purPrice;
	}
	public void setPurPrice(int purPrice) {
		this.purPrice = purPrice;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getInQuantity() {
		return inQuantity;
	}
	public void setInQuantity(int inQuantity) {
		this.inQuantity = inQuantity;
	}
	public int getInStoreTime() {
		return inStoreTime;
	}
	public void setInStoreTime(int inStoreTime) {
		this.inStoreTime = inStoreTime;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public int getGoodsOrderId() {
		return goodsOrderId;
	}
	public void setGoodsOrderId(int goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
	}
	public int getGoodsOrderItemId() {
		return goodsOrderItemId;
	}
	public void setGoodsOrderItemId(int goodsOrderItemId) {
		this.goodsOrderItemId = goodsOrderItemId;
	}
	

}
