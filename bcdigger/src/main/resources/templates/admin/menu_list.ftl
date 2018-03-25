<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>欢迎使用bcdigger系统</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/css/nprogress.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="/build/css/custom.min.css" rel="stylesheet">
    
    <!-- iCheck -->
    <link href="/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

	<!-- jQuery -->
    <script src="/vendors/jquery/dist/jquery.min.js"></script>
    <script src="/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="/build/js/custom.js"></script>
  </head>
  <div class="x_content">
	  <table id="datatable" class="table table-striped table-bordered">
	      <thead>
	        <tr>
	          <th>权限名称</th>
	          <th>权限URL</th>
	          <th>权限logo</th>
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
                      <td>${(menu.menuName)!}</td>
                      <td>${(menu.menuUrl)!}</td>
                      <td>${(menu.menuLogoUrl)!}</td>
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
                  <td colspan="9" style="text-align: center">尚未添加任何权限信息</td>
                </tr>
		 	</#if>
	 	</tbody>
	 </table>
	</div>
	<!-- 分页  -->
	<div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate">
	</div>
  </body>
</html>
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
