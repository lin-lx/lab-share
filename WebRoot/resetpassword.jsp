<%@ page language="java" import="java.util.*" import="bean.*" import="op.*"pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>高校实验室资源共享平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	 <title>高校实验室资源共享平台</title>	
	 
	<script type="text/javascript">
function validate(v)
{   
   
if(v.phone.value=="")
{
  alert("请输入手机号！");
 v.phone.focus();
  return false;
} 
if(v.password.value=="")
{
  alert("请输入密码！");
 v.password.focus();
  return false;
} 
if(v.verifycode.value=="")
{
  alert("请输入验证码！");
 v.verifycode.focus();
  return false;
}
 var regs = /^1[3|5|7|8][0-9]\d{4,8}$/;  
   if(regs.test(v.phone.value) === false)  
   {  
       alert("手机号输入不合法");
        v.phone.focus();  
       return  false;  
   } 
 
}
</script>
<style type="text/css">

body{
  background:url(images/c.jpg);
  background-size:cover;

}

#main
{

  margin-top:100px; 
  margin-left:350px; 
  width:800px; 
  height:550px;
  font-family:楷体;
  }


  #right
{
  position:relative;
  margin-top:0px; 
  margin-left:0px; 
  background:url(images/a.jpg);
  background-size:cover;
  width:800px; 
  height:460px;
  font-family:楷体;
  }




#myinput
{ 
  border: #808080 1px solid;/* 设置输入边框的颜色粗细 */
  font-size: 15px;/* 设置输入框中字体的大小 */
  height: 45px;/* 设置输入框的高度 */
  width:300px;
  border-radius: 5px; /* 设置输入框圆角的大小 */
  border-top:0px;
  border-left:0px;
  border-right:0px;
  border-botton:1px;
  color: #000000;/* 输入框中文字的颜色 */
  display: inline;/* 使input框在同一行信息，不会换行显示，不使用float: left;样式，应为该样式会让input框周围的其它标签样式乱掉 */
}

#myfont1
{
  font-size:30px;
  font-weight: bold;
  color:white;
  font-family:楷体;
}
#myfont2
{
  font-size:25px;
  font-weight: bold;
  color:white;
  font-family:楷体;
}

#myfont3
{
  font-size:20px;
  font-weight: bold;
  color:white;
  font-family:楷体;
}

#myfont4
{
  font-size:40px;
  font-weight: bold;
  color:white;
  font-family:楷体;
}


#mybutton
{

 
  font-size:20px;
  font-weight: bold;
  color:white;
  height:35px;
   font-family:楷体;
  border-style:none;
  background:url(images/c.jpg);

}

.table-c table{border-right:1px solid  skyblue;border-bottom:1px solid skyblue}
.table-c table td{border-left:1px solid skyblue;border-top:1px solid skyblue}

</style>
 	</head>
 
 
<body> 

	<div id="main" style="border:2px  solid skyblue">
    		<table  border="0" style="width:800px;">
    		<tr><td id="myfont4"  colspan="2"><center>高校实验室资源共享平台</center></td></tr>
    		</table>
    
    	
    	  <div id="right">
    	  <center>
    <form action="ResetPasswordServlet" method="post" onSubmit="return validate(this)">
    <table width="550px" border="0">
    <tr>
    	<td colspan="2" align="center" id="myfont1">重置密码</td>
    </tr>
 		<tr>
   			<td id="myfont2">手机号:</td>
   			<td><input id="myinput" type="text" name="phone" autofocus placeholder="请输入注册手机号">&nbsp;&nbsp;<input type="button" style="height:35px" value="点击获取验证码"></td>
   		</tr>
   		<!-- <tr>
   			<td colspan="2" id="myfont2" align="center">点击获取验证码</td>
   			
   		</tr> -->
 		<tr>
   			<td id="myfont2">验证码:</td>
   			<td><input id="myinput" type="text" name="verifycode"autofocus placeholder="请输入验证码"></td>
   		</tr>
        <tr>
   			<td id="myfont2">新密码:</td>
   			<td><input id="myinput" type="password" name="password"autofocus placeholder="请输入新密码"></td>
   		</tr>
   		
   		
    <tr>
    	<td align="center" colspan="2">
    		<input id="mybutton" type="submit"  name="btnReset" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="mybutton" type="button" onclick="location.href='index.jsp'" value="返回">
		</td>
    </tr>
    </table>
    </form>
    </center>
        	</div>
        <table  border="0" style="width:800px;">
    		<tr><td id="myfont3"  colspan="2"><center>  2019 高校实验室资源共享平台 </center></td></tr>
    		</table>
		</div>


  </body>
</html>

