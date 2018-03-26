<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>用户角色管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="addAdminRoleEvent()">添加用户角色</button>
      </div>

	  <div class="x_content" id="adminRole_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加角色 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加用户角色</h4>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditAdminRoleForm">
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">角色名称</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="roleName" name="roleName" placeholder="角色名称">
	                      </div>
						</div>
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">角色描述</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="roleDesc" name="roleDesc" placeholder="角色描述">
	                      </div>
						</div>
						<div class="form-group">  
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">状态</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                         <select class="select2_single form-control" tabindex="-1" id="state" name="state">
	                        	<option value="1">正常</option>
	                            <option value="0">停用</option>
	                          </select>
	                      </div>
                      </div>
					  <input type="hidden" name="id" id="role_id" value="0"> 
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
var ajax_contents='adminRole_datas';

$(function(){
	// 查询角色信息
	getAdminRole();
	// 初始化参数校验
	initValidator();
});

// 分页查询角色信息
function getAdminRole(){
	ajax_request_url='/admin/getAdminRoles';
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



// 打开新增角色页面
function addAdminRoleEvent(){
	// 重设form
	$('#addOrEditAdminRoleForm')[0].reset();
	$('#myModalLabel').html('添加用户角色');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addAdminRole();
	});
}

// 添加用户角色
function addAdminRole(){
	$("#addOrEditAdminRoleForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditAdminRoleForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditAdminRoleForm").serialize();
		$.ajax({
			url: '/admin/addAdminRole',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getAdminRole();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}


// 打开编辑角色页面
function editAdminRole(id){
	// 重设form
	$('#addOrEditAdminRoleForm')[0].reset();
	$('#myModalLabel').html('编辑用户角色');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='id='+id;
	$.ajax({
		url: '/admin/getAdminRole',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var adminRole=json.adminRole;
				if(adminRole==undefined){
					return;
				}
				if(adminRole.roleName!=null && adminRole.roleName!=undefined){
					$('#roleName').val(adminRole.roleName);
				}
				if(adminRole.roleDesc!=null && adminRole.roleDesc!=undefined){
					$('#roleDesc').val(adminRole.roleDesc);
				}
				if(!isNaN(adminRole.state)){
					$("#state").find("option[value="+adminRole.state+"]").attr("selected",true);
				}
				if(!isNaN(adminRole.id)){
					$('#role_id').val(adminRole.id);
				}
				
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateAdminRole();
				});
			}
		}
	})
}

// 添加用户角色
function updateAdminRole(){
	$("#addOrEditAdminRoleForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditAdminRoleForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditAdminRoleForm").serialize();
		$.ajax({
			url: '/admin/updateAdminRole',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getAdminRole();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}



// 初始化 添加、编辑角色校验
function initValidator(){

	$('#addOrEditAdminRoleForm').bootstrapValidator({
		message: '输入值不符合要求',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	    	roleName: {
	        	validators: {
	            	notEmpty: {
	                	message: '请输入角色名称'
	            	}
	        	}
	       	},
	        roleDesc: {
	        	validators: {
	            	notEmpty: {
	                        message: '请输入角色描述'
	                }
	            }
	        }
	    }   
	});

}

</script>