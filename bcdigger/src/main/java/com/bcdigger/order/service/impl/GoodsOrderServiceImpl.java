package com.bcdigger.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONObject;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.common.utils.DateUtils;
import com.bcdigger.goods.dao.GoodsDao;
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.kingdee.util.DateTime;
import com.bcdigger.order.dao.GoodsOrderDao;
import com.bcdigger.order.dao.GoodsOrderItemDao;
import com.bcdigger.order.entity.GoodsOrder;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.entity.GoodsOrderItemModel;
import com.bcdigger.order.service.GoodsOrderService;


@Service("goodsOrderService")
public class GoodsOrderServiceImpl implements GoodsOrderService {
	
	@Resource
	private PlatformTransactionManager transactionManager;

	@Autowired
	private GoodsOrderDao goodsOrderDao;
	
	@Autowired
	private GoodsOrderItemDao goodsOrderItemDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public JSONObject addGoodsOrder(GoodsOrder goodsOrder, GoodsOrderItemModel orderItemModel) {
		JSONObject json = new JSONObject();
		// 货号列表
		List<String> goodsNos = null;
		// 数量列表
		List<Integer> quantitys = null;
		// 要货时间列表
		List<String> instoreTimes = null;
		// 备注列表
		List<String> memos = null;
		// 订单号
		String orderNo = null;
		try{
			// 参数校验
			if( goodsOrder == null){
				json.put("result", 10003);
				return json;
			}
			if(orderItemModel != null){
				goodsNos = orderItemModel.getGoodsNo();
				quantitys = orderItemModel.getQuantity();
				instoreTimes = orderItemModel.getInstoreTime();
				memos = orderItemModel.getMemo();
				// 参数不合法
				if(goodsNos==null || goodsNos.size()<1 || quantitys == null 
					|| instoreTimes == null || memos == null
					|| goodsNos.size() != quantitys.size()
					|| goodsNos.size() != instoreTimes.size() 
					|| goodsNos.size() != memos.size() ){
					json.put("result", 10004);// 参数校验失败
					return json;
				}
			} else {
				json.put("result", 10005);// 订单明细数据为空
				return json;
			}
			
			GoodsOrder goodsOrderTemp = new GoodsOrder();
			goodsOrderTemp.setAddTime(DateUtils.getTodayStartTime());
			// 查询当日订单数量，确定流水号
			int orderNumber = goodsOrderDao.countGoodsOrder(goodsOrderTemp);
			
			String flowNumberStr=String.valueOf(100000+orderNumber+1).substring(1);
			
			Date now = new Date();
			// 生成订单号
			orderNo="OMSDH"+DateUtils.getReqDateyyyyMMdd(now)+flowNumberStr;
			goodsOrder.setOrderNo(orderNo);
			goodsOrder.setState(10016);
			goodsOrder.setAddTime(now);
			goodsOrder.setUpdateTime(now);
		}catch(Exception e){
			json.put("result", 0);// 系统异常
			e.printStackTrace();
			return json;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			// 添加订货单信息
			int orderId = goodsOrderDao.insert(goodsOrder);
			// 添加订单明细
			GoodsOrderItem goodsOrderItem = null;
			String goodsNo;
			String instoreTimeStr;
			Date instoreTime;
			for( int i=0,j=goodsNos.size(); i<j; i++){
				goodsOrderItem = new GoodsOrderItem();
				
				goodsNo = goodsNos.get(i);
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("goodsNo", goodsNo);
				Goods goods = goodsDao.getBy(paramMap);
				if(goods == null || goods.getId() < 0 || goods.getState() != 1){
					json.put("result", 10006);// 商品不存在 或者 已下架
					// 事务回滚
					transactionManager.rollback(status);
					return json;
				}
				goodsOrderItem.setGoodsNo(goodsNo);
				goodsOrderItem.setGoodsId(goods.getId());
				goodsOrderItem.setGoodsOrderId(orderId);
				goodsOrderItem.setOrderNo(orderNo);
				goodsOrderItem.setOrderQuantity(quantitys.get(i));
				goodsOrderItem.setMemo(memos.get(i));
				
				instoreTimeStr = instoreTimes.get(i);
				instoreTime=DateTime.parseDate(instoreTimeStr);
				goodsOrderItem.setInstoreTime(instoreTime);
				// 添加订货单明细
				goodsOrderItemDao.insert(goodsOrderItem);
			}
			
			// 下单成功
			json.put("result", 10000);
			
			// 事务提交
			transactionManager.commit(status);
		}catch(Exception e){
			// 事务回滚
			transactionManager.rollback(status);
			json.put("result", 0);// 系统异常
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public int updateGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderDao.update(goodsOrder);
	}

	@Override
	public GoodsOrder getGoodsOrderById(int id) {
		return goodsOrderDao.getById(id);
	}

	@Override
	public PageInfo<GoodsOrder> getGoodsOrders(GoodsOrder goodsOrder, PageInfo pageInfo) {
		return goodsOrderDao.findGoodsOrders(pageInfo, goodsOrder);
	}

}
