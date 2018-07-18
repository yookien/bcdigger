package com.bcdigger.store.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.store.dao.StoreDao;
import com.bcdigger.store.entity.Store;
import com.bcdigger.store.service.StoreService;

/**
 * 门店service实现类
* <p>Title: StoreServiceImpl</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年4月29日
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;
	
	@Override
	public int addStore(Store store) {
		return storeDao.insert(store);
	}

	@Override
	public int updateStore(Store store) {
		return storeDao.update(store);
	}

	@Override
	public Store getStoreById(int id) {
		return storeDao.getById(id);
	}

	@Override
	public PageInfo<Store> getStores(Store store, PageInfo pageInfo) {
		return storeDao.findStores(pageInfo, store);
	}
	
	public void addOrUpdateStore(Store store){
		try{
			// 校验参数
			if(store==null || store.getStoreCode()==null || store.getStoreCode().equals("")){
				return;
			}
			// 根据货号查询商品是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeCode", store.getStoreCode());
			Store storeTemp = storeDao.getBy(params);
			if( storeTemp == null || storeTemp.getId() <= 0 ){
				// 添加商品
				storeDao.insert(store);
			} else {
				store.setId(storeTemp.getId());
				// 更新商品
				storeDao.update(store);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
