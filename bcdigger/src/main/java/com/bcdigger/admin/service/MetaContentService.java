package com.bcdigger.admin.service;

import java.util.List;

import com.bcdigger.admin.entity.MetaContent;
import com.bcdigger.common.page.PageInfo;

public interface MetaContentService {

	public MetaContent getMetaContent(int id);

	public MetaContent getMetaContentInfoById(MetaContent metaContent);

	public MetaContent getMetaContent(MetaContent metaContent);

	public PageInfo<MetaContent> getMetaContents(String name, PageInfo pageInfo);

	public PageInfo<MetaContent> getMetaContents(MetaContent metaContent, PageInfo pageInfo);

	public int addMetaContent(MetaContent metaContent);

	public int updateMetaContent(MetaContent metaContent);

	public void addOrUpdateMetaContent(MetaContent metaContent);
	
	public List<MetaContent> listMetaContentByCondation(MetaContent metaContent);

}
