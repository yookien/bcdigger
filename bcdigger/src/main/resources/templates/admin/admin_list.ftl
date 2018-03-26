
<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>系统操作员管理 </h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    
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
			<#list adminList as admin>
            <tr>          	
	            	<td>${admin_index+1}</td>
	            	<td>${admin.name}</td>
	            	<td>${admin.nickname}</td>
	            	<td>${admin.mobile}</td>
	            	<td>${admin.email}</td>
	            	<td><#if admin.state==1>正常<#else>停用</#if></td>
	            	<td><a class="fa fa-pencil-square" style="font-size:20px" title="修改"></a>
	            	<span style="margin-right:20px;margin-left:10px"></span>
	            	<a class="fa fa-close" style="font-size:20px;color:red" title="失效"></a></td>
            </tr>
            </#list>n
        </tbody>
    </table>
 </div>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/pages.ftl">
 
                </div>
              </div>