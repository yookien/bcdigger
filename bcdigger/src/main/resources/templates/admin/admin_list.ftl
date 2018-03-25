
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
	            	<td>${admin.id}</td>
	            	<td>${admin.name}</td>
	            	<td>${admin.nickname}</td>
	            	<td>${admin.mobile}</td>
	            	<td>${admin.email}</td>
	            	<td>${admin.state}</td>
	            	<td>${admin.state}</td>
            </tr>
            </#list>
        </tbody>
    </table>
 </div>
                </div>
              </div>