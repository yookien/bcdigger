<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>关闭收货单管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div class="well" style="overflow: auto">
      	<form class="form-horizontal form-label-left input_mask" id="searchForm">
      		<div class="col-md-10 col-sm-10 col-xs-12">
      		 	
      		 	<label class="control-label" style="float:left;padding-top: 8px;">收货单编号： </label>
            	<input id="search_order_no" name="orderNo" class="form-control"  type="text" style="float:left;width:150px;margin-right:15px">
      		 	
        		<label class="control-label" style="float:left;padding-top: 8px;">门店名称： </label>
            	<input id="search_store_name" name="storeChineseName" class="form-control"  type="text" style="float:left;width:250px;margin-right:15px">
            	
            	<label class="control-label" style="float:left;padding-top: 8px;">收货人姓名： </label>
            	<input id="search_operator_name" name="operatorName" class="form-control"  type="text" style="float:left;width:150px;margin-right:15px">
           		
			 	<button type="button" style="float:left;margin-left:15px" class="btn btn btn-primary" onclick="getCloseOrders()">查询</button>
			</div>
		</form>
		</div>

	  <div class="x_content" id="close_order_datas">
		 
	  </div>
    </div>
</div>



<!-- 收货单审核 -->
<div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document" style="width:800px">  
        <div class="modal-content">  
            <div class="modal-header">  
               <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_btn">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>
                <h1 class="modal-title" id="myModalLabel" style="text-align:center;">弈杰收货单</h1>
            </div>
            <div class="modal-body">
            	<form class="form-horizontal form-label-left input_mask" id="closeOrderForm">
            		<input type="hidden" id="admin_id" name="id">
            		<div class="col-md-12 col-sm-12 col-xs-12 form-group">
            			<p class="control-label col-md-3 col-sm-3 col-xs-12" stype="text-align: left;">订货单编号：<small id="goodsOrderNoSpan">SKJD001</small></p>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">单据日期： <small id="updateTimeSpan">2018-07-01</small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">订货单种类：<small id="orderTypeSpan">牛奶</small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">订货单状态：<small id="orderStateSpan">收货中</small></div>
                     </div>
                     <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                     	<div class="control-label col-md-3 col-sm-3 col-xs-12">客      户： <small id="storeChineseNameSpan">中央厨房</small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">下  单   人：<small id="operatorNameSpan"> 张三</small></div>
            			<!-- <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
            			<label class="control-label col-md-3 col-sm-3 col-xs-12"></label> -->
            		</div>
            		<table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>序号</th>
                          <th>物料编码</th>
                          <th>物料名称</th>
                          <th>规格型号</th>
                          <th>销售单位</th>
                          <th>订货总数量</th>
                          <th>已收货数量</th>
                          <th>本次收货数量</th>
                          <th>要货日期</th>
                          <th>备注</th>
                        </tr>
                      </thead>
                      <tbody id="closeOrderTbody">
                        
                      </tbody>
                    </table>
            	</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="not_close_order_btn">取消关闭</button>  
                <button type="button" class="btn btn-primary" id="close_order_btn">关闭收货单</button>
            </div>
        </div>  
    </div>  
</div>

<script type="text/javascript">
// ajax分页参数，待优化
var ajax_request_url='';
var ajax_pars='';
var ajax_contents='close_order_datas';

$(function(){
	// 查询待关闭的收货单信息
	getCloseOrders();
});

// 分页查询待关闭的收货单信息
function getCloseOrders(){
	ajax_request_url='/goods/getCloseOrders';
	ajax_pars=$("#searchForm").serialize();
//	alert(ajax_pars);
	$.ajax({
		url: ajax_request_url,
		type:'POST',
		data:ajax_pars,
		dataType:'html',
		success:function (data) {
			if (data != "") {
            	$('#'+ajax_contents).html(data);
            }
		}
	})
}


// 打开需关闭收货单详细model
function lisCloseOrderInfo(id){
	// 重设form
	$('#closeOrderForm')[0].reset();
	//$('#myModalLabel').html('编辑门店信息');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='goodsOrderId='+id;
	$.ajax({
		url: '/goods/getCloseOrderInfo',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var list=json.list;
				if(list == undefined){
					return;
				}
				var closeOrderInfos = eval(list);
				var html = ""
				var goodsOrderId=0;
				for (var i in closeOrderInfos ){
					if(i==0){
						goodsOrderId = closeOrderInfos[i].goodsOrderId;
						$('#goodsOrderNoSpan').text(closeOrderInfos[i].orderNo);
						$('#updateTimeSpan').text(closeOrderInfos[i].updateTime);
						$('#orderTypeSpan').text(closeOrderInfos[i].orderType);
						$('#orderStateSpan').text(closeOrderInfos[i].orderState);
						$('#storeChineseNameSpan').text(closeOrderInfos[i].storeChineseName);
						$('#operatorNameSpan').text(closeOrderInfos[i].operatorName);
					}
					html=html +"<tr><th scope='row'>"+i+"</th><td>"+closeOrderInfos[i].goodsNo+"</td>"+
					"<td>"+closeOrderInfos[i].goodsName+"</td>"+
					"<td>"+closeOrderInfos[i].model+"</td>"+
					"<td>"+closeOrderInfos[i].unit+"</td>"+
					"<td>"+closeOrderInfos[i].orderQuantity+"</td>"+
					"<td>"+closeOrderInfos[i].instoreQuantity+"</td>"+
					"<td style='color:red'>"+closeOrderInfos[i].inQuantity+"</td>"+
					"<td>"+closeOrderInfos[i].instoreTime+"</td>"+
					"<td>"+closeOrderInfos[i].memo+"</td></tr>"
				}
				$('#closeOrderTbody').html(html);
				
				$("#close_order_btn").unbind();
				$("#not_close_order_btn").unbind();
				$("#close_order_btn").click(function(){
				  	closeOrder(goodsOrderId);
				});
				$("#not_close_order_btn").click(function(){
					$("#close_btn").click();
				});
			}
		}
	})
}

// 更新订货单信息
function closeOrder(goodsOrderId){
	
	var pars = "goodsOrderId="+goodsOrderId;
	$.ajax({
		url: '/goods/updateCloseOrderInfo',
		type:'POST',
		data: pars,
		dataType:'JSON',
		success:function (json) {
			if(json.result==1){
				getCloseOrders();
				$("#close_btn").click();
			}else{
				alert(json.result);
			}
		}
	})
}

</script>