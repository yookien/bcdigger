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

// 时间戳 转 "yyyy-MM-dd"格式
function unixFormatStr(obj){
    return new Date(obj).Format('yyyy-MM-dd');
    return dateFormatStr(date);
}
//时间戳 转 "yyyy-MM-dd hh:mm:ss"格式
function unixFormatFullStr(obj){
    return new Date(obj).Format('yyyy-MM-dd hh:mm:ss');
    return dateFormatStr(date);
}

//时间对象 转 "yyyy-MM-dd"格式
function dateFormatStr(date){
	return date.Format('yyyy-MM-dd');
}
//时间对象 转 "yyyy-MM-dd hh:mm:ss"格式
function dateFormatFullStr(date){
	return date.Format('yyyy-MM-dd hh:mm:ss');
}

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2018-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2018-7-2 8:9:4.18   
//(new Date()).Format("yyyy-MM-dd")      ==> 2018-07-02  
Date.prototype.Format = function(fmt) {
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}

//全选
function checkedAllBox(name) {
	var el = document.getElementsByTagName("input");
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = true;
		}
	}
}

//至少选择一个
function checkedLestOne(name) {
	var el = document.getElementsByTagName("input");
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			if(el[i].checked == true)
				return true;
		}
	}
	return false;
}

//全不选
function unCheckedAllBox(name) {
	var el = document.getElementsByTagName("input");
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = false;
		}
	}
}

//反选
function switchCheckedBox(name) {
	var el = document.getElementsByTagName("input");
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = !el[i].checked;
		}
	}
}