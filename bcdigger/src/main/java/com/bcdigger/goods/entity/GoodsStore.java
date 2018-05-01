package com.bcdigger.goods.entity;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 商品库存表
* <p>Title: GoodsStore</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsStore extends BaseEntity{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int goodsId;//商品id
	private int repositoryId;//仓库id
	private int storeId;//店铺id
	private int quantity;//数量
	private int perfectQuantity;//理论库存
	private int alertQuantity  ;//预警库存
	private int state   ;//状态
	private int addTime;//添加时间
	private int updateTime;//更新时间
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPerfectQuantity() {
		return perfectQuantity;
	}
	public void setPerfectQuantity(int perfectQuantity) {
		this.perfectQuantity = perfectQuantity;
	}
	public int getAlertQuantity() {
		return alertQuantity;
	}
	public void setAlertQuantity(int alertQuantity) {
		this.alertQuantity = alertQuantity;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getAddTime() {
		return addTime;
	}
	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}
	public int getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
