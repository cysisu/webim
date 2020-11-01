$(function() {
	$("#register_btn").click(function() {
	    console.log("hello world");
		$("#register_form").css("display", "block");
		$("#login_form").css("display", "none");
	});
	$("#back_btn").click(function() {
		$("#register_form").css("display", "none");
		$("#login_form").css("display", "block");
	});
	$("#login_btn").click(function() {
	    var username = $('#login_username').val();
        var password= $("#login_password").val();
        console.log("username:"+username)
	    $.ajax({
             url : '/admin/login',
             type : 'post',
             data :{
                    userName:username,
                    password :password
             },
             success : function(data) {
                   console.log(data);
                   if(data=="fail"){
                        console.log("用户名或者密码错误")
                   }
                   else if(data=="success"){
                        parent.location.href = '/index';
                   }
             }
        });

    });

    $("#sign_up").click(function() {
           console.log("sign_up");
    	    var username = $('#sign_username').val();
            var password= $("#password1").val();
            var password1=$("#password2").val();
            if(password!=password1){
                 alert("两次输入密码不相同!");
                 return;
            }
    	    $.ajax({
                 url : '/admin/register',
                 type : 'post',
                 data :{
                        userName:username,
                        password :password
                 },
                 success : function(data) {
                       console.log(data);
                       if(data=="success"){
                            alert("注册成功！");
                            parent.location.href = '/';
                       }
                       else if(data=="already"){
                            alert("用户名已经存在!");
                       }
                       else{
                            alert("注册失败！");
                       }
                 }
            });

    });
});

