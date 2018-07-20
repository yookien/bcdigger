
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th>序号</th>
          	<th>订货单编号</th>
          	<th>金蝶订单编号</th>
          	<th>收货门店</th>
          	<th>收货人</th>
          	<th>订货总数量</th>
          	<th>已收货总数量</th>
          	<th>待收货总数量</th>
          	<th>操作</th>
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as goodsInstoreBiz>
				<tr>
					<td>${goodsInstoreBiz_index+1}</td>
					<td>${(goodsInstoreBiz.orderNo)!1}</td>
                  	<td>${(goodsInstoreBiz.kingdeeCustNo)!}</td>
                  	<td>${(goodsInstoreBiz.storeChineseName)!}</td>
                  	<td>${(goodsInstoreBiz.operatorName)!}</td>
                  	<td>${(goodsInstoreBiz.orderQuantity)!0}</td>
                  	<td>${(goodsInstoreBiz.instoreQuantity)!0}</td>
                  	<td style="color:red">${(goodsInstoreBiz.inQuantity)!0}</td>
                  	<td>
                  		<a href="###" onclick="lisCloseOrderInfo(${(goodsInstoreBiz.goodsOrderId)!0})" name="lisCloseOrderInfo" data-toggle="modal" data-target="#myModal" goodsOrderId="${(goodsInstoreBiz.goodsOrderId)!}" >查看</a>
				  	</td>
                </tr>
		 	</#list>
		<#else>
			<tr>
              <td colspan="10" style="text-align: center">尚未有任何需审核的收货信息</td>
            </tr>
	 	</#if>
 	</tbody>
 </table>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/ajax_pages.ftl">
