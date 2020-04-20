package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import op.ShowManNoteFinishPictureOp;
import op.ShowManTeaCreditPictureOp;
import bean.CodeExchange;
import bean.Credit;
import bean.Note;
import bean.User;

public class ShowManTeaCreditPictureServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public ShowManTeaCreditPictureServlet() {
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


		this.doPost(request, response);
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
		Credit credit;
		User user;
		ShowManTeaCreditPictureOp m ;

String number=ce.ChineseCoding(request.getParameter("number"));
String school=ce.ChineseCoding(request.getParameter("school"));
String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));

			
		

				ArrayList<Credit> credits=new ArrayList<Credit>();
				 m = new ShowManTeaCreditPictureOp();
				 credit=new Credit();
				 credit.setNumber(number);
				 credit.setExperimenttime(experimenttime);
				 credit.setExperimentdate(experimentdate);
				 user=new User();
				 user.setSchool(school);
				 credits = m.quire(credit, user);

				

				HttpSession session=request.getSession();	

				ServletContext scx=session.getServletContext();	

				scx.setAttribute("credits",credits);	
				request.getRequestDispatcher("showmanteacreditpicture.jsp").forward(request, response);		
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
