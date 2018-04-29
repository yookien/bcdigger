package com.bcdigger.store.entity;

import java.util.Date;

import com.bcdigger.common.entity.BaseEntity;
/**
 * 门店表
* <p>Title: Store</p>  
* <p>Description:门店表 </p>  
* @author yookien
* @date 2018年4月29日
 */
public class Store extends BaseEntity {
	/*
	 * 门店编码
	 */
	private String  storeCode;
	//中文名称
	private String  chineseName;
	//电话
	private String  phone;
	//负责人手机
	private String mobile;
	//地址
	private String  address;
	//地区
	private String direction;
	//开业时间
	private String  openHour;
	//停业时间
	private String  closeHour;
	//
	private String   englishName;
	private String   englishAddress;
	//位置示意图
	private String   locationImage;
	//门店主图
	private String  storeImages;
	//门店所属省
	private String  storeProvince;
	//门店所属城市名
	private String   storeCity;
	//城市下的区域
	private String   cityArea;
	//是否在开业
	private int   isOpen;
	//百度位置
	private String   bmapPosition;
	//百度x位置
	private Double bmapPositionX;
	//百度y位置
	private Double   bmapPositionY;
	//搜索地址
	private String   searchUrl;
	//公交情况
	private String   bus;
	//地铁情况
	private String subway;
	//附近建筑
	private String  nearby;
	//邮箱
	private String email;
	//店面图片1
	private String  storeImage1;
	private String  storeImage2;
	private String  storeImage3;
	private String  storeImage4;
	//预约限制
	private int  appointLimit;
	//门店类型：0-自营店，1-写字楼店，2-商场店，3-加盟店
	private int  storeType;
	//是否预约
	private int isBooking;
	
	private Date openTime;//开业时间
	private Date addTime;
	private Date updateTime;
	
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getOpenHour() {
		return openHour;
	}
	public void setOpenHour(String openHour) {
		this.openHour = openHour;
	}
	public String getCloseHour() {
		return closeHour;
	}
	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getEnglishAddress() {
		return englishAddress;
	}
	public void setEnglishAddress(String englishAddress) {
		this.englishAddress = englishAddress;
	}
	public String getLocationImage() {
		return locationImage;
	}
	public void setLocationImage(String locationImage) {
		this.locationImage = locationImage;
	}
	public String getStoreImages() {
		return storeImages;
	}
	public void setStoreImages(String storeImages) {
		this.storeImages = storeImages;
	}
	public String getStoreProvince() {
		return storeProvince;
	}
	public void setStoreProvince(String storeProvince) {
		this.storeProvince = storeProvince;
	}
	public String getStoreCity() {
		return storeCity;
	}
	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}
	public String getCityArea() {
		return cityArea;
	}
	public void setCityArea(String cityArea) {
		this.cityArea = cityArea;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public String getBmapPosition() {
		return bmapPosition;
	}
	public void setBmapPosition(String bmapPosition) {
		this.bmapPosition = bmapPosition;
	}
	public Double getBmapPositionX() {
		return bmapPositionX;
	}
	public void setBmapPositionX(Double bmapPositionX) {
		this.bmapPositionX = bmapPositionX;
	}
	public Double getBmapPositionY() {
		return bmapPositionY;
	}
	public void setBmapPositionY(Double bmapPositionY) {
		this.bmapPositionY = bmapPositionY;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
	public String getSubway() {
		return subway;
	}
	public void setSubway(String subway) {
		this.subway = subway;
	}
	public String getNearby() {
		return nearby;
	}
	public void setNearby(String nearby) {
		this.nearby = nearby;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStoreImage1() {
		return storeImage1;
	}
	public void setStoreImage1(String storeImage1) {
		this.storeImage1 = storeImage1;
	}
	public String getStoreImage2() {
		return storeImage2;
	}
	public void setStoreImage2(String storeImage2) {
		this.storeImage2 = storeImage2;
	}
	public String getStoreImage3() {
		return storeImage3;
	}
	public void setStoreImage3(String storeImage3) {
		this.storeImage3 = storeImage3;
	}
	public String getStoreImage4() {
		return storeImage4;
	}
	public void setStoreImage4(String storeImage4) {
		this.storeImage4 = storeImage4;
	}
	public int getAppointLimit() {
		return appointLimit;
	}
	public void setAppointLimit(int appointLimit) {
		this.appointLimit = appointLimit;
	}
	public int getStoreType() {
		return storeType;
	}
	public void setStoreType(int storeType) {
		this.storeType = storeType;
	}
	public int getIsBooking() {
		return isBooking;
	}
	public void setIsBooking(int isBooking) {
		this.isBooking = isBooking;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
