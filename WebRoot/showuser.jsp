<%@ page language="java" import="java.util.*" import="bean.*" pageEncoding="utf-8"%>
<%@ page import="java.text.*"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	 <script type="text/javascript" src="jquery-1.12.4/jquery-1.12.4.js"></script> 
	
			<script type="text/javascript">





function to_page(pag){
	if(pag){
		var zys=${Page.totalPagecount}
		if(pag>0&&pag<=zys){
			var input = document.getElementById("page");
			input.value=pag;
		}
	}
	 var form = document.getElementById('form');
	 form.submit();
}
function pagee(data){
	var thisoblect=data.value;
	var input = document.getElementById("page");
	if(thisoblect>0&&thisoblect<=${Page.totalPagecount}){
	input.value=thisoblect;
	}
	to_page();
}






 function resetFun(){
  $('name').value='';
  $('school').value='';
  $('sex').value='';
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
	<a href="admfirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
  </div>
  <div id="top">
  当前位置：用户信息管理
  
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
   <div id="right" style="border:1px  solid skyblue">
   <center>
   <form class="table-c" method="post" action="SelectUserServlet">
   <table  border="0" style="height:100px;width:1295px">
   <tr><td style="background:url(images/a.jpg);height:30;"id="myfont1">信息检索</td></tr>
   <tr>
	<td>
	姓名:<input type="text" name="name" id="myinput">
	学校：<select id="myinput" name="school">
 				<option value=""></option>
 				<option value="福建江夏学院">福建江夏学院</option>
 				<option value="福建中医药大学">福建中医药大学</option>
 				<option value="福建医科大学">福建医科大学</option>
 				<option value="福建师范大学">福建师范大学</option>
 				<option value="福建农林大学">福建农林大学</option>
 				</select>
	性别：<select id="myinput" name="sex">	
	            <option value=""></option>			
 				<option value="男">男</option>
 				<option value="女">女</option>
 				</select>	
	<input type="submit"  value="查询" id="mybutton">&nbsp;
	<input type="reset"  value="重置" id="mybutton" onclick="resetFun()">
	</td>
	</tr>
   </table>
   </form>
   
   
   
   <form class="table-c">
   <table border="0" style="width:1295px">
   <tr><td colspan="10"id="myfont1" style="background:url(images/a.jpg);height:30;">用户信息</td></tr>
   <tr style="font-weight: bold;">
				<td><center>用户名</center></td>
				<td><center>手机号</center></td>
				<td><center>性别</center></td>
				<td><center>姓名</center></td>
				<td><center>民族</center></td>
				<td><center>住址</center></td>
				<td><center>国籍</center></td>
				<td><center>教育程度</center></td>
				<td><center>学校</center></td>
				<td><center>注册时间</center></td>
				</tr>
				
       <% ArrayList<User> users=(ArrayList<User>)application.getAttribute("users");	
			if(users!=null){
	          	for(int i=0;i<users.size();i++)
	         	 {
	            	 User user=users.get(i);
	            	 %>
	            	 <tr>
	            	 <td><center><%=user.getUsername()%></center></td>
	            	 <td><center><%=user.getPhone()%></center></td>
	            	 <td><center><%=user.getSex()%></center></td>
	            	 <td><center><%=user.getName()%></center></td>
	            	 <td><center><%=user.getNation()%></center></td>
	            	 <td><center><%=user.getAddress()%></center></td>
	            	 <td><center><%=user.getNativepla()%></center></td>
	            	 <td><center><%=user.getEducation()%></center></td>
	            	 <td><center><%=user.getSchool()%></center></td>
	            	 <td><center><%=user.getRegistertime()%></center></td>
	            	 </tr>
	            	 <%
	             }
	         } %>


<tr><td colspan="10">


总共[<B>${Page.totalcount}</B>]记录   共[<B>${Page.totalPagecount}</B>]页
			每页显示
				<select onchange="to_page()" name="pageSize" id="pageSize">
				    <option value="10"  <c:if test="${Page.pagesize==10}"></c:if> selected="selected">10</option>
					<%-- <option value="15"  <c:if test="${Page.pagesize==15}"></c:if> >15</option>	 --%>				
				</select>
					条
			当前页码   <input  readonly="readonly" size="1" name="page" id="page" type="text" style="border: 0" value="${Page.currpageno}">
			<%-- [<button style="border:0;background-color: ;"  onclick="javascript:to_page(${Page.currpageno-1})">前一页</button>] --%>
			[<a href="ShowUserServlet?page=${Page.currpageno-1}" style="text-decoration:none">前一页</a>]
			
			
			<%-- <c:choose>
				<c:when test="${Page.totalPagecount<=5}">
					<c:set var="begin" value="1"/>
 					<c:set var="end" value="${Page.totalPagecount}"/>
				</c:when>
				<c:when test="${Page.totalPagecount>5}">
					<c:if test="${Page.currpageno<=3}">
						<c:set var="begin" value="1"/>
						<c:set var="end" value="5"/>
					</c:if>
					<c:if test="${Page.currpageno>3}">
						<c:set var="begin" value="${Page.currpageno-2}"/>
						<c:if test="${Page.totalPagecount>Page.currpageno+2}">
 							<c:set var="end" value="${Page.currpageno+2}"/>
 						</c:if>
 						<c:if test="${Page.totalPagecount<=Page.currpageno+2}">
 							<c:set var="end" value="${Page.totalPagecount}"/>
 						</c:if>
 					</c:if>
				</c:when>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}" var="v" >
    				<input onclick="pagee(this)" readonly="readonly" size="1"  type="text" style="border: 0" value="${v}">
			</c:forEach>
			<c:if test="${end<Page.totalPagecount }">
      			<span>...</span>
    		</c:if>  --%>
			<%-- [<button style="border:0;background-color: ;" onclick="javascript:to_page(${Page.currpageno+1})">后一页</button>] --%>
            [<a href="ShowUserServlet?page=${Page.currpageno+1}" style="text-decoration:none">后一页</a>]
            

</td></tr>


   </table>
   </form>
   </center>
   </div>
   </div>
   <div id="footer">
    2019 系统管理员 </div>
</body>
</html>
