package servlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


import op.VerifyResultOp;
import bean.Application;
import bean.CodeExchange;
import bean.Page;
import bean.User;

public class VerifyResultServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public VerifyResultServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		this.doPost(request,response) ;
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		//request.setCharacterEncoding("utf-8");
		Application application;
		VerifyResultOp vr  ;
		User user;
		
		
		
		String btnApp = request.getParameter("btnApp");
	

	

		if(btnApp!=null)
		{
			String number= ce.ChineseCoding(request.getParameter("number"));
			String opentime1 = ce.ChineseCoding(request.getParameter("opentime1"));
			int seatnumber = Integer.parseInt(request.getParameter("seatnumber"));
			String equipment = ce.ChineseCoding(request.getParameter("equipment"));
			String labschool = ce.ChineseCoding(request.getParameter("labschool"));
			String lableader = ce.ChineseCoding(request.getParameter("lableader"));
			String name = ce.ChineseCoding(request.getParameter("name"));
			String phone = ce.ChineseCoding(request.getParameter("phone"));
			String type = ce.ChineseCoding(request.getParameter("type"));
			String teaschool = ce.ChineseCoding(request.getParameter("teaschool"));
			String experimenttime = ce.ChineseCoding(request.getParameter("experimenttime"));
			String applytime = ce.ChineseCoding(request.getParameter("applytime"));
			String username = ce.ChineseCoding(request.getParameter("username"));
			String result=ce.ChineseCoding(request.getParameter("result"));
			String reason=ce.ChineseCoding(request.getParameter("reason"));
			String verifyname=ce.ChineseCoding(request.getParameter("verifyname"));
			String opentime2 = ce.ChineseCoding(request.getParameter("opentime2"));
			String opentime3 = ce.ChineseCoding(request.getParameter("opentime3"));
			String opentime4 = ce.ChineseCoding(request.getParameter("opentime4"));
			String date=ce.ChineseCoding(request.getParameter("date"));
			
			
			application= new Application();
			application.setNumber(number);
			application.setOpentime1(opentime1);
			application.setSeatnumber(seatnumber);
			application.setEquipment(equipment);
			application.setLableader(lableader);
			application.setName(name);
			application.setPhone(phone);
			application.setType(type);
			application.setTeaschool(teaschool);
			application.setExperimenttime(experimenttime);
			application.setUsername(username);
			application.setApplytime((String)new Date().toLocaleString());
			application.setOpentime2(opentime2);
			application.setOpentime3(opentime3);
			application.setOpentime4(opentime4);
			application.setResult(result);
		    application.setReason(reason);
		    application.setVerifyname(verifyname);
		    application.setDate(date);
		    
		    String school=ce.ChineseCoding(request.getParameter("school"));
		    user=new User();
		    user.setSchool(school);
			
			vr = new VerifyResultOp();
			boolean altFlag = vr.update(application,user);
			if(altFlag)
			{
				JOptionPane.showMessageDialog(null, "审核成功!");
			}		 	
			else
			{
				JOptionPane.showMessageDialog(null, "审核失败!");
			}
		}	
		
		

	
		String school = ce.ChineseCoding(request.getParameter("school"));
		
		String currpageno=null;
		String pageSize=null;
		
			 currpageno =request.getParameter("page");
			 pageSize = request.getParameter("pageSize");
		
		
		
		
		
		Page p=new Page();
		
		if(pageSize!=null&&!"".equals(pageSize)) {
			p.setPagesize(Integer.parseInt(pageSize.trim()));
		}
		//System.out.println("3333333333333333333333333333=="+currpageno);
		if(currpageno==null||"".equals(currpageno)) {
			p.setCurrpageno(1);
		}else {
			p.setCurrpageno(Integer.parseInt(currpageno.trim()));
		}
		
		

		ArrayList<Application> applications=new ArrayList<Application>();
		 vr = new VerifyResultOp();
		user=new User();
		user.setSchool(school);
		p.setTotalcount((vr.getTotalCount(user)));
		 try {
		 applications =vr.select(user,p.getCurrpageno(), p.getPagesize());

		 
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 
		 
		HttpSession session=request.getSession();	

		ServletContext scx=session.getServletContext();	


		scx.setAttribute("applications",applications);	
		request.getSession().setAttribute("school", school);
		request.setAttribute( "Page",p);
		request.getRequestDispatcher("verifyapply.jsp").forward(request, response);		
		 
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}