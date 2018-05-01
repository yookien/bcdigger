package com.bcdigger.goods.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.goods.entity.Goods;

@Repository("goodsDao")
public class GoodsDao extends BaseDaoImpl<Goods> {

	@Autowired
	private SqlSession sqlSession;

}
