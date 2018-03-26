
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th>序号</th>
          	<th>角色名称</th>
          	<th>角色描述</th>
          	<th>状态</th>
          	<th>添加时间</th>
          	<th>更新时间</th>
          	<th>操作</th>
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as role>
				<tr>
					<td>${(role.id)!1}</td>
                  	<td>${(role.roleName)!}</td>
                  	<td>${(role.roleDesc)!}</td>
                  	<td><#if role.state==1>正常<#else>停用</#if></td>
                  	<td><#if (role.addTime)??>${role.addTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                  	<td><#if (role.updateTime)??>${role.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                  	<td>
                  		<a href="###" onclick="editAdminRole(${(role.id)!0})" name="editRoleBt" data-toggle="modal" data-target="#myModal" roleId="${(role.id)!}" >编辑</a>
				  	</td>
                </tr>
		 	</#list>
		<#else>
			<tr>
              <td colspan="10" style="text-align: center">尚未添加任何角色信息</td>
            </tr>
	 	</#if>
 	</tbody>
 </table>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/ajax_pages.ftl">
