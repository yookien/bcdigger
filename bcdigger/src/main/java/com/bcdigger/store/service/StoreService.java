package com.bcdigger.store.service;

import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.store.entity.Store;
/**
 * 门店维护Service
* <p>Title: StoreService</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年4月29日
 */
public interface StoreService {
	/**
	 * 添加门店表
	 * <p>Title: addStore</p>  
	 * <p>Description: </p>  
	 * @param store
	 * @return
	 */
	public int addStore(Store store);
	/**
	 * 更新门店信息
	 * <p>Title: updateStore</p>  
	 * <p>Description: </p>  
	 * @param store
	 * @return
	 */
	public int updateStore(Store store);
	/**
	 * 根据id查找门店信息
	 * <p>Title: getStoreById</p>  
	 * <p>Description: </p>  
	 * @param id
	 * @return
	 */
	public Store getStoreById(int id);
	/**
	 * 根据门店信息分页查找门店
	 * <p>Title: getStores</p>  
	 * <p>Description: </p>  
	 * @param store
	 * @param pageInfo
	 * @return
	 */
	public PageInfo<Store> getStores(Store store,PageInfo pageInfo);
}
