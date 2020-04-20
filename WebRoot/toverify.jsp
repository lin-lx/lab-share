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

 <script type="text/javascript" src="js/echarts.min.js"></script> 

<script src="js/echarts.common.min.js"></script>  





	<script type="text/javascript">
 
  function resetFun(){
  $('reason').value='';
  return;
 }
 

 function validate(v)
{   
   
if(v.verifyname.value=="")
{
  alert("请输入你的姓名！");
 v.verifyname.focus();
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
  font-family:楷体;
  font-size:22px;
}

#right1{
  position:absolute; 
  margin-left:40px;
  top:40px; 
  height:528;
  border-right:1px  solid skyblue;
  font-family:楷体;
  font-size:22px;
}

#right2{
  position:relative; 
  margin-left:450px; 
  height:540;
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
  
  <body onload="opener.location.reload()">
  
 <script language="javascript">
window.opener.document.location.reload()
</script>

  <div id="header">
 

 <a href="index.jsp" style="text-decoration:none;color:white;"><img src="sysimages/relogin.png" style="width:20px;height:20px">退出登录</a>
  <a href="resetpassword.jsp" style="text-decoration:none;color:white;"><img src="sysimages/alterpassword.png" style="width:20px;height:20px">修改密码</a>
  <a href="leafirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
  </div>
  <div id="top">
  当前位置：<a href="VerifyApplyServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;">审核申请管理</a> > 填写审核单
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
   	<tr><td id="myfont1"><center><a href="ShowLeaderServlet?username=<%=session.getAttribute("username")%>"style="text-decoration:none;color:white;"><img src="sysimages/myinformation.png" style="width:25px;height:25px">我的信息管理</a></center></td></tr>
    <tr><td id="myfont1" ><center><a href="VerifyApplyServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/check.png" style="width:25px;height:25px">审核申请管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowLeaVerifyResultServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/notes.png" style="width:25px;height:25px">我已审核管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowTeachersLServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/users.png" style="width:25px;height:25px">教师信息管理</a></center></td></tr>
	<tr><td id="myfont1"><center><a href="ShowManagesLServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/users.png" style="width:25px;height:25px">管理员信息管理</a></center></td></tr>
   	<tr><td id="myfont1"><center><a href="LeaCountTotalServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;"><img src="sysimages/total.png" style="width:25px;height:25px">实验室使用情况</a></center></td></tr> 
   </table>
   </form>
   </div>
   
   <div id="right"style="border:1px  solid skyblue">
    <form class="table-c">
   <table border="0" style="width:1320px">
   <tr><td colspan="5"id="myfont1" style="background:url(images/a.jpg);height:30;">审核实验室</td></tr>
   </table>
   </form>
   
    
   <div id="right1">
 
 
   <div id="main" style="width: 450px;height:400px; border:0px solid skyblue;" ></div>
 <table>
  <tr><td>使用实验室:<%=session.getAttribute("usesize")%>次</td>
 </tr>
 <tr><td>信誉总分:<%=session.getAttribute("creditgrade")%>分</td>
 </tr>
 
 <tr>
 <td style="color:red">信誉总分计算方式：使用次数*每次的信誉得分（一次满分为10分）</td>
 </tr>
 
 </table>
   </div>
   
 
   
  
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
            title: {
                text: '教师信誉信息',
                link:'http://echarts.baidu.com/index.html'
            },
            tooltip: {
            	show:true,
            	trigger:'item'  //axis-坐标轴触发  item--数据条目触发
            	},
            legend: {
                data:['次数'],
                show:true
            },
            xAxis: {
            	position:'bottom',  //top
                data: ["申请次数","使用次数","取消次数","违约次数","赔偿次数"]
            },
            yAxis: {
            	show:true,
            	position:'left'   //right
            },
            series: [{
                name: '次数',  //要和legend中的data对应起来
                type: 'line',  //pie-饼图，bar-柱状图,line-折线图
                 data:[<%=session.getAttribute("applysize")%>, <%=session.getAttribute("usesize")%>, <%=session.getAttribute("cancelsize")%>, <%=session.getAttribute("treatysize")%>, <%=session.getAttribute("compensationsize")%>]

            }]
        };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

   
   
   
  
    <!--    <div id="main" style="height:400px"></div> -->
   
   
   <div id="right2">
   <center>

   <form action="VerifyResultServlet?school=<%=session.getAttribute("school") %>" method="post" onSubmit="return validate(this)">
		<table border="0">
			<tr>
				<td align="center">实验室号:</td>
				<td ><input  type="text" name="number" value="<%=request.getParameter("number")%>" id="myinput" readonly="true" style="color:gray"></td>
				<td align="center">座位数量:</td>
				<td><input type="text" name="seatnumber" value="<%=request.getParameter("seatnumber")%>" id="myinput" readonly="true" style="color:gray"></td>
			</tr>	
			
			<tr><td align="center">开放时间:</td>
      <td colspan="3"><input type="text" name="opentime1" value="<%=request.getParameter("opentime1")%>" style="border:0;color:gray; font-size: 15px;width:90">
      <input type="text" name="opentime2" value="<%=request.getParameter("opentime2")%>" style="border:0;color:gray; font-size: 15px;width:90">
      <input type="text" name="opentime3" value="<%=request.getParameter("opentime3")%>" style="border:0;color:gray; font-size: 15px;width:90">
       <input type="text" name="opentime4" value="<%=request.getParameter("opentime4")%>" style="border:0;color:gray; font-size: 15px;width:90">
       </td>
       
    </tr> 
			<tr>				
				<td align="center">实验设备:</td>
			    <td><input type="text" name="equipment" value="<%=request.getParameter("equipment")%>" id="myinput" readonly="true" style="color:gray"></td>
			    <td align="center">申请教师:</td>
				<td><input type="text" name="name" value="<%=request.getParameter("name")%>" id="myinput" readonly="true" style="color:gray"></td>
			</tr>
			
			<tr>
			<td align="center">实验类型:</td>
				<td><input type="text" name="type" value="<%=request.getParameter("type")%>" id="myinput" readonly="true" style="color:gray"></td>				
				<td align="center">手机号:</td>
				<td><input type="text" name="phone" value="<%=request.getParameter("phone")%>" id="myinput" readonly="true" style="color:gray"></td>
			</tr>
			
			<tr>
				<td align="center">实验日期:</td>
				<td><input type="text" name="date" value="<%=request.getParameter("date")%>" id="myinput" readonly="true" style="color:gray"></td>
				<td align="center">实验时间:</td>
				<td><input type="text" name="experimenttime" value="<%=request.getParameter("experimenttime")%>" id="myinput" readonly="true" style="color:gray"></td>
			</tr>
				<tr>
				<td align="center">教师学校:</td>
				<td><input type="text" name="teaschool" value="<%=request.getParameter("teaschool")%>" id="myinput" readonly="true" style="color:gray"></td>
				<td align="center">审核人：</td>
			    <td><input type="text" name="verifyname" value="<%=request.getParameter("verifyname")%>" id="myinput" readonly="true" style="color:gray"></td>
				
			</tr>
			<tr>
			<td align="center">审核意见:</td>
				<td><input type="radio" name="result" value="审核通过"  checked="checked">同意
				    <input type="radio" name="result" value="审核不通过">不同意</td>
			<td align="center">备注：</td>
			    <td><input type="text" name="reason" id="myinput"></td>
			    
			</tr>
			<tr><td><input type="hidden" name="username"value="<%=request.getParameter("username")%>"></td></tr>
			<tr>
				<td colspan="4">
				<center><input id="mybutton" type="submit" value="提交" name="btnApp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="mybutton" type="reset" value="重置" onclick="resetFun()">
				
				</center>
				</td>
			</tr>
		</table>
	</form>
   
   </center>
   </div>  
   </div>
   </div>
   <div id="footer">
    2019 <%=session.getAttribute("school") %></div>
</body>
</html>
