package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;



import op.ShowAdministratorOp;
import op.ShowTeacherOp;
import bean.CodeExchange;
import bean.User;

public class ShowTeacherServlet extends HttpServlet {

	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ShowTeacherServlet() {
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
		User user;
		ShowTeacherOp t ;

		String username = ce.ChineseCoding(request.getParameter("username"));
		

				ArrayList<User> users=new ArrayList<User>();
				t = new ShowTeacherOp();
				user=new User();
				user.setUsername(username);
				 users = t.select(user);
				// JOptionPane.showMessageDialog(null, users.size());
				

				HttpSession session=request.getSession();	

				ServletContext scx=session.getServletContext();	


				scx.setAttribute("users",users);	
				response.sendRedirect("showteacher.jsp");		
				 
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
