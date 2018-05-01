package com.bcdigger.goods.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.goods.entity.GoodsPurchase;

@Repository("goodsPurchaseDao")
public class GoodsPurchaseDao extends BaseDaoImpl<GoodsPurchase> {

	@Autowired
	private SqlSession sqlSession;

}
