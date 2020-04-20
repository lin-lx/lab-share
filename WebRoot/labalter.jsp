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
  $('picture').value='';
  return;
 }
 
 
 function validate(v)
{   
   
if(mySelect.options[1].selected == false||mySelect.options[2].selected == false||mySelect.options[3].selected == false||mySelect.options[4].selected == false)
{
  alert("请选择实验室开放时间！");

  return false;
} 
if(v.type.value=="科研研究" && v.expense.value=="")
{
  alert("实验室类型为科研研究实验室，请输入费用");
 v.expense.focus();
  return false;
} 

if(v.type.value=="教学需要" && v.expense.value!="")
{
  alert("实验室免费提供教学需要，无收费");
 v.expense.focus();
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
  <a href="manfirst.jsp" style="text-decoration:none;color:white;"><img src="sysimages/first.png" style="width:20px;height:20px">首页</a>
 
 
  </div>
  <div id="top">
  当前位置：<a href="ShowLabServlet?school=<%=session.getAttribute("school")%>" style="text-decoration:none;color:white;">实验室信息管理</a> > 修改实验室信息
  </div>
  
   <div id="content">
   <div id="left" style="border:1px  solid skyblue">
   <table border="0" style="height:70px;width:200px;background:url(images/a.jpg);border-bottom:0px;">
   <tr><td id="myfont1"><img src="sysimages/user.png" style="width:30px;height:30px"> <%=session.getAttribute("username")%></td></tr>
   <tr><td id="myfont1">>>欢迎进入本系统！</td></tr>
   <tr><td id="myfont1"> <img src="sysimages/date.png" style="width:30px;height:30px">v<%
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
   <tr><td colspan="5"id="myfont1" style="background:url(images/a.jpg);height:30;">修改实验室信息</td></tr>
   </table>
   </form>
   <form action="AlterLabServlet?school=<%=session.getAttribute("school") %>" method="post" onSubmit="return validate(this)" enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td align="center">实验室号:</td>
				<td ><input  type="text" name="number" value="<%=request.getParameter("number")%>" id="myinput" readonly="true" style="color:gray"></td>
				<td align="center">所属学校:</td>
				<td><input type="text" name="labschool" value="<%=request.getParameter("labschool")%>" id="myinput" readonly="true" style="color:gray"></td>				
			</tr>	
			
			<tr>
			<td  align="center">开放时间:</td>
				<td colspan="3">			
				   <input type="checkbox" name="opentime1" value="8:00-10:00" checked="checked"> 8:00-10:00  
				   <input type="checkbox" name="opentime2" value="8:20-12:00"> 8:20-12:00  
				   <input type="checkbox" name="opentime3" value="14:00-16:00"> 14:00-16:00 
				   <input type="checkbox" name="opentime4" value="16:10-17:30"> 15:10-17:30 
				</td>	
			</tr>
           


			<tr>
				<td align="center">座位数量:</td>
				<td><input type="text" name="seatnumber" value="<%=request.getParameter("seatnumber")%>" id="myinput"></td>
				<td align="center">设备及数量:</td>
			    <td><input type="text" name="equipment" value="<%=request.getParameter("equipment")%>" id="myinput"></td>
			</tr>
			
		
			<tr>
				
				<td align="center">实验室管理员:</td>
				<td><input type="text" name="lableader" value="<%=request.getParameter("lableader")%>" id="myinput" readonly="true" style="color:gray"></td>
				<td align="center">实验室类型:</td>
				 <td>
				 <select  name="type" id="myinput">
				 <option {if $sex==教学需要}selected="selected"{/if}>  教学需要  </option>
                <option {if $sex==科研研究}selected="selected"{/if}>  科研研究  </option>
				 </select>
                 </td>				
			</tr>
	
	          <tr>
				 <td  align="center">实验室费用:</td>
			     <td><input type="text" name="expense" id="myinput" value="<%=request.getParameter("expense")%>"></td>
			     <td colspan="2"style="color:red">*实验室类型为科研研究时才收取费用</td>
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
			<tr>
				<td colspan="4">
				<center><input id="mybutton" type="submit" value="修改" name="btnAlt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
