<link rel="styleSheet" href="/js/dTree/dtree.css" type="text/css" />
<script type="text/javascript" src="/js/dTree/wtree.js"></script>
  <div class="col-md-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>角色权限管理</h2>
        <div class="clearfix"></div>
      </div>
      <div class="x_content">
        <div class="row">
          <div class="col-sm-3 mail_list_column">
            <button class="btn btn-sm btn-success btn-block" type="button">角色</button>
            <ul id="roleUl" style="height: 100%;padding-left:10px;">
	            <#if roleList?? && roleList?size != 0>
		            <#list roleList as role>
			            <li name="role_li" style="cursor:pointer;list-style-type:none;" roleId="${(role.id)!0}">
			                <h3>${(role.roleName)!}</h3>
			            </li>
		            </#list>
	            </#if>
            </ul>
            
          </div>
          <!-- /MAIL LIST -->

          <!-- CONTENT MAIL -->
          <div class="col-sm-9 mail_view">
            <div class="inbox-body">
           
	            <input id="hdUserID" type="hidden" type="hidden" value="" />
				<font id="treeRoleName" size="4" color="#CD3700"></font>权限树信息：
				<input id="subApp" class="submit3" name="subApp" type="button" value="提交选中权限" />
				<div id="treearea" style="padding-top: 10px; font-size: 14px; height: 100%; overflow: auto;"></div>
            
            </div>

          </div>
          <!-- /CONTENT MAIL -->
        </div>
      </div>
    </div>
  </div>



<script type="text/javascript">
	var d =null;
	$(function(){
		// 创建权限树
		createTree();
		// 添加角色点击事件
		
		$("li[name='role_li']").click(function(){
			ajaxRolemenu(this);
		});
		// 保存角色权限菜单
		$('#subApp').click(function(){
			saveRolemenu();
		});
		
	});
	
	// 创建权限树
	function createTree(){
		d=new dTree('d','/js/dTree/image/system/menu/'),
		d.config.folderLinks=true;
		d.add(0,-1,'管理系统',"#")

		<#if menuList?? && menuList?size != 0>
	        <#list menuList as menu>
				d.add(${(menu.id)!0},${(menu.parentId)!0},'${(menu.menuName)!}','###');
			</#list>
        </#if>
        
		d.config.target = "main";
		d.config.useIcons = true;
		d.config.check=true;
		d.config.open=true;
		document.getElementById('treearea').innerHTML = d;
	}
	
	function checkTree(str){
		var funcs = eval(str);
		for(var n=0; n<funcs.funcs.length;n++){
			d.co(funcs.funcs[n].menudm).checked=true;
		}
	}
	
	function ajaxRolemenu(obj){
		$('#roleUl li').css("background-color","#FFFFFF");
		$(obj).css("background-color","#D6F5F5");
		var roleId=$(obj).attr('roleId');
		$('#hdUserID').val(roleId);
		//根据角色ID 查找 
		$("#treearea input[type='checkbox']").removeAttr("checked"); 
		$.ajax({
			   type: "POST", 
			   dataType:"json",
			   url: "/admin/getRoleMenuRefByRoleId.do",
			   data: {roleId:roleId},
	           success: function(msg){
					if(parseInt(msg.infoNo) == 1){
						checkTree(msg.str);
					}else if(parseInt(msg.infoNo) == 3){
						alert(msg.str);
					}else{
						return;
					}
				}
		 });
	}
	

	function saveRolemenu(){
		var menuIds=''
		var count=$('#treearea input[type="checkbox"]:checked');
		var size=count.size();
		var preid=$('#hdUserID').val();
		if(size>0){
			count.each(function(){
				menuIds+=this.value+","
			})
			menuIds=menuIds.substring(0,menuIds.length-1);
		}
		//绑定后的权限提交
		if(isNaN(preid) || preid<0){
			alert('数据不完整不能提交');
			return;
		}
	
		if(menuIds==''){
			alert('请勾选相应的数据');
			return;
		}
		$.ajax({			   
		   type: "POST", 
		   dataType:"json",
		   url: "/admin/saveRoleMenuRef.do",
		   data: {roleId:preid,menuIds:menuIds,state:1},
           success: function(msg){
				if(parseInt(msg.infoNo) == 1){
					alert("保存成功");
				}else {
					alert(msg.str);
				}
			}
	   });
	}		
	
	
</script>
