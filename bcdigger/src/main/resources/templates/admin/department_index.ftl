<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>部门管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="addDepartmentEvent()">添加用户部门</button>
      </div>

	  <div class="x_content" id="Department_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加部门 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加部门</h4>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditDepartmentForm">
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">部门名称</label>
	                      <div class="col-md-6 col-sm-6 col-xs-8 ">
	                        <input type="text" class="form-control" id="name" name="name" placeholder="部门名称">
	                      </div>
						</div>
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">部门负责人</label>
	                      <div class="col-md-6 col-sm-6 col-xs-8">
	                        <input type="text" class="form-control" id="adminId" name="adminId" placeholder="部门负责人">
	                        <a><i class="fa fa-search"></i></a>
	                      </div>
						</div>
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">显示顺序</label>
	                      <div class="col-md-6 col-sm-6 col-xs-8 ">
	                        <input type="text" class="form-control" id="displayOrder" name="displayOrder" placeholder="显示顺序">
	                      </div>
						</div>
						<div class="form-group"> 
						 	<div class="form-group has-feedback col-md-6 col-sm-6 col-xs-12" >
		                        <label class="control-label col-md-4 col-sm-4 col-xs-8">状态</label>
		                        <input id="state" name="state" type="hidden">
		                        <div class="col-md-8 col-sm-8 col-xs-12">
		                          <div class="btn-group" data-toggle="buttons">
		                            <label name="state_label" onclick="clickRadioBtn(this)" targetId="state" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
		                              <input  type="radio"  value="1" data-parsley-multiple="state"> &nbsp; 有效 &nbsp;
		                            </label>
		                            <label name="state_label" onclick="clickRadioBtn(this)" targetId="state" class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
		                              <input  type="radio"  value="0" data-parsley-multiple="state"> 无效
		                            </label>
		                          </div>
		                        </div>
	                        </div>
                      </div>
					  <input type="hidden" name="id" id="department_id" value="0"> 
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
var ajax_contents='Department_datas';

$(function(){
	// 查询部门信息
	getDepartment();
	// 初始化参数校验
	initValidator();
});

// 分页查询部门信息
function getDepartment(){
	ajax_request_url='/admin/getDepartments';
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



// 打开新增部门页面
function addDepartmentEvent(){
	// 重设form
	$('#addOrEditDepartmentForm')[0].reset();
	$('#myModalLabel').html('添加部门信息');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addDepartment();
	});
}

// 添加部门信息
function addDepartment(){
	$("#addOrEditDepartmentForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditDepartmentForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditDepartmentForm").serialize();
		$.ajax({
			url: '/admin/addDepartment',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getDepartment();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}


// 打开编辑部门页面
function editDepartment(id){
	// 重设form
	$('#addOrEditDepartmentForm')[0].reset();
	$('#myModalLabel').html('编辑部门信息');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='id='+id;
	$.ajax({
		url: '/admin/getDepartment',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var department=json.department;
				if(department == undefined){
					return;
				}
				if(department.name != null && department.name != undefined){
					$('#name').val(department.name);
				}
				if(department.adminId != null && department.adminId != undefined){
					$('#adminId').val(department.adminId);
				}
				if(department.displayOrder != null && department.displayOrder != undefined){
					$('#displayOrder').val(department.displayOrder);
				}
				if(!isNaN(department.state)){
					$("[targetId='state']").each(function(){
						if($(this).find("[type='radio']").attr('value')==department.state){
							$(this).attr("class","btn btn-primary");
						}else{
							$(this).attr("class","btn btn-default");
						}
				    })
					$("#state").val(department.state);
				}
				if(!isNaN(department.id)){
					$('#department_id').val(department.id);
				}
				
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateDepartment();
				});
			}
		}
	})
}

// 添加用户部门
function updateDepartment(){
	$("#addOrEditDepartmentForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditDepartmentForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditDepartmentForm").serialize();
		$.ajax({
			url: '/admin/updateDepartment',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getDepartment();
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}



// 初始化 添加、编辑部门校验
function initValidator(){

	$('#addOrEditDepartmentForm').bootstrapValidator({
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
	                	message: '请输入部门名称'
	            	}
	        	}
	       	},
	        adminId: {
	        	validators: {
	            	notEmpty: {
	                        message: '请选择部门负责人'
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