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
	<script language=javascript type=text/javascript src="jquery-1.12.4/jquery-1.12.4.js"></script>
	
	<script type="text/javascript">
 
  function resetFun(){
  $('name').value='';
  $('phone').value='';
  $('teaschool').value='';
  $('experimenttime').value='';
  $('pay').value='';
  return;
 }
 
function validate(v)
{   
   
if(v.name.value=="")
{
  alert("请输入姓名！");
 v.name.focus();
  return false;
} 
if(v.phone.value=="")
{
  alert("请输入手机号码！");
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
   
if(v.date.value=="")
{
  alert("请选择实验日期！");
 v.date.focus();
  return false;
} 
if(v.teaschool.value=="")
{
  alert("请输入你所在学校！");
 v.teaschool.focus();
  return false;
} 
if(v.date.value=="")
{
  alert("请选择实验日期！");
 v.date.focus();
  return false;
}
if(v.experimenttime.value=="")
{
  alert("请选择实验时间！");
 v.experimenttime.focus();
  return false;
}

if(v.expense.value=="科研研究"&&v.pay.value=="")
{
  alert("实验室类型为科研研究，请付款！");
 v.pay.focus();
  return false;
}

if(v.expense.value!=v.pay.value)
{
  alert("您申请的实验室类型为科研研究实验室类型，请付清实验室费用！");
 v.pay.focus();
  return false;
}


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
	    
	    if(v.date.value< nowDate){
		alert("申请日期不能小于当前日期！");
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
  当前位置：<a href="ShowTeaLabServlet" style="text-decoration:none;color:white;">申请实验室</a> > 填写实验室申请单
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
    %></td>
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
   <tr><td id="myfont1" style="background:url(images/a.jpg);height:30;">实验室申请单</td></tr>
   </table>
   </form>
   
   <form action="ApplyLabServletTwo" method="post" onSubmit="return validate(this)">
   <table border="0">
   <tr><td colspan="4" style="color:red">*温馨提醒</td></tr>
     <tr>
   <tr><td colspan="4" style="color:red">1.亲爱的教师，您的使用实验室情况将都会被记录，请您申请实验室后，按申请时间文明诚信使用实验室</td></tr>
     <tr>
      <tr><td colspan="4" style="color:red">2.当申请科研研究的实验室类型时，需要付款</td></tr>
     <tr>
      <tr>
      <tr><td colspan="4" style="color:red">3.科研研究类型的实验室，在实验前三天内取消申请，会扣取50%费用</td></tr>
     <tr>
         <td align="center">实验室号:</td>
         <td><input type="text" name="number" value="<%=request.getParameter("number")%>" id="myinput" readonly="true" style="color:gray"></td>
         <td align="center">实验室管理员:</td>
         <td><input type="text" name="lableader" value="<%=request.getParameter("lableader")%>" id="myinput" readonly="true" style="color:gray"></td>
    </tr>
    
    <tr><td align="center">开放时间:</td>
      <td colspan="3"><input type="text" name="opentime1" value="<%=request.getParameter("opentime1")%>" style="border:0;color:gray; font-size: 15px;width:110">
      <input type="text" name="opentime2" value="<%=request.getParameter("opentime2")%>" style="border:0;color:gray; font-size: 15px;width:110">
      <input type="text" name="opentime3" value="<%=request.getParameter("opentime3")%>" style="border:0;color:gray; font-size: 15px;width:110">
       <input type="text" name="opentime4" value="<%=request.getParameter("opentime4")%>" style="border:0;color:gray; font-size: 15px;width:110">
       </td>
       
    </tr>
   
    <tr><td align="center">实验设备:</td>
       <td><input type="text" name="equipment" value="<%=request.getParameter("equipment")%>" id="myinput" readonly="true" style="color:gray"></td>
       <td align="center">实验室学校:</td>
       <td><input type="text" name="labschool" value="<%=request.getParameter("labschool")%>" id="myinput" readonly="true" style="color:gray"></td>
    </tr>  
   <tr> <td align="center">我的学校:</td>
       <td><input type="text" name="teaschool" value="<%=request.getParameter("school")%>" id="myinput" readonly="true" style="color:gray"></td>
        <td align="center">座位数量:</td>
       <td><input type="text" name="seatnumber" value="<%=request.getParameter("seatnumber")%>" id="myinput" readonly="true" style="color:gray"></td>
   </tr>
   
     <tr>
     <td align="center">实验室类型:</td>
     <td><input type="text" name="type" value="<%=request.getParameter("type")%>" id="myinput" readonly="true" style="color:gray" onchange="chg(this)"> </td> 
     <td align="center">管理员手机:</td>
     <td><input type="text" name="lableaderphone" value="<%=request.getParameter("lableaderphone")%>" id="myinput" readonly="true" style="color:gray"> </td> 
     
     </tr>
    
     <tr>
     <td align="center">我的姓名:</td>
       <td><input type="text" name="name" id="myinput" value="<%=request.getParameter("name")%>" readonly="true" style="color:gray"></td>
     <td align="center">联系电话:</td>
       <td><input type="text" name="phone" id="myinput" value="<%=request.getParameter("phone")%>" readonly="true" style="color:gray"></td>      
   </tr>
   
   <tr>
    <td align="center">实验日期:</td>
       <td><input type="text" name="date" id="myinput" class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})"></td>
       <td align="center">实验时间:</td>
       <td>
       <select name="experimenttime" id="myinput">
       <option value=""></option>
       <option value="<%=request.getParameter("opentime1")%>"><%=request.getParameter("opentime1")%></option>
       <option value="<%=request.getParameter("opentime2")%>"><%=request.getParameter("opentime2")%></option>
       <option value="<%=request.getParameter("opentime3")%>"><%=request.getParameter("opentime3")%></option>
       <option value="<%=request.getParameter("opentime4")%>"><%=request.getParameter("opentime4")%></option>
       </select>
       </td>    
   </tr>
 
   <tr> 
   <td><input type="hidden" name="username" value="<%=session.getAttribute("username")%>" id="myinput" readonly="true"></td>
   </tr>
   
   <tr><td align="center"></td>
       <td><input type="hidden" name="result" value="待审核" id="myinput"></td>
   </tr>
   
    <tr>
      <td align="center">实验室费用:</td>
     <td><input type="text" name="expense" value="<%=request.getParameter("expense")%>" id="myinput" readonly="true" style="color:gray"> </td>
     <td align="center">请付款:</td>
        <td><input type="text" name="pay" id="myinput"></td>
   </tr>
   
   <tr>
   <td colspan="4">
				<center><input id="mybutton" type="submit" value="申请" name="btnApp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
