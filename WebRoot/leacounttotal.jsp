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
  $('number').value='';
  $('experimenttime').value='';
 $('name').value='';
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
  font-family:楷体;
  font-size:22px;
}

#right1{
  position:absolute; 
  top:50px;
  margin-left:100px; 
  height:540;
  font-family:楷体;
  font-size:22px;
}


#right2{
  position:relative;
  top:50px; 
  margin-left:700px; 
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

#myfont3{
font-size:18px;
color:red;
font-family:楷体;
font-weight: bold;

}

#myfont4{
font-size:18px;
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
  <a href="leafirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
  </div>
  
  <div id="top">
  当前位置：实验室使用情况
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

   
   <div id="right1">
  
   <div id="bar" style="width: 450px;height:430px; border:0px;" ></div>
    
   
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bar'));
    // 指定图表的配置项和数据
    var option = {
            title: {
                text: '实验室使用情况柱形图',
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
                data: ["申请次数","使用次数","取消次数","违约次数","故障次数"]
            },
            yAxis: {
            	show:true,
            	position:'left'   //right
            },
            series: [{
                name: '次数',  //要和legend中的data对应起来
                type: 'bar',  //pie-饼图，bar-柱状图,line-折线图
                 data:[<%=session.getAttribute("applysize")%>, <%=session.getAttribute("usesize")%>, <%=session.getAttribute("cancelsize")%>, <%=session.getAttribute("treatysize")%>, <%=session.getAttribute("compensationsize")%>]

            }]
        };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script> 

 <script type="text/javascript">
 function per(number1,number2){
 return (Math.round(number1/number2*10000)/100.00+"%");
 
  }
  
 </script>
 
 <table border="0">
 <tr><td id="myfont3">统计结果分析:</td><tr>
<tr><td id="myfont4">使用率：
 <script type="text/javascript">
document.write(per(<%=session.getAttribute("usesize")%>,<%=session.getAttribute("applysize")%>))
</script></td>
<td id="myfont4">故障率：
 <script type="text/javascript">
document.write(per(<%=session.getAttribute("compensationsize")%>,<%=session.getAttribute("usesize")%>))
</script></td>
<td id="myfont4">违约率：
 <script type="text/javascript">
document.write(per(<%=session.getAttribute("treatysize")%>,<%=session.getAttribute("applysize")%>))
</script></td>
</tr>
 </table>
  </div>
  
  
<div id="right2">
<div id="line" style="width: 450px;height:430px; border:0px;" ></div>
   <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('line'));
    // 指定图表的配置项和数据
    var option = {
            title: {
                text: '实验室使用情况折线图',
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
                data: ["申请次数","使用次数","取消次数","违约次数","故障次数"]
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
  </div>
   </div>
   </div>
   
   <div id="footer">
    2019 <%=session.getAttribute("school") %> </div>
</body>
</html>
