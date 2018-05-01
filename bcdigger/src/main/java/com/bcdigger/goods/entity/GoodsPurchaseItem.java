package com.bcdigger.goods.entity;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 商品采购单明细
* <p>Title: GoodsPurchaseItem</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsPurchaseItem extends BaseEntity{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int purchaseId;//采购单id
	private int   goodsId   ;//商品id
	private String   goodsNo;//商品货号
	private int   quantity   ;//采购数量
	private int   purPrice  ;//采购价格
	private int   state      ;//状态
	
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPurPrice() {
		return purPrice;
	}
	public void setPurPrice(int purPrice) {
		this.purPrice = purPrice;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	

}
