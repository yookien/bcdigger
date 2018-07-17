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

// 时间戳 转 "yyyy--mm--dd"格式
function fmtDate(obj){
    var date =  new Date(obj);
    var y = 1900+date.getYear();
    var m = "0"+(date.getMonth()+1);
    var d = "0"+date.getDate();
    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
}