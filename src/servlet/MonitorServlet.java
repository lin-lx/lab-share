package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import op.MonitorOp;
import bean.Application;
import bean.CodeExchange;
import bean.Note;
import bean.Page;
import bean.User;

public class MonitorServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public MonitorServlet() {
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
		MonitorOp m ;
    	User user;
    	Note note;
		String school = ce.ChineseCoding(request.getParameter("school"));
		String number=ce.ChineseCoding(request.getParameter("number"));
		String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
		String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));
		String date=ce.ChineseCoding(request.getParameter("date"));
		
		
		
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
		 m = new MonitorOp();
		user=new User();
		user.setSchool(school);
		note=new Note();
		note.setExperimenttime(experimenttime);
		note.setNumber(number);
		note.setExperimentdate(experimentdate);
		application=new Application();
		application.setNumber(number);
		application.setExperimenttime(experimenttime);
		application.setDate(date);
		p.setTotalcount((m.getTotalCount(user)));
		 try {
		applications =m.select(note,user,p.getCurrpageno(), p.getPagesize());
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		HttpSession session=request.getSession();	

		ServletContext scx=session.getServletContext();	


		scx.setAttribute("applications",applications);	
		request.setAttribute( "Page",p);
		request.getSession().setAttribute("school", school);
		request.getRequestDispatcher("monitor.jsp").forward(request, response);		
		 
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
