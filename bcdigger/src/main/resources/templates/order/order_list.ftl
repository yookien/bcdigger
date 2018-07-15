
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th>序号</th>
          	<th>门店编号</th>
          	<th>门店名称</th>
          	<th>负责人手机</th>
          	<th>状态</th>
          	<th>添加时间</th>
          	<th>更新时间</th>
          	<th>操作</th>
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as order>
				<tr>
					<td>${(order.id)!1}</td>
                  	<td>${(order.orderCode)!}</td>
                  	<td>${(order.chineseName)!}</td>
                  	<td>${(order.mobile)!}</td>
                  	<td><#if order.isOpen==1>开业<#else>停业</#if></td>
                  	<td><#if (order.addTime)??>${order.addTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                  	<td><#if (order.updateTime)??>${order.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                  	<td>
                  		<a href="###" onclick="editOrder(${(order.id)!0})" name="editRoleBt" data-toggle="modal" data-target="#myModal" roleId="${(order.id)!}" >编辑</a>
				  	</td>
                </tr>
		 	</#list>
		<#else>
			<tr>
              <td colspan="10" style="text-align: center">尚未添加任何门店信息</td>
            </tr>
	 	</#if>
 	</tbody>
 </table>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/ajax_pages.ftl">
