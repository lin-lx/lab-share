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
	
	<script type="text/javascript">
 
 
   function resetFun(){
  $('pay').value='';

  return;
 }
 
function validate(v)
{   
   
if(v.payments.value=="")
{
  alert("请先付款！");
 v.payments.focus();
  return false;
} 

if(v.payments.value!=v.money.value)
{
  alert("请按实际赔偿费用进行支付！");
 v.payments.focus();
  return false;
} 

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
  display: inline;

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
  <a href="teafirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
  </div>
  <div id="top">
  
 当前位置：<a href="ShowCompensationTeaServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;">赔偿信息处理</a> > 支付实验室赔偿
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
   	<tr><td id="myfont1"><center><a href="ShowTeacherServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/myinformation.png" style="width:25px;height:25px">我的信息管理</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="ShowTeaLabServlet" style="text-decoration:none;color:white;"><img src="sysimages/applylab.png" style="width:25px;height:25px">去申请实验室</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ApplyLabServletTwo?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/applyfinish.png" style="width:25px;height:25px">我的申请管理</a></center></td></tr>
    <tr><td id="myfont1"><center><a href="ShowTeaFinishLabServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/notes.png" style="width:25px;height:25px">去评论实验室</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowTeaCommentServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/mycomment.png" style="width:25px;height:25px">我的评论管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="TeaNoteSituationServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/check.png" style="width:25px;height:25px">查看反馈情况</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowCompensationTeaServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/paycompensation.png" style="width:25px;height:25px">赔偿信息处理</a></center></td></tr>	
    <tr><td id="myfont1"><center><a href="ShowTeaFinishCompensationServlet?username=<%=session.getAttribute("username")%>" style="text-decoration:none;color:white;"><img src="sysimages/compensation.png" style="width:25px;height:25px">我的赔偿记录</a></center></td></tr>
   </table>
   </form>
   </div>
   <div id="right"style="border:1px  solid skyblue">
   <center>
    <form class="table-c">
   <table border="0" style="width:1320px">
   <tr><td colspan="5"id="myfont1" style="background:url(images/a.jpg);height:30;">赔偿信息处理</td></tr>
   </table>
   </form>
   
  <form action="PayCompensationServlet?username=<%=session.getAttribute("username")%>" method="post" onSubmit="return validate(this)">
  <table border="0">
  
  <tr>
				<td align="center">实验室号:</td>
				<td><input type="text" name="number" id="myinput" value="<%=request.getParameter("number")%>" readonly="true" style="color:gray"></td>
				<td align="center">实验室所属学校:</td>
				<td><input type="text" name="labschool" id="myinput" value="<%=request.getParameter("labschool")%>" readonly="true" style="color:gray"></td>
			</tr>	
			<tr>
			<td align="center">实验日期:</td>
				<td><input type="text" name="experimentdate" id="myinput" value="<%=request.getParameter("experimentdate")%>" readonly="true" style="color:gray"></td>
				<td align="center">实验时间:</td>
				<td><input type="text" name="experimenttime" id="myinput" value="<%=request.getParameter("experimenttime")%>" readonly="true" style="color:gray"></td>
				
			</tr>
			<tr>
			<td  align="center">赔偿金额:</td>
				<td><input  type="text" name="money"  id="myinput" value="<%=request.getParameter("money")%>" readonly="true" style="color:gray"></td>
			<td  align="center">赔偿说明:</td>
			<td><input  type="text" name="illustration"  id="myinput" value="<%=request.getParameter("illustration")%>" readonly="true" style="color:gray"></td>
				
			</tr>	
			<tr><td><input type="hidden" name="pay" value="是"></td></tr>

             <tr>
             
             <td  align="center">付款方式:</td>
		     <td><input  type="radio" name="payment" value="微信" checked="checked"><img src="sysimages/wechat.png" style="width:40px;height:35px">
		     <input type="radio" name="payment" value="支付宝"><img src="sysimages/alipay.png" style="width:40px;height:35px">
		     </td>
             
             <td  align="center">请付款:</td>
		     <td><input  type="text" name="payments" id="myinput"></td>
             </tr>	
			
			<tr>
			
				<td colspan="4">
				<center><input id="mybutton" type="submit" value="付款" name="btnAlt"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="mybutton" type="reset" value="重置" onclick="resetFun()"></center>
				</td>
			</tr>
  
  </table>
  </form>
   </center>
   </div>
   </div>
   <div id="footer">
    2019 <%=session.getAttribute("school") %> </div>
</body>
</html>
