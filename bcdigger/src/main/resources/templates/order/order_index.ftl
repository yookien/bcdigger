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
	                        <input type="text" class="form-control" id="OrderCode" name="OrderCode" placeholder="订货单编号">
	                      </div>
	                      
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">订货单日期</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="订货单名称">
	                      </div>
	                      
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">客户</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="订货单名称">
	                      </div>
	                      
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">客户</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="订货单名称">
	                      </div>
	                      
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
	                      <tbody>
	                        <tr>
	                          <td><input type="text" class="form-control" id="goodsNo1" name="chineseName" autocomplete="off"></td>
	                          <td><input type="text" class="form-control" id="chineseName" name="chineseName"></td>
	                          <td><input type="text" class="form-control" id="chineseName" name="chineseName"></td>
	                          <td><input type="text" class="form-control" id="chineseName" name="chineseName"></td>
	                          <td><input type="text" class="form-control" id="chineseName" name="chineseName"></td>
	                          <td>
	                          	<input id="single_cal4" class="form-control has-feedback-left" aria-describedby="inputSuccess2Status4" type="text">
								<span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
								<span id="inputSuccess2Status4" class="sr-only">(success)</span>
	                          </td>
	                          <td><input type="text" class="form-control" id="chineseName" name="chineseName"></td>
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
	
	initGoodsNo('goodsNo1');
});

function displayResult(item, val, text) {
console.log(item);
	alert('You selected <strong>' + val + '</strong>: <strong>' + text + '</strong>');
}

// 初始化联想查询货号
function initGoodsNo(id){
	$('#'+id).typeahead({
        source: [
		     { id: 1, full_name: 'Toronto', first_two_letters: 'To' },
		    { id: 2, full_name: 'Montreal', first_two_letters: 'Mo' },
		    { id: 3, full_name: 'New York', first_two_letters: 'Ne' },
		    { id: 4, full_name: 'Buffalo', first_two_letters: 'Bu' },
		    { id: 5, full_name: 'Boston', first_two_letters: 'Bo' },
		    { id: 6, full_name: 'Columbus', first_two_letters: 'Co' },
		    { id: 7, full_name: 'Dallas', first_two_letters: 'Da' },
		    { id: 8, full_name: 'Vancouver', first_two_letters: 'Va' },
		    { id: 9, full_name: 'Seattle', first_two_letters: 'Se' },
		    { id: 10, full_name: 'Los Angeles', first_two_letters: 'Lo' }
	    ],
	    display: 'full_name',
        itemSelected: displayResult
    });

}

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
			url: '/order/addOrder',
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
function editOrder(id){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	$('#myModalLabel').html('编辑订货单信息');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='id='+id;
	$.ajax({
		url: '/order/getOrder',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var order = json.order;
				if(order == undefined){
					return;
				}
				if(order.OrderCode != null && order.OrderCode != undefined){
					$('#OrderCode').val(order.OrderCode);
				}
				if(order.chineseName != null && order.chineseName != undefined){
					$('#chineseName').val(order.chineseName);
				}
				if(order.phone != null && order.phone != undefined){
					$('#phone').val(order.phone);
				}
				
				if(order.mobile != null && order.mobile != undefined){
					$('#mobile').val(order.mobile);
				}
				if(order.address != null && order.address != undefined){
					$('#address').val(order.address);
				}
				if(order.direction != null && order.direction != undefined){
					$('#direction').val(order.direction);
				}
				
				if(order.openHour != null && order.openHour != undefined){
					$('#openHour').val(order.openHour);
				}
				if(order.closeHour != null && order.closeHour != undefined){
					$('#closeHour').val(order.closeHour);
				}
				if(order.englishName != null && order.englishName != undefined){
					$('#englishName').val(order.englishName);
				}
				
				
				
				if(order.englishAddress != null && order.englishAddress != undefined){
					$('#englishAddress').val(order.englishAddress);
				}
				if(order.locationImage != null && order.locationImage != undefined){
					$('#locationImage').val(order.locationImage);
				}
				if(order.OrderImages != null && order.OrderImages != undefined){
					$('#OrderImages').val(order.OrderImages);
				}
				
				if(order.OrderProvince != null && order.OrderProvince != undefined){
					$('#OrderProvince').val(order.OrderProvince);
				}
				if(order.OrderCity != null && order.OrderCity != undefined){
					$('#OrderCity').val(order.OrderCity);
				}
				if(order.cityArea != null && order.cityArea != undefined){
					$('#cityArea').val(order.cityArea);
				}
				
				if(!isNaN(order.isOpen)){
					$("[targetId='isOpen']").each(function(){
						if($(this).find("[type='radio']").attr('value')==order.isOpen){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#isOpen").val(order.isOpen);
				}
				
				if(order.bmapPosition != null && order.bmapPosition != undefined){
					$('#bmapPosition').val(order.bmapPosition);
				}
				if(order.searchUrl != null && order.searchUrl != undefined){
					$('#searchUrl').val(order.searchUrl);
				}
				if(order.bus != null && order.bus != undefined){
					$('#bus').val(order.bus);
				}
				
				if(order.subway != null && order.subway != undefined){
					$('#subway').val(order.subway);
				}
				if(order.nearby != null && order.nearby != undefined){
					$('#nearby').val(order.nearby);
				}
				if(order.email != null && order.email != undefined){
					$('#email').val(order.email);
				}
				
				if(order.OrderImage1 != null && order.OrderImage1 != undefined){
					$('#OrderImage1').val(order.OrderImage1);
				}
				if(order.OrderImage2 != null && order.OrderImage2 != undefined){
					$('#OrderImage2').val(order.OrderImage2);
				}
				if(order.OrderImage3 != null && order.OrderImage3 != undefined){
					$('#OrderImage3').val(order.OrderImage3);
				}
				if(order.OrderImage4 != null && order.OrderImage4 != undefined){
					$('#OrderImage4').val(order.OrderImage4);
				}
				
				if(order.appointLimit != null && order.appointLimit != undefined){
					$('#appointLimit').val(order.appointLimit);
				}
				
				
				if(!isNaN(order.isBooking)){
					$("[targetId='isBooking']").each(function(){
						if($(this).find("[type='radio']").attr('value')==order.isBooking){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#isBooking").val(order.isBooking);
				}
				
				if(!isNaN(order.id)){
					$('#Order_id').val(order.id);
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