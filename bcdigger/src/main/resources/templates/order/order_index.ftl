<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>订货单管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="addOrderEvent()">添加订货单</button>
      </div>

	  <div class="x_content" id="order_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加订货单 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document" style="width:70%">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加订货单</h4>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditOrderForm">
						<div class="form-group">
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">订货单编号</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3 ">
	                        <input type="text" class="form-control" id="orderNo">
	                      </div>
	                      
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">订货单日期</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control" id="addTime">
	                      </div>
	                      
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">门店</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control" id="storeName" >
	                      </div>
	                      
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">订货种类</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control" id="orderType">
	                      </div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-1 col-sm-1 col-xs-3 " onclick="addGoodsInfoEvent()">选择商品</label>
						</div>
					
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
	                      <thead>
	                        <tr>
	                          <th>物料编码</th>
	                          <th>物料名称</th>
	                          <th>规格型号</th>
	                          <th>销售单位</th>
	                          <th>销售数量</th>
	                          <th>要货日期</th>
	                          <th>备注</th>
	                        </tr>
	                      </thead>
	                      <tbody id="choose_goods_tbody">
	                        <tr>
	                          <td>
	                          	<input type="text" class="form-control" id="goodsNo1" name="goodsNo" onclick="addGoodsInfoEvent()">
	                          </td>
	                          <td><input type="text" class="form-control" id="goodsNam1"></td>
	                          <td><input type="text" class="form-control" id="chineseName"></td>
	                          <td><input type="text" class="form-control" id="unit1"></td>
	                          <td><input type="text" class="form-control" id="quantity1" name="quantity"></td>
	                          <td>
	                          	<input id="single_cal4" name="instoreTime" class="form-control has-feedback-left" aria-describedby="inputSuccess2Status4" type="text">
								<span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
								<span id="inputSuccess2Status4" class="sr-only">(success)</span>
	                          </td>
	                          <td><input type="text" class="form-control" id="memo1" name="memo" value="无" placeholder="备注"></td>
	                        </tr>
	                        
	                       </tbody>
						</table>
						
					   <input type="hidden" name="id" id="Order_id" value="0"> 
                 </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close_btn">关闭</button>  
                <button type="button" class="btn btn-primary" id="save_btn">保存</button>
            </div>
        </div>  
    </div>  
</div>


<div class="modal fade" id="myGoodsModal" tabindex="-1" > 
    <div class="modal-dialog" role="document" style="width:70%">  
        <div class="modal-content">
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">选择商品</h4>
            </div>
            <form id="chooseGoodsInfoForm">
	            <div class="modal-body" id="goodsInfoDatas">
	            </div>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="goods_close_btn">关闭</button>  
                <button type="button" class="btn btn-primary" id="choose_goods_btn">返回数据</button>
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
	
	// 初始化日历
	initDaterangepicker('single_cal4');
	

});


// 初始化日历
function initDaterangepicker(id){
	$('#'+id).daterangepicker({
	  singleDatePicker: true,
	  singleClasses: "picker_4"
	}, function(start, end, label) {
	  console.log(start.toISOString(), end.toISOString(), label);
	});
}

// 分页查询订货单信息
function getOrder(){
	ajax_request_url='/order/getGoodsOrders';
	ajax_contents='order_datas';
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

// 打开商品选择页面
function addGoodsInfoEvent(){
	$('#myGoodsModal').modal('show');
	getGoodsInfo();
	
	$("#choose_goods_btn").unbind();
	$("#choose_goods_btn").click(function(){
	  	chooseGoods();
	});
}

// 分页查询商品信息
function getGoodsInfo(){
	ajax_request_url='/goods/getGoodsInfo';
	ajax_contents='goodsInfoDatas';
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

function chooseGoods(){
	var pars=$("#chooseGoodsInfoForm").serialize();
	alert(unescape(pars));
	if( pars != undefined ){
		var goodsInfos=pars.split('&');
		var htmlStr="";
		for (var i =0;i< goodsInfos.length;i++ ){
			var goods = goodsInfos[i];
			if( goods == undefined ){
				continue;
			}
			var goodsAttr=goods.split('|')
			if( goodsAttr == undefined || goodsAttr.length < 4 ){
				continue;
			}
			htmlStr=htmlStr + 
					"<tr><td><input type='text' class='form-control' name='goodsNo' value='"+goodsAttr[0]+"'></td>"+
					"<td>"+goodsAttr[1]+"</td>"+
					"<td>"+goodsAttr[2]+"</td>"+
					"<td>"+goodsAttr[3]+"</td>"+
					"<td><input type='text' class='form-control' id='quantity1' name='quantity'></td>"+
					"<td><input id='single_cal4' name='instoreTime'/></td>"+
					"<td><input type='text' class='form-control' id='memo1' name='memo' value='无'></td></tr>"
		}
		$("#choose_goods_tbody").append(pars);
	} else {
		alert('请至少选择一个商品');
	}
	$("#goods_close_btn").click();
}

// 打开新增订货单页面
function addOrderEvent(){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	$('#myModalLabel').html('添加订货单信息');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addOrder();
	});
}

// 添加订货单信息
function addOrder(){
	$("#addOrEditOrderForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditOrderForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditOrderForm").serialize();
		$.ajax({
			url: '/order/addGoodsOrder',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getOrder();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}

// 打开编辑订货单页面
function viewOrder(id){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	$('#myModalLabel').html('订货单信息');
	if(isNaN(id) || id<=0){
		return;
	}
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
					$('#orderNo').val(order.orderNo);
				}
				if(order.addTime != null && order.addTime != undefined){
					$('#addTime').val(fmtDate(order.addTime));
				}
				
				
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateOrder();
				});
			}
		}
	})
}


// 打开编辑订货单页面
function editOrder(id){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	$('#myModalLabel').html('编辑订货单信息');
	if(isNaN(id) || id<=0){
		return;
	}
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
					$('#orderNo').val(order.orderNo);
				}
				if(order.addTime != null && order.addTime != undefined){
					$('#addTime').val(fmtDate(order.addTime));
				}
				
				
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateOrder();
				});
			}
		}
	})
}

// 添加用户订货单
function updateOrder(){
	$("#addOrEditOrderForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditOrderForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditOrderForm").serialize();
		$.ajax({
			url: '/order/updateOrder',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
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

	$('#addOrEditOrderForm').bootstrapValidator({
		message: '输入值不符合要求',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	    	name: {
	        	validators: {
	            	notEmpty: {
	                	message: '请输入订货单名称'
	            	}
	        	}
	       	},
	        adminId: {
	        	validators: {
	            	notEmpty: {
	                        message: '请选择订货单负责人'
	                }
	            }
	        },
	        displayOrder: {
	        	validators: {
	            	notEmpty: {
	                        message: '请输入显示顺序'
	                }
	            }
	        }
	    }   
	});

}

</script>