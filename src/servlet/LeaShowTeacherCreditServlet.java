package servlet;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import op.LeaShowTeacherCreditOp;

import bean.Application;
import bean.CodeExchange;
import bean.User;

public class LeaShowTeacherCreditServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public LeaShowTeacherCreditServlet() {
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
		Application application;
		LeaShowTeacherCreditOp aa ;
		User user;
	
		
		
		String username = ce.ChineseCoding(request.getParameter("username"));
		String school=ce.ChineseCoding(request.getParameter("school"));
		String number=ce.ChineseCoding(request.getParameter("number"));
		String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
		

		
		aa = new LeaShowTeacherCreditOp();
		application=new Application();
		application.setUsername(username);
		application.setNumber(number);
		application.setExperimenttime(experimenttime);
		
		user=new User();
		user.setSchool(school);
		ResultSet rs = aa.ck(application,user);

		
			try {
				if (rs.next()) {
					request.getRequestDispatcher("leashowteachercredit.jsp?&name=" +rs.getString(7)+  "&teaschool=" + rs.getString(11)).forward(request, response);;
				}else{
		
					response.sendRedirect("leashowteachercredit.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
