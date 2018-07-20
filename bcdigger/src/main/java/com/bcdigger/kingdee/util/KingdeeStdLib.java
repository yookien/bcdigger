package com.bcdigger.kingdee.util;


import org.springframework.beans.factory.annotation.Value;

/**
 * 
 * ClassName: KingdeeStdLib
 * @Description: 金蝶系统常量库
 * @author liubei
 * @date 2017年6月27日
 */
public class KingdeeStdLib {
	/**
	 * 金蝶服务器域名
	 */
	@Value("${KINGDEE_BASE_PATH}")  
	private static final String KINGDEE_BASE_PATH = PropUtil.getProp().getProperty("KINGDEE_BASE_PATH");

	/**
	 * 用户验证
	 */
	public static final String KINGDEE_VALIDATE_USER_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";
	
	/**
	 * 保存
	 */
	public static final String KINGDEE_SAVE_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc";
	
	/**
	 * 暂存
	 */
	public static final String KINGDEE_DRAFT_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Draft.common.kdsvc";
	
	/**
	 * 推送
	 */
	public static final String KINGDEE_PUSH_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Push.common.kdsvc";
	
	/**
	 * 审核
	 */
	public static final String KINGDEE_AUDIT_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc";
	/**
	 * 删除
	 */
	public static final String KINGDEE_DELETE_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Delete.common.kdsvc";
	/**
	 * 反审核
	 */
	public static final String KINGDEE_UNAUDIT_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.UnAudit.common.kdsvc";
	/**
	 * 提交
	 */
	public static final String KINGDEE_SUBMIT_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Submit.common.kdsvc";
	/**
	 * 查看
	 */
	public static final String KINGDEE_VIEW_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.View.common.kdsvc";
	/**
	 * 状态转换
	 */
	public static final String KINGDEE_STATUS_CONVERT_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.StatusConvert.common.kdsvc";
	/**
	 * 批量查询
	 */
	public static final String KINGDEE_EXCCUTE_BILL_QUERY_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc";
	/**
	 * 批量保存
	 */
	public static final String KINGDEE_BATCH_SAVE_URL=KINGDEE_BASE_PATH+"/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.BatchSave.common.kdsvc";
	
	/**
	 * 金蝶登录sessionkey
	 */
	public static String sessionkey = "kdservice-sessionid";
	/**
	 * 金蝶登录aspnetsessionkey
	 */
	public static String aspnetsessionkey = "ASP.NET_SessionId";
	
	/***
	 *  登录acctID
	 */
	public static final String acctID = PropUtil.getProp().getProperty("KINGDEE_ACC_ID");
	/**
	 * 用户名
	 */
	public static final String userName = PropUtil.getProp().getProperty("KINGDEE_USER_NAME");
	/**
	 * 密码
	 */
	public static final String password = PropUtil.getProp().getProperty("KINGDEE_PASSWORD");
	/**
	 * lcid（字符编码）
	 */
	public static final String lcid = PropUtil.getProp().getProperty("KINGDEE_LCID");
	/**
	 * 组织编码
	 */
	public static final String saleOrgId = PropUtil.getProp().getProperty("KINGDEE_SALE_ORG_ID");
	
}
