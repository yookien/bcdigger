/**
* 公共方法js
*/

// 单选框点击方法
function clickRadioBtn(btn){
	var targetId=$(btn).attr('targetId');
	$("[targetId='"+targetId+"']").each(function(){
		if(btn==this){
			$(this).attr("class","btn btn-primary");
			$('#'+targetId).val($(this).find("[type='radio']").attr('value'));
		}else{
			$(this).attr("class","btn btn-default");
		}
    })
} 