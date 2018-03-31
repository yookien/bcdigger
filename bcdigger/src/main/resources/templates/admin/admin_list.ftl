
                    
 	 <table id="datatable" class="table table-striped table-bordered">
		<thead>
        	<tr>
            	<th>序号</th>
            	<th>登录名</th>
                <th>昵称</th>
                <th>手机</th>
                <th>邮箱</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
		<tbody>
			<#if pageInfo??  >
			<#list pageInfo.list as admin>
            <tr>          	
	            	<td>${admin_index+1}</td>
	            	<td>${(admin.name)!}</td>
	            	<td>${(admin.nickname)!}</td>
	            	<td>${(admin.mobile)!}</td>
	            	<td>${(admin.email)!}</td>
	            	<td><#if (admin.state)?? && admin.state==1>正常<#else>停用</#if></td>
	            	<td><a href="###" onclick="javascript:editAdmin(${(admin.id)!0});" data-toggle="modal" data-target="#adminModal" class="fa fa-pencil-square" style="font-size:20px" title="修改操作员信息"></a>
		            	<span style="margin-right:20px;margin-left:10px"></span>
		            	<a href="###" onclick="javascript:disableAdmin(${(admin.id)!});" class="fa fa-close" style="font-size:20px;color:red" title="失效"></a>
		            	
		            	<span style="margin-right:20px;margin-left:10px"></span>
		            	<a href="###" onclick="ajaxAdminRoleRef(${(admin.id)!})">权限</a>
		            	<button style="display:none;" type="button" class="btn btn-primary" data-toggle="modal" data-target="#adminRoleModal" id="btn_${(admin.id)!}">权限</button>
		            	
	            	</td>
            </tr>
            </#list>
            </#if>
        </tbody>
    </table>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/ajax_pages.ftl">

              