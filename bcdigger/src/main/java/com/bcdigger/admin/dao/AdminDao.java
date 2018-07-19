package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.Admin;
import com.bcdigger.common.exceptions.BizException;
import com.bcdigger.core.dao.BaseDaoImpl;
import com.bcdigger.order.entity.GoodsOrderItem;

@Repository("adminDao")
public class AdminDao extends BaseDaoImpl<Admin>{
	
	@Autowired
	private SqlSession sqlSession;
	/**
	 * 更新操作员密码
	 * @param admin
	 * @return
	 */
	public int updatePassword(Admin admin) {
		if (admin == null)
			throw new RuntimeException("Admin is null");

		int result = sqlSession.update("updatePassword", admin);

		if (result <= 0)
			throw BizException.DB_UPDATE_RESULT_0;

		return result;
	}
	
	public Admin getAdminInfoById(Admin admin) {
		try{
			admin= sqlSession.selectOne("getAdminInfoById", admin);
		}catch(Exception e){
			e.printStackTrace();
			admin = null;
		}
		return admin;
	}
	
}
