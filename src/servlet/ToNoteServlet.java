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
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Lab;
import bean.Note;
import bean.User;

public class ToNoteServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ToNoteServlet() {
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
		ToNoteOp tn ;
		User user;
		String number = ce.ChineseCoding(request.getParameter("number"));
		String name = ce.ChineseCoding(request.getParameter("name"));
		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
		String type=ce.ChineseCoding(request.getParameter("type"));
		String phone=ce.ChineseCoding(request.getParameter("phone"));
		String username=ce.ChineseCoding(request.getParameter("username"));
		String date=ce.ChineseCoding(request.getParameter("date"));
		String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));
		
		user=new User();
		String school=ce.ChineseCoding(request.getParameter("school"));
		user.setSchool(school);
		
		application = new  Application();

		application.setNumber(number);
		application.setName(name);
		application.setExperimenttime(experimenttime);
		application.setType(type);
		application.setPhone(phone);
		application.setUsername(username);
        application.setLabschool(labschool);
        application.setDate(date);
		tn = new ToNoteOp();
		
		ArrayList<Note> notes=new ArrayList<Note>();	
		Note note;
		note=new Note();
		note.setNumber(number);
		note.setLabschool(labschool);
		note.setExperimenttime(experimenttime);
		note.setExperimentdate(experimentdate);
		notes=tn.select(note);
	//	JOptionPane.showMessageDialog(null, notes.size());	
		
		if(notes.size()>0){
			
			JOptionPane.showMessageDialog(null, "此时间段实验室使用情况已记录完，您可以去记录其他实验室使用情况！");
			response.sendRedirect("monitor.jsp");
			 return;
							
		}
	
		
		
		ResultSet rs =tn.ck(application, user);
		user.setUsername(username);
		ResultSet rs1 =tn.ck(user);


			try {
				if (rs.next()&&rs1.next()) {
					request.getRequestDispatcher("notesituation.jsp?&number=" + rs.getString(1)
							+ "&labschool=" + rs.getString(5)+ "&name=" + rs.getString(7)+ "&phone=" + rs.getString(8)+ "&type=" + rs.getString(9)+ "&experimenttime=" + rs.getString(10)+ "&teaschool=" + rs.getString(11)+ "&username=" + rs.getString(13)+ "&date=" + rs.getString(21)+ "&notename=" + rs1.getString(8)+ "&notephone=" + rs1.getString(4)).forward(request, response);;
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
