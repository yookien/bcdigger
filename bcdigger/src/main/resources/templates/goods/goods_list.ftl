<#setting number_format="0.######"/>
  <table id="datatable" class="table table-striped table-bordered">
      <thead>
        <tr>
        	<th><a href="javascript:switchCheckedBox('goodsInfos')">选择</a></th>
          	<th>物料编码</th>
          	<th>物料名称</th>
          	<th>规格型号</th>
          	<th>销售单位</th>
        </tr>
      </thead>
      <tbody>
		<#if (pageInfo.list)?? && (pageInfo.list?size>0) >
			<#list pageInfo.list as goods>
				<tr>
					<td><input type="checkbox" name="goodsInfos" 
					value="${(goods.goodsNo)!}|${(goods.goodsName)!}|${(goods.model)!}|${(goods.unit)!}" /></td>
					<td>${(goods.goodsNo)!}</td>
                  	<td>${(goods.goodsName)!}</td>
                  	<td>${(goods.model)!}</td>
                  	<td>${(goods.unit)!}</td>
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
 <div style="float:right"><ul id='pages_2'></ul></div>
 <#include "/lib/ajax_pages_2.ftl">
