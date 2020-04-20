package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import op.LeaCountTotalOp;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Note;
import bean.User;

public class LeaCountTotalServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public LeaCountTotalServlet() {
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
		//request.setCharacterEncoding("utf-8");
		
		Application application;
		application=new Application();
		
		User user;
		user=new User();
		
		Note note;
		note=new Note();
		
		Compensation compensation;
		compensation=new Compensation();
		
		LeaCountTotalOp l;
		l=new LeaCountTotalOp();
		
		
		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		String school=ce.ChineseCoding(request.getParameter("school"));
		
		ArrayList<Application> applynumber=new ArrayList<Application>();

        
        application.setLabschool(labschool);
        user.setSchool(school);
		
	
		applynumber = l.select1(application,user);
		
	//	JOptionPane.showMessageDialog(null, applynumber.size());
		 
		int applysize=applynumber.size();
		 request.getSession().setAttribute("applysize",applysize);

		 
		 
		
//使用次数
		ArrayList<Note> usenumber=new ArrayList<Note>();
	
	     
		 
        note.setLabschool(labschool);
        user.setSchool(school);
		
			
		
	        usenumber = l.select2(note,user);
			
		//	JOptionPane.showMessageDialog(null, usenumber.size());
			 
			int usesize=usenumber.size();
			 request.getSession().setAttribute("usesize",usesize);
			
			 
//取消申请次数			 

		ArrayList<Application> cancelumber=new ArrayList<Application>();
			
			      
		 
        application.setLabschool(labschool);
        user.setSchool(school);
		
			
					
				
			        cancelumber = l.select3(application,user);
					
					//JOptionPane.showMessageDialog(null, cancelumber.size());
					 
					int cancelsize=cancelumber.size();
					 request.getSession().setAttribute("cancelsize",cancelsize);
					 
					
					 
	//违约次数				 
					 

		ArrayList<Note> treatynumber=new ArrayList<Note>();
							
				 
					  
		 
        note.setLabschool(labschool);
        user.setSchool(school);
				      
				      note=new Note();
									
								
				      treatynumber=l.select4(note,user);
									
			//	JOptionPane.showMessageDialog(null, treatynumber.size());
									 
				int treatysize=treatynumber.size();
				 request.getSession().setAttribute("treatysize",treatysize);
				 
				
				 
					//故障次数				 
				 

					ArrayList<Compensation> compensationnumber=new ArrayList<Compensation>();
										
							   
					 
			        compensation.setLabschool(labschool);
			        user.setSchool(school);
							      
							    
												
											
							      compensationnumber=l.select5(compensation,user);
												
							//JOptionPane.showMessageDialog(null, compensationnumber.size());
												 
							int compensationsize=compensationnumber.size();
							 request.getSession().setAttribute("compensationsize",compensationsize);							 								 
								request.getRequestDispatcher("leacounttotal.jsp").forward(request, response);	
							 
							 
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
