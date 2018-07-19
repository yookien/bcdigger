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
                <h1 class="modal-title" id="myModalLabel" style="text-align:center;">弈杰订货单</h1>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditOrderForm">
						<div class="form-group">
	                      <div class="col-md-3 col-sm-3 col-xs-4 ">
	                     	 单据编号：<middle id="goodsOrderNoSpan"> </middle>
	                      </div>
	                      
	                      <div class="col-md-3 col-sm-3 col-xs-4">
	                     	 单据日期：<middle id="updateTimeSpan"> </middle>
	                      </div>
	                      
	                      <div class="col-md-3 col-sm-3 col-xs-4">
	                      	门店：<middle id="storeChineseNameSpan"></middle>
	                      </div>
	                      <!-- 暂时隐藏 
	                      <label class="control-label col-md-1 col-sm-1 col-xs-3 ">订货种类</label>
	                      <div class="col-md-2 col-sm-2 col-xs-3">
	                        <input type="text" class="form-control">
	                      </div>-->
	                      
						</div>
						
						<div class="form-group" id="choose_goods_bt">
							<label class="col-md-1 col-sm-1 col-xs-3 " onclick="addGoodsInfoEvent()">添加商品</label>
						</div> 
					
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
	                      <thead>
	                        <tr>
	                          <th width="17%">物料编码</th>
	                          <th width="22%">物料名称</th>
	                          <th width="12%">规格型号</th>
	                          <th width="10%">销售单位</th>
	                          <th width="10%">销售数量</th>
	                          <th width="12%">要货日期
	                          	<a onclick="copyFirstTime('instoreTime')">填充</a>
	                          </th>
	                          <th width="12%">备注</th>
	                          <th class="operator_t" width="5%">操作</th>
	                        </tr>
	                      </thead>
	                      <tbody id="choose_goods_tbody">
		                      	
	                      </tbody>
                      		<tr id="choose_goods_tr">
	                          <td>
	                          	<input type="text" class="form-control" onclick="addGoodsInfoEvent()">
	                          </td>
	                          <td><input type="text" class="form-control"></td>
	                          <td><input type="text" class="form-control"></td>
	                          <td><input type="text" class="form-control"></td>
	                          <td><input type="text" class="form-control"></td>
	                          <td>
	                          	<input class="form-control" type="text">
	                          </td>
	                          <td><input type="text" class="form-control"value=""></td>
	                          <td class="operator_t" >&nbsp;</td>
	                        </tr>
					    </table>
						
					   <input type="hidden" name="id" id="order_id" value="0"> 
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
            <div class="modal-body"> 
				<div class="form-group">
					<form id="searchGoodsInfoForm">
						<input type="hidden" name="like" value="1">
						
		                <label class="control-label col-md-1 col-sm-1 col-xs-3 ">商品名称（包含）</label>
		                <div class="col-md-2 col-sm-2 col-xs-3 ">
		                    <input type="text" class="form-control" name="goodsName" value="">
		                </div>
		                  
		                <label class="control-label col-md-1 col-sm-1 col-xs-3 ">商品编号（包含）</label>
		                <div class="col-md-2 col-sm-2 col-xs-3">
		                    <input type="text" class="form-control" name="goodsNo" value="" >
		                </div>
		                  
		                <button type="button" class="btn btn-default" onclick="getGoodsInfo()">检索</button> 
	                </form>	
				</div>
				
	            <form id="chooseGoodsInfoForm">
		            <div class="modal-body" id="goodsInfoDatas">
		            </div>
	            </form>
            </div>
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
});

// 分页查询订货单信息
function getOrder(){
	ajax_request_url='/order/getGoodsOrders';
	ajax_contents='order_datas';
	ajax_pars='';
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
	$('#searchGoodsInfoForm')[0].reset();
	
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
	var pars=$("#searchGoodsInfoForm").serialize();
	ajax_pars=pars;
	$.ajax({
		url: ajax_request_url,
		type:'POST',
		data: ajax_pars,
		dataType:'html',
		success:function (data) {
			if (data != "") {
            	$('#'+ajax_contents).html(data);
            }
		}
	})
}

function getCheckBoxValues(name){
	var checks = document.getElementsByName(name);
	var values = "";
	if(checks && checks.length>0 ){
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked){
				values+=checks[i].value;
				if(i != checks.length-1){
					values+=",";
				}
			}
		}
	}
	values=values.substring(0,values.length-1);
	return values;
}


function chooseGoods(){
	if(!checkedLestOne('goodsInfos')){
		alert('请至少选择一个商品');
		return;
	}

	var pars = getCheckBoxValues('goodsInfos');
	
	if( pars != undefined ){
		var goodsInfos=pars.split(',');
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
					"<tr><td><input type='text' class='form-control' readonly='readonly' name='goodsNo' value='"+goodsAttr[0]+"'>"+
					"<input type='hidden' name='orderItemId' value='0'</td>"+
					"<td><input type='text' class='form-control' readonly='readonly' value='"+goodsAttr[1]+"'></td>"+
					"<td><input type='text' class='form-control' readonly='readonly' value='"+goodsAttr[2]+"'></td>"+
					"<td><input type='text' class='form-control' readonly='readonly' value='"+goodsAttr[3]+"'></td>"+
					"<td><input type='text' class='form-control' name='quantity' value='0'></td>"+
					"<td><input name='instoreTimeStr' class='instoreTime'/></td>"+
					"<td><input type='text' class='form-control' id='memo1' name='memo' value=' '></td>"+
					"<td class='removeGoods'><a href='###'><middle>移除</middle></a></td></tr>"
		}
		$("#choose_goods_tbody").append(htmlStr);
		// 初始化时间插件
		initDaterangepicker('instoreTime');
		// 添加绑定事件
		$(".removeGoods").click(function(){
		  	removeGoods(this);
		});
		$(".removeGoods").show();
		$(".operator_t").show();
	} else {
		alert('请至少选择一个商品');
		return;
	}
	$("#goods_close_btn").click();
}

// 打开新增订货单页面
function addOrderEvent(){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	//$('#myModalLabel').html('添加订货单信息');
	$('#choose_goods_tbody').html('');
	
	$('#choose_goods_bt').hide();//show()
	$('#choose_goods_tr').show();
	
	$('#goodsOrderNoSpan').html('');
	$('#updateTimeSpan').html('');
	$('#storeChineseNameSpan').html('');
	// 加载系统时间及用户所属门店信息
	$.ajax({
		url: '/order/getUserInfo',
		type:'POST',
		dataType:'json',
		success:function (json) {
			if(json.result == 10000){
				var storeName = json.storeName;
				var addTime = json.now;
				if(addTime != null && addTime != undefined){
					$('#updateTimeSpan').html(unixFormatFullStr(addTime));
				}
				if(storeName != null && storeName != undefined){
					$('#storeChineseNameSpan').html(storeName);
				}
			}
		}	
	});	
		
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addOrder();
	});
}

// 添加订货单信息
function addOrder(){
	$("#save_btn").attr("disabled",true);
    if (parameterValidator()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditOrderForm").serialize();
		$.ajax({
			url: '/order/addGoodsOrder',
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
    } else {
    	$("#save_btn").attr("disabled",false);
    }
}


// 打开编辑订货单页面
function openOrder(id,type){
	// 重设form
	$('#addOrEditOrderForm')[0].reset();
	//$('#myModalLabel').html('订货单信息');
	// 清空缓存数据
	$('#choose_goods_tbody').html('');
	if( type == 'view' ){
		// 隐藏审核按钮
		$('.modal-footer').hide();
		$('#choose_goods_bt').hide();
		$('#choose_goods_tr').hide();
	} else {
		// 展示审核按钮
		$('.modal-footer').show();
		$('#choose_goods_bt').hide();//show()
		$('#choose_goods_tr').show();
	}
	
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
					$('#goodsOrderNoSpan').html(order.orderNo);
				}
				if(order.addTime != null && order.addTime != undefined){
					$('#updateTimeSpan').html(unixFormatFullStr(order.addTime));
				}
				if(order.storeName != null && order.storeName != undefined){
					$('#storeChineseNameSpan').html(order.storeName);
				}
				
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
						htmlStr=htmlStr + 
							"<tr><td><input type='text' class='form-control' readonly='readonly' name='goodsNo' value='"+orderItem.goodsNo+"'>"+
							"<input type='hidden' name='orderItemId' value='"+orderItem.id+"'></td>"+
							"<td><input type='text' class='form-control' readonly='readonly' value='"+orderItem.goodsName+"'></td>"+
							"<td><input type='text' class='form-control' readonly='readonly' value='"+orderItem.goodsModel+"'></td>"+
							"<td><input type='text' class='form-control' readonly='readonly' value='"+orderItem.goodsUnit+"'></td>"+
							"<td><input type='text' class='form-control' name='quantity' value='"+orderItem.orderQuantity+"'></td>"+
							"<td><input class='instoreTime' name='instoreTimeStr' value='"+unixFormatStr(orderItem.instoreTime)+"'/></td>"+
							"<td><input type='text' class='form-control' id='memo1' name='memo' value='"+orderItem.memo+"'></td>"+
							"<td class='removeGoods'><a href='###'><middle>移除</middle></a></td></tr>"
					}
					$("#choose_goods_tbody").append(htmlStr);
					// 初始化时间插件
					initDaterangepicker('instoreTime');
				}
				if( type == 'edit' ){
					$('#order_id').val(order.id);
					$("#save_btn").unbind();
					$("#save_btn").click(function(){
					  	updateOrder();
					  	$("#save_btn").attr("disabled",false);
					});
					// 添加绑定事件
					$(".removeGoods").click(function(){
					  	removeGoods(this);
					});
					$(".removeGoods").show();
					$(".operator_t").show();
				} else {
					$(".removeGoods").unbind();
					$(".removeGoods").hide();
					$(".operator_t").hide();
				}
			}
		}
	})
}

// 移除指定对象的父元素
function removeGoods(obj){
	$(obj).parent().remove();
}

// 添加用户订货单
function updateOrder(){
	$("#save_btn").attr("disabled",true);
    if (parameterValidator()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditOrderForm").serialize();
		$.ajax({
			url: '/order/updateGoodsOrder',
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
    } else {
    	$("#save_btn").attr("disabled",false);
    }
}

// 向下填充时间
function copyFirstTime(className){
	var instoreTimeStr='';
	$('.'+className).each(function(index){
		if( index == 0 ){
			instoreTimeStr = $(this).val();
			if( instoreTimeStr == undefined || instoreTimeStr == '' ){
				// alert('第一行时间为空，无法自动填充');
				// return;
				instoreTimeStr = dateFormatStr(new Date(new Date() - 3));
				$(this).val(instoreTimeStr)
			}
		} else if( $(this).val() == undefined || $(this).val() == '' ){
			$(this).val(instoreTimeStr);
		}
	});
}

// 初始化日历
function initDaterangepicker(className){
	$('.'+className).daterangepicker({
		 startDate: moment().startOf('day'),
		 minDate: moment().startOf('day'),
         dateLimit : {  
             days : 90
         }, //起止时间的最大间隔  
         showDropdowns : true,  
         showWeekNumbers : false, //是否显示第几周  
         timePicker : false, //是否显示小时和分钟  
         timePickerIncrement : 60, //时间的增量，单位为分钟  
         timePicker12Hour : false, //是否使用12小时制来显示时间  
         opens : 'right', //日期选择框的弹出位置  
         buttonClasses : [ 'btn btn-default' ],  
         applyClass : 'btn-small btn-primary blue',  
         cancelClass : 'btn-small',  
         separator : ' to ', 
         autoUpdateInput:false,
         locale : {  
             applyLabel : '确定',  
             cancelLabel : '取消',  
             fromLabel : '起始时间',  
             toLabel : '结束时间',  
             customRangeLabel : '自定义',  
             daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],  
             monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',  
                     '七月', '八月', '九月', '十月', '十一月', '十二月' ],  
             firstDay : 1  ,
             format : 'YYYY-MM-DD', //控件中from和to 显示的日期格式  
             //format : 'YYYY-MM-DD HH:mm:ss', //控件中from和to 显示的日期格式  
         },
	  	 singleDatePicker: true
	}, function(start, end, label) {
	  	// console.log(start.toISOString(), end.toISOString(), label);
	  	//$('#className').val(start.format('YYYY-MM-DD')); 
	});
	
	$('.'+className).each(function(index){
		$(this).data('daterangepicker').autoUpdateInput=true;
	});
}

function parameterValidator(){
	if( $("[name='goodsNo']") == undefined || $("[name='goodsNo']").length < 1 ){
		alert('请至少选择一款商品');
		return false;
	}
	var quantitys=$("#addOrEditOrderForm").find("[name='quantity']").serialize();
	if( quantitys == undefined || quantitys == ''){
		alert('请至少选择一款商品');
		return false;
	}
	quantitys = quantitys.split('&');
	if(quantitys == undefined || quantitys.length < 1 ){
		alert('请至少选择一款商品');
		return false;
	}
	for(var i=0;i<quantitys.length;i++){
		var quantity = quantitys[i].split('=')[1];
		if(isNaN(quantity)){
			alert('请准确填写 第'+(i+1)+'行商品数量');
			return false;
		} else if( quantity < 1) {
			alert('第'+(i+1)+'行商品数量不能为小于1');
			return false;
		}
	}
	
	var instoreTimeStrs=$("#addOrEditOrderForm").find("[name='instoreTimeStr']").serialize();
	if( instoreTimeStrs == undefined || instoreTimeStrs == ''){
		alert('请至少选择一款商品');
		return false;
	}
	instoreTimeStrs = instoreTimeStrs.split('&');
	if(instoreTimeStrs == undefined || instoreTimeStrs.length < 1 ){
		alert('请至少选择一款商品');
		return false;
	}
	for(var i=0;i<instoreTimeStrs.length;i++){
		var instoreTimeStr = instoreTimeStrs[i].split('=')[1];
		if(instoreTimeStr == undefined || instoreTimeStr == ''){
			alert('请选择 第'+(i+1)+'行商品要货日期');
			return false;
		}
	}
	return true;
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
	    	quantity: {
	        	validators: {
	            	notEmpty: {
	                	message: '请输入订货单名称'
	            	}
	        	}
	       	},
	        instoreTimeStr: {
	        	validators: {
	            	notEmpty: {
	                        message: '请选择订货单负责人'
	                }
	            }
	        }
	    }   
	});

}

</script>