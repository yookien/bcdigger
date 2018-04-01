<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>系统菜单</h3>
                <ul class="nav side-menu">
		                  
				<#if level1List?? && level1List?size != 0>
					<#list level1List as menu1>
						<#if (menu1.isLeaf)?? && menu1.isLeaf==1>
							<li><a><i class="fa ${(menu1.menuLogoUrl)!'fa-cog'}"></i> ${(menu1.menuName)!} <span class="fa fa-chevron-down"></span></a>
								<ul class="nav child_menu">
									<#if level2List?? && level2List?size != 0>
										<#list level2List as menu2>
											<#if menu1.id=menu2.parentId>
												<#if (menu2.isLeaf)?? && menu2.isLeaf==1>
													<li><a><i class="fa ${(menu2.menuLogoUrl)!}"></i> ${(menu2.menuName)!} <span class="fa fa-chevron-down"></span></a>
														<ul class="nav child_menu">
															<#if level3List?? && level3List?size != 0>
																<#list level3List as menu3>
																	<li><a href="javascript:goto('${(menu3.menuUrl)!}')">${(menu3.menuName)!}</a></li>
																</#list>
															</#if>
														</ul>
	                  								</li>
												<#else>
													<li><a href="javascript:goto('${(menu2.menuUrl)!}')">${(menu2.menuName)!}</a></li>
												</#if>
											</#if>
										</#list>
									</#if>
						 		</ul>
	                  		</li>
                  		<#else>
							<li><a href="javascript:goto('${(menu1.menuUrl)!}')"><i class="fa fa-home"></i> ${(menu1.menuName)!}</a></li>
						</#if>
					</#list>
				</#if>     
                
                <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="javascript:goto('/admin/adminIndex')">管理员列表</a></li>
                      <li><a href="javascript:goto('/admin/sysMenusIndex')">菜单管理</a></li>
                      <li><a href="javascript:goto('/admin/adminRoleIndex')">角色管理</a></li>
                      <li><a href="javascript:goto('/admin/roleMenuRefIndex')">角色权限管理</a></li>
                    </ul>
                  </li>
                <!--
                  <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="form.html">General Form</a></li>
                      <li><a href="form_advanced.html">Advanced Components</a></li>
                      <li><a href="form_validation.html">Form Validation</a></li>
                      <li><a href="form_wizards.html">Form Wizard</a></li>
                      <li><a href="form_upload.html">Form Upload</a></li>
                      <li><a href="form_buttons.html">Form Buttons</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="general_elements.html">General Elements</a></li>
                      <li><a href="media_gallery.html">Media Gallery</a></li>
                      <li><a href="typography.html">Typography</a></li>
                      <li><a href="icons.html">Icons</a></li>
                      <li><a href="glyphicons.html">Glyphicons</a></li>
                      <li><a href="widgets.html">Widgets</a></li>
                      <li><a href="invoice.html">Invoice</a></li>
                      <li><a href="inbox.html">Inbox</a></li>
                      <li><a href="calendar.html">Calendar</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">Tables</a></li>
                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-clone"></i>Layouts <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="fixed_sidebar.html">Fixed Sidebar</a></li>
                      <li><a href="fixed_footer.html">Fixed Footer</a></li>
                    </ul>
                  </li>
                  -->
                </ul>
              </div>
            </div>
            <!-- /menu footer buttons -->
            <!-- <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div> -->
            <!-- /menu footer buttons -->
            
<script type="text/javascript">
	var request_url;
	function goto(requesturl){
		request_url = requesturl;
		$.ajax({
	        type: "post",
	        dataType: "html",
	        url: request_url,
	        success: function (data) {
	            if (data != "") {
	            	$('#contents').html(data);
	            }
	        }
	    });
		
	}
</script>