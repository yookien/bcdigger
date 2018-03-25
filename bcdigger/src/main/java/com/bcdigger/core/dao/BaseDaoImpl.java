package com.bcdigger.core.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcdigger.common.entity.BaseEntity;
import com.bcdigger.common.exceptions.BizException;
import com.bcdigger.common.page.PageInfo;
import com.bcdigger.core.mybatis.interceptor.ExecutorInterceptor;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @描述: 数据访问层基础支撑类.
 * @作者: yookien .
 * @创建时间: 2013-7-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param <T>
 */
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

	protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_DELETE_BY_ID = "deleteById";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 *//*
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}
	*/

	/*@Autowired
	private DataSource dataSource;*/
	@Autowired
	private SqlSession sqlSession;
	
	/*public BaseDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
    }*/
	
	public long insert(T t) {

		if (t == null)
			throw new RuntimeException("T is null");

		int result = sqlSession.insert(getStatement(SQL_INSERT), t);

		if (result <= 0)
			throw BizException.DB_INSERT_RESULT_0;

		if (t != null && t.getId() != null && result > 0)
			return t.getId();

		return result;
	}

	public long insert(List<T> list) {

		if (list == null || list.size() <= 0)
			return 0;

		int result = sqlSession.insert(getStatement(SQL_BATCH_INSERT), list);

		if (result <= 0)
			throw BizException.DB_INSERT_RESULT_0;

		return result;
	}

	public long update(T t) {
		if (t == null)
			throw new RuntimeException("T is null");

		int result = sqlSession.update(getStatement(SQL_UPDATE), t);

		if (result <= 0)
			throw BizException.DB_UPDATE_RESULT_0;

		return result;
	}

	public long update(List<T> list) {

		if (list == null || list.size() <= 0)
			return 0;

		int result = 0;

		for (int i = 0; i < list.size(); i++) {
			this.update(list.get(i));
			result += 1;
		}

		if (result <= 0)
			throw BizException.DB_UPDATE_RESULT_0;

		return result;
	}

	public T getById(int id) {
		return sqlSession.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	public long deleteById(int id) {
		return (long) sqlSession.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	public PageInfo listPage(PageInfo pageInfo, Map<String, Object> paramMap, String sqlId) {

		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<Object> list = sqlSession.selectList(getStatement(sqlId),paramMap);
		
		return new PageInfo(list);
	}

	public PageInfo listPage(PageInfo pageInfo, Map<String, Object> paramMap) {

		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<Object> list = sqlSession.selectList(SQL_LIST_PAGE, paramMap);
		pageInfo.setList(list);
		return pageInfo;
		
		// 是否统计当前分页条件下的数据：1:是，其他为否
		/*Object isCount = paramMap.get("isCount");
		if (isCount != null && "1".equals(isCount.toString())) {
			Map<String, Object> countResultMap = sqlSession.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM), paramMap);
			return new PageInfo(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), list, countResultMap);
		} else {
			return new PageInfo(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), list);
		}*/
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> listBy(Map<String, Object> paramMap) {
		return (List) this.listBy(paramMap, SQL_LIST_BY);
	}

	public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		return sqlSession.selectList(getStatement(sqlId), paramMap);
	}

	@SuppressWarnings("unchecked")
	public T getBy(Map<String, Object> paramMap) {
		return (T) this.getBy(paramMap, SQL_LIST_BY);
	}

	public Object getBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null || paramMap.isEmpty())
			return null;

		return sqlSession.selectOne(getStatement(sqlId), paramMap);
	}

	public String getStatement(String sqlId) {

		String name = this.getClass().getName();

		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

		return sb.toString();
	}

	/**
	 * 根据序列名称,获取序列值
	 */
	public String getSeqNextValue(String seqName) {
		boolean isClosedConn = false;
		// 获取当前线程的连接
		Connection connection = this.sqlSession.getConnection();
		// 获取Mybatis的SQLRunner类
		SqlRunner sqlRunner = null;
		try {
			// 要执行的SQL
			String sql = "SELECT  FUN_SEQ('" + seqName.toUpperCase() + "')";
			// 如果状态为关闭,则需要从新打开一个连接
			if (connection.isClosed()) {
				//connection = sqlSessionFactory.openSession().getConnection();
				connection = sqlSession.getConnection();
				isClosedConn = true;
			}
			sqlRunner = new SqlRunner(connection);
			Object[] args = {};
			// 执行SQL语句
			Map<String, Object> params = sqlRunner.selectOne(sql, args);
			for (Object o : params.values()) {
				return o.toString();
			}
			return null;
		} catch (Exception e) {
			throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR.newInstance("获取序列出现错误!序列名称:{%s}", seqName);
		} finally {
			if (isClosedConn) {
				sqlRunner.closeConnection();
			}
		}
	}
}
