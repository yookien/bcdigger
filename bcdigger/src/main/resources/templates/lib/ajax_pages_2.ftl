<script src="/js/bootstrap-paginator.min.js"></script>
<#if pageInfo?? && pageInfo.list?? && pageInfo.list?size!=0>
	<script type="text/javascript">
	$(function(){
		 $('#pages_2').bootstrapPaginator({
		        bootstrapMajorVersion:3,
		        currentPage: ${(pageInfo.pageNum)!1},
		        numberOfPages: ${(pageInfo.pageNumCount)!5},
		        totalPages:${(pageInfo.pages)!1},
		        itemTexts: function (type, page, current) {
					switch (type) {
						case "first": return "首页";
						case "prev": return "上一页";
						case "next": return "下一页";
						case "last": return "末页";
						case "page": return page;
					}//默认显示的是第一页。
				},
				onPageClicked: function (event, originalEvent, type, page){//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
					if(ajax_pars=='')
						temp_pars = 'pageNum='+page;
					else
						temp_pars = ajax_pars+'&pageNum='+page;
					$.ajax({
						url: ajax_request_url,
						type:'POST',
						data: temp_pars,
						dataType:'html',
						success:function (data) {
							if (data != "") {
				            	$('#'+ajax_contents).html(data);
				            }
						}
					})
				}
		  });
		
	});
</script>
</#if>
