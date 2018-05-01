package com.bcdigger.goods.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.goods.entity.Goods;
import com.bcdigger.goods.entity.GoodsStore;

@Repository("goodsStoreDao")
public class GoodsStoreDao extends BaseDaoImpl<GoodsStore> {

	@Autowired
	private SqlSession sqlSession;

}
