package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import op.ApplyLabOpOne;
import op.ToNoteOp;
import op.ToSendCompensationOp;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Note;
import bean.User;

public class ToSendCompensationServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public ToSendCompensationServlet() {
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
			ToSendCompensationOp ts ;
			Compensation compensation;

			
			String number = ce.ChineseCoding(request.getParameter("number"));
			String labschool=ce.ChineseCoding(request.getParameter("labschool"));
			String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
			String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));


		
			ArrayList<Compensation> compensations=new ArrayList<Compensation>();
		

			compensation=new Compensation();
			compensation.setNumber(number);
            compensation.setExperimenttime(experimenttime);
            compensation.setExperimentdate(experimentdate);
            compensation.setLabschool(labschool);
           
			ts = new ToSendCompensationOp();
			compensations =ts.select(compensation);
			//JOptionPane.showMessageDialog(null, compensations.size());
			
				
					
					if(compensations.size()>0){
						
						JOptionPane.showMessageDialog(null, "您已发送过此实验室赔偿信息！");
						response.sendRedirect("notesituationresult.jsp");
						 return;
										
					}
				
			
			
			note = new  Note();
			note.setNumber(number);

			note.setExperimenttime(experimenttime);

	        note.setLabschool(labschool);
	        note.setExperimentdate(experimentdate);

			ts = new ToSendCompensationOp();
			
			ResultSet rs =ts.ck(note);
			User user;
			user=new User();
			String username=ce.ChineseCoding(request.getParameter("username"));
			user.setUsername(username);
			ResultSet rs1 =ts.ck(user);


				try {
					if (rs.next()&&rs1.next()) {
						request.getRequestDispatcher("sendcompensation.jsp?&number=" + rs.getString(1)
								+ "&labschool=" + rs.getString(5)+ "&name=" + rs.getString(2)+ "&experimenttime=" + rs.getString(3)+ "&teaschool=" + rs.getString(4)+ "&username=" + rs.getString(9)+ "&damagedescribe=" + rs.getString(13)+ "&experimentdate=" + rs.getString(17)+ "&notename=" + rs1.getString(8)+ "&notephone=" + rs1.getString(4)).forward(request, response);;
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
