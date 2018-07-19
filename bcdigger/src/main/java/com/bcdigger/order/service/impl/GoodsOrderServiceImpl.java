package com.bcdigger.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcdigger.admin.dao.AdminDao;
import com.bcdigger.admin.entity.Admin;
import com.bcdigger.common.constant.GoodsOrderStateConstant;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.common.utils.DateUtils;
import com.bcdigger.goods.dao.GoodsDao;
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.kingdee.util.AccessToken;
import com.bcdigger.kingdee.util.DateTime;
import com.bcdigger.kingdee.util.KingdeeStdLib;
import com.bcdigger.kingdee.util.KingdeeUtil;
import com.bcdigger.order.dao.GoodsOrderDao;
import com.bcdigger.order.dao.GoodsOrderItemDao;
import com.bcdigger.order.entity.GoodsOrder;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.entity.GoodsOrderItemModel;
import com.bcdigger.order.service.GoodsOrderService;
import com.bcdigger.store.dao.StoreDao;
import com.bcdigger.store.entity.Store;


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
	
	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private AdminDao adminDao;

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
				instoreTimes = orderItemModel.getInstoreTimeStr();
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
			goodsOrder.setState(GoodsOrderStateConstant.INIT_STATE);
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
	public JSONObject updateGoodsOrder(GoodsOrder goodsOrder, GoodsOrderItemModel orderItemModel) {
		JSONObject json = new JSONObject();
		// 货号列表
		List<String> goodsNos = null;
		// 数量列表
		List<Integer> quantitys = null;
		// 要货时间列表
		List<String> instoreTimes = null;
		// 备注列表
		List<String> memos = null;
		// 订单明细id列表
		List<Integer> orderItemId = null;
		// 订单号
		String orderNo = null;
		
		// 订单明细map，用于处理删减的订单明细
		Map<Integer,GoodsOrderItem> orderItemMap = new HashMap<Integer,GoodsOrderItem>();
		try{
			// 参数校验
			if( goodsOrder == null || goodsOrder.getId() <= 0){
				json.put("result", 10003);
				return json;
			}
			// 校验订单明细参数
			if(orderItemModel != null){
				goodsNos = orderItemModel.getGoodsNo();
				quantitys = orderItemModel.getQuantity();
				instoreTimes = orderItemModel.getInstoreTimeStr();
				memos = orderItemModel.getMemo();
				orderItemId = orderItemModel.getOrderItemId();
				// 参数不合法
				if(goodsNos==null || goodsNos.size()<1 || quantitys == null 
					|| instoreTimes == null || memos == null || orderItemId == null
					|| goodsNos.size() != quantitys.size()
					|| goodsNos.size() != instoreTimes.size() 
					|| goodsNos.size() != memos.size()
					|| goodsNos.size() != orderItemId.size()){
					json.put("result", 10004);// 参数校验失败
					return json;
				}
			} else {
				json.put("result", 10005);// 订单明细数据为空
				return json;
			}
			// 校验并设置更新参数
			GoodsOrder goodsOrderTemp = goodsOrderDao.getById(goodsOrder.getId());
			if( goodsOrderTemp != null && goodsOrderTemp.getId() > 0){
				if( goodsOrderTemp.getState() == GoodsOrderStateConstant.INIT_STATE
						|| goodsOrderTemp.getState() == GoodsOrderStateConstant.REFUSE_AUDIT_STATE ){
					// 设置需要更新的参数
					Date now = new Date();
					goodsOrderTemp.setUpdateTime(now);
					//goodsOrderTemp.setOrderType(goodsOrder.getOrderType());
					goodsOrder = goodsOrderTemp;
				} else {
					json.put("result", 10007);// 只能编辑 初始及审核拒绝
					return json;
				}
			} else {
				json.put("result", 10008);// 未查到指定订单
				return json;
			}
			GoodsOrderItem goodsOrderItem = new GoodsOrderItem();
			goodsOrderItem.setGoodsOrderId(goodsOrder.getId());
			// 订单明细列表
			List<GoodsOrderItem> orderItemlist = goodsOrderItemDao.getGoodsOrderItems(goodsOrderItem);
			if( orderItemlist != null && orderItemlist.size() > 0 ){
				for(int i=0,j=orderItemlist.size(); i<j; i++){
					goodsOrderItem = orderItemlist.get(i);
					if( goodsOrderItem == null ){
						continue;
					}
					orderItemMap.put(goodsOrderItem.getId(), goodsOrderItem);
				}
			}
		}catch(Exception e){
			json.put("result", 0);// 系统异常
			e.printStackTrace();
			return json;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			// 更新订货单信息
			int result = goodsOrderDao.update(goodsOrder);
			if( result != 1){
				json.put("result", 10009);// 更新订单信息失败
				// 事务回滚
				transactionManager.rollback(status);
				return json;
			}
			// 添加订单明细
			GoodsOrderItem goodsOrderItem = null;
			String goodsNo;
			String instoreTimeStr;
			Date instoreTime;
			for( int i=0,j=goodsNos.size(); i<j; i++){
				// 需要新增
				if(orderItemId.get(i) == null || orderItemId.get(i) == 0 
						|| orderItemMap.get(orderItemId.get(i)) == null ){
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
					goodsOrderItem.setGoodsOrderId(goodsOrder.getId());
					goodsOrderItem.setOrderNo(orderNo);
					goodsOrderItem.setOrderQuantity(quantitys.get(i));
					goodsOrderItem.setMemo(memos.get(i));
					
					instoreTimeStr = instoreTimes.get(i);
					instoreTime=DateTime.parseDate(instoreTimeStr);
					goodsOrderItem.setInstoreTime(instoreTime);
					// 添加订货单明细
					goodsOrderItemDao.insert(goodsOrderItem);
				} else {// 需要更新信息
					goodsOrderItem = orderItemMap.get(orderItemId.get(i));
					
					// 设置可以修改的参数
					goodsOrderItem.setOrderQuantity(quantitys.get(i));
					goodsOrderItem.setMemo(memos.get(i));
					
					instoreTimeStr = instoreTimes.get(i);
					instoreTime=DateTime.parseDate(instoreTimeStr);
					
					goodsOrderItem.setInstoreTime(instoreTime);
					goodsOrderItemDao.update(goodsOrderItem);
					// 移除已经更新过的订单明细
					orderItemMap.remove(orderItemId.get(i));
				}
			}
			
			// 删除订单明细列表里面剩余的 订单明细
			if (!orderItemMap.isEmpty()) {
				Iterator<Map.Entry<Integer,GoodsOrderItem>> iterator = orderItemMap.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Integer,GoodsOrderItem> entry = iterator.next();
					Integer itemId = entry.getKey();
					if(itemId != null && itemId > 0){
						this.goodsOrderItemDao.deleteById(itemId);
					}
					//entry.getValue();
				}
			}
			
			// 更新订单成功
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
	public GoodsOrder getGoodsOrderById(int id) {
		GoodsOrder goodsOrder = null;
		try{
			// 根据id查询订货单
			goodsOrder = goodsOrderDao.getById(id);
			if( goodsOrder == null ){
				return null;
			}
			GoodsOrderItem goodsOrderItem = new GoodsOrderItem();
			goodsOrderItem.setGoodsOrderId(id);
			// 根据订单id查询订单订单明细列表
			List<GoodsOrderItem> orderItemList=this.goodsOrderItemDao.getGoodsOrderItems(goodsOrderItem);
			goodsOrder.setOrderItemList(orderItemList);
			
			// 根据门店id查询门店信息
			Store store = storeDao.getById(goodsOrder.getStoreId());
			if( store != null){
				goodsOrder.setStoreKingdeeCustId(store.getKingdeeCustId());
				goodsOrder.setStoreKingdeeCustNo(store.getStoreCode());
				goodsOrder.setStoreName(store.getChineseName());
			}
			// 查询下单人信息
			Admin admin = adminDao.getById(goodsOrder.getOrderUserId());
			if( admin != null){
				goodsOrder.setOrderUserName(admin.getNickname());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return goodsOrder;
	}

	@Override
	public PageInfo<GoodsOrder> getGoodsOrders(GoodsOrder goodsOrder, PageInfo pageInfo) {
		return goodsOrderDao.findGoodsOrders(pageInfo, goodsOrder);
	}
	
	public int auditingGoodsOrder(GoodsOrder goodsOrder){
		int auditResult = 0;
		try{
			if( goodsOrder == null){
				return 0;
			}
			if(goodsOrder.getState() == GoodsOrderStateConstant.SUCCESS_AUDIT_STATE){
				GoodsOrder goodsOrderTemp = this.goodsOrderDao.getById(goodsOrder.getId());
				if( goodsOrderTemp == null || goodsOrderTemp.getId() <= 0 
						|| goodsOrderTemp.getState() != GoodsOrderStateConstant.INIT_STATE){// 初始状态才允许审核
					return 0;
				}
				if( goodsOrderTemp.getKingdeeCustId() > 0 
						|| (goodsOrderTemp.getKingdeeCustNo() != null 
							&& "".equals(goodsOrderTemp.getKingdeeCustNo())) ){
					goodsOrderDao.auditingGoodsOrder(goodsOrder);
					return 10000;
				}
				GoodsOrderItem goodsOrderItem = new GoodsOrderItem();
				goodsOrderItem.setGoodsOrderId(goodsOrderTemp.getId());
				List<GoodsOrderItem> orderItemList=this.goodsOrderItemDao.getGoodsOrderItems(goodsOrderItem);
				if( orderItemList == null || orderItemList.size() <= 0){
					return 0;
				}
				goodsOrderTemp.setOrderItemList(orderItemList);
				
				Store store = storeDao.getById(goodsOrderTemp.getStoreId());
				if( store != null && store.getKingdeeCustId() >0 ){
					goodsOrderTemp.setStoreKingdeeCustId(store.getKingdeeCustId());
					goodsOrderTemp.setStoreKingdeeCustNo(store.getStoreCode());
				}
				// 审核通过调用方法推送到金蝶
				String resultStr = putOrder(goodsOrderTemp);
				// 解析保存销售单返回信息
				if( resultStr != null ){
					JSONObject json = JSONObject.parseObject(resultStr).getJSONObject("Result");
					
					if(json.getJSONObject("ResponseStatus").getBooleanValue("IsSuccess")){
						int kingdeeCustId = json.getIntValue("Id");
						String kingdeeCustNo = json.getString("Number");
						goodsOrderTemp.setKingdeeCustId(kingdeeCustId);
						goodsOrderTemp.setKingdeeCustNo(kingdeeCustNo);
						
						goodsOrderTemp.setState(GoodsOrderStateConstant.SUCCESS_AUDIT_STATE);
						// 保存成功就保存订单，防止重复推送
						// 审核订单 并 保存金蝶内码、金蝶单号
						goodsOrderDao.auditingGoodsOrder(goodsOrderTemp);
						
						boolean result = submitOrder(goodsOrderTemp);
						
						if( result ){
							result = auditOrder(goodsOrderTemp);
						}
						
						auditResult = 10000;// 成功
					} else {
						// 保存失败
					}
				} else {
					// 保存失败
				}
			} else {
				// 拒绝订单
				goodsOrderDao.auditingGoodsOrder(goodsOrder);
				auditResult = 10000;// 成功
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return auditResult;
	}
	
	/**
	 * 金蝶相关接口
	 */
	private static String sessionValue = "";
	private static String aspnetsessionValue = "";
	
	private static String sessionkey = "kdservice-sessionid";
	private static String aspnetsessionkey = "ASP.NET_SessionId";
	
	/**
	 * 将审核通过的订单推送到金蝶
	 * 
	 * @param tOrder
	 */
	public String putOrder(GoodsOrder goodsOrder) {
		try {
			// 得到登录接口
			AccessToken accessToken = KingdeeUtil.getAccessToken();
			if (accessToken != null) {
				sessionValue = accessToken.getSessionValue();
				aspnetsessionValue = accessToken.getAspnetsessionValue();
			}
			// 定义httpClient的实例
			HttpClient httpclient = new DefaultHttpClient();
			// 订单保存接口
			String save_URL = KingdeeStdLib.KINGDEE_SAVE_URL;
			URI save_uri = new URI(save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("formid", "SAL_SaleOrder");

			JSONObject jsonData = new JSONObject();
			JSONObject jsonModel = new JSONObject();
			
			jsonModel.put("FBillTypeID", KingdeeUtil.getFNumber("XSDD01_SYS"));// 标准销售单
			jsonModel.put("FDate", KingdeeUtil.getDateForString(goodsOrder.getAddTime()));
			jsonModel.put("FSaleOrgId",KingdeeUtil.getFNumber(KingdeeStdLib.saleOrgId)); //销售组织
			//jsonModel.put("FBillNo", "goodsOrder.getOrderNo()");
			jsonModel.put("FCustId", KingdeeUtil.getFNumber(goodsOrder.getStoreKingdeeCustNo()));// 客户
			//jsonModel.put("FSaleDeptId", KingdeeUtil.getFNumber(tOrder.getKd_dept())); // 销售部门
			//jsonModel.put("FSalerId", KingdeeUtil.getFNumber(tOrder.getKd_salerNo())); // 销售员

			// 支付详情
			JSONObject jsFinance = new JSONObject();
			jsFinance.put("FSettleCurrId", KingdeeUtil.getFNumber("PRE001"));// 结算币别
			jsFinance.put("FExchangeTypeId", KingdeeUtil.getFNumber("HLTX01_SYS"));
			jsFinance.put("FExchangeRate", 1.00); // 汇率

			jsonModel.put("FSaleOrderFinance", jsFinance);
			
			// 库存与数量
			JSONArray jsArrEntry = new JSONArray();
			List<GoodsOrderItem> orderItemList = goodsOrder.getOrderItemList();
			for (GoodsOrderItem item : orderItemList) {
				JSONObject jsEntry = new JSONObject();
				jsEntry.put("FMaterialId", KingdeeUtil.getFNumber(item.getGoodsNo())); // 物料单号
				jsEntry.put("FUnitID", KingdeeUtil.getFNumber("Pcs"));// 单位
				// 数量
				jsEntry.put("FQty", item.getOrderQuantity());
				jsEntry.put("FTaxPrice", 0.0); // 支付单价
				jsEntry.put("FEntryTaxRate", 0);
				//jsEntry.put("FTaxAmount", 16.00);// 税额
				//jsEntry.put("FDiscount", 0);// 折扣额
				//jsEntry.put("FAllAmount", 0);// 销售额

				jsEntry.put("FDeliveryDate", KingdeeUtil.getDateForString(item.getInstoreTime()));
				jsEntry.put("FSettleOrgIds",KingdeeUtil.getFNumber(KingdeeStdLib.saleOrgId));
				jsArrEntry.add(jsEntry);
			}
			jsonModel.put("FSaleOrderEntry", jsArrEntry);
			jsonData.put("Model", jsonModel);
			json.put("data", jsonData);
			// 设置json格式
			StringEntity entity = new StringEntity(json.toString(), "utf-8");
			System.out.println(json.toString());
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			// 将登陆信息放入
			method.setHeader(sessionkey, sessionValue);
			method.setHeader(aspnetsessionkey, aspnetsessionValue);
			method.setEntity(entity);
			HttpResponse result = httpclient.execute(method);
			String str = "";
			if (result.getStatusLine().getStatusCode() == 200) {
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
	/**
	 * @Description:提交订单
	 * @param goodsOrder
	 * @return
	 * @return boolean
	 * @author liubei
	 * @date 2018年7月18日
	 */
	private boolean submitOrder(GoodsOrder goodsOrder) {
		boolean saveResult = false;
		try {
			// 获取登录session
			AccessToken accessToken = KingdeeUtil.getAccessToken();
			if (accessToken != null) {
				sessionValue = accessToken.getSessionValue();
				aspnetsessionValue = accessToken.getAspnetsessionValue();
			}
			// 定义httpClient的实例
			HttpClient httpclient = new DefaultHttpClient();
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_SUBMIT_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "SAL_SaleOrder");// 提交
			JSONObject objson = new JSONObject();
			JSONArray jArray = new JSONArray();
			jArray.add(goodsOrder.getKingdeeCustNo());
			
			if (jArray.size() < 1) {
				return true;
			}
			objson.put("Numbers", jArray);
			json.put("data", objson);

			String jsonStr = json.toString();
			System.out.println("请求参数：" + jsonStr);
			StringEntity entity = new StringEntity(jsonStr, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");

			// 把成功登录的Session信息传进去，获取连接信息
			method.setHeader(KingdeeStdLib.sessionkey, sessionValue);
			method.setHeader(KingdeeStdLib.aspnetsessionkey, aspnetsessionValue);
			// 方法参数
			method.setEntity(entity);
			HttpResponse result = httpclient.execute(method);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				System.out.println("接口返回数据：" + str);
				// 把json字符串转换成json对象，方便操作
				JSONObject jsonResult;
				if (str != null && !"".equals(str)) {
					try {
						jsonResult = JSONObject.parseObject(str);
						saveResult = jsonResult.getJSONObject("Result").getJSONObject("ResponseStatus")
								.getBoolean("IsSuccess");
					} catch (Exception e) {
						saveResult = false;
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			saveResult = false;
		}
		return saveResult;
	}

	/**
	 * @Description:审核订单信息
	 * @param goodsOrder
	 * @return
	 * @return boolean
	 * @author liubei
	 * @date 2018年7月18日
	 */
	private boolean auditOrder(GoodsOrder goodsOrder){
		boolean saveResult = false;
		try {
			// 获取登录session
			AccessToken accessToken = KingdeeUtil.getAccessToken();
			if (accessToken != null) {
				sessionValue = accessToken.getSessionValue();
				aspnetsessionValue = accessToken.getAspnetsessionValue();
			}
			// 定义httpClient的实例
			HttpClient httpclient = new DefaultHttpClient();
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_AUDIT_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "SAL_SaleOrder");// 销售单
			JSONObject objson = new JSONObject();
			JSONArray jArray = new JSONArray();
			jArray.add(goodsOrder.getKingdeeCustNo());
			
			if (jArray.size() < 1) {
				return true;
			}
			objson.put("Numbers", jArray);
			json.put("data", objson);

			String jsonStr = json.toString();
			System.out.println("请求参数：" + jsonStr);
			StringEntity entity = new StringEntity(jsonStr, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");

			// 把成功登录的Session信息传进去，获取连接信息
			method.setHeader(KingdeeStdLib.sessionkey, sessionValue);
			method.setHeader(KingdeeStdLib.aspnetsessionkey, aspnetsessionValue);
			// 方法参数
			method.setEntity(entity);
			HttpResponse result = httpclient.execute(method);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				System.out.println("接口返回数据：" + str);

				// 把json字符串转换成json对象，方便操作
				JSONObject jsonResult;
				if (str != null && !"".equals(str)) {
					try {
						jsonResult = JSONObject.parseObject(str);
						saveResult = jsonResult.getJSONObject("Result").getJSONObject("ResponseStatus")
								.getBoolean("IsSuccess");
					} catch (Exception e) {
						saveResult = false;
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			saveResult = false;
		}
		return saveResult;
	}


}
