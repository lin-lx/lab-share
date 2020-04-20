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

import op.ApplyLabOpOne;
import op.DeleteLabOp;
import op.UpdateLabOp;
import bean.Application;
import bean.CodeExchange;
import bean.Lab;
import bean.Page;
import bean.User;


public class UpdateLabServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public UpdateLabServlet() {
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
		Lab lab;
		Application application;

		UpdateLabOp l ;
		
		String number = ce.ChineseCoding(request.getParameter("number"));
		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		String date=ce.ChineseCoding(request.getParameter("date"));

		lab = new  Lab();
		lab.setNumber(number);
		lab.setLabschool(labschool);
		
		l = new UpdateLabOp();
		
		
		ArrayList<Application> applications=new ArrayList<Application>();
	
        application=new Application();
        application.setNumber(number);
        application.setLabschool(labschool);
        application.setDate(date);

		l = new UpdateLabOp();
		applications = l.select(application);
		//JOptionPane.showMessageDialog(null, applications.size());
		
			
				
				if(applications.size()>0){
					
					JOptionPane.showMessageDialog(null, "此实验室正在使用中，不能修改，请下次再来！");

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
		
				
					
			ArrayList<Lab> labs=new ArrayList<Lab>();
			 l = new UpdateLabOp();
			 String school=ce.ChineseCoding(request.getParameter("school"));
			 User user;
			 user=new User();
			 user.setSchool(school);		 
			 p.setTotalcount((l.getTotalCount(user)));	 
			 try{
			 labs = l.quire(user,p.getCurrpageno(),p.getPagesize());
		   // JOptionPane.showMessageDialog(null, labs.size());
			 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			HttpSession session=request.getSession();	

			ServletContext scx=session.getServletContext();	

			scx.setAttribute("labs",labs);	
			request.getSession().setAttribute("school", school);
			request.setAttribute( "Page",p);
			request.getRequestDispatcher("labmanage.jsp").forward(request, response);	
					 return;
									
				}
	
		
		
		
		ResultSet rs = l.ck(lab);


			try {
				if (rs.next()) {
					request.getRequestDispatcher("labalter.jsp?&number=" + rs.getString(1)
							+ "&opentime=" + rs.getString(2)+ "&seatnumber=" + rs.getInt(3)+ "&equipment=" + rs.getString(4)+ "&labschool=" + rs.getString(5)+ "&lableader=" + rs.getString(6)+ "&type=" + rs.getString(13)+ "&expense=" + rs.getString(14)+ "&picture=" + rs.getString(8)).forward(request, response);;
				}else{
					JOptionPane.showMessageDialog(null, "异常");
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
