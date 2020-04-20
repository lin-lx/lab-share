package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import op.ManToGradeCreditOp;
import bean.CodeExchange;
import bean.Note;
import bean.User;

public class ManToGradeCreditServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public ManToGradeCreditServlet() {
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
            Note note;
            User user;
			ManToGradeCreditOp mt ;
			String number = ce.ChineseCoding(request.getParameter("number"));		
			String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
			String school=ce.ChineseCoding(request.getParameter("school"));
			String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));

			
	

			
			note = new  Note();
			note.setNumber(number);
			note.setExperimenttime(experimenttime);
			note.setExperimentdate(experimentdate);
			user=new User();
            user.setSchool(school);
            mt = new ManToGradeCreditOp();

			
			
			ResultSet rs =mt.ck(note,user);
			String username=ce.ChineseCoding(request.getParameter("username"));
			user.setUsername(username);
			ResultSet rs1 =mt.ck(user);


				try {
					if (rs.next()&&rs1.next()) {
						request.getRequestDispatcher("mangradecredit.jsp?&number=" + rs.getString(1)
								+ "&name=" + rs.getString(2)+ "&experimenttime=" + rs.getString(3)+ "&teaschool=" + rs.getString(4)+ "&labschool=" + rs.getString(5)+ "&username=" + rs.getString(9)+ "&experimentdate=" + rs.getString(17)+ "&notename=" + rs1.getString(8)).forward(request, response);;
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
