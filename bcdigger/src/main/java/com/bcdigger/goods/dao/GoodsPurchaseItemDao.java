package com.bcdigger.goods.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.goods.entity.GoodsInstore;
import com.bcdigger.goods.entity.GoodsPurchaseItem;

@Repository("goodsPurchaseItemDao")
public class GoodsPurchaseItemDao extends BaseDaoImpl<GoodsPurchaseItem> {

	@Autowired
	private SqlSession sqlSession;

}
