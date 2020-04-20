<%@ page language="java" import="java.util.*"import="bean.*"pageEncoding="utf-8"%>
<%@ page import="java.text.*"%> 
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
	
	<!-- <link rel="stylesheet" type="text/css" href="css/public.css" charset="utf-8"> -->
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	
	 <script type="text/javascript"> 
	 
	 
 	 function validBirthday(v)
	 {	
	  var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var day = date.getDate();
    if (month < 10) {
    month = "0" + month;
  }
if (day < 10) {
    day = "0" + day;
}
var nowDate = year + "-" + month + "-" + day; 
	    
	    if(v.birthday.value> nowDate){
		alert("生日不能大于当前日期！");
		return false;
	}
	
	
	    if(v.hiredate.value> nowDate){
		alert("入职日期不能大于当前日期！");
		return false;
	}
	
	
		if(v.name.value=="")
{
  alert("请输入你的姓名！");
 v.name.focus();
  return false;
} 
if(v.phone.value=="")
{
  alert("请输入你的手机号！");
 v.phone.focus();
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
	 
	 
 function resetFun(){
  $('name').value='';
  $('sex').value='';
  $('phone').value='';
  $('education').value='';
  $('nation').value='';
  $('birthday').value='';
  $('hiredate').value='';
  $('addess').value='';
  $('nativapla').value='';
  $('username').value='';
  return;
 }
 </script>
	
	
	<style type="text/css">
	

	#header
{

  text-align:right;
  height:100px;
  line-height:100px;
  width:100%;
  background:url(images/e.jpg);
  background-size:cover;
   font-size:18px;
  color:white;
  margin-left:0px;
  margin-top:0px;
}



#top
{
  font-weight:bold;
  margin-left:0px;
  margin-top:0px;
  height:30px;
  width:100%;
  line-height:30px;
  font-size:18px;
  color:white;
  background:url(images/a.jpg);
  background-size:cover;
  border:solid;
  border-width:0px;
  border-right:none;
  border-left:none;
  font-family:楷体;
}

#content
{
  margin-top:0px;
  position:relative;

  width:100%; 
  margin:0;
  padding:0;
}

#left{
  position:absolute;
  top:0px; 
  width:200px; 
  height:570;
  background-size:cover;
  margin-left:0px;
  font-family:楷体;
}

#right{
  position:relative; 
  top:0px; 
  margin-left:200px; 
  height:570;
  background-size:cover;
  font-family:楷体;
  font-size:22px;
}

#footer
{ 
  margin-top:3px;
  width:100%;
  font-size:20px;
  height:26px;
  color:white;
  font-family:楷体;
  background:url(images/a.jpg);
  background-size:cover;
}

#mybutton
{

 
  font-size:20px;
  font-weight: bold;
  color:white;
  font-family:楷体;
  border-style:none;
  background:url(images/a.jpg);

}


#myfont1{
font-size:20px;
color:white;
 font-family:楷体;
font-weight: bold;

}

#myfont2{
font-size:25px;
color:white;
font-family:楷体;
font-weight: bold;

}


#myinput
{ 

  border: skyblue 1px solid;/* 设置输入边框的颜色粗细 */
  font-size: 15px;/* 设置输入框中字体的大小 */
  height: 35px;/* 设置输入框的高度 */
  width:200px;
  border-radius: 5px; /* 设置输入框圆角的大小 */

}


.table-c table{border-right:1px solid  skyblue;border-bottom:1px solid skyblue}
.table-c table td{border-left:1px solid skyblue;border-top:1px solid skyblue}
.table-a table{border-right:0;border-bottom:1px solid skyblue}
.table-a table td{border-left:0;border-top:1px solid skyblue;height:40px}
		</style>
	
  
</head>
  
  <body>
  <div id="header">
 
<a href="index.jsp" style="text-decoration:none;color:white;"><img src="sysimages/relogin.png" style="width:20px;height:20px">退出登录</a>
  <a href="resetpassword.jsp" style="text-decoration:none;color:white;"><img src="sysimages/alterpassword.png" style="width:20px;height:20px">修改密码</a>
  <a href="manfirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
  </div>
  <div id="top">
  当前位置：<a href="ShowManageServlet?username=<%=session.getAttribute("username")%>"style="text-decoration:none;color:white;">我的信息管理</a> > 修改我的信息
  </div>
  
   <div id="content">
   <div id="left" style="border:1px  solid skyblue">
   <table border="0" style="height:70px;width:200px;background:url(images/a.jpg);border-bottom:0px;">
   <tr><td id="myfont1"><img src="sysimages/user.png" style="width:30px;height:30px"> <%=session.getAttribute("username")%></td></tr>
   <tr><td id="myfont1">>>欢迎进入本系统！</td></tr>
   <tr><td id="myfont1"> <img src="sysimages/date.png" style="width:30px;height:30px"> <%
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(date);
        out.print(s);
    %></td></tr>
   </table>

   <form class="table-a">
    <table border="0" style="width:200px;background:url(images/b.jpg);">
    <tr><td id="myfont2" style="border-top:0px;"><img src="sysimages/manager.png" style="width:30px;height:30px">功能选项>>></td></tr>
   	<tr><td id="myfont1"><center><a href="ShowManageServlet?username=<%=session.getAttribute("username")%>"style="text-decoration:none;color:white;"><img src="sysimages/myinformation.png" style="width:25px;height:25px">我的信息管理</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="ShowLabServlet?school=<%=session.getAttribute("school")%>" style="text-decoration:none;color:white;"><img src="sysimages/labs.png" style="width:25px;height:25px">实验室信息管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="MonitorServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/notes.png" style="width:25px;height:25px">记录使用情况</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowManFinishNoteServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/check.png" style="width:25px;height:25px">检查记录管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowManToGradeCreditServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/grade.png" style="width:25px;height:25px">教师信誉评分</a></center></td></tr>	
	<tr><td id="myfont1"><center><a href="ShowManTeaCreditServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/credit.png" style="width:25px;height:25px">信誉评分管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowManToSendCompensationServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/send.png" style="width:25px;height:25px">发送赔偿消息</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowManAllCompensationServlet?school=<%=session.getAttribute("school")%>&&username=<%=session.getAttribute("username")%>"style="text-decoration:none;color:white;"><img src="sysimages/compensation.png" style="width:25px;height:25px">赔偿信息管理</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="ShowManLabNumberServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/total.png" style="width:25px;height:25px">统计使用情况</a></center></td></tr>
   </table>
    </form>
   
   </div>
   <div id="right"style="border:1px  solid skyblue">
   <center>
    <form class="table-c">
   <table border="0" style="width:1320px">
   <tr><td id="myfont1" style="background:url(images/a.jpg);height:30;">修改我的信息</td></tr>
    </table>
    </form>
    <form action="AlterManagerServlet" method="post" onSubmit="return validBirthday(this)">
    <table border="0">
    <tr>
  <td>姓名:</td>
  <td><input type="text" name="name" value="<%=request.getParameter("name")%>" id="myinput"></td>
      
     <td>性别:</td>
  <td> 
 <select id="myinput" name="sex">
      
 		<option {if $sex==男}selected="selected"{/if}>  男  </option>
        <option {if $sex==女}selected="selected"{/if}>  女  </option>
 		</select></td>
       
      <td>身份证:</td>
   <td><input type="text" name="ide" value="<%=request.getParameter("ide")%>" readonly="true" style="color:gray" id="myinput"></td>
    </tr>  
    <tr> 
      <td>手机:</td>
    <td><input type="text" name="phone" value="<%=request.getParameter("phone")%>" id="myinput"></td>
      
      <td>学历:</td>
    <td><input type="text" name="education" value="<%=request.getParameter("education")%>" id="myinput"></td>
      
      <td>民族:</td>
    <td><input type="text" name="nation" value="<%=request.getParameter("nation")%>" id="myinput"></td>
      </tr>
      <tr>
      <td> 生日:</td>
     <td><input type="text" name="birthday" value="<%=request.getParameter("birthday")%>" id="myinput" class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})"></td>
       
       <td>入职日期:</td>
      <td><input type="text" name="hiredate" value="<%=request.getParameter("hiredate")%>" id="myinput" class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})"></td>
       
       <td>住址:</td>
       <td><input type="text" name="address" value="<%=request.getParameter("address")%>" id="myinput"></td>
       </tr>
       <tr>
        <td>籍贯:</td>
       <td><input type="text" name="nativepla" value="<%=request.getParameter("nativepla")%>" id="myinput"></td>
        
         <td>用户名:</td>
       <td><input type="text" name="username" value="<%=request.getParameter("username")%>" id="myinput"></td>
        <td>学校:</td>
       <td><input type="text" name="school" value="<%=request.getParameter("school")%>" id="myinput" readonly="true" style="color:gray"></td>
      </tr>
      <tr><td colspan="6" align="center"><input id="mybutton" type="submit" name="btnAlt" value="修改" >
 		<input id="mybutton" type="button" value="重置" onclick="resetFun()"></td></tr>
 </table>
   </form>
   </center>
   </div>
   </div>
   <div id="footer">
    2019 <%=session.getAttribute("school") %> </div>
</body>
</html>
