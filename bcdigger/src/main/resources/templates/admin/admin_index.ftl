<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>操作管理员管理 </h2>
        <div class="clearfix"></div>
      </div>
      	<div class="well" style="overflow: auto">
      	<form class="form-horizontal form-label-left input_mask" id="searchForm">
      		<div class="col-md-10 col-sm-10 col-xs-12">
      		 
        		<label class="control-label" style="float:left;padding-top: 8px;">操作员姓名： </label>
            	<input id="search_name" name="name" class="form-control"  type="text" style="float:left;width:150px;margin-right:15px">
        	<!-- </div>
	        <div class="col-md-7 col-sm-7 col-xs-12"> -->
           		<label class="control-label" style="float:left;padding-top: 8px;">添加时间： </label>
			    <div id="reportrange_right" class="pull-left" style=" background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
			    	<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
			    	<span></span> <b class="caret"></b>
			    	<input id="start_time"  name="startTime" type="hidden" >
			    	<input id="end_time" name="endTime" type="hidden" >
			 	</div>
			 	<button type="button" style="float:left;margin-left:15px" class="btn btn-info" onclick="getAdmins()">查询</button>
			</div>
		   	
		    <div class="col-md-2 col-sm-2 col-xs-12">
			    
			 	<button type="button" style="float:right;" class="btn btn-primary" data-toggle="modal" data-target="#adminModal" onclick="addAdminEvent()">添加操作员</button>
			   
			 </div>
		</form>
		</div>

	  <div class="x_content" id="admin_list">
		 
	  </div>
    </div>
</div>

<div class="modal fade" id="adminModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">  
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_btn">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加系统操作员</h4>  
            </div>  
            <div class="modal-body">  
                    <form class="form-horizontal form-label-left input_mask" id="addOrEditAdminForm">
						<input type="hidden" id="admin_id" name="id">
                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control has-feedback-left" id="name" name="name" placeholder="登录名">
                        <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                      </div>

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control" id="nickname" name="nickname" placeholder="真实姓名">
                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                      </div>
                      
                      <div id="password_div" class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                      	<input type="password" class="form-control has-feedback-left" id="password" name="password" placeholder="登录密码">
                        <span class="fa fa-sign-in form-control-feedback left" aria-hidden="true"></span>
                      </div>

                      <div id="confirmPassword_div" class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="确认密码">
                        <span class="fa fa-sign-in form-control-feedback right" aria-hidden="true"></span>
                      </div>

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control has-feedback-left" id="mobile" name="mobile" placeholder="手机">
                        <span class="fa fa-mobile form-control-feedback left" aria-hidden="true"></span>
                      </div>
                      
                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                        <span class="fa fa-envelope form-control-feedback right" aria-hidden="true"></span>
                      </div>

					  <div class="form-group">
                        
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12 ">职位</label>
                        <div class="col-md-10 col-sm-10 col-xs-12 ">
                          <input type="text" class="form-control" id='duty' name='duty' placeholder="职位">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12">家庭地址</label>
                        <div class="col-md-10 col-sm-10 col-xs-12">
                          <input type="text" class="form-control" id="address" name="address" placeholder="家庭地址">
                        </div>
                      </div>
                      <div class="form-group">
                      	<div  class="form-group has-feedback col-md-6 col-sm-6 col-xs-12">
	                        <label class="control-label col-md-4 col-sm-4 col-xs-8">性别</label>
	                        <input id="sex" name="sex" type="hidden">
	                        <div class="col-md-8 col-sm-8 col-xs-12">
	                          <div class="btn-group" data-toggle="buttons" id='sex_div'>
	                            <label onclick="clickSexBtn(this)" name="sex_label"class="btn btn-default" >
	                              <input  type="radio"  value="0" data-parsley-multiple="sex"> &nbsp; 男性 &nbsp;
	                            </label>
	                            <label onclick="clickSexBtn(this)" name="sex_label" class="btn btn-primary " >
	                              <input  type="radio"  value="1" data-parsley-multiple="sex"> 女性
	                            </label>
	                          </div>
	                        </div>
	                    </div>
                      	<div class="form-group has-feedback col-md-6 col-sm-6 col-xs-12" >
	                        <label class="control-label col-md-4 col-sm-4 col-xs-8">状态</label>
	                        <input id="state" name="state" type="hidden">
	                        <div class="col-md-8 col-sm-8 col-xs-12">
	                          <div class="btn-group" data-toggle="buttons">
	                            <label name="state_label" onclick="clickStateBtn(this)" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                              <input  type="radio"  value="1" data-parsley-multiple="state"> &nbsp; 有效 &nbsp;
	                            </label>
	                            <label name="state_label" onclick="clickStateBtn(this)" class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                              <input  type="radio"  value="0" data-parsley-multiple="state"> 无效
	                            </label>
	                          </div>
	                        </div>
                        </div>
                        
                      </div>
                    </form>
                 
               
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
                <button type="button" id="save_btn" class="btn btn-primary">保存</button>  
            </div>  
        </div>  
    </div>  
</div>

<script type="text/javascript">
// ajax分页参数，待优化
var ajax_request_url='';
var ajax_pars='';
var ajax_contents='admin_list';

$(function(){
	// 查询管理员信息
	getAdmins();
	//初始化日历选择控件
	initDateRangePicker();
	// 初始化参数校验
	initValidator();
});

function clickStateBtn(btn){
	$("[name='state_label']").each(function(){
		if(btn==this){
			$(this).attr("class","btn btn-primary");
			$('#state').val($(this).find("[type='radio']").attr('value'));
		}else{
			$(this).attr("class","btn btn-default");
		}
    })
	
} 

function clickSexBtn(btn){
	$("[name='sex_label']").each(function(){
		if(btn==this){
			$(this).attr("class","btn btn-primary");
			$('#sex').val($(this).find("[type='radio']").attr('value'));
		}else{
			$(this).attr("class","btn btn-default");
		}
    })
} 

// 分页查询管理员信息
function getAdmins(type){
	ajax_request_url='/admin/getAdmins';
	var startTime='';
	var endTime='';
	var rangeDate = $('#reportrange_right span').html();
	if(rangeDate!=''&& typeof(rangeDate)!='undefined'){
		$('#start_time').val(rangeDate.split(" - ")[0]);
		$('#end_time').val(rangeDate.split(" - ")[1]);
	}
	ajax_pars=$("#searchForm").serialize();
	if(type==1){
		ajax_pars ="";
	}
	//ajax_pars='name='+$('#search_name').val() + '&startTime='+startTime+'&endTime='+endTime;
	//alert(ajax_pars);
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



// 打开新增菜单页面
function addAdminEvent(){
	// 重设form
	$('#addOrEditAdminForm')[0].reset();
	$('#myModalLabel').html('添加系统操作员信息');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addAdmin();
	});
}

// 添加系统菜单
function addAdmin(){
	
	$("#addOrEditAdminForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditAdminForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditAdminForm").serialize();
		$.ajax({
			url: '/admin/addAdmin',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}


// 打开编辑菜单页面
function editAdmin(id){
	// 重设form
	$('#addOrEditAdminForm')[0].reset();
	$('#myModalLabel').html('编辑系统操作员信息');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='id='+id;
	$.ajax({
		url: '/admin/getAdmin',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var admin=json.admin;
				if(admin==undefined){
					return;
				}
				if(admin.name!=null && admin.name!=undefined){
					$('#name').val(admin.name);
				}
				if(admin.nickname!=null && admin.nickname!=undefined){
					$('#nickname').val(admin.nickname);
				}
				if(admin.duty!=null && admin.duty!=undefined){
					$('#duty').val(admin.duty);
				}
				if(admin.mobile!=null && admin.mobile!=undefined){
					$('#mobile').val(admin.mobile);
				}
				if(admin.email!=null && admin.email!=undefined){
					$('#email').val(admin.email);
				}
				if(admin.address!=null && admin.address!=undefined){
					$('#address').val(admin.address);
				}
				if(!isNaN(admin.state)){
					$("[name='state_label']").each(function(){
						if($(this).find("[type='radio']").attr('value')==admin.state){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#state").val(admin.state);
				}
				if(!isNaN(admin.sex)){
					$("[name='sex_label']").each(function(){
						if($(this).find("[type='radio']").attr('value')==admin.sex){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#sex").val(admin.sex);
				}
				if(!isNaN(admin.id)){
					$('#admin_id').val(admin.id);
				}
				$("#password_div").hide();
				$("#confirmPassword_div").hide();
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateAdmin();
				});
			}
		}
	})
}

// 添加系统操作员
function updateAdmin(){
	$("#addOrEditAdminForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditAdminForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditAdminForm").serialize();
    alert(pars);
		$.ajax({
			url: '/admin/editAdmin',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					//var adminId=$('#admin_id').val();
					getAdmins(1);
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}
//初始化日历选择控件
function initDateRangePicker(){
	
	 //$('#reportrange_right span').html(moment().subtract( 1,'hours').format('YYYY-MM-DD HH:mm:ss') + ' - ' + moment().format('YYYY-MM-DD HH:mm:ss')); 
	 $('#reportrange_right span').html(moment().subtract( 1,'hours').format('YYYY-MM-DD') + ' - ' + moment().format('YYYY-MM-DD'));  
     
     $('#reportrange_right').daterangepicker(  
             {  
                 // startDate: moment().startOf('day'),  
                 //endDate: moment(),  
                 //minDate: '01/01/2012',    //最小时间  
                 maxDate : moment(), //最大时间   
                 dateLimit : {  
                     days : 30  
                 }, //起止时间的最大间隔  
                 showDropdowns : true,  
                 showWeekNumbers : false, //是否显示第几周  
                 timePicker : false, //是否显示小时和分钟  
                 timePickerIncrement : 60, //时间的增量，单位为分钟  
                 timePicker12Hour : false, //是否使用12小时制来显示时间  
                 ranges : {  
                     //'最近1小时': [moment().subtract('hours',1), moment()],  
                     '今日': [moment().startOf('day'), moment()],  
                     '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],  
                     '最近7日': [moment().subtract('days', 6), moment()],  
                     '最近30日': [moment().subtract('days', 29), moment()]  
                 },  
                 opens : 'right', //日期选择框的弹出位置  
                 buttonClasses : [ 'btn btn-default' ],  
                 applyClass : 'btn-small btn-primary blue',  
                 cancelClass : 'btn-small',  
                 //format : 'YYYY-MM-DD HH:mm:ss', //控件中from和to 显示的日期格式  
                 format : 'YYYY-MM-DD', //控件中from和to 显示的日期格式  
                 separator : ' to ',  
                 locale : {  
                     applyLabel : '确定',  
                     cancelLabel : '取消',  
                     fromLabel : '起始时间',  
                     toLabel : '结束时间',  
                     customRangeLabel : '自定义',  
                     daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],  
                     monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',  
                             '七月', '八月', '九月', '十月', '十一月', '十二月' ],  
                     firstDay : 1  
                 }  
             }, function(start, end, label) {//格式化日期显示框  
                   
             //$('#reportrange_right span').html(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));  
            $('#reportrange_right span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));  
            });  

}

// 初始化 添加、编辑菜单校验
function initValidator(){

	$('#addOrEditAdminForm').bootstrapValidator({
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
	                	message: '请输入系统登录名'
	            	}
	        	}
	       	},
	        nickname: {
	            validators: {
	               notEmpty: {
	                  message: '请输入操作员真实姓名'
	               }
	            }
	        },
	        email: {
	        	validators: {
	            	emailAddress: {
	                	message: '请输入正确的邮件地址'
	             	}
	        	}
	     	},
	        password: {
	        	validators: {
	            	notEmpty: {
	                        message: '请输入密码'
	                    },
	                    identical: {
	                        field: 'confirmPassword',
	                        message: '两次输入密码不一致'
	                    },
	                    different: {
	                        field: 'name',
	                        message: '密码不能跟登录名相同'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入确认密码'
	                    },
	                    identical: {
	                        field: 'password',
	                        message: '密码和确认密码不一致'
	                    },
	                    different: {
	                        field: 'name',
	                        message: '密码不能跟登录名相同'
	                    }
	                }
	            },
	           
	        }
	});

}

</script>