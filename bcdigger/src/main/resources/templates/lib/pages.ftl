<script src="/js/bootstrap-paginator.min.js"></script>
<#if pageInfo??  >
<script type="text/javascript">
$(function(){
	//alert(request_url);
	 $('#pages').bootstrapPaginator({
	        bootstrapMajorVersion:3,
	        currentPage: ${(pageInfo.pageNum)!1},
	        numberOfPages: ${(pageInfo.pageSize)!1},
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
				$.ajax({
					url:request_url,
					type:'POST',
					data:{'pageNum':page},
					dataType:'html',
					success:function (data) {
						
						if (data != "") {
			            	$('#contents').html(data);
			            }
						//var page_count=callback.page_count;
						//var page_cont=callback.page_content;
						//$('contents').append(page_cont);
						//$('#contents').text(page_count)
					}
				})
			}
	  });
	
});
</#if>
</script>
