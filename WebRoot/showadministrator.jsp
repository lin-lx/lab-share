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
	
	
	<style type="text/css">
	

	
	#header
{


  height:100px;
  line-height:100px;
  width:100%;
  background:url(images/e.jpg);
  background-size:cover;
  text-align:right;
  font-size:18px;
  color:white;
  margin-left:0px;
  margin-top:0px;
}

#top
{
  margin-left:0px;
  margin-top:0px;
  height:30px;
  font-weight:bold;
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
  height:26px;
  width:100%;
  font-size:18px;
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
  <a href="admfirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
 
  </div>
  <div id="top">
 当前位置：我的信息管理
  </div>
  
   <div id="content">
   <div id="left" style="border:1px  solid skyblue">
   <table border="0" style="height:70px;width:200px;background:url(images/a.jpg);border-bottom:0px;">
   <tr><td id="myfont1"><img src="sysimages/user.png" style="width:30px;height:30px"> <%=session.getAttribute("username")%></td></tr>
   <tr><td id="myfont1">>>欢迎进入本系统！</td></tr>
   <tr><td id="myfont1"><img src="sysimages/date.png" style="width:30px;height:30px"> <%
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(date);
        out.print(s);
    %></td></tr>
   </table>
   
   <form class="table-a">
   <table border="0" style="width:200px;background:url(images/b.jpg);">
    <tr><td id="myfont2" style="border-top:0px;"><img src="sysimages/manager.png" style="width:30px;height:30px">功能选项>>></td></tr>
    <tr><td id="myfont1"><center><a href="ShowAdministratorServlet?username=<%=session.getAttribute("username")%>"  style="text-decoration:none;color:white;"><img src="sysimages/myinformation.png" style="width:25px;height:25px">我的信息管理</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="ShowRemindServlet"style="text-decoration:none;color:white;"><img src="sysimages/remind.png" style="width:25px;height:25px">首页公告管理</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="ShowUserServlet"  style="text-decoration:none;color:white;"><img src="sysimages/users.png" style="width:25px;height:25px">查看用户信息</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="AdministratorShowLabsServlet" style="text-decoration:none;color:white;"><img src="sysimages/labs.png" style="width:25px;height:25px">查看实验室信息</a></center></td></tr>
   
   </table>
   </form>
   
   </div>
   <div id="right"style="border:1px  solid skyblue">
   <center>
   <form class="table-c">
   <table border="0" style="width:1320px">
   <tr><td colspan="10"id="myfont1" style="background:url(images/a.jpg);height:30;">我的信息</td></tr>
   <tr style="font-weight: bold;">
				<td><center>姓名</center></td>
				<td><center>性别</center></td>
				<td><center>身份证</center></td>
				<td><center>手机</center></td>
				<td><center>学历</center></td>
				<td><center>民族</center></td>
				<td><center>生日</center></td>
				<td><center>入职日期</center></td>
				<td><center>住址</center></td>
				<td><center>籍贯</center></td>
				</tr>
 
      <% ArrayList<User> users=(ArrayList<User>)application.getAttribute("users");	
			if(users!=null){
	          	for(int i=0;i<users.size();i++)
	         	 {
	            	 User user=users.get(i);
	            	 %>
       <tr><td align="center"><%=user.getName()%></td>
       <td align="center"><%=user.getSex()%></td>
       <td align="center"><%=user.getIde()%></td>
       <td align="center"><%=user.getPhone()%></td>
       <td align="center"><%=user.getEducation()%></td>
       <td align="center"><%=user.getNation()%></td>
       <td align="center"><%=user.getBirthday()%></td>
       <td align="center"><%=user.getHiredate()%></td>
       <td align="center"><%=user.getAddress()%></td>
       <td align="center"><%=user.getNativepla()%></td>
       </tr>
              <tr><td colspan="10" align="center"><input id="mybutton" type="button" value="修改信息" onclick="location.href='AlterAdministratorButtonServlet?ide=<%=user.getIde()%>'">
       
       <%
	             }
	         } %>
       
   </table>
   </form>
   </center>
   </div>
   </div>
   <div id="footer">
    2019 系统管理员 </div>
</body>
</html>
