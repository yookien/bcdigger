package com.bcdigger.goods.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;
/***
 * 商品分类Entity
* <p>Title: GoodsCategory</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年5月1日
 */
public class GoodsCategory extends BaseEntity{
	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	//分类名称
	private String catName;
	//描述
	private String catDesc;
	//状态
	private int state;
	//左节点id
	private int leftId;
	//右节点id
	private int rightId;
	//显示顺序
	private int displayOrder;
	//父节点id
	private int parentId;
	//全称
	private String innerName;
	//分类图片
	private String catImage;
	//添加时间
	private Date addTime;
	//更新时间
	private Date updateTime;
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLeftId() {
		return leftId;
	}
	public void setLeftId(int leftId) {
		this.leftId = leftId;
	}
	public int getRightId() {
		return rightId;
	}
	public void setRightId(int rightId) {
		this.rightId = rightId;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getInnerName() {
		return innerName;
	}
	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}
	public String getCatImage() {
		return catImage;
	}
	public void setCatImage(String catImage) {
		this.catImage = catImage;
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
