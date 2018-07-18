package com.bcdigger.goods.controller;

import java.util.HashMap;
import java.util.List;
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
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsInstoreBiz;
import com.bcdigger.goods.service.GoodsInstoreService;
import com.bcdigger.goods.service.GoodsService;
import com.bcdigger.order.entity.GoodsOrderItem;
import com.bcdigger.order.service.GoodsOrderItemService;

/**
 * ClassName: GoodsController
 * @Description: 商品信息
 * @author liubei
 * @date 2018年7月18日
 */
@Controller	
//@RestController  //只返回值，不放回页面渲染
@EnableAutoConfiguration
@RequestMapping("/goods")
@AdminAuth  // 门店管理全部需要登录鉴权
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;

	/**
	 * @Description: 分页查询商品信息
	 * @param GoodsInstore
	 * @param pageInfo
	 * @param map
	 * @return
	 * @return String
	 * @author liubei
	 * @date 2018年7月18日
	 */
	@RequestMapping(value ="/getGoodsInfo",method={RequestMethod.GET,RequestMethod.POST})
	public String getGoodsInfo(Goods goods, PageInfo<Goods> pageInfo,ModelMap map) {
		try{
			if(pageInfo==null){
				pageInfo=new PageInfo<Goods>();
			}
			//设置每页显示个数
			pageInfo.setPageSize(10);
			PageInfo<Goods> goodsPages = goodsService.getGoods(goods, pageInfo);
			map.addAttribute("pageInfo", goodsPages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/goods/goods_list";
	}
	
}
