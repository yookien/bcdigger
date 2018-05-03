package com.bcdigger.goods.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.annotation.AdminAuth;
import com.bcdigger.goods.entity.GoodsStore;
import com.bcdigger.goods.service.GoodsStoreService;

/**
 * 
 * ClassName: GoodsGoodsStoreController
 * @Description: 库存Controller
 * @author ipui
 * @date 2018年5月3日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/goods")
@AdminAuth  // 门店管理全部需要登录鉴权
public class GoodsStoreController {
	
	@Autowired
	private GoodsStoreService goodsStoreService;
	
	@RequestMapping(value ="/addGoodsStore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> addGoodsStore(GoodsStore goodsStore){
		Map<String, Object> map = new HashMap<>();  
		try{
			int result = goodsStoreService.addGoodsStore(goodsStore);
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
	@RequestMapping(value ="/getGoodsStore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getGoodsStore(GoodsStore goodsStore) {
		Map<String, Object> map = new HashMap<>();
		try{
			if( goodsStore==null || goodsStore.getId()<=0){
				return null;
			}
			goodsStore = goodsStoreService.getGoodsStore(goodsStore.getId());
			map.put("result", 1);//登录成功
			map.put("GoodsStore", goodsStore);
		}catch(Exception e){
			map.put("result", 0);//系统异常
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 更新门店信息
	 * @param GoodsStore
	 * @return Map<String,Object>
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/updateGoodsStore",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> updateGoodsStore(GoodsStore goodsStore){
		Map<String, Object> map = new HashMap<>();  
		try{
			// 参数校验，待完善
			if(goodsStore==null){
				map.put("result", -1);// 参数为空
				return map;
			}
			int result = goodsStoreService.updateGoodsStore(goodsStore);
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
	@RequestMapping(value ="/goodsGtoreIndex")
	public String sysMenusIndex() {
		return "/goods/goodsStore_index";
	}

	/**
	 * @Description: 分页查询菜单信息
	 * @param pageNum
	 * @return Map<String,Object>  
	 * @date 2018年3月25日
	 */
	@RequestMapping(value ="/getGoodsStores",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsStores(GoodsStore goodsStore, PageInfo pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			
			PageInfo<GoodsStore> goodsStorePages = goodsStoreService.getGoodsStore(goodsStore, pageInfo);
			map.addAttribute("pageInfo", goodsStorePages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/goods/goodsStore_list";
	}

}
