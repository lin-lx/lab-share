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


 function resetFun(){
  $('username').value='';
  $('password').value='';
  $('school').value='';
  return;
 }
 
function validate(v)
{   
   
if(v.username.value=="")
{
  alert("请输入账户名！");
 v.username.focus();
  return false;
} 
if(v.password.value=="")
{
  alert("请输入密码！");
 v.password.focus();
  return false;
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

#left
{
 position:absolute;
  margin-top:0px; 
  margin-left:0px; 
  background:url(images/a.jpg);
  background-size:cover;
  width:300px; 
  height:460px;
  font-family:楷体;
  }
  
  #left img
{
position:relative;
width:300px;
margin-left:0px;
margin-right:0px;
top:150px;
border:0;
  }
  
  #right
{
  position:relative;
  margin-top:0px; 
  margin-left:300px; 
  background:url(images/a.jpg);
  background-size:cover;
  width:500px; 
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
  focus:outline:blue;
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
    
    	  <div id="left" style="border-right:1px  solid skyblue;">
    	 
    	 <img src="images/d.jpg"/>
    	 
    	  </div>
    	  <div id="right">
    	   <form name="form1" action="LoginServlet" method="post" onSubmit="return validate(this)">
    	   <table border="0" style="height:440px;width:500px">
    	
			<tr>
    			<td colspan="2" align="center"><h2 id="myfont1">系统登录</h2></td>
			</tr>
			<tr>
    			<td id="myfont2">身&nbsp;&nbsp;份:</td>
    			<td id="myfont3" colspan="2"><input type="radio" name="identity" value="系统管理员" checked="checked"/>&nbsp;<label>系统管理员</label>&nbsp;&nbsp;
        		<input type="radio" name="identity" value="教师" />&nbsp;<label>教师</label>&nbsp;<br>
            	<input type="radio" name="identity" value="实验室管理员" />&nbsp;<label>实验室管理员</label>
            	<input type="radio" name="identity" value="实验室负责人" />&nbsp;<label>实验室负责人</label></td>
			</tr>    
			<tr>
 				<td id="myfont2">账&nbsp;&nbsp;户：</td>
 				<td><input id="myinput" type="text" name="username" autofocus placeholder="请输入用户名"></td>
			</tr>		
 			<tr>
   				<td id="myfont2">密&nbsp;&nbsp;码:</td>
   				<td><input id="myinput" type="password" name="password" autofocus placeholder="请输入密码"></td>
   			</tr>  		
   			<tr>
 				<td id="myfont2">学&nbsp;&nbsp;校:</td>
 				<td>
 				<select id="myinput" name="school">
 				<option value="">请选择学校</option>
 				<option value="福建江夏学院">福建江夏学院</option>
 				<option value="福建中医药大学">福建中医药大学</option>
 				<option value="福建医科大学">福建医科大学</option>
 				<option value="福建师范大学">福建师范大学</option>
 				<option value="福建农林大学">福建农林大学</option>
 				</select>
 				</td>
			</tr>	
			<tr>
				<td colspan="2" align="center">
				<input id="mybutton" type="submit" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="mybutton" type="reset" value="重置" onclick="resetFun()">
				</td>
			</tr>	
			<tr>
				<td colspan="2">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="resetpassword.jsp" style="text-decoration:none;font-size:20px;color:white;">忘记密码？</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="register.jsp" style="text-decoration:none;font-size:20px;color:white;">注册账户</a>
				</td>
			</tr>
    		</table>
    		
        	</form>
        	</div>
        <table  border="0" style="width:800px;">
    		<tr><td id="myfont3"  colspan="2"><center>  2019 高校实验室资源共享平台 </center></td></tr>
    		</table>
		</div>


  </body>
</html>

