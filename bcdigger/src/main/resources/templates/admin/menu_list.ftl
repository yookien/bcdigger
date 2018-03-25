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
		        	<th>权限logo</th>
		          	<th>权限名称</th>
		          	<th>权限URL</th>
		          	<th>权限描述</th>
		          	<th>状态</th>
		          	<th>排序值</th>
		          	<th>添加时间</th>
		          	<th>更新时间</th>
		          	<th>操作</th>
		        </tr>
		      </thead>
		      <tbody>
				<#if menuList?? && (menuList?size>0) >
					<#list menuList as menu>
						<tr>
							<td>${(menu.id)!1}</td>
							<td>${(menu.menuLogoUrl)!}</td>
	                      	<td>${(menu.menuName)!}</td>
	                      	<td>${(menu.menuUrl)!}</td>
	                      	<td>${(menu.menuDesc)!}</td>
	                      	<td><#if menu.state==1>正常<#else>停用</#if></td>
	                      	<td>${(menu.displayOrder)!}</td>
	                      	<td><#if (menu.addTime)??>${menu.addTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
	                      	<td><#if (menu.updateTime)??>${menu.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
	                      	<td>
	                      		<a href="#" name="editRoleBt" roleId="${(menu.id)!}" >编辑</a>
								<span style="margin:0 1px">|</span>
								<a href="#" name="viewRoleBt" roleId="${(menu.id)!}" >查看</a>
						  	</td>
	                    </tr>
				 	</#list>
				<#else>
					<tr>
	                  <td colspan="10" style="text-align: center">尚未添加任何权限信息</td>
	                </tr>
			 	</#if>
		 	</tbody>
		 </table>
		</div>
		<!-- 分页  -->
		<div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate">
		</div>
    </div>
</div>	
<script>
     /**$('#datatable_paginate').bootstrapPaginator({
     	currentPage: 1,//当前的请求页面。
      	totalPages: 20,//一共多少页。
      	size:"normal",//应该是页眉的大小。
      	bootstrapMajorVersion: 3,//bootstrap的版本要求。
      	alignment:"right",
      	numberOfPages:5,//一页列出多少数据。
      	itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
         	switch (type) {
         		case "first": return "首页";
         		case "prev": return "上一页";
         		case "next": return "下一页";
         		case "last": return "末页";
         		case "page": return page;
         	}
     	}
 	});*/
 </script>
