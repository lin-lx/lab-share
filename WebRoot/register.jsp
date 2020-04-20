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
	 
	 
	
 <script type="text/javascript" src="jquery-1.12.4/jquery-1.12.4.js"></script> 	 
<script type="text/javascript">
 
 function createXMLHttpRequest() {
    try {
      xmlHttp = new XMLHttpRequest();//除了ie之外的其他浏览器使用ajax
    } catch (tryMS) {
      try {
        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");//ie浏览器适配
      } catch (otherMS) {
        try {
          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//ie浏览器适配
        } catch (failed) {
          xmlHttp = null;
        }
      }
    }
    return xmlHttp;
  }
  
 

 
var flag=true;
var xmlHttp;
 //验证用户名
 function CheckUsername(){
 //获取jsp页面输入的值
var username=document.getElementById("username").value;//用户名

//验证用户名
 if(username==""||username==null){
 document.getElementById("span01").innerHTML="<font color='red'>*用户名不能为空!</font>"
 return false;
 }
 
 

 //访问字符串
    var url = "RegisterServlet";
    //创建核心xmlhttprequest组件
    xmlHttp = createXMLHttpRequest();
    //设置回调函数
    xmlHttp.onreadystatechange = proessRequest;
    //初始化核心组件
    xmlHttp.open("post", url, true);
    //设置请求头
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
    //发送请求
    xmlHttp.send("username="+username);
  }
  //回调函数
  function proessRequest() {
 //readyState==4表示请求已经完成，且响应已就绪。status==200表示“OK”
if(xmlHttp.readyState==4 && xmlHttp.status==200){
var data=xmlHttp.responseText;//获取CheckUserNameServlet中response中存储的值
if(data == 1){
 document.getElementById("span01").innerHTML="<font color='red'>*用户名已存在!</font>"
 flag=false;
 }
if(data == 2){
 document.getElementById("span01").innerHTML="<font color='green'>√</font>"
 }
 }
 }
 
 

 
  //验证密码
 function CheckPassword(){
 //获取jsp页面输入的值
var password=document.getElementById("password").value;//密码

//验证密码
 if(password==""||password==null){
 document.getElementById("span02").innerHTML="<font color='red'>*密码不能为空!</font>"
 flag=false;
 }
 
  else{
 document.getElementById("span02").innerHTML="<font color='green'>√</font>"
 flag=true;
 }
}

 //验证确认密码
 function CheckPasswordSure(){
var passwordsure=document.getElementById("passwordsure").value;//确认密码
var password=document.getElementById("password").value;//密码
 if(passwordsure==""||passwordsure==null){
 document.getElementById("span03").innerHTML="<font color='red'>*确认密码不能为空!</font>"
  flag=false;
 }

 //验证密码和确认密码是否一致 
 if(password!=passwordsure){
 document.getElementById("span03").innerHTML="<font color='red'>*密码和确认密码不一致!</font>"
  flag=false;
 }

 else{
 document.getElementById("span03").innerHTML="<font color='green'>√</font>"
 flag=true;
 }
}


  //验证身份证号
 var flag=true;
 function CheckIde(){
var ide=document.getElementById("ide").value;

 if(ide==""||ide==null){
 document.getElementById("span04").innerHTML="<font color='red'>*身份证不能为空!</font>"
return false;
 }

 //验证身份证输入是否合法
  var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
  if(reg.test(ide)===false){
 document.getElementById("span04").innerHTML="<font color='red'>*身份证输入不合法!</font>"
 return false;
 }


//访问字符串
    var url = "IdeServlet";
    //创建核心xmlhttprequest组件
    xmlHttp = createXMLHttpRequest();
    //设置回调函数
    xmlHttp.onreadystatechange = proessRequest1;
    //初始化核心组件
    xmlHttp.open("post", url, true);
    //设置请求头
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
    //发送请求
    xmlHttp.send("ide="+ide);
  }
  //回调函数
  function proessRequest1() {
 //readyState==4表示请求已经完成，且响应已就绪。status==200表示“OK”
if(xmlHttp.readyState==4 && xmlHttp.status==200){
var data1=xmlHttp.responseText;//获取CheckUserNameServlet中response中存储的值
if(data1 == 1){
 document.getElementById("span04").innerHTML="<font color='red'>*身份证号已存在!</font>"
 flag=false;
 }
if(data1 == 2){
 document.getElementById("span04").innerHTML="<font color='green'>√</font>"

 }
 }
 }
 

 
 
 //验证姓名
 function CheckName(){
var name=document.getElementById("name").value;//姓名
 if(name==""||name==null){
 document.getElementById("span05").innerHTML="<font color='red'>*姓名不能为空!</font>"
  flag=false;
 }
 else{
 document.getElementById("span05").innerHTML="<font color='green'>√</font>"
 flag=true;
 }
}


 //验证手机号
 var flag=true;
 function CheckPhone(){
var phone=document.getElementById("phone").value;//手机号

 if(phone==""||phone==null){
 document.getElementById("span06").innerHTML="<font color='red'>*手机号不能为空!</font>"
 return false;
 }
 //验证手机号输入是否合法
 var regs = /^1[3|5|7|8][0-9]\d{4,8}$/;  
  if(regs.test(phone)===false){
 document.getElementById("span06").innerHTML="<font color='red'>*手机号输入不合法!</font>"
 return false;
 }
 
 
 //访问字符串
    var url = "PhoneServlet";
    //创建核心xmlhttprequest组件
    xmlHttp = createXMLHttpRequest();
    //设置回调函数
    xmlHttp.onreadystatechange = proessRequest2;
    //初始化核心组件
    xmlHttp.open("post", url, true);
    //设置请求头
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
    //发送请求
    xmlHttp.send("phone="+phone);
  }
  //回调函数
  function proessRequest2() {
 //readyState==4表示请求已经完成，且响应已就绪。status==200表示“OK”
if(xmlHttp.readyState==4 && xmlHttp.status==200){
var data2=xmlHttp.responseText;//获取CheckUserNameServlet中response中存储的值
if(data2 == 1){
 document.getElementById("span06").innerHTML="<font color='red'>*手机号已存在!</font>"
 flag=false;
 }
if(data2 == 2){
 document.getElementById("span06").innerHTML="<font color='green'>√</font>"

 }
 }
 }
 






//提交时表单再次校验
function validate(v)
{   
   
if(v.username.value=="")
{
  alert("账户名不可为空！");
 v.username.focus();
  return false;
} 

if(v.password.value=="")
{
  alert("密码不可为空！");
 v.password.focus();
  return false;
}
 
if(v.passwordsure.value=="")
{
  alert("确认密码不可为空！");
 v.passwordsure.focus();
  return false;
} 

if(v.passwordsure.value!=v.password.value)
{
  alert("确认密码与密码不一致！");
 v.passwordsure.focus();
  return false;
}

if(v.ide.value=="")
{
  alert("身份证号不可为空！");
 v.ide.focus();
  return false;
    
}

 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(v.ide.value) === false)  
   {  
       alert("身份证输入不合法"); 
        v.ide.focus(); 
       return  false;  
   } 


 var regs = /^1[3|5|7|8][0-9]\d{4,8}$/;  
   if(regs.test(v.phone.value) === false)  
   {  
       alert("手机号输入不合法");
        v.phone.focus();  
       return  false;  
   } 

if(v.phone.value=="")
{
  alert("手机号码不可为空！");
 v.phone.focus();
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

  margin-top:80px; 
  margin-left:350px; 
  width:800px; 
  height:600px;
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
  height:510px;
  font-family:楷体;
  }




.myinput
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
   <form name="register" name="form" action="RegisterServlet" method="post" onSubmit="return validate(this)">
    <table style="width:620px;height:450px;" border="0">
    <tr>
    	 <td colspan="2" align="center" id="myfont1">注册新用户</td>
    </tr>
    
         
		<tr>
 		<td id="myfont2">账&nbsp;&nbsp;&nbsp;&nbsp;户：</td>
 		<td><input class="myinput" type="text" name="username" id="username" autofocus placeholder="请输入账户名" onblur="CheckUsername()"><span id="span01" style="color:red">*用户名不能为空!</span></td>
		</tr>
		
 		<tr>
   			<td id="myfont2">密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
   			<td><input class="myinput" type="password" name="password" id="password" autofocus placeholder="请输入密码" onblur="CheckPassword()"><span id="span02" style="color:red">*密码不能为空!</span></td>
   		</tr>
   		<tr>
   			<td id="myfont2">确认密码:</td>
   			<td><input class="myinput" type="password" name="passwordsure" id="passwordsure" autofocus placeholder="请再次输入密码" onblur="CheckPasswordSure()"><span id="span03" style="color:red">*确认密码不能为空!</span></td>
   		</tr>
   		
   		<tr>
   			<td id="myfont2">身份证号:</td>
   			<td><input class="myinput" type="text" name="ide" id="ide" autofocus placeholder="请输入身份证号" onblur="CheckIde()"><span id="span04" style="color:red">*身份证不能为空!</span></td>
   		</tr>
   		<tr>
   			<td id="myfont2">姓&nbsp;&nbsp;&nbsp;&nbsp;名:</td>
   			<td><input class="myinput" type="text" name="name" id="name" autofocus placeholder="请输入姓名" onblur="CheckName()"><span id="span05" style="color:red">*姓名不能为空!</span></td>
   		</tr>
   		<tr>
   			<td id="myfont2">手机号码:</td>
   			<td><input class="myinput" type="text" name="phone" id="phone" autofocus placeholder="请输入手机号码" onblur="CheckPhone()"><span id="span06" style="color:red">*手机号不能为空!</span></td>
   		</tr>
   		
   		<tr>
   			<td id="myfont2">学&nbsp;&nbsp;&nbsp;&nbsp;校:</td>
   			<td><select class="myinput" name="school">
 				<option value="">请选择学校</option>
 				<option value="福建江夏学院">福建江夏学院</option>
 				<option value="福建中医药大学">福建中医药大学</option>
 				<option value="福建医科大学">福建医科大学</option>
 				<option value="福建师范大学">福建师范大学</option>
 				<option value="福建农林大学">福建农林大学</option>
 				</select></td>
   		</tr>
   		
   		
   		<tr>
    	<td id="myfont2">身&nbsp;&nbsp;&nbsp;&nbsp;份:</td>
    		<td id="myfont3">
    		<input type="radio" name="identity" value="系统管理员" checked="checked"/>&nbsp;<label>系统管理员</label>&nbsp;&nbsp;&nbsp;
        	<input type="radio" name="identity" value="教师" />&nbsp;<label>教师</label>&nbsp;<br>
            <input type="radio" name="identity" value="实验室管理员" />&nbsp;<label>实验室管理员</label>&nbsp;
            <input type="radio" name="identity" value="实验室负责人" />&nbsp;<label>实验室负责人</label></td>
		</tr>
   		<tr>
    	<td id="myfont2">性&nbsp;&nbsp;&nbsp;&nbsp;别:</td>
    		<td id="myfont3"><input type="radio" name="sex" value="男" checked="checked"/>&nbsp;<label>男</label>&nbsp;&nbsp;&nbsp;
        	<input type="radio" name="sex"  value="女" />&nbsp;<label>女</label>&nbsp;<br>
           </td>
		</tr>
    <tr>
    	<td align="center" colspan="2">
    		<input id="mybutton" type="submit" name="btnReg" value="注册">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="mybutton" type="button" onclick="location.href='index.jsp'" value="返回" >
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

