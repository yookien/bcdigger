package com.bcdigger.kingdee.util;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * 
 * ClassName: KingdeeUtil
 * @Description: 金蝶接口登录信息处理公共类
 * @author liubei
 * @date 2017年5月31日
 */
@SuppressWarnings("deprecation")
public class KingdeeUtil {
	
	@SuppressWarnings("resource")
	public static AccessToken getAccessToken() throws Exception {
		//读取已保存的登录信息
		AccessToken accessToken = ReadWriteKingdeeToken.readXML();
		if(accessToken!=null && !KingdeeUtil.tokenIsExpire(accessToken)){
			if(accessToken.getSessionValue()==null || "".equals(accessToken.getSessionValue())
					|| accessToken.getAspnetsessionValue()==null || "".equals(accessToken.getAspnetsessionValue())){
				System.out.println("获取登陆信息为空");
			}
			return accessToken;
		}
		// 重新拉取登录信息
		HttpClient httpclient = new DefaultHttpClient();
		// 登录，校验用户的API接口地址
		URI uri = new URI(KingdeeStdLib.KINGDEE_VALIDATE_USER_URL);
		HttpPost method = new HttpPost(uri);
		try {
			JSONObject jsonResult = null;
			// 登录请求参数
			JSONObject jsonParam=new JSONObject();
			jsonParam.put("acctID", KingdeeStdLib.acctID);
			jsonParam.put("userName", KingdeeStdLib.userName);
			jsonParam.put("password", KingdeeStdLib.password);
			jsonParam.put("lcid", KingdeeStdLib.lcid);
			System.out.println("登录参数:"+jsonParam.toString());
			//String jsonParam = "{\"acctID\":\"592657ff80cddb\",\"userName\":\"administrator\",\"password\":\"888888\",\"lcid\":2052}";
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			method.setEntity(entity);
			HttpResponse result = httpclient.execute(method);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				System.out.println("登录返回结果：" + str );
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(str);
				
				// 判断登录是否成功
				if (jsonResult.getInteger("LoginResultType") == 1 || jsonResult.getInteger("LoginResultType") == -4) {
					System.out.println("登录成功！");
					// 获取Cookie
					Header[] headers = result.getHeaders("Set-Cookie");
					// 登录成功返回的登录session信息，保存下来，下面再调用接口的时候传给服务端
					accessToken = new AccessToken();
					accessToken.setCreateTime(new Date().getTime()/1000);// 设置创建时间
					for (int i = 0; i < headers.length; i++) {
						Header header = headers[i];
						String headerValue = header.getValue();
						if (headerValue.trim().startsWith(KingdeeStdLib.sessionkey)) {
							int endIndex = headerValue.indexOf(';');
							String sessionValue = headerValue.substring(20, endIndex);
							accessToken.setSessionValue(sessionValue);
						} else if (headerValue.trim().startsWith(KingdeeStdLib.aspnetsessionkey)) {
							int endIndex = headerValue.indexOf(';');
							String aspnetsessionValue = headerValue.substring(18, endIndex);
							accessToken.setAspnetsessionValue(aspnetsessionValue);
						}
					}
					ReadWriteKingdeeToken.writeXML(accessToken);
				} else {// 登录失败，不继续
					System.out.println("登录失败！");
					return null;
				}
			} else {
				System.out.println("登录异常！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + e);
			return null;
		}finally{  
			 method.releaseConnection();
		}
		if(accessToken.getSessionValue()==null || "".equals(accessToken.getSessionValue())
				|| accessToken.getAspnetsessionValue()==null || "".equals(accessToken.getAspnetsessionValue())){
			System.out.println("获取登陆信息为空");
		}
		return accessToken;
	}

	public static Boolean tokenIsExpire(AccessToken accessToken) {
		if (accessToken == null || accessToken.getCreateTime() == 0) {
			return true;
		}
		long now = new Date().getTime() / 1000;
		long ct = accessToken.getCreateTime();
		return ct + 600 < now ? true : false;
	}
	
	public static JSONObject getFNumber(String id){
		JSONObject jsonFN=new JSONObject();
		jsonFN.put("FNumber",id);
		return jsonFN;
	}
	
   /**
    * 得到年月日 小时 分秒
    * @param date
    * @return
    */
   public static String getDateForString(Date date){
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String dateString=sdf.format(date);   
       return dateString;
   }
   /**
    * 得到年月日
    * @Description:
    * @param date
    * @return
    * @return String
    * @author liubei
    * @date 2018年7月19日
    */
   public static String dateToString(Date date){
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
       String dateString=sdf.format(date);   
       return dateString;
   }
	
}
