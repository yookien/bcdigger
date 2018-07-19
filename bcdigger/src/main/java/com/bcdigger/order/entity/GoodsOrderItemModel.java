package com.bcdigger.order.entity;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: GoodsOrderItemModel
 * 
 * @Description: 传递list参数使用
 * @author liubei
 * @date 2018年7月17日
 */
public class GoodsOrderItemModel {
	/**
	 * 货号列表
	 */
	private List<String> goodsNo;

	/**
	 * 数量列表
	 */
	private List<Integer> quantity;

	/**
	 * 要货时间列表
	 */
	private List<String> instoreTimeStr;

	/**
	 * 备注列表
	 */
	private List<String> memo;
	/**
	 * 订单明细id列表
	 */
	private List<Integer> orderItemId;

	public List<String> getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(List<String> goodsNo) {
		this.goodsNo = goodsNo;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}

	public List<String> getInstoreTimeStr() {
		return instoreTimeStr;
	}

	public void setInstoreTimeStr(List<String> instoreTimeStr) {
		this.instoreTimeStr = instoreTimeStr;
	}

	public List<String> getMemo() {
		return memo;
	}

	public void setMemo(List<String> memo) {
		this.memo = memo;
	}

	public List<Integer> getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(List<Integer> orderItemId) {
		this.orderItemId = orderItemId;
	}

}