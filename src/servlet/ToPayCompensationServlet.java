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

import op.ToPayCompensationOp;
import op.ToSendCompensationOp;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.User;

public class ToPayCompensationServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public ToPayCompensationServlet() {
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
		Compensation compensation;
		ToPayCompensationOp tp ;
		
		String number = ce.ChineseCoding(request.getParameter("number"));

		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
		String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));


		

		
		compensation = new  Compensation();
		compensation.setNumber(number);

		compensation.setExperimenttime(experimenttime);
		compensation.setExperimentdate(experimentdate);

		compensation.setLabschool(labschool);

		tp = new ToPayCompensationOp();
		
		ResultSet rs =tp.ck(compensation);


			try {
				if (rs.next()) {
					request.getRequestDispatcher("paycompensation.jsp?&number=" + rs.getString(1)
							+ "&labschool=" + rs.getString(2)+ "&experimenttime=" + rs.getString(6)+ "&money=" + rs.getString(7)+ "&illustration=" + rs.getString(8)+ "&experimentdate=" + rs.getString(18)).forward(request, response);;
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
