
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th>序号</th>
          	<th>部门名称</th>
          	<th>负责人</th>
          	<th>显示顺序</th>
          	<th>状态</th>
          	<th>添加时间</th>
          	<th>更新时间</th>
          	<th>操作</th>
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as department>
				<tr>
					<td>${(department.id)!1}</td>
                  	<td>${(department.name)!}</td>
                  	<td>${(department.principal)!}</td>
                  	<td>${(department.displayOrder)!}</td>
                  	<td><#if department.state==1>正常<#else>停用</#if></td>
                  	<td><#if (department.addTime)??>${department.addTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                  	<td><#if (department.updateTime)??>${department.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                  	<td>
                  		<a href="###" onclick="editDepartment(${(department.id)!0})" name="editRoleBt" data-toggle="modal" data-target="#myModal" roleId="${(department.id)!}" >编辑</a>
				  	</td>
                </tr>
		 	</#list>
		<#else>
			<tr>
              <td colspan="10" style="text-align: center">尚未添加任何部门信息</td>
            </tr>
	 	</#if>
 	</tbody>
 </table>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/ajax_pages.ftl">
