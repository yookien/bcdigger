package com.bcdigger.goods.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.order.entity.GoodsOrder;

@Repository("goodsDao")
public class GoodsDao extends BaseDaoImpl<Goods> {

	@Autowired
	private SqlSession sqlSession;
	public Goods getGoodsInfoByCondation(Goods goods) throws Exception {
		try{
			goods = sqlSession.selectOne("getGoodsInfoByCondation", goods);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		return goods;
	}
}
