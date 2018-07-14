package com.bcdigger.kingdee.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * ClassName: ReadWriteKingdeeToken
 * @Description: 读取、写入accessToken工具类
 * @author liubei
 * @date 2017年5月31日
 */
public class ReadWriteKingdeeToken {
	private static Logger log = Logger.getLogger(ReadWriteKingdeeToken.class);
	// 指定文件路径和名称
	private static String path = "";
	private static File filename = null;

	static {
		try {
			path = ReadWriteKingdeeToken.class.getClassLoader().getResource("kingdeeAccessToken.xml").toURI().getPath();
			filename = new File(path);
		} catch (URISyntaxException e) {
			log.error("读取文件失败！");
			e.printStackTrace();
		}
	}

	public static void writeXML(AccessToken accessToken) throws Exception {
		InputStream inputStream = new FileInputStream(filename);
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();

		Element session_value = root.element("session_value");
		session_value.setText(accessToken.getSessionValue());

		Element aspnet_session_value = root.element("aspnet_session_value");
		aspnet_session_value.setText(accessToken.getAspnetsessionValue());

		Element createTime = root.element("createTime");
		createTime.setText(accessToken.getCreateTime() + "");

		XMLWriter writer = new XMLWriter(new FileOutputStream(filename));
		writer.write(document);
		inputStream.close();
		writer.close();
		inputStream = null;
		writer = null;
	}

	public static AccessToken readXML() throws Exception {
		AccessToken accessToken = new AccessToken();
		// 从request中取得输入流
		InputStream inputStream = new FileInputStream(filename);
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		String session_value = root.elementText("session_value");
		String aspnet_session_value = root.elementText("aspnet_session_value");
		String createTime = root.elementText("createTime");
		try {
			if (createTime != null && !"".equals(createTime)
					&& ((session_value != null && !"".equals(session_value)) 
					    || (aspnet_session_value != null && !"".equals(aspnet_session_value)))) {
				accessToken.setSessionValue(session_value);
				accessToken.setCreateTime(Long.parseLong(createTime));
				accessToken.setAspnetsessionValue(aspnet_session_value);
			} else {
				accessToken = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			accessToken = null;
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return accessToken;
	}

}
