package com.bcdigger.kingdee.util;

/**
 * 金蝶通用接口登录凭证
 * 
 * @author
 * @date
 */
public class AccessToken {

	private String sessionValue = "";
	private String aspnetsessionValue = "";

	// 创建时间
	private long createTime;

	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue;
	}

	public String getAspnetsessionValue() {
		return aspnetsessionValue;
	}

	public void setAspnetsessionValue(String aspnetsessionValue) {
		this.aspnetsessionValue = aspnetsessionValue;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

}