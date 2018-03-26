<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>系统操作员管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <div style="float:right;margin:0px 50px;font-size:20px">
      	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加菜单</button>
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
	                        <input type="text" class="form-control" id="isLeaf" name="isLeaf" placeholder="是否有子节点">
	                      </div>
	                    </div>
						<div class="form-group">  
	                      <label class="control-label col-md-2 col-sm-2 col-xs-12 ">状态</label>
	                      <div class="col-md-10 col-sm-10 col-xs-12 ">
	                        <input type="text" class="form-control" id="state" name="state" placeholder="状态">
	                      </div>
                      </div>
						
                 </form>
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
                <button type="button" class="btn btn-primary" id="save_btn" onclick="addSysMenus();">保存</button>  
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
					$(".close").click();
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