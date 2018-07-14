package com.bcdigger.kingdee.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * ClassName: PropUtil
 * 
 * @Description: 读取配置文件的工具类
 * @author liubei
 * @date 2017年5月31日
 */
public class PropUtil {
	private static Logger log = Logger.getLogger(PropUtil.class);

	private final static Properties prop = new Properties();

	static {
		InputStream is = null;
		try {
			is = PropUtil.class.getClassLoader().getResourceAsStream("systemConfig.properties");
			prop.load(is);
		} catch (IOException e) {
			log.error("读取配置文件失败！");
		} finally {
			// 关闭资源
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getProp() {
		return prop;
	}

}
