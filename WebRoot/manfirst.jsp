<%@ page language="java" import="java.util.*" import="bean.*" import="op.*" pageEncoding="utf-8"%>
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
window.onload = function(){
  var oDiv = document.getElementById('scroll');
  var btnUp = document.getElementById('up');
  var btnDown = document.getElementById('down');
  var oUl = oDiv.getElementsByTagName('ul')[0];
  var timer = null;
  var speed = -1;
  oUl.innerHTML += oUl.innerHTML;
  setTimeout(move,1500);
  btnUp.onclick = function(){
    clearInterval(timer);
    speed = -1;
    move();
  };
  btnDown.onclick = function(){
    clearInterval(timer);
    speed = 1;
    move();
  };
  oUl.onmouseover = function(){
    clearInterval(timer);
  };
  oUl.onmouseout = function(){
    move();
  };
  function move(){
    timer = setInterval(function(){
      oUl.style.top = oUl.offsetTop + speed + 'px';
      if(oUl.offsetTop <= - oUl.offsetHeight / 2){
        oUl.style.top = '0';
      }else if(oUl.offsetTop >= 0){
        oUl.style.top = - oUl.offsetHeight / 2 + 'px';
      };
    },30);
  };
};
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

#right1{
  position:absolute; 
  top:0px; 
  margin-left:100px; 
  height:570;
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
	<a href="manfirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/reflash.png" style="width:20px;height:20px">刷新</a>
  

  </div>
  <div id="top">
	当前位置：首页
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
   
<div id="right1">
  
	 
	  <h2>通知公告</h2>
	  <marquee height=300px direction="up" behavior="scroll" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()"> 
   <ul>
    <% 
		    RemindOp r;
		   r = new RemindOp();
		   Page p=new Page();
		    %>
		
    <% 
    ArrayList  h=r.quire(p.getCurrpageno(), p.getPagesize());
    ArrayList<Remind> reminds=(ArrayList<Remind>)(h);
			if(reminds!=null){
	          	for(int i=0;i<reminds.size();i++)
	         	 {
	            	 Remind remind=reminds.get(i);
	            	 %>    
	            	 <li>       	
	           <div> <%=remind.getContent()%>..................<%=remind.getDate() %></div>
	             </li>                            
	               <br>
	            	 <%
	             }
	         } %>
	        </ul>
	         </marquee>
	         
 </div>
 </div>
   </div>
   <div id="footer">
    2019 <%=session.getAttribute("school") %> </div>
</body>
</html>
