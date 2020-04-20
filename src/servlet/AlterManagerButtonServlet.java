package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import op.AlterManagerButtonOp;
import bean.CodeExchange;
import bean.User;

public class AlterManagerButtonServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public AlterManagerButtonServlet() {
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
		AlterManagerButtonOp am ;
		String ide = ce.ChineseCoding(request.getParameter("ide"));
		String username = ce.ChineseCoding(request.getParameter("username"));
		
		user = new  User();
		user.setIde(ide);
		user.setUsername(username);
		
		am = new AlterManagerButtonOp();
		ResultSet rs = am.ck(user);


			try {
				if (rs.next()) {
					request.getRequestDispatcher("altermanager.jsp?&username=" + rs.getString(1)
							+ "&name=" + rs.getString(8)+ "&sex=" + rs.getString(6)+ "&ide=" + rs.getString(3)+ "&phone=" + rs.getString(4)+ "&nation=" + rs.getString(9)+ "&birthday=" + rs.getString(10)+ "&hiredate=" + rs.getString(11)+"&address=" + rs.getString(12)+"&nativepla=" + rs.getString(13)+"&education=" + rs.getString(14)+"&school=" + rs.getString(15)).forward(request, response);;
				}else{

					response.sendRedirect("fail.jsp");
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
