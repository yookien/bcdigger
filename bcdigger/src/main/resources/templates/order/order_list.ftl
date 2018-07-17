
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th>单据编号</th>
          	<th>下单日期</th>
          	<th>门店名称</th>
          	<th>金蝶K3单号</th>
          	<th>状态</th>
          	<th>操作</th> 
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as order>
				<tr>
					<td>${(order.orderNo)!1}</td>
                  	<td>${order.addTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                  	<td>${(order.storeName)!}</td>
                  	<td>${(order.storeName)!}</td>
                  	<td>
                  		<#if order.state==10000>待审核
	                  	<#elseif order.state==10010>已审核
	                  	<#elseif order.state==10020>收货中
	                  	<#elseif order.state==10030>已完成
	                  	<#elseif order.state==10040>已失效
	                  	</#if>
                  	</td>
                  	<td>
                  		<a href="###" onclick="editOrder(${(order.id)!0})" name="editOrderBt" data-toggle="modal" data-target="#myModal" orderId="${(order.id)!}" >编辑</a>
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
