<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>订单管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="addOrderEvent()">添加订单</button>
      </div>

	  <div class="x_content" id="order_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加订单 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加订单</h4>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditOrderForm">
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">订单编号</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="OrderCode" name="OrderCode" placeholder="订单编号">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">订单名称</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="订单名称">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">电话</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="phone" name="phone" placeholder="电话">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">手机</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">地址</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="address" name="address" placeholder="地址">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">大区</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="direction" name="direction" placeholder="大区">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">所属省</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="OrderProvince" name="OrderProvince" placeholder="所属省">
	                      </div>
	                      
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">所属城市</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="OrderCity" name="OrderCity" placeholder="所属城市">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">城市区域</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="cityArea" name="cityArea" placeholder="城市区域">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">开业时间</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="openHour" name="openHour" placeholder="开业时间">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">停业时间</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="closeHour" name="closeHour" placeholder="停业时间">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">英文名称</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="englishName" name="englishName" placeholder="英文名称">
	                      </div>
	                    </div> 
	                    
	                    
	                    <div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">英文地址</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12">
	                        <input type="text" class="form-control" id="englishAddress" name="englishAddress" placeholder="英文地址">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">位置示意图</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12">
	                        <input type="text" class="form-control" id="locationImage" name="locationImage" placeholder="位置示意图">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">订单主图</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12">
	                        <input type="text" class="form-control" id="OrderImages" name="OrderImages" placeholder="订单主图">
	                      </div>
						</div>
						
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">百度位置</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12">
	                        <input type="text" class="form-control" id="bmapPosition" name="bmapPosition" placeholder="百度位置">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">搜索地址</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="searchUrl" name="searchUrl" placeholder="搜索地址">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">公交情况</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="bus" name="bus" placeholder="公交情况">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">地铁情况</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="subway" name="subway" placeholder="地铁情况">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">附近建筑</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="nearby" name="nearby" placeholder="附近建筑">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">邮箱</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="email" name="email" placeholder="邮箱">
	                      </div>
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片1</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="OrderImage1" name="OrderImage1" placeholder="店面图片1">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片2</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="OrderImage2" name="OrderImage2" placeholder="店面图片2">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片3</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="OrderImage3" name="OrderImage3" placeholder="店面图片3">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片4</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="OrderImage4" name="OrderImage4" placeholder="店面图片4">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">预约限制</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="appointLimit" name="appointLimit" value="1" placeholder="预约限制">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">订单类型</label>
	                      <div class="col-md-4 col-sm-4 col-xs-86 ">
	                         <select id="OrderType" name="OrderType" class="form-control" required>
	                            <option value="0">自营</option>
	                            <option value="1">写字楼店</option>
	                            <option value="2">商场店</option>
	                            <option value="3">加盟店</option>
	                          </select>
	                      </div>
	                      
						</div>
						
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">是否预约</label>
	                      <input id="isBooking" name="isBooking" value="0" type="hidden">
	                        <div class="col-md-4 col-sm-4 col-xs-6 ">
	                          <div class="btn-group" data-toggle="buttons">
	                            <label name="state_label" onclick="clickRadioBtn(this)" targetId="isBooking" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                              <input  type="radio"  value="1" data-parsley-multiple="isBooking"> &nbsp; 是 &nbsp;
	                            </label>
	                            <label name="state_label" onclick="clickRadioBtn(this)" targetId="isBooking" class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                              <input  type="radio"  value="0" data-parsley-multiple="isBooking"> 否
	                            </label>
	                          </div>
	                        </div>
	                      <!--
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">开业时间</label>
	                      <div class="col-md-4 col-sm-4 col-xs-86 ">
	                        <input type="text" class="form-control" id="openTime" name="openTime" placeholder="开业时间">
	                      </div>-->
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">是否开业</label>
	                      
	                        <input id="isOpen" name="isOpen" type="hidden" value="0">
	                        <div class="col-md-4 col-sm-4 col-xs-6">
	                          <div class="btn-group" data-toggle="buttons">
	                            <label name="state_label" onclick="clickRadioBtn(this)" targetId="isOpen" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                              <input  type="radio"  value="1" data-parsley-multiple="isOpen"> &nbsp; 是 &nbsp;
	                            </label>
	                            <label name="state_label" onclick="clickRadioBtn(this)" targetId="isOpen" class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                              <input  type="radio"  value="0" data-parsley-multiple="isOpen"> 否
	                            </label>
	                          </div>
	                        </div>
						</div>
						  
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
	// 查询订单信息
	getOrder();
	// 初始化参数校验
	initValidator();
});

// 分页查询订单信息
function getOrder(){
	ajax_request_url='/order/getOrders';
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



// 打开新增订单页面
function addOrderEvent(){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	$('#myModalLabel').html('添加订单信息');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addOrder();
	});
}

// 添加订单信息
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


// 打开编辑订单页面
function editOrder(id){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	$('#myModalLabel').html('编辑订单信息');
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

// 添加用户订单
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



// 初始化 添加、编辑订单校验
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
	                	message: '请输入订单名称'
	            	}
	        	}
	       	},
	        adminId: {
	        	validators: {
	            	notEmpty: {
	                        message: '请选择订单负责人'
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