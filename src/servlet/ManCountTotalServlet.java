package servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import op.ManCountTotalOp;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Note;
import bean.User;

public class ManCountTotalServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ManCountTotalServlet() {
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

			//response.setContentType("text/html");
			//request.setCharacterEncoding("utf-8");
			
			
			String sqlstr=" and ";
			String number=null;			
			number=new String(request.getParameter("number").getBytes("ISO8859_1"),"UTF-8");
			
			Application application;
			application=new Application();
			
		    User user;
			user=new User();
			
			Note note;
			note=new Note();
			
			Compensation compensation;
			compensation=new Compensation();
			
			ManCountTotalOp m;
			m=new ManCountTotalOp();
			
			
			String labschool=ce.ChineseCoding(request.getParameter("labschool"));
			String school=ce.ChineseCoding(request.getParameter("school"));
			
			
			if (number!=null&&!"".equals(number)) {
				sqlstr+="number='"+number+"'";
			}
			
			
			// else sqlstr=sqlstr.substring(0, sqlstr.length()-4);
			
			
			
			
			
			
			
			//申请次数
			
			ArrayList<Application> applynumber=new ArrayList<Application>();
	
	       try{ 
	        application.setLabschool(labschool);
	        application.setNumber(number);
	        user.setSchool(school);
			
		
			applynumber = m.select1(sqlstr,application,user);
			
	       } catch (Exception e) {
	   		
	   		e.printStackTrace();
	   	}
			
		//	JOptionPane.showMessageDialog(null, applynumber.size());
			 
			int applysize=applynumber.size();
			 request.getSession().setAttribute("applysize",applysize);

	
		 
			
//使用次数
			 
			 


			ArrayList<Note> usenumber=new ArrayList<Note>();
		
			try { 
			 
	        note.setLabschool(labschool);
	        application.setNumber(number);
	        user.setSchool(school);
			
				
			
		        usenumber = m.select2(sqlstr,note,user);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
				
			//	JOptionPane.showMessageDialog(null, usenumber.size());
				 
				int usesize=usenumber.size();
				 request.getSession().setAttribute("usesize",usesize);
				
				 
	//取消申请次数	
				 

			ArrayList<Application> cancelumber=new ArrayList<Application>();
				
				      
			try {  
	        application.setLabschool(labschool);
	        application.setNumber(number);
	        user.setSchool(school);
			
				
						
					
		cancelumber = m.select3(sqlstr,application,user);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
						
						//JOptionPane.showMessageDialog(null, cancelumber.size());
						 
						int cancelsize=cancelumber.size();
					 request.getSession().setAttribute("cancelsize",cancelsize);
						 
						
						 
		//违约次数				 

					

			ArrayList<Note> treatynumber=new ArrayList<Note>();
								
					 
						  
			try {   
	        note.setLabschool(labschool);
	        note.setNumber(number);
	        user.setSchool(school);
					      
					      note=new Note();
										
									
		 treatynumber=m.select4(sqlstr,note,user);
			} catch (Exception e) {
				
				e.printStackTrace();
			}   
					  	
										
				//	JOptionPane.showMessageDialog(null, treatynumber.size());
										 
					int treatysize=treatynumber.size();
					 request.getSession().setAttribute("treatysize",treatysize);
					 
					
					 
						//故障次数				 


						ArrayList<Compensation> compensationnumber=new ArrayList<Compensation>();
											
								try{   
						 
				        compensation.setLabschool(labschool);
				        compensation.setNumber(number);
				        user.setSchool(school);
								      
								    
													
												
								      compensationnumber=m.select5(sqlstr,compensation,user);
								} catch (Exception e) {
									
									e.printStackTrace();
								}
													
								//JOptionPane.showMessageDialog(null, compensationnumber.size());
													 
								int compensationsize=compensationnumber.size();
								 request.getSession().setAttribute("compensationsize",compensationsize);
								 								 
								 	
									request.getRequestDispatcher("mancounttotal.jsp").forward(request, response);	
								 
								 
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
