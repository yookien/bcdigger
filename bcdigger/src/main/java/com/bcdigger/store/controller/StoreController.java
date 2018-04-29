package com.bcdigger.store.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.admin.entity.SysMenu;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;
import com.bcdigger.store.entity.Store;
import com.bcdigger.store.service.StoreService;

/**
 * 门店controller
* <p>Title: StoreController</p>  
* <p>Description: </p>  
* @author yookien
* @date 2018年4月29日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/store")
@AdminAuth  // 门店管理全部需要登录鉴权
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	@RequestMapping(value ="/addStore",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addStore(Store store){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(store==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			if(StringUtils.isBlank(store.getChineseName())){
				map.put("result", -2);// 菜单名称不能为空
				return map;
			}
			Date now=new Date();
			store.setAddTime(now);
			store.setUpdateTime(now);
			int result = storeService.addStore(store);
			map.put("result", result);//登录成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Description: 根据id查询门店信息
	 * @param id
	 * @return Map<String,Object>  
	 * @throws
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getStore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getStore(Store store) {
		Map<String, Object> map = new HashMap<>();
		try{
			if( store==null || store.getId()<=0){
				return null;
			}
			store = storeService.getStoreById(store.getId());
			map.put("result", 1);//登录成功
			map.put("store", store);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 更新门店信息
	 * @param store
	 * @return Map<String,Object>
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateStore",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStore(Store store){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(store==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			Date now=new Date();
			store.setUpdateTime(now);
			int result = storeService.updateStore(store);
			map.put("result", result);//更新成功
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description:打开门店管理首页
	 * @param request
	 * @return
	 * @return String
	 * @date 2018年3月26日
	 */
	@RequestMapping(value ="/storeIndex")
	public String sysMenusIndex() {
		return "/store/store_index";
	}

	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getStores",method={RequestMethod.GET,RequestMethod.POST})
	public String getStores(Store store, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<Store> storePages = storeService.getStores(store, pageInfo);
			map.addAttribute("pageInfo", storePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/store/store_list";
	}

}
