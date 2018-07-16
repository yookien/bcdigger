<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>门店管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="addStoreEvent()">添加门店</button>
      </div>

	  <div class="x_content" id="Store_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加门店 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加门店</h4>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditStoreForm">
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">门店编号</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="storeCode" name="storeCode" placeholder="门店编号">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">门店名称</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="门店名称">
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
	                        <input type="text" class="form-control" id="storeProvince" name="storeProvince" placeholder="所属省">
	                      </div>
	                      
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">所属城市</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6">
	                        <input type="text" class="form-control" id="storeCity" name="storeCity" placeholder="所属城市">
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
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">门店主图</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12">
	                        <input type="text" class="form-control" id="storeImages" name="storeImages" placeholder="门店主图">
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
	                        <input type="text" class="form-control" id="storeImage1" name="storeImage1" placeholder="店面图片1">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片2</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="storeImage2" name="storeImage2" placeholder="店面图片2">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片3</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="storeImage3" name="storeImage3" placeholder="店面图片3">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">店面图片4</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="storeImage4" name="storeImage4" placeholder="店面图片4">
	                      </div>
						</div>
						
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">预约限制</label>
	                      <div class="col-md-4 col-sm-4 col-xs-6 ">
	                        <input type="text" class="form-control" id="appointLimit" name="appointLimit" value="1" placeholder="预约限制">
	                      </div>
	                      
	                      <label class="control-label col-md-2 col-sm-2 col-xs-6 ">门店类型</label>
	                      <div class="col-md-4 col-sm-4 col-xs-86 ">
	                         <select id="storeType" name="storeType" class="form-control" required>
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
						  
					   <input type="hidden" name="id" id="store_id" value="0"> 
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
var ajax_contents='Store_datas';

$(function(){
	// 查询门店信息
	getStore();
	// 初始化参数校验
	initValidator();
});

// 分页查询门店信息
function getStore(){
	ajax_request_url='/store/getStores';
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



// 打开新增门店页面
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



// 初始化 添加、编辑门店校验
function initValidator(){

	$('#addOrEditStoreForm').bootstrapValidator({
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
	                	message: '请输入门店名称'
	            	}
	        	}
	       	},
	        adminId: {
	        	validators: {
	            	notEmpty: {
	                        message: '请选择门店负责人'
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