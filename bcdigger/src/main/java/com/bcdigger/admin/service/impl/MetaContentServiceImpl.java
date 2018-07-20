package com.bcdigger.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcdigger.admin.dao.MetaContentDao;
import com.bcdigger.admin.entity.MetaContent;
import com.bcdigger.admin.service.MetaContentService;
import com.bcdigger.common.page.PageInfo;

@Service("metaContentService")
public class MetaContentServiceImpl implements MetaContentService {

	@Autowired
	private MetaContentDao metaContentDao;

	@Override
	public MetaContent getMetaContent(int id) {

		MetaContent MetaContent = metaContentDao.getById(id);
		return MetaContent;
	}

	public MetaContent getMetaContent(MetaContent metaContent) {
		try {
			if (metaContent == null) {
				return null;
			}
			metaContent = metaContentDao.getByCondation(metaContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metaContent;
	}

	public MetaContent getMetaContentInfoById(MetaContent metaContent) {
		try {
			if (metaContent == null || metaContent.getId() <= 0) {
				return null;
			}
			metaContent = metaContentDao.getById(metaContent.getId());
		} catch (Exception e) {
			e.printStackTrace();
			metaContent = null;
		}
		return metaContent;
	}

	@Override
	public PageInfo<MetaContent> getMetaContents(String name, PageInfo pageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		return metaContentDao.listPage(pageInfo, params);
	}

	@Override
	public PageInfo<MetaContent> getMetaContents(MetaContent metaContent, PageInfo pageInfo) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", metaContent.getName());
		if (StringUtils.isBlank(metaContent.getName()))
			params.put("like", 0);
		else
			params.put("like", 1);
		return metaContentDao.listPage(pageInfo, params);
	}

	/**
	 * 插入操作 用户
	 */
	public int addMetaContent(MetaContent metaContent) {
		return metaContentDao.insert(metaContent);
	}

	/**
	 * 更新操作 用户信息
	 */
	public int updateMetaContent(MetaContent metaContent) {
		return metaContentDao.update(metaContent);
	}

	public void addOrUpdateMetaContent(MetaContent metaContent) {
		try{
			if ( metaContent == null || metaContent.getKingdeeCustId() <= 0 ){
				return;
			}
			// 查询数据是否存在
			MetaContent metaContentTemp=metaContentDao.getByCondation(metaContent);
			if( metaContentTemp != null 
					&& metaContentTemp.getKingdeeCustId() == metaContent.getKingdeeCustId()){
				metaContent.setDefineId(metaContentTemp.getDefineId());
				// 已存在 更新
				metaContentDao.updateBykingdeeCustId(metaContent);
			} else {
				// 不存在 新增
				metaContentDao.insert(metaContent);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<MetaContent> listMetaContentByCondation(MetaContent metaContent){
		List<MetaContent> metaContentList=null;
		try{
			metaContentList = metaContentDao.listMetaContentByCondation(metaContent);
		}catch(Exception e){
			e.printStackTrace();
		}
		return metaContentList;
	}

}
