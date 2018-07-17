package com.bcdigger.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	
	public int auditingGoodsOrder(GoodsOrder goodsOrder){
		try{
			if( goodsOrder == null){
				return 0;
			}
			if(goodsOrder.getState() == GoodsOrderStateConstant.SUCCESS_AUDIT_STATE){
				GoodsOrder goodsOrderTemp = this.goodsOrderDao.getById(goodsOrder.getId());
				if( goodsOrderTemp == null || goodsOrderTemp.getId() <= 0){
					return 0;
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
				
				//	goodsOrder.setKingdeeCustId(goodsOrderTemp.getKingdeeCustId());
				//	goodsOrder.setKingdeeCustNo(goodsOrderTemp.getKingdeeCustNo());
				// 审核订单
				//goodsOrderDao.auditingGoodsOrder(goodsOrder);
			} else {
				// 审核订单
				goodsOrderDao.auditingGoodsOrder(goodsOrder);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
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
			jsonModel.put("FSaleOrgId",KingdeeUtil.getFNumber("00")); //销售组织
			jsonModel.put("FBillNo", "");//goodsOrder.getOrderNo()
			jsonModel.put("FCustId", KingdeeUtil.getFNumber(goodsOrder.getStoreKingdeeCustNo()));// 客户
			//jsonModel.put("FSaleDeptId", KingdeeUtil.getFNumber(tOrder.getKd_dept())); // 销售部门
			//jsonModel.put("FSalerId", KingdeeUtil.getFNumber(tOrder.getKd_salerNo())); // 销售员
			/**jsonModel.put("F_PAEZ_ZZYH", tOrder.getReceiver());// 收货人
			jsonModel.put("F_PAEZ_Sheng", tOrder.getProvince());
			jsonModel.put("F_PAEZ_Shi", tOrder.getCity());
			jsonModel.put("F_PAEZ_Qu", tOrder.getDistrict());// 区，县
			jsonModel.put("F_PAEZ_SHMXDZ", tOrder.getAddress());// 详细地址
			jsonModel.put("F_PAEZ_DH", tOrder.getMobile());// 电话*/

			// 支付详情
			JSONObject jsFinance = new JSONObject();
			jsFinance.put("FSettleCurrId", KingdeeUtil.getFNumber("PRE001"));// 结算币别
			jsFinance.put("FExchangeTypeId", KingdeeUtil.getFNumber("HLTX01_SYS"));
			jsFinance.put("FExchangeRate", 1.00); // 汇率
			// jsFinance.put("FRecConditionId", value); //收款条件
			// jsFinance.put("FSettleModeId",KingdeeUtil.getFNumber(String.valueOf(tOrder.getPayType())));//支付方式

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
				jsEntry.put("FSettleOrgIds",KingdeeUtil.getFNumber("00"));
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
				System.out.println("result:"+str);
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 提交/审核
	 * 
	 * @param arrInStoreId
	 */
	public List<String> putForWardKindee(String formId, String url, List<String> arrInStoreId) {
		JSONObject json = new JSONObject();
		json.put("formid", formId);
		JSONObject jsonData = new JSONObject();
		List<String> arrRight = new ArrayList<String>();
		// 定义这个数组
		String[] arrNum = new String[arrInStoreId.size()];
		// 转化数组
		arrInStoreId.toArray(arrNum);
		jsonData.put("Numbers", arrNum);
		json.put("data", jsonData);
		try {
			AccessToken accessToken = KingdeeUtil.getAccessToken();
			if (accessToken != null) {
				sessionValue = accessToken.getSessionValue();
				aspnetsessionValue = accessToken.getAspnetsessionValue();
			}
			// 定义httpClient的实例
			HttpClient httpclient = new DefaultHttpClient();
			// String save_URL =
			URI save_uri = new URI(url);
			HttpPost method = new HttpPost(save_uri);
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
				System.out.println(str);
				JSONObject jsonObj = JSONObject.parseObject(str);
				JSONObject resultjson = jsonObj.getJSONObject("Result");
				JSONObject jsResponse = resultjson.getJSONObject("ResponseStatus");
				// 得到提交成功后的单号
				JSONArray rightArray = jsResponse.getJSONArray("SuccessEntitys");
				for (int s = 0; s < rightArray.size(); s++) {
					JSONObject objError = rightArray.getJSONObject(s);
					String name = objError.getString("Number");
					arrRight.add(name);
				}
			} else {
				// 出现异常则删除插入的订单
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 返回正确执行
		return arrRight;
	}


}
