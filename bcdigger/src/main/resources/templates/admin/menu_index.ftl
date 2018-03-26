<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>系统菜单管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:left;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" onclick="getSysMenus(0)">返回主目录</button>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="addSysMenusEvent()">添加菜单</button>
      </div>

	  <div class="x_content" id="sysMenus_datas">
		 
	  </div>
    </div>
</div>



<!-- 添加菜单 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加系统菜单</h4>
            </div>
            <div class="modal-body">  
                   <form class="form-horizontal form-label-left input_mask" id="addOrEditSysMenusForm">
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">菜单名称</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="menuName" name="menuName" placeholder="菜单名称">
	                      </div>
						</div>
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">菜单logo</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="menuLogoUrl" name="menuLogoUrl" placeholder="菜单logo">
	                      </div>
	                    </div>
						<div class="form-group">  
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">菜单URL</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                      	<input type="test" class="form-control" id="menuUrl" name="menuUrl" placeholder="菜单URL">
	                      </div>
						</div>
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">菜单描述</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="menuDesc" name="menuDesc" placeholder="菜单描述">
	                      </div>
						</div>
						<div class="form-group">
                        	<label class="control-label col-md-2 col-sm-2 col-xs-12 ">显示顺序</label>
                        	<div class="col-md-10 col-sm-10 col-xs-12 ">
                          		<input type="text" class="form-control" id='displayOrder' name='displayOrder' placeholder="显示顺序">
                        	</div>
                        </div>
						<div class="form-group">
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">子节点</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                          <select class="select2_single form-control" tabindex="-1" id="isLeaf" name="isLeaf">
	                            <option value="0">无</option>
	                            <option value="1">有</option>
	                          </select>
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
					  <input type="hidden" name="id" id="menu_id" value="0"> 
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
var ajax_contents='sysMenus_datas';

$(function(){
	// 查询菜单信息
	getSysMenus(0);
	// 初始化参数校验
	initValidator();
});

// 分页查询菜单信息
function getSysMenus(parentId){
	ajax_request_url='/admin/getSysMenus';
	ajax_pars='parentId='+parentId+'&';
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
function addSysMenusEvent(){
	// 重设form
	$('#addOrEditSysMenusForm')[0].reset();
	$('#myModalLabel').html('添加系统菜单');
	$("#save_btn").unbind();
	$("#save_btn").click(function(){
	  	addSysMenus();
	});
}

// 添加系统菜单
function addSysMenus(){
	$("#addOrEditSysMenusForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditSysMenusForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditSysMenusForm").serialize();
		var parentId=$('#parentId').val();
		pars='parentId='+parentId+'&'+pars;
		$.ajax({
			url: '/admin/addMenu',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					getSysMenus(parentId);
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}


// 打开编辑菜单页面
function editSysMenus(id){
	// 重设form
	$('#addOrEditSysMenusForm')[0].reset();
	$('#myModalLabel').html('编辑系统菜单');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='id='+id;
	$.ajax({
		url: '/admin/getSysMenu',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var sysMenu=json.sysMenu;
				if(sysMenu==undefined){
					return;
				}
				if(sysMenu.menuName!=null && sysMenu.menuName!=undefined){
					$('#menuName').val(sysMenu.menuName);
				}
				if(sysMenu.menuLogoUrl!=null && sysMenu.menuLogoUrl!=undefined){
					$('#menuLogoUrl').val(sysMenu.menuLogoUrl);
				}
				if(sysMenu.menuUrl!=null && sysMenu.menuUrl!=undefined){
					$('#menuUrl').val(sysMenu.menuUrl);
				}
				if(sysMenu.menuDesc!=null && sysMenu.menuDesc!=undefined){
					$('#menuDesc').val(sysMenu.menuDesc);
				}
				if(!isNaN(sysMenu.displayOrder)){
					$('#displayOrder').val(sysMenu.displayOrder);
				}
				if(!isNaN(sysMenu.isLeaf)){
					$("#isLeaf").find("option[value="+sysMenu.isLeaf+"]").attr("selected",true);
				}
				if(!isNaN(sysMenu.state)){
					$("#state").find("option[value="+sysMenu.state+"]").attr("selected",true);
				}
				if(!isNaN(sysMenu.id)){
					$('#menu_id').val(sysMenu.id);
				}
				
				$("#save_btn").unbind();
				$("#save_btn").click(function(){
				  	updateSysMenus();
				});
			}
		}
	})
}

// 添加系统菜单
function updateSysMenus(){
	$("#addOrEditSysMenusForm").bootstrapValidator('validate');//提交验证  
    if ($("#addOrEditSysMenusForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
        var pars=$("#addOrEditSysMenusForm").serialize();
		$.ajax({
			url: '/admin/updateMenu',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					var parentId=$('#parentId').val();
					getSysMenus(parentId);
					$("#close_btn").click();
				}else{
					alert(json.result);
				}
			}
		})
    } 
}



// 初始化 添加、编辑菜单校验
function initValidator(){

	$('#addOrEditSysMenusForm').bootstrapValidator({
		message: '输入值不符合要求',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	    	menuName: {
	        	validators: {
	            	notEmpty: {
	                	message: '请输入菜单名称'
	            	}
	        	}
	       	},
	        menuLogoUrl: {
	            validators: {
	               notEmpty: {
	                  message: '菜单logo'
	               }
	            }
	        },
	        menuUrl: {
	        	validators: {
	            	notEmpty: {
	                	message: '请输入正确菜单URL'
	             	}
	        	}
	     	},
	        menuDesc: {
	        	validators: {
	            	notEmpty: {
	                        message: '请输入菜单描述'
	                }
	            }
	        },
	        displayOrder: {
	        	validators: {
	            	notEmpty: {
	                        message: '显示顺序'
	                }
	            }
	        },
	    }   
	});

}

</script>