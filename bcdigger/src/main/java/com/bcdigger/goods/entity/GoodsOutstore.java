package com.bcdigger.goods.entity;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 商品出库
* <p>Title: GoodsOutstore</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsOutstore extends BaseEntity{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int goodsId;//商品id
	private String  batchNo;//出库批次
	private int    type;//出库类型 5100 正常出库 5101 盘点出库 5102 调仓出库
	private int    repositoryId;//出库仓库
	private int    outQuantity ;//出库数量
	private int    outStoreTime;//出库时间
	private int    operator;//操作人
	private String    memo;//备注
	private int    state;//状态
	private int    sourceId;//出库来源 0pc端 1手机端 2paid 3其他
	private int storeId;//店铺id
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}
	public int getOutQuantity() {
		return outQuantity;
	}
	public void setOutQuantity(int outQuantity) {
		this.outQuantity = outQuantity;
	}
	public int getOutStoreTime() {
		return outStoreTime;
	}
	public void setOutStoreTime(int outStoreTime) {
		this.outStoreTime = outStoreTime;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
}
