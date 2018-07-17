package com.bcdigger.order.entity;

import java.util.List;

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
	private List<String> goodsNos;

	/**
	 * 数量列表
	 */
	private List<Integer> quantitys;

	/**
	 * 要货时间列表
	 */
	private List<String> instoreTimes;

	/**
	 * 备注列表
	 */
	private List<String> memos;

	public List<String> getGoodsNos() {
		return goodsNos;
	}

	public void setGoodsNos(List<String> goodsNos) {
		this.goodsNos = goodsNos;
	}

	public List<Integer> getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(List<Integer> quantitys) {
		this.quantitys = quantitys;
	}

	public List<String> getInstoreTimes() {
		return instoreTimes;
	}

	public void setInstoreTimes(List<String> instoreTimes) {
		this.instoreTimes = instoreTimes;
	}

	public List<String> getMemos() {
		return memos;
	}

	public void setMemos(List<String> memos) {
		this.memos = memos;
	}

}