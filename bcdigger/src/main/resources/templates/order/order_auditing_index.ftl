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
               <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>
                <h1 class="modal-title" id="myModalLabel" style="text-align:center;">弈杰订货单</h1>
            </div>
            <div class="modal-body">
            	<form class="form-horizontal form-label-left input_mask" id="auditOrderForm">
            		<input type="hidden" id="order_id" name="id">
            		<input type="hidden" id="order_state" name="state">
            		<div class="col-md-12 col-sm-12 col-xs-12 form-group">
            			<p class="control-label col-md-3 col-sm-3 col-xs-12" stype="text-align: left;">订货单编号：<small id="goodsOrderNoSpan"> </small></p>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">单据日期： <small id="updateTimeSpan"> </small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">订货单种类：<small id="orderTypeSpan"> </small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">订货单状态：<small id="orderStateSpan"> </small></div>
                     </div>
                     <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                     	<div class="control-label col-md-3 col-sm-3 col-xs-12">客      户： <small id="storeChineseNameSpan">中央厨房</small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">下  单   人：<small id="operatorNameSpan"> 张三</small></div>
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
function viewOrder(id){
	// 重设form
	$('#auditOrderForm')[0].reset();
	
	// 隐藏审核按钮
	$('.modal-footer').hide();
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
					$('#updateTimeSpan').val(order.orderNo);
				}
				if(order.addTime != null && order.addTime != undefined){
					$('#updateTimeSpan').val(fmtDate(order.addTime));
				}
				
				
				$("#audit_btn").unbind();
				$("#audit_btn").click(function(){
				  	auditingGoodsOrder(10010);
				});
				$("#not_audit_btn").unbind();
				$("#not_audit_btn").click(function(){
				  	auditingGoodsOrder(10020);
				});
				
			}
		}
	})
}

// 打开审核订货单页面
function auditingOrder(id){
	// 重设form
	$('#auditOrderForm')[0].reset();
	//$('#myModalLabel').html('编辑订货单信息');
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
					$('#updateTimeSpan').val(order.orderNo);
				}
				if(order.addTime != null && order.addTime != undefined){
					$('#updateTimeSpan').val(fmtDate(order.addTime));
				}
				
				
				$("#audit_btn").unbind();
				$("#audit_btn").click(function(){
				  	auditingGoodsOrder(10010);
				});
				$("#not_audit_btn").unbind();
				$("#not_audit_btn").click(function(){
				  	auditingGoodsOrder(10020);
				});
				
			}
		}
	})
}

// 添加用户订货单
function auditingGoodsOrder(state){
	if(isNaN(state)){
		return;
	}
	$('#order_state').val(state);
	$("#auditOrderForm").bootstrapValidator('validate');//提交验证  
    if ($("#auditOrderForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#auditOrderForm").serialize();
		$.ajax({
			url: '/order/auditingGoodsOrder',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==10000){
					getOrder();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
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