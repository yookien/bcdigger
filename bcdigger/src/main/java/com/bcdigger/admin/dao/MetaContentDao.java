package com.bcdigger.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcdigger.admin.entity.MetaContent;
import com.bcdigger.core.dao.BaseDaoImpl;

@Repository("metaContentDao")
public class MetaContentDao extends BaseDaoImpl<MetaContent> {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * @Description: 根据金蝶内码id、元数据类型 更新元数据
	 * @param metaContent
	 * @return
	 * @return int
	 * @author liubei
	 * @date 2018年7月20日
	 */
	public int updateBykingdeeCustId(MetaContent metaContent) {
		int result = 0;
		try {
			if (metaContent == null) {
				return result;
			}
			result = sqlSession.update("updateBykingdeeCustId", metaContent);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	public MetaContent getByCondation(MetaContent metaContent) {
		try {
			List<MetaContent> metaContentList = sqlSession.selectList("getByCondation", metaContent);
			if (metaContentList != null && metaContentList.size() >= 1) {
				metaContent = metaContentList.get(0);
			} else {
				metaContent = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			metaContent = null;
		}
		return metaContent;
	}
	
	public List<MetaContent> listMetaContentByCondation(MetaContent metaContent) {
		List<MetaContent> metaContentList = null;
		try {
			metaContentList = sqlSession.selectList("getByCondation", metaContent);
		} catch (Exception e) {
			e.printStackTrace();
			metaContentList = null;
		}
		return metaContentList;
	}
	
}
