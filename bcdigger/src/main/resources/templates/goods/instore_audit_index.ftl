<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>收货审核管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div class="well" style="overflow: auto">
      	<form class="form-horizontal form-label-left input_mask" id="searchForm">
      		<div class="col-md-10 col-sm-10 col-xs-12">
      		 
        		<label class="control-label" style="float:left;padding-top: 8px;">门店名称： </label>
            	<input id="search_store_name" name="goodsInstoreBiz.storeChineseName" class="form-control"  type="text" style="float:left;width:250px;margin-right:15px">
            	
            	<label class="control-label" style="float:left;padding-top: 8px;">收货人姓名： </label>
            	<input id="search_operator_name" name="goodsInstoreBiz.operatorName" class="form-control"  type="text" style="float:left;width:150px;margin-right:15px">
           		
			 	<button type="button" style="float:left;margin-left:15px" class="btn btn btn-primary" onclick="getGoodsInStoreAudits()">查询</button>
			</div>
		</form>
		</div>

	  <div class="x_content" id="instore_audit_datas">
		 
	  </div>
    </div>
</div>



<!-- 收货单审核 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">收货单审核</h4>
            </div>
            <div class="modal-body">
            	<h2>弈杰收货单</h2>  
      			<div></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close_btn">暂不审核</button>  
                <button type="button" class="btn btn-primary" id="save_btn">审核通过</button>
            </div>
        </div>  
    </div>  
</div>

<script type="text/javascript">
// ajax分页参数，待优化
var ajax_request_url='';
var ajax_pars='';
var ajax_contents='instore_audit_datas';

$(function(){
	// 查询需审核的收货信息
	getGoodsInStoreAudits();
});

// 分页查询需审核的收货信息
function getGoodsInStoreAudits(){
	ajax_request_url='/goods/getGoodsInstoreAudits';
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



// 打开收货明细信息
function addStoreEvent(){
	// 重设form
	$('#addOrEditStoreForm')[0].reset();
	$('#myModalLabel').html('添加门店信息');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addStore();
	});
}

// 添加门店信息
function addStore(){
	$("#addOrEditStoreForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditStoreForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditStoreForm").serialize();
		$.ajax({
			url: '/store/addStore',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getStore();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}


// 打开编辑门店页面
function editStore(id){
	// 重设form
	$('#addOrEditStoreForm')[0].reset();
	$('#myModalLabel').html('编辑门店信息');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='id='+id;
	$.ajax({
		url: '/store/getStore',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var store=json.store;
				if(store == undefined){
					return;
				}
				if(store.storeCode != null && store.storeCode != undefined){
					$('#storeCode').val(store.storeCode);
				}
				if(store.chineseName != null && store.chineseName != undefined){
					$('#chineseName').val(store.chineseName);
				}
				if(store.phone != null && store.phone != undefined){
					$('#phone').val(store.phone);
				}
				
				if(store.mobile != null && store.mobile != undefined){
					$('#mobile').val(store.mobile);
				}
				if(store.address != null && store.address != undefined){
					$('#address').val(store.address);
				}
				if(store.direction != null && store.direction != undefined){
					$('#direction').val(store.direction);
				}
				
				if(store.openHour != null && store.openHour != undefined){
					$('#openHour').val(store.openHour);
				}
				if(store.closeHour != null && store.closeHour != undefined){
					$('#closeHour').val(store.closeHour);
				}
				if(store.englishName != null && store.englishName != undefined){
					$('#englishName').val(store.englishName);
				}
				
				
				
				if(store.englishAddress != null && store.englishAddress != undefined){
					$('#englishAddress').val(store.englishAddress);
				}
				if(store.locationImage != null && store.locationImage != undefined){
					$('#locationImage').val(store.locationImage);
				}
				if(store.storeImages != null && store.storeImages != undefined){
					$('#storeImages').val(store.storeImages);
				}
				
				if(store.storeProvince != null && store.storeProvince != undefined){
					$('#storeProvince').val(store.storeProvince);
				}
				if(store.storeCity != null && store.storeCity != undefined){
					$('#storeCity').val(store.storeCity);
				}
				if(store.cityArea != null && store.cityArea != undefined){
					$('#cityArea').val(store.cityArea);
				}
				
				if(!isNaN(store.isOpen)){
					$("[targetId='isOpen']").each(function(){
						if($(this).find("[type='radio']").attr('value')==store.isOpen){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#isOpen").val(store.isOpen);
				}
				
				if(store.bmapPosition != null && store.bmapPosition != undefined){
					$('#bmapPosition').val(store.bmapPosition);
				}
				if(store.searchUrl != null && store.searchUrl != undefined){
					$('#searchUrl').val(store.searchUrl);
				}
				if(store.bus != null && store.bus != undefined){
					$('#bus').val(store.bus);
				}
				
				if(store.subway != null && store.subway != undefined){
					$('#subway').val(store.subway);
				}
				if(store.nearby != null && store.nearby != undefined){
					$('#nearby').val(store.nearby);
				}
				if(store.email != null && store.email != undefined){
					$('#email').val(store.email);
				}
				
				if(store.storeImage1 != null && store.storeImage1 != undefined){
					$('#storeImage1').val(store.storeImage1);
				}
				if(store.storeImage2 != null && store.storeImage2 != undefined){
					$('#storeImage2').val(store.storeImage2);
				}
				if(store.storeImage3 != null && store.storeImage3 != undefined){
					$('#storeImage3').val(store.storeImage3);
				}
				if(store.storeImage4 != null && store.storeImage4 != undefined){
					$('#storeImage4').val(store.storeImage4);
				}
				
				if(store.appointLimit != null && store.appointLimit != undefined){
					$('#appointLimit').val(store.appointLimit);
				}
				
				
				if(!isNaN(store.isBooking)){
					$("[targetId='isBooking']").each(function(){
						if($(this).find("[type='radio']").attr('value')==store.isBooking){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#isBooking").val(store.isBooking);
				}
				
				if(!isNaN(store.id)){
					$('#store_id').val(store.id);
				}
				
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateStore();
				});
			}
		}
	})
}

// 添加用户门店
function updateStore(){
	$("#addOrEditStoreForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditStoreForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditStoreForm").serialize();
		$.ajax({
			url: '/store/updateStore',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getStore();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}




</script>