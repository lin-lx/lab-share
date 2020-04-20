package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import op.ApplyLabOpOne;
import op.ToCommentOp;
import bean.Application;
import bean.CodeExchange;
import bean.Lab;
import bean.User;

public class ToCommentServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ToCommentServlet() {
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
		Application application;
		ToCommentOp tm ;
		String number = ce.ChineseCoding(request.getParameter("number"));
		String labschool = ce.ChineseCoding(request.getParameter("labschool"));
		
		application=new Application();
		application.setNumber(number);
		application.setLabschool(labschool);

		

		tm = new ToCommentOp();
		
		ResultSet rs = tm.ck(application);


			try {
				if (rs.next()) {
					request.getRequestDispatcher("tocomment.jsp?&number=" + rs.getString(1)
							+ "&labschool=" + rs.getString(5)+ "&type=" + rs.getString(9)+"&experimenttime=" + rs.getString(10)+"&date=" + rs.getString(21)).forward(request, response);;
				}else{
					JOptionPane.showMessageDialog(null, "error");
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
