<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>门店收货管理 </h2>
        <div class="clearfix"></div>
      </div>
      
      <!-- <div class="well" style="overflow: auto">
      	<form class="form-horizontal form-label-left input_mask" id="searchForm">
      		<div class="col-md-10 col-sm-10 col-xs-12">
      		 
        		<label class="control-label" style="float:left;padding-top: 8px;">门店名称： </label>
            	<input id="search_store_name" name="storeChineseName" class="form-control"  type="text" style="float:left;width:250px;margin-right:15px">
            	
            	<label class="control-label" style="float:left;padding-top: 8px;">收货人姓名： </label>
            	<input id="search_operator_name" name="operatorName" class="form-control"  type="text" style="float:left;width:150px;margin-right:15px">
           		
			 	<button type="button" style="float:left;margin-left:15px" class="btn btn btn-primary" onclick="getGoodsInStoreAudits()">查询</button>
			</div>
		</form>
		</div> -->

	  <div class="x_content" id="instore_audit_datas">
		 
	  </div>
    </div>
</div>



<!-- 收货单审核 -->
<div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> 
    <div class="modal-dialog" role="document" style="width:800px">  
        <div class="modal-content">  
            <div class="modal-header">  
               <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_btn">  
                    <span aria-hidden="true"><i class="fa fa-times"></i></span>  
                </button>
                <h1 class="modal-title" id="myModalLabel" style="text-align:center;">弈杰收货单</h1>
            </div>
            <div class="modal-body">
            	<form class="form-horizontal form-label-left input_mask" id="auditInstoreForm">
            		<input type="hidden" id="admin_id" name="id">
            		<div class="col-md-12 col-sm-12 col-xs-12 form-group">
            			<p class="control-label col-md-3 col-sm-3 col-xs-12" stype="text-align: left;">订货单编号：<small id="goodsOrderNoSpan"> SKJD001</small></p>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">单据日期： <small id="updateTimeSpan">2018-07-01</small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">订货单种类：<small id="orderTypeSpan">牛奶</small></div>
            			<div class="control-label col-md-3 col-sm-3 col-xs-12">订货单状态：<small id="orderStateSpan">收货中</small></div>
                     </div>
                     <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                     	<div class="control-label col-md-3 col-sm-3 col-xs-12">客      户： <small id="storeChineseNameSpan">中央厨房</small></div>
            			<!-- <div class="control-label col-md-3 col-sm-3 col-xs-12">下  单   人：<small id="operatorNameSpan"> 张三</small></div> -->
            			<!-- <label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
            			<label class="control-label col-md-3 col-sm-3 col-xs-12"></label> -->
            		</div>
            		<table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>序号</th>
                          <th>物料编码</th>
                          <th>物料名称</th>
                          <th>规格型号</th>
                          <th>销售单位</th>
                          <th>订货总数量</th>
                          <th>已收货数量</th>
                          <th>本次收货数量</th>
                        </tr>
                      </thead>
                      <tbody id="instoreInfoTbody">
                        
                      </tbody>
                    </table>
            	</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="not_save_btn">取消收货</button>  
                <button type="button" class="btn btn-primary" id="save_btn">保存收货</button>
            </div>
        </div>  
    </div>  
</div>

<script type="text/javascript">
// ajax分页参数，待优化
var ajax_request_url='';
var ajax_pars='';
var ajax_contents='instore_audit_datas';

$(function(){
	// 查询需审核的收货信息
	getGoodsInStoreAdds();
});

// 分页查询需审核的收货信息
function getGoodsInStoreAdds(){
	ajax_request_url='/goods/getGoodsInstoreAdds';
	ajax_pars=$("#searchForm").serialize();
//	alert(ajax_pars);
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


// 打开审核收货详细model
function addInstore(id){
	// 重设form
	$('#auditInstoreForm')[0].reset();
	//$('#myModalLabel').html('编辑门店信息');
	if(isNaN(id) || id<=0){
		return;
	}
	var pars='goodsOrderId='+id+"&instoreState=1";
	
	$.ajax({
		url: '/goods/getAddGoodsInstoreInfo',
		type:'POST',
		data: pars,
		dataType:'json',
		success:function (json) {
			if(json.result==1){
				var list=json.list;
				if(list == undefined){
					return;
				}
				var instoreInfos = eval(list);
				var html = ""
				for (var i in instoreInfos ){
					if(i==0){
						$('#goodsOrderNoSpan').text(instoreInfos[i].orderNo);
						$('#updateTimeSpan').text(instoreInfos[i].updateTime);
						$('#orderTypeSpan').text(instoreInfos[i].orderType);
						$('#orderStateSpan').text("收货");
						$('#storeChineseNameSpan').text(instoreInfos[i].storeChineseName);
						//$('#operatorNameSpan').text(instoreInfos[i].operatorName);
					}
					html=html +"<tr><th scope='row'>"+i+"</th><td>"+instoreInfos[i].goodsNo+"</td>"+
					"<td>"+instoreInfos[i].goodsName+"</td>"+
					"<td>"+instoreInfos[i].model+"</td>"+
					"<td>"+instoreInfos[i].unit+"</td>"+
					"<td>"+instoreInfos[i].orderQuantity+"</td>"+
					"<td>"+instoreInfos[i].instoreQuantity+"</td>"+
					"<td><input id='inQuantity"+i+"' name='inQuantity' class='form-control' type='text' style='width:80px;'></td></tr>"
				}
				$('#instoreInfoTbody').html(html);
				
				$("#save_btn").unbind();
				$("#not_save_btn").unbind();
				$("#save_btn").click(function(){
				  	addInStore(instoreInfos);
				});
				$("#not_save_btn").click(function(){
					$("#close_btn").click();
				});
			}
		}
	})
}

// 添加入库信息
function addInStore(instoreInfos){
	
	for (var i in instoreInfos ){
		var inQuantity = $("#inQuantity"+i).val()
		if(isNaN(inQuantity) || inQuantity=="" || inQuantity==0){
			if((instoreInfos.length-1) == i){
				getGoodsInStoreAdds();
				$("#close_btn").click();
			}
			continue;
		}
		var pars = "goodsOrderItemId="+instoreInfos[i].goodsOrderItemId+
			"&storeId="+instoreInfos[i].storeId+
			"&inQuantity="+inQuantity;
		$.ajax({
			url: '/goods/addGoodsInstore',
			type:'POST',
			data: pars,
			dataType:'JSON',
			success:function (json) {
				if(json.result==1){
					if((instoreInfos.length-1) == i){
						getGoodsInStoreAdds();
						$("#close_btn").click();
					}
				}else{
					alert(json.result);
				}
			}
		})
	}
	
}

</script>