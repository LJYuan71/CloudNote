$(function(){
  //给按钮追加单击处理
  $("#login").click(function(){
      //清除原有的提示信息
      $("#count_msg").html("");
      $("#password_msg").html("");
      //获取请求数据
      var name = $("#count").val().trim();
      var password = $("#password").val().trim();
      //检查数据格式
      var ok = true;
      if(name==""){
    	  $("#count_msg").html("用户名不能为空");
          ok = false;
      }
      if(password==""){
    	  $("#password_msg").html("密码不能为空");
          ok = false;
      }
      if(ok){
        //发送Ajax请求
        $.ajax({
           url:sessionStorage.projectName+"/user/login.do",
           type:"post",
           data:{"name":name,"pwd":password},
           dataType:"json",
           success:function(result){
              //result是服务器返回的json结果
              if(result.status==0){//成功
                 //获取用户ID，写入Cookie
                 var userId = result.data;
                 addCookie("uid",userId,2);//存储2小时
                 window.location.href="edit.html";
              }else if(result.status==1){//用户名错
                 $("#count_msg").html(result.msg);
              }else if(result.status==2){//密码错
                 $("#password_msg").html(result.msg);
              }
           }
        });
     }//end if
  });
});