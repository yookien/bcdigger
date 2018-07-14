package com.bcdigger.kingdee.util;

import java.net.URI;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * ClassName: KingdeeTest
 * 
 * @Description: 金蝶接口测试类
 * @author liubei
 * @date 2017年5月31日
 */
@SuppressWarnings("deprecation")
public class KingdeeTest {

	private static String sessionValue = "";
	private static String aspnetsessionValue = "";

	public static void main(String[] args) throws Exception {
		//saveSurplus();
		//submitSurplus();
		//auditSurplus();
		//syncStock();
		saveStore();
		//submitStock();
		//auditStock();
	}
	
	private static void saveStore() throws Exception {

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
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_BATCH_SAVE_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "BD_STOCK");// 仓库
			JSONObject objjson = new JSONObject();
			JSONArray jArray=new JSONArray();
			for(int i=0;i<1;i++){
				JSONObject objson = new JSONObject();
				//objson.put("FStockId", 103164);// 仓库ID
				objson.put("FName", "虚拟仓");// 仓库名称
				objson.put("FNumber", "T2017071300110"+i);// 仓库编码
				objson.put("FStockType", "50008");// 仓库类型
				objson.put("FStockStatusType", "0,1,2,3,4,5,6,7,8");//库存状态类型
				objson.put("FStockProperty", "1");//仓库属性
				objson.put("FUseOrgId", KingdeeUtil.getFNumber("100"));//使用组织
				objson.put("FCreateOrgId",KingdeeUtil.getFNumber("100"));//创建组织
				jArray.add(objson);
			}
			objjson.put("Model", jArray);
			objjson.put("NeedReturnFields", "[FStockId,FNumber,FDocumentStatus,FDefStockStatusId]");//返回字段
			json.put("data", objjson);

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
						System.err.println(jsonResult.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
	}
	
	private static void submitStock() throws Exception {

		// 获取登录session
		AccessToken accessToken = KingdeeUtil.getAccessToken();
		if (accessToken != null) {
			sessionValue = accessToken.getSessionValue();
			aspnetsessionValue = accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();

		try {
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_SUBMIT_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "BD_STOCK");// 盘盈单
			JSONObject objson = new JSONObject();
			JSONArray jArray = new JSONArray();
			jArray.add("T20170713003");
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
						System.err.println(jsonResult.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
		/********** 查询物料End ************************/
	}
	
	private static void auditStock() throws Exception {

		// 获取登录session
		AccessToken accessToken = KingdeeUtil.getAccessToken();
		if (accessToken != null) {
			sessionValue = accessToken.getSessionValue();
			aspnetsessionValue = accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();

		try {
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_AUDIT_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "BD_STOCK");// 盘盈单
			JSONObject objson = new JSONObject();
			JSONArray jArray = new JSONArray();
			jArray.add("T20170713003");
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
						System.err.println(jsonResult.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
		/********** 查询物料End ************************/
	}
	
	public static void syncStock() throws Exception {
		// 获取登录session
		AccessToken accessToken=KingdeeUtil.getAccessToken();
		if(accessToken!=null){
			sessionValue=accessToken.getSessionValue();
			aspnetsessionValue=accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();
		/********** 查询物料Beigin ************************/
		try {
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_EXCCUTE_BILL_QUERY_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);
			
			JSONObject json=new JSONObject();
			JSONObject objjson=new JSONObject();
			objjson.put("FormId", "BD_STOCK");// 仓库
			objjson.put("TopRowCount", 0);
			objjson.put("Limit", 20);
			objjson.put("StartRow", 0);
			objjson.put("FilterString", " FModifyDate>='2017-01-01 00:00:00'");//标准的SQL语句
			objjson.put("OrderString", "FStockId asc ");
			objjson.put("FieldKeys", "FStockId,FNumber,FName,FStockType,FAddress,FStockStatusType,FStockProperty,FUseOrgId,FCreateOrgId");//
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
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				// 把json字符串转换成json数组
				JSONArray infos=JSONArray.parseArray(str);
				JSONArray info=null;
				for(int i=0;i<infos.size();i++){
					info=infos.getJSONArray(i);
					//0FStockId,1FNumber,2FName,3FStockType,4FAddress,5FStockStatusType,6FStockProperty,7FUseOrgId,8FCreateOrgId
					System.out.println("FStockId:"+info.getInteger(0) +" FNumber:"+info.getString(1) +" FName:"+info.getString(2)
					+" FStockType:"+info.getString(3) +" FAddress:"+info.getString(4)+ "FStockStatusType:"+info.getString(5) 
					+ "FStockProperty:"+info.getString(6)  +" FUseOrgId:"+info.getString(7) +" FCreateOrgId:"+info.getString(8));
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
	}
	
	
	private static void auditSurplus() throws Exception {

		// 获取登录session
		AccessToken accessToken = KingdeeUtil.getAccessToken();
		if (accessToken != null) {
			sessionValue = accessToken.getSessionValue();
			aspnetsessionValue = accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();

		try {
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_AUDIT_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "STK_StockCountGain");// 盘盈单
			JSONObject objson = new JSONObject();
			JSONArray jArray = new JSONArray();
			jArray.add("20170713002");
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
						System.err.println(jsonResult.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
		/********** 查询物料End ************************/
	}
	
	
	
	private static void submitSurplus() throws Exception {

		// 获取登录session
		AccessToken accessToken = KingdeeUtil.getAccessToken();
		if (accessToken != null) {
			sessionValue = accessToken.getSessionValue();
			aspnetsessionValue = accessToken.getAspnetsessionValue();
		}
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();

		try {
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_SUBMIT_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "STK_StockCountGain");// 盘盈单
			JSONObject objson = new JSONObject();
			JSONArray jArray = new JSONArray();
			jArray.add("20170713002");
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
						System.err.println(jsonResult.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
		/********** 查询物料End ************************/
	}
	

	/**
	 * 保存盘盈盘亏
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "resource" })
	private static void saveSurplus() throws Exception {

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
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_SAVE_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			json.put("FormId", "STK_StockCountGain");// 盘盈单

			JSONObject objjson = new JSONObject();

			JSONObject objson = new JSONObject();
			objson.put("FBillNo", "20170713003");// 单据编号-盘盈单号
			// 单据类型
			objson.put("FBillTypeID", KingdeeUtil.getFNumber("PY01_SYS"));// 单据类型-盘盈入库

			objson.put("FOwnerTypeIdHead", "BD_OwnerOrg");
			objson.put("FOwnerIdHead", KingdeeUtil.getFNumber("100"));
			objson.put("FStockOrgId", KingdeeUtil.getFNumber("100"));
			// 单据日期
			objson.put("FDate", DateTime.formatDate(new Date()));
			JSONArray jArray = new JSONArray();
			for (int i = 0; i < 1; i++) {
				JSONObject ojson = new JSONObject();
				// 物料 编码
				ojson.put("FMaterialId", KingdeeUtil.getFNumber("000000001"));// 物料
																				// 编码
				ojson.put("FStockId", KingdeeUtil.getFNumber("123"));// 仓库 编码
				ojson.put("FCountQty", 120);// 实盘数量
				// 库存状态 编码
				ojson.put("FStockStatusId", KingdeeUtil.getFNumber("KCZT01_SYS"));
				// 单位 编码
				ojson.put("FUnitID", KingdeeUtil.getFNumber("Pcs"));
				ojson.put("FKeeperTypeId", "BD_KeeperOrg");
				ojson.put("FOwnerTypeId", "BD_OwnerOrg");
				jArray.add(ojson);
			}
			objson.put("FBillEntry", jArray);
			objjson.put("Model", objson);
			json.put("data", objjson);

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
						System.err.println(jsonResult.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
	}


	private static void queryInfoFromKingdee() throws Exception {

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
			// 数据保存接口地址
			String Save_URL = KingdeeStdLib.KINGDEE_EXCCUTE_BILL_QUERY_URL;
			URI save_uri = new URI(Save_URL);
			HttpPost method = new HttpPost(save_uri);

			JSONObject json = new JSONObject();
			JSONObject objjson = new JSONObject();
			objjson.put("FormId", "BD_STOCK");// 仓库
			objjson.put("TopRowCount", 0);
			objjson.put("Limit", 20);
			objjson.put("StartRow", 0);
			objjson.put("FilterString", " FModifyDate>='2017-01-01 00:00:00'");// 标准的SQL语句
			objjson.put("OrderString", "FStockId asc ");
			objjson.put("FieldKeys", "FStockId,FNumber,FName,FPrincipal,FStockType,FAddress,FModifyDate,FCreateDate");//
			json.put("data", objjson);

			// String
			// jsonParam="{\"formid\":\"BD_MATERIAL\",\"data\":{\"CreateOrgId\":\"0\",\"Number\":\"\",\"Id\":\"\"}}";

			// JSONArray jArray=new JSONArray();
			// jArray.add(json);

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
				System.out.println(str);
				// 成功的字符串类似
				// {"Result":{"ResponseStatus":{"IsSuccess":true},"Id":100040,"Number":"CGRK00016"}}
				// 失败 的字符串类似
				// {"Result":{"ResponseStatus":{"ErrorCode":500,"IsSuccess":false,"Errors":[{"FieldName":"FStockId","Message":"单据体“明细信息”第1行字段“仓库”是必填项"},{"FieldName":"AbstractInteractionResult","Message":"AbstractInteractionResult.InteractionContext
				// is null"}]},"Id":""}}

				// 把json字符串转换成json对象，方便操作
				/**
				 * if(str!=null && !"".equals(str)){ try{ jsonResult =
				 * JSONObject.fromObject(str); }catch(Exception e){
				 * e.printStackTrace(); } }
				 */
				// 把json字符串转换成json数组
				JSONArray infos = JSONArray.parseArray(str);
				JSONArray info = null;
				for (int i = 0; i < infos.size(); i++) {
					info = infos.getJSONArray(i);
					System.out.println(
							"FID:" + info.getInteger(0) + " FNumber:" + info.getString(1) + " FName:" + info.getString(2)
					);

					// System.out.println(DateTime.parseDate(info.getString(15)));
					// FID,FNumber,FName,FModifyDate,FCreateDate,FCustTypeId,FSALDEPTID,FShortName,FTContact,FOFFICEPHONE,FMOBILE,FAX1,FADDRESS

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
		}
		/********** 查询物料End ************************/
	}

}
