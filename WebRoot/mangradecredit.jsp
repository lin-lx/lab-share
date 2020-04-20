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
	
	
	
	function setImagePreview(avalue) {
    var docObj = document.getElementById("doc");
    var imgObjPreview = document.getElementById("preview");
    if(docObj.files && docObj.files[0])
    {
       
        imgObjPreview.style.display = 'block';
        imgObjPreview.style.width = '200px';
        imgObjPreview.style.height = '100px';

        imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
    }
    else
    {
       
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag");
        localImagId.style.width = "200px";
        localImagId.style.height = "100px"; 
        try {
            localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        } catch(e) {
            alert("上传图片格式不正确，请重新上传!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
    }
    return true;
}



function setImagePreview1(avalue) {
    var docObj = document.getElementById("doc1");
    var imgObjPreview = document.getElementById("preview1");
    if(docObj.files && docObj.files[0])
    {
       
        imgObjPreview.style.display = 'block';
        imgObjPreview.style.width = '200px';
        imgObjPreview.style.height = '100px';

        imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
    }
    else
    {
       
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag");
        localImagId.style.width = "200px";
        localImagId.style.height = "100px"; 
        try {
            localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        } catch(e) {
            alert("上传图片格式不正确，请重新上传!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
    }
    return true;
}
	
	
	  function resetFun(){
  $('notename').value='';
  $('detail').value='';

  return;
 }
	
function validate(v)
{   
   
if(v.name.value=="")
{
  alert("请输入你的姓名！");
 v.name.focus();
  return false;
} 
if(v.damage.value=="")
{
  alert("请给设备使用情况评分！");
 v.damage.focus();
  return false;
} 
if(v.environment.value=="")
{
  alert("请给卫生环境情况评分！");
 v.environment.focus();
  return false;
} 
if(v.totalcredit.value=="")
{
  alert("请点击信誉总分按钮计算信誉分数！");
 
  return false;
}

 var reg = /(^[1-5]*$)/;  
   if(reg.test(v.damage.value) === false)  
   {  
       alert("设备使用评分请输入大于0小于5的分数"); 
        v.damage.focus(); 
       return  false;  
   } 
   
   if(reg.test(v.environment.value) === false)  
   {  
       alert("卫生情况评分请输入大于0小于5的分数"); 
        v.environment.focus(); 
       return  false;  
   } 
   
   
}


function sum(){
var num1=parseInt(document.getElementById("num1").value);
var num2=parseInt(document.getElementById("num2").value);
var num3=document.getElementById("num3");
num3.value=(num1+num2);
  
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

.myinput1
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
  <a href="manfirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
  </div>
  <div id="top">
  
  当前位置：<a href="ShowManToGradeCreditServlet?school=<%=session.getAttribute("school")%>"style="text-decoration:none;color:white;">教师信誉评分</a> > 填写信誉评分表
  </div>
  
   <div id="content">
   <div id="left" style="border:1px  solid skyblue">
   <table border="0" style="height:70px;width:200px;background:url(images/a.jpg);border-bottom:0px;">
   <tr><td id="myfont1">
<img src="sysimages/user.png" style="width:30px;height:30px"> <%=session.getAttribute("username")%></td></tr>
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
   <center>
    <form class="table-c">
   <table border="0" style="width:1320px">
   <tr><td colspan="5"id="myfont1" style="background:url(images/a.jpg);height:30;">记录实验室使用情况</td></tr>
   </table>
   </form>
   
  <form action="ManGradeCreditServlet?school=<%=session.getAttribute("school")%>" method="post" onSubmit="return validate(this)" enctype="multipart/form-data">
  <table>
  <tr><td colspan="4" style="color:red" align="center">说明：教师信誉评分是为了更好的管理实验室，卫生和设备情况评分满分各为5分，请根据实际使用情况评分！</td></tr>
  <tr>
				<td align="center">实验室号:</td>
				<td ><input  type="text" name="number" id="myinput" value="<%=request.getParameter("number")%>" readonly="true" style="color:gray"></td>
				<td  align="center">申请教师:</td>
				<td><input  type="text" name="name" id="myinput" value="<%=request.getParameter("name")%>" readonly="true" style="color:gray"></td>
			</tr>	
			<tr>
				<td align="center">教师学校:</td>
				<td><input  type="text" name="teaschool"  id="myinput" value="<%=request.getParameter("teaschool")%>" readonly="true" style="color:gray"></td>			 
				<td align="center">实验时间:</td>
				<td><input type="text" name="experimenttime" id="myinput" value="<%=request.getParameter("experimenttime")%>" readonly="true" style="color:gray"></td>
               				
			</tr>
			 			
			  <tr>
				<td align="center">我的姓名:</td>
				<td><input  type="text" name="notename"  id="myinput" value="<%=request.getParameter("notename")%>" readonly="true" style="color:gray"></td>					
								
				<td align="center">设备情况评分:</td>
				<td><input type="text" name="damage" class="myinput1" id="num1"></td>
				</tr>
				
				<tr>				
				<td align="center">卫生情况评分:</td>
				<td><input type="text" name="environment" class="myinput1" value="" id="num2"></td>
				<td align="center"><input type="button" value="信誉总分" Onclick="sum();" id="mybutton"></td>
				<td><input type="text" name="totalcredit" class="myinput1" id="num3" readonly="true" style="color:gray"></td>
				</tr>

                 <tr>
                 <td align="center">扣分理由:</td>
                 <td><input type="text" name="reason" id="myinput"></td>
                 </tr>
                 
                <tr>
				<td align="center">上传图片1:</td>
				<td><input type="file" name="picture" id="doc" onchange="javascript:setImagePreview();" style="border: skyblue 1px solid; height: 35px;"></td>
				<td align="center">上传图片2:</td>
				<td><input type="file" name="picture1" id="doc1" onchange="javascript:setImagePreview1();" style="border: skyblue 1px solid; height: 35px;"></td>				 				
			   </tr>

			    <tr>
				 <td align="center">图片预览:</td>
                 <td height="101">
                 <div id="localImag"><img id="preview" src="" width="200" height="100" style="display: block; width: 200px; height: 100px;"></div>
               </td>
				 <td align="center">图片预览:</td>
               <td height="101">
                <div id="localImag"><img id="preview1" src="" width="200" height="100" style="display: block; width: 200px; height: 100px;"></div>
               </td>
			   </tr>
			   
			   <tr><td><input type="hidden" name="experimentdate" id="myinput" value="<%=request.getParameter("experimentdate")%>"></td></tr>
			   
			  <tr>
			  <td><input type="hidden" name="username" id="myinput" value="<%=request.getParameter("username")%>" readonly="true" style="color:gray"></td>
			  <td><input type="hidden" name="labschool" value="<%=request.getParameter("labschool")%>" id="myinput" readonly="true" style="color:gray"></td>
			  </tr>
			
			<tr>
				<td colspan="4">
				<center>
				<input id="mybutton" type="submit" value="提交" name="btnAdd"/>&nbsp;&nbsp;
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
