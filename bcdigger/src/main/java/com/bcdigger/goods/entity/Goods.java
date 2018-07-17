package com.bcdigger.goods.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 商品Entity
* <p>Title: Goods</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class Goods extends BaseEntity{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	private int catId;//分类id
	private String goodsNo;//商品货号
	private String  goodsName;//商品名称
	private int state ;//状态 0不可用 1可用
	private String smallImage;//商品小图
	private String middleImage;//商品大图
	private String goodsImage;//商品主图
	private int weight;//重量
	private String unit;//单位
	private String model;//规格型号
	private String innerName;//全名
	private int type  ;//类型
	private int price ;//售价
	private int marketPrice;//市场价
	private int purPrice;//采购价
	private String goodsDesc;//商品描述
	private int displayOrder;//显示顺序
	private Date addTime;//添加时间
	private Date updateTime;//更新时间
	
	// 金蝶内码id
	private int kingdeeCustId;
		
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getSmallImage() {
		return smallImage;
	}
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	public String getMiddleImage() {
		return middleImage;
	}
	public void setMiddleImage(String middleImage) {
		this.middleImage = middleImage;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getInnerName() {
		return innerName;
	}
	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}
	public int getPurPrice() {
		return purPrice;
	}
	public void setPurPrice(int purPrice) {
		this.purPrice = purPrice;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getKingdeeCustId() {
		return kingdeeCustId;
	}
	public void setKingdeeCustId(int kingdeeCustId) {
		this.kingdeeCustId = kingdeeCustId;
	}

}