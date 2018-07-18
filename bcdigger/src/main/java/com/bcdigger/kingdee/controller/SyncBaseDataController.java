package com.bcdigger.kingdee.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.goods.service.GoodsService;
import com.bcdigger.kingdee.util.AccessToken;
import com.bcdigger.kingdee.util.DateTime;
import com.bcdigger.kingdee.util.KingdeeStdLib;
import com.bcdigger.kingdee.util.KingdeeUtil;
import com.bcdigger.store.entity.Store;
import com.bcdigger.store.service.StoreService;

/**
 * 
 * ClassName: SyncBaseDataController
 * @Description: 库存Controller
 * @author ipui
 * @date 2018年5月3日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/")
public class SyncBaseDataController {
	
	private static String sessionValue = "";
	private static String aspnetsessionValue = "";
	private String modifyDate;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value ="/autoSyncBaseData",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> autoSyncBaseData() throws Exception {
		Map<String, Object> map = new HashMap<>(); 
		try {
			// 同步36小时内更新的资料
			Date now = new Date();
			modifyDate = DateTime.formatDateTime(DateTime.subtractMinute(now, 60 * 24 * 365));
			// 同步物料
			syncGoodsInfo();

			// 同步客户信息
			syncCustomer();
			
			map.put("result", 1);// 同步成功
		} catch (Exception e) {
			map.put("result", 0);// 系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	public void syncGoodsInfo() throws Exception {
		// 获取登录session
		AccessToken accessToken = KingdeeUtil.getAccessToken();
		if (accessToken != null) {
			sessionValue = accessToken.getSessionValue();
			aspnetsessionValue = accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();
		try {
			if(modifyDate == null || modifyDate.equals("")){
				modifyDate = DateTime.formatDateTime(DateTime.subtractMinute(new Date(), 60 * 72));// 同步72小时内更新的资料
			}else{
				modifyDate=DateTime.formatDateTime(DateTime.parseDate(modifyDate));
			}
			boolean haveNext = true;
			int nextNums=0;
			int startRow=2000;
			while (haveNext) {
				if(nextNums>=20){
					return;
				}
				// 数据保存接口地址
				URI save_uri = new URI(KingdeeStdLib.KINGDEE_EXCCUTE_BILL_QUERY_URL);
				HttpPost method = new HttpPost(save_uri);
				// 采购入库单保存参数
				JSONObject json = new JSONObject();
				JSONObject objjson = new JSONObject();
				objjson.put("FSaleOrgId",KingdeeUtil.getFNumber(KingdeeStdLib.saleOrgId));
				objjson.put("FormId", "BD_MATERIAL");
				objjson.put("TopRowCount", 0);
				objjson.put("Limit", 2000);
				objjson.put("StartRow", startRow * nextNums);
				objjson.put("FilterString", " FModifyDate >= '" + modifyDate +"' and FUSEORGID.FNUMBER='" + KingdeeStdLib.saleOrgId + "' ");// 标准的SQL语句
				objjson.put("OrderString", "FMaterialID ASC");
				objjson.put("FieldKeys",
						"FMaterialID,FNumber,FName,FForbidStatus,FModifyDate,FSpecification,FBaseUnitId,FWEIGHTUNITID,FVOLUMEUNITID,FExpUnit,FGROSSWEIGHT,FVOLUME,FExpPeriod,FCreateDate,FDefaultVendor");
				json.put("data", objjson);

				StringEntity entity = new StringEntity(json.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				System.out.println("物料同步参数："+json.toString());

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
					System.out.println("物料同步结果："+str);
					// 把json字符串转换成json数组
					JSONArray infos = JSONArray.parseArray(str);
					JSONArray info = null;
					Goods goods = null;
					if(infos==null || infos.size()==0){
							return;
					}else if(infos!=null && infos.size()==2000){
						haveNext=true;
					}else{
						haveNext=false;
					}
					
					for (int i = 0; i < infos.size(); i++) {
						info = infos.getJSONArray(i);
						if (info == null || info.size() < 15) {
							continue;
						}
						goods = new Goods();
						
						// 0FMaterialID,1FNumber,2FName,3FForbidStatus,4FModifyDate,5FSpecification,6FBaseUnitId,
						// 7FWEIGHTUNITID,8FVOLUMEUNITID,9FExpUnit,10FGROSSWEIGHT,11FVOLUME,12FExpPeriod,13FCreateDate,14FDefaultVendor
						// 解析参数
						String goodsNo = info.getString(1);// 商品货号
						String goodsName = info.getString(2);// 商品名称
						int kingdeeCustId = info.getInteger(0);// 物料金蝶系统内码
						String createDate = info.getString(13);// 创建时间
						
						Date addTime = new Date();
						if( createDate != null && createDate.length() >= 8 ){
							addTime = DateTime.parseDate(createDate);
						}
						
						String modifyDate = info.getString(4);
						Date updateTime = new Date();
						if( modifyDate != null && modifyDate.length() >= 8 ){
							updateTime = DateTime.parseDate(modifyDate);
						}

						int baseUnitId = info.getInteger(6);// 计量单位
						int weightUnitId = info.getInteger(7);// 重量单位

						String expUnit = info.getString(9);// 保质期单位
						
						int grossweight = info.getInteger(10);// 重量
						int volume = info.getInteger(11);// 体积
						int expPeriod = info.getInteger(12);// 保质期

						goods.setGoodsNo(goodsNo);
						goods.setGoodsName(goodsName);
						goods.setKingdeeCustId(kingdeeCustId);
						goods.setWeight(grossweight);
						goods.setUpdateTime(updateTime);
						goods.setAddTime(addTime);
						goods.setState(1);
						goods.setUnit("PCS");
						
						// 调用接口保存
						goodsService.addOrUpdateGoods(goods);
					}
				}
				nextNums++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
	}

	/**
	 * 
	 * @Description: 同步客户信息
	 * @throws Exception
	 *             void
	 * @author liubei
	 * @date 2017年7月8日 下午5:24:06
	 */
	public void syncCustomer() throws Exception {
		// 获取登录session
		AccessToken accessToken = KingdeeUtil.getAccessToken();
		if (accessToken != null) {
			sessionValue = accessToken.getSessionValue();
			aspnetsessionValue = accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();
		/********** 查询物料Beigin ************************/
		try {
			if(modifyDate == null || modifyDate.equals("")){
				modifyDate = DateTime.formatDateTime(DateTime.subtractMinute(new Date(), 60 * 36));// 同步36小时内更新的资料
			}else{
				modifyDate=DateTime.formatDateTime(DateTime.parseDate(modifyDate));
			}
			
			// 数据批量查询接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_EXCCUTE_BILL_QUERY_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			JSONObject objjson = new JSONObject();
			objjson.put("FSaleOrgId",KingdeeUtil.getFNumber(KingdeeStdLib.saleOrgId));
			objjson.put("FormId", "BD_Customer");// 客户信息
			objjson.put("TopRowCount", 0);
			// objjson.put("Limit", 2000);
			objjson.put("StartRow", 0);
			objjson.put("FilterString", " FModifyDate >= '" + modifyDate +"'");// 标准的SQL语句
			objjson.put("OrderString", "FCustId ASC");
			objjson.put("FieldKeys",
					"FCustId,FNumber,FName,FCustTypeId,FSALDEPTID,FShortName,FTContact,FTEL,FMOBILE,FFAX,FADDRESS,FModifyDate,FCreateDate,FForbidStatus");
			json.put("data", objjson);

			StringEntity entity = new StringEntity(json.toString(), "utf-8");
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
				System.out.println("请求成功");
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				// 把json字符串转换成json数组
				JSONArray infos = JSONArray.parseArray(str);
				JSONArray info = null;
				Store store = null;
				for (int i = 0; i < infos.size(); i++) {
					info = infos.getJSONArray(i);
					//System.out.println("FCustId:" + info.getInteger(0) + " FNumber:" + info.getString(1) + " FName:"
					//		+ info.getString(2));

					// "0FCustId,1FNumber,2FName,3FCustTypeId,4FSALDEPTID,5FShortName,6FTContact,7FTEL,8FMOBILE,9FFAX,10FADDRESS,11FModifyDate,12FCreateDate");
					// 解析参数，保存客户信息
					store = new Store();
					store.setKingdeeCustId(info.getInteger(0));
					store.setStoreCode(info.getString(1));
					store.setChineseName(info.getString(2));
	
					store.setMobile(info.getString(8));

					store.setAddress(info.getString(10));
					store.setUpdateTime(DateTime.parseDate(info.getString(11)));
					store.setAddTime(DateTime.parseDate(info.getString(12)));
					System.out.println("FCustId:" + store.getKingdeeCustId() + " FNumber:" + store.getStoreCode() + " FName:"
							+ store.getChineseName() );
					storeService.addOrUpdateStore(store);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
	}


}
