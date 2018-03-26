
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th>序号</th>
        	<th>菜单logo</th>
          	<th>菜单名称</th>
          	<th>菜单URL</th>
          	<th>菜单描述</th>
          	<th>状态</th>
          	<th>排序值</th>
          	<th>添加时间</th>
          	<th>更新时间</th>
          	<th>操作</th>
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as menu>
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
						<a href="javascript:getSysMenus(${(menu.id)!});" name="viewRoleBt" roleId="${(menu.id)!}" >查看</a>
				  	</td>
                </tr>
		 	</#list>
		<#else>
			<tr>
              <td colspan="10" style="text-align: center">尚未添加任何菜单信息</td>
            </tr>
	 	</#if>
 	</tbody>
 </table>
<input type="hidden" name="parentId" id="parentId" value="${parentId!0}">
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/ajax_pages.ftl">
