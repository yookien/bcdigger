<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>欢迎使用bcdigger系统</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="/css/animate.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="/css/custom.min.css" rel="stylesheet">
    
    <script src="/js/jquery-3.3.1.min.js" type="text/javascript" ></script>
    
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form>
              <h1>欢迎使用bcdigger系统 </h1>
              <div>
                <input type="text" id="login_name" class="form-control" placeholder="请输入用户名称" required="" />
              </div>
              <div>
                <input type="password" id="login_password" class="form-control" placeholder="请输入密码" required="" />
              </div>
              
              <div id="login_vrifycode_div" style="diaplay:none;">
                <input type="text" id="login_vrifycode" class="form-control" placeholder="请输入验证码" required="" />
                <img src="" id="login_vrifycode_img"/> <a href="javascript:getVrifycode();">刷新</a>
              </div>
              
              <div>
                <a class="btn btn-default submit" href="javascript:doLogin()">登录</a>
                <a class="reset_pass" href="#">忘记密码?</a>
              </div>

              <div class="clearfix" id="login_info" style="display:none"><i class="fa fa-exclamation-triangle" style="color:red"></i>
              	 &nbsp&nbsp&nbsp&nbsp<span id="login_info_span">用户名或者密码输入错误，请重试...</span>
              </div>

              <div class="separator">
               <!--  <p class="change_link">注册新用户?
                  <a href="#signup" class="to_register"> 注册 </a>
                </p> -->

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> www.bcdigger.com</h1>
                  <p>©2016 All Rights Reserved. www.bcdigger.com</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>注册</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <a class="btn btn-default submit" href="index.html">提交</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> 登录 </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> www.bcdigger.com</h1>
                  <p>©2018 All Rights Reserved. www.bcdigger.com</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	getVrifycode();
});

function doLogin(){
	var name=$('#login_name').val();
	if(name==''){
		$('#login_info_span').html("请输入用户名称...");
		$('#login_info').css("display","block");
		$('#login_name').focus();
		return;
	}
	
	var password=$('#login_password').val();
	if(password==''){
		$('#login_info_span').html("请输入密码...");
		$('#login_info').css("display","block");
		$('#password').focus();
		return;
	}
	
	var vrifycode=$('#login_vrifycode').val();
	if(vrifycode==''){
		$('#login_info_span').html("请输入验证码...");
		$('#login_info').css("display","block");
		$('#vrifycode').focus();
		return;
	}
	
	$.ajax({
		type: "POST",
		cache: false,
		dataType: "json",
		data: {name:name,password:password,vrifycode:vrifycode},
		url: "/admin/userLogin",
		success: function(json){
			if(json.result==1){
				window.location.href='/admin/index';
			}else {
				if(json.result==-4){
					$('#login_info_span').html("请输入正确的验证码...");
					$('#login_info').css("display","block");
				}else{
					$('#login_info_span').html("输入的用户名或密码错误，请重试...");
					$('#login_info').css("display","block");	
				}
				getVrifycode();
			}
		}
	});
}


function getVrifycode(){
	var timenow = new Date().getTime();
	$('#login_vrifycode_img').attr('src','/vrifycode/random?d='+timenow);
	$('#login_vrifycode_div').show();
}


</script>