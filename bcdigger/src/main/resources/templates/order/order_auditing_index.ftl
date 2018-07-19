<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>订货单审核 </h2>
        <div class="clearfix"></div>
      </div>
      
	  <div class="x_content" id="order_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加订货单 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
<div class="modal-dialog" role="document" style="width:800px">  
        <div class="modal-content">  
            <div class="modal-header">  
               <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_btn">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>
                <h1 class="modal-title" id="myModalLabel" style="text-align:center;">弈杰订货单</h1>
            </div>
            <div class="modal-body">
            	<form class="form-horizontal form-label-left input_mask" id="auditOrderForm">
            		<input type="hidden" id="order_id" name="id">
            		<input type="hidden" id="order_state" name="state">
            		<div class="col-md-12 col-sm-12 col-xs-12 form-group">
            			<div class="col-md-4 col-xs-12">单据编号：<middle id="goodsOrderNoSpan"> </middle></div>
            			<div class="col-md-4 col-xs-12">金蝶单号：<middle id="kingdeeCustNoSpan"> </middle></div>
            			<div class="col-md-4 col-xs-12">单据日期：<middle id="updateTimeSpan"> </middle></div>
                     </div>
                     <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                     	<div class="col-md-4 col-xs-12">客户： <middle id="storeChineseNameSpan"></middle></div>
            			<div class="col-md-2 col-xs-12">下单人：<middle id="operatorNameSpan">  </middle></div>
            			<div class="col-md-2 col-xs-12">单据状态：<middle id="orderStateSpan"> </middle></div>
            			<div class="col-md-4 col-xs-12">单据类型：<middle id="orderTypeSpan"> </middle></div>
            			
            		</div>
            		<table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>序号</th>
                          <th>物料编码</th>
                          <th>物料名称</th>
                          <th>规格型号</th>
                          <th>销售单位</th>
                          <th>订货数量</th>
                          <th>要货日期</th>
                          <th>备注</th>
                        </tr>
                      </thead>
                      <tbody id="orderItemTbody">
                        
                      </tbody>
                    </table>
            	</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="not_audit_btn">审核不通过</button>  
                <button type="button" class="btn btn-primary" id="audit_btn">审核通过</button>
            </div>
        </div>  
    </div> 
</div>

<script type="text/javascript">
// ajax分页参数，待优化
var ajax_request_url='';
var ajax_pars='';
var ajax_contents='order_datas';

$(function(){
	// 查询订货单信息
	getOrder();
	// 初始化参数校验
	initValidator();
	
	
});



// 分页查询订货单信息
function getOrder(){
	ajax_request_url='/order/getGoodsOrdersForAuditing';
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

// 查看订货单页面
function openOrder(id,type){
	// 重设form
	$('#auditOrderForm')[0].reset();
	
	if( type == 'view' ){
		// 隐藏审核按钮
		$('.modal-footer').hide();
	} else {
		// 展示审核按钮
		$('.modal-footer').show();
	}
	if(isNaN(id) || id<=0){
		return;
	}
	$('#order_id').val(id);
	var pars='id='+id;
	$.ajax({
		url: '/order/getGoodsOrder',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var order = json.goodsOrder;
				if(order == undefined){
					return;
				}
				if(order.orderNo != null && order.orderNo != undefined){
					$('#goodsOrderNoSpan').html(order.orderNo);
				}
				if(order.addTime != null && order.addTime != undefined){
					$('#updateTimeSpan').html(unixFormatFullStr(order.addTime));
				}
				if(order.storeName != null && order.storeName != undefined){
					$('#storeChineseNameSpan').html(order.storeName);
				}
				if(order.kingdeeCustNo != null && order.kingdeeCustNo != undefined){
					$('#kingdeeCustNoSpan').html(order.kingdeeCustNo);
				}
				if(order.orderUserName != null && order.orderUserName != undefined){
					$('#operatorNameSpan').html(order.orderUserName);
				}
				
				if ( order.state==10000 ) 
					$('#orderStateSpan').html('待审核');  
              	else if ( order.state==10010 )
              		$('#orderStateSpan').html('已审核'); 
              	else if ( order.state==10020 )
              		$('#orderStateSpan').html('收货中'); 
              	else if ( order.state==10030 )
              		$('#orderStateSpan').html('已完成'); 
              	else if ( order.state==10040 )
              		$('#orderStateSpan').html('已失效'); 
              	else if ( order.state==10050 )
              		$('#orderStateSpan').html('已拒绝'); 
				
				var orderItemList = order.orderItemList;
				var orderItemInfos = eval(orderItemList);
				if( orderItemInfos != undefined && orderItemInfos.length>0 ){
					var htmlStr = ""
					var orderItem;
					for(var i=0;i<orderItemInfos.length;i++){
						orderItem = orderItemInfos[i];
						if(orderItem == undefined ){
							continue;
						}
						htmlStr = htmlStr +"<tr><th scope='row'>"+(i+1)+"</th><td>"+orderItem.goodsNo+"</td>"+
						"<td>"+orderItem.goodsName+"</td>"+
						"<td>"+orderItem.goodsModel+"</td>"+
						"<td>"+orderItem.goodsUnit+"</td>"+
						"<td>"+orderItem.orderQuantity+"</td>"+
						"<td>"+unixFormatStr(orderItem.instoreTime)+"</td>"+
						"<td>"+orderItem.memo+"</td></tr>"
					}
					$('#orderItemTbody').html(htmlStr);
				}
				if( type == 'auditing' ){
					$("#audit_btn").attr("disabled",false);
					$("#audit_btn").click(function(){
					  	auditingGoodsOrder(10010);// 审核通过
					});
					
					$("#not_audit_btn").attr("disabled",false);
					$("#not_audit_btn").click(function(){
					  	auditingGoodsOrder(10050);// 拒绝
					});
				}
			}
		}
	})
}

// 审核用户订货单
function auditingGoodsOrder(state){
	if(isNaN(state)){
		return;
	}
	$('#order_state').val(state);
	
	$("#audit_btn").unbind();
	$("#audit_btn").attr("disabled",true);
	$("#not_audit_btn").unbind();
	$("#not_audit_btn").attr("disabled",true);

    var pars=$("#auditOrderForm").serialize();
	$.ajax({
		url: '/order/auditingGoodsOrder',
		type:'POST',
		data: pars,
		dataType:'JSON',
		success:function (json) {
			if(json.result == 10000){
				getOrder();
				$("#close_btn").click();
			}else{
				alert(json.result);
			}
		}
	})
}



// 初始化 添加、编辑订货单校验
function initValidator(){

	$('#auditOrderForm').bootstrapValidator({
		message: '输入值不符合要求',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	        
	    }   
	});

}

</script>