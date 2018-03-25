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
            </tr>
        </thead>
		<tbody>
            <tr>
            	<#assign admins="${pageInfo.list}" />
            	<#list admins as admin>
	            	<td>${admin.id}</td>
	            	<td>${admin.name}</td>
	            	<td>${admin.nickname}</td>
	            	<td>${admin.mobile}</td>
	            	<td>${admin.email}</td>
	            	<td>${admin.state}</td>
				</#list>
            </tr>
        </tbody>
    </table>
 </div>