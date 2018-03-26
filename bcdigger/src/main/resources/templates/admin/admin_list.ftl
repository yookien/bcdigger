
<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>系统操作员管理 </h2>
                    <div class="clearfix"></div>
                  </div>
                  <div style="float:right;margin:0px 50px;font-size:20px">
                  	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加</button>
                  	<!-- <i class="fa fa-plus" data-toggle="modal" data-target="#myModal"></i> -->
                  </div>
                  <div class="x_content">
                    
 	 <table id="datatable" class="table table-striped table-bordered">
		<thead>
        	<tr>
            	<th>序号</th>
            	<th>登录名</th>
                <th>昵称</th>
                <th>手机</th>
                <th>邮箱</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
        </thead>
		<tbody>
			<#if pageInfo??  >
			<#list pageInfo.list as admin>
            <tr>          	
	            	<td>${admin_index+1}</td>
	            	<td>${admin.name}</td>
	            	<td>${admin.nickname}</td>
	            	<td>${admin.mobile}</td>
	            	<td>${admin.email}</td>
	            	<td><#if admin.state==1>正常<#else>停用</#if></td>
	            	<td><a class="fa fa-pencil-square" style="font-size:20px" title="修改"></a>
	            	<span style="margin-right:20px;margin-left:10px"></span>
	            	<a class="fa fa-close" style="font-size:20px;color:red" title="失效"></a></td>
            </tr>
            </#list>
            </#if>
        </tbody>
    </table>
 </div>
 <!-- 分页信息 ,注意要放到ul元素里面-->
 <div style="float:right"><ul id='pages'></ul></div>
 <#include "/lib/pages.ftl">
 
                </div>
              </div>
              
              
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">  
    <div class="modal-dialog" role="document">  
        <div class="modal-content">  
            <div class="modal-header">  
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>  
                <h4 class="modal-title" id="myModalLabel">添加系统操作员</h4>  
            </div>  
            <div class="modal-body">  
                    <form class="form-horizontal form-label-left input_mask" id="adminForm">

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control has-feedback-left" id="name" name="name" placeholder="登录名">
                        <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                      </div>

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control" id="nickname" name="nickname" placeholder="真实姓名">
                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                      </div>
                      
                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                      	<input type="password" class="form-control has-feedback-left" id="password" name="password" placeholder="登录密码">
                        <span class="fa fa-sign-in form-control-feedback left" aria-hidden="true"></span>
                      </div>

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <input type="text" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="确认密码">
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
                    </form>
                 
               
            </div>  
            <div class="modal-footer">  
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
                <button type="button" onclick="addAdminInfo()" class="btn btn-primary">保存</button>  
            </div>  
        </div>  
    </div>  
</div> 

<script type="text/javascript">
function addAdminInfo(){
	$.ajax({
        type: "post",
        dataType: "html",
        url: "/admin/addAdmin",
        success: function (data) {
            if (data == 1) {
            	
            	goto(request_url)
            }
        }
    })
}
$(function(){
	$('#adminForm').bootstrapValidator({
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
	                        field: 'username',
	                        message: '密码不能跟登录名相同'
	                    }
	                }
	            },
	           
	        }
	});
});

</script>