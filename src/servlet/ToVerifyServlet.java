package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import op.LabOp;
import op.LoginOp;
import op.TeacherCancelApplyOp;
import op.ToVerifyOp;
import op.UpdateLabOp;
import op.VerifyApplyOp;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Credit;
import bean.Lab;
import bean.Note;
import bean.Page;
import bean.User;

public class ToVerifyServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ToVerifyServlet() {
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
		User user;
		Note note;
		Compensation compensation;
		Credit credit;
		ToVerifyOp tv ;
		
		String number = ce.ChineseCoding(request.getParameter("number"));
		String experimenttime = ce.ChineseCoding(request.getParameter("experimenttime"));
		String date=ce.ChineseCoding(request.getParameter("date"));
		String school=ce.ChineseCoding(request.getParameter("school"));
		String username=ce.ChineseCoding(request.getParameter("username"));

		
		
		application = new  Application();
		application.setNumber(number);
		application.setExperimenttime(experimenttime);
        application.setDate(date);
        application.setUsername(username);
     
        
        user=new User();
        user.setSchool(school);
        user.setUsername(username);
	
        tv = new ToVerifyOp();	
		note=new Note();
		compensation=new Compensation();
		credit=new Credit();

	   		
	  	String labschool=ce.ChineseCoding(request.getParameter("labschool"));
	   		
	   		
	   		
			application=new Application();
			application.setLabschool(labschool);
			application.setNumber(number);
			application.setExperimenttime(experimenttime);
			application.setDate(date);
					
		

					ArrayList<Application> applications=new ArrayList<Application>();
					applications = tv.select(application,user);
						 
								if(applications.size()>0){
									
									JOptionPane.showMessageDialog(null, "已超过实验时间，不可以审核啦!");
									
									
									
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

									ArrayList<Application> appli=new ArrayList<Application>();
									tv = new ToVerifyOp();
									user=new User();
								
									user.setSchool(school);
									p.setTotalcount((tv.getTotalCount(user)));
									 try {
										 appli =tv.select(user,p.getCurrpageno(), p.getPagesize());
									 
									 } catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
																		
									
									HttpSession session=request.getSession();	

									ServletContext scx=session.getServletContext();	

									scx.setAttribute("appli",appli);	
									request.getSession().setAttribute("school", school);
									request.setAttribute( "Page",p);
									request.getRequestDispatcher("verifyapply.jsp").forward(request, response);	
									 return;
													
								}
				   	       

						
									 
	//返回结果								
	
		ResultSet rs = tv.ck(application,user);
		user.setUsername(username);
		ResultSet rs2 = tv.ck(user);
	
	
			try {
				if (rs.next()&&rs2.next()) {
					
				
					
					request.getRequestDispatcher("toverify.jsp?&number=" + rs.getString(1)
							+ "&opentime1=" + rs.getString(2)+ "&seatnumber=" + rs.getInt(3)+ "&equipment=" + rs.getString(4)+ "&name=" + rs.getString(7)+ "&phone=" + rs.getString(8)+ "&type=" + rs.getString(9)+ "&experimenttime=" + rs.getString(10)+ "&teaschool=" + rs.getString(11)+ "&username=" + rs.getString(13)+ "&opentime2=" + rs.getString(18)+ "&opentime3=" + rs.getString(19)+ "&opentime4=" + rs.getString(20)+ "&date=" + rs.getString(21)+ "&id=" + rs.getString(25)+ "&verifyname=" + rs2.getString(8)
							).forward(request, response);;
				}else{
					JOptionPane.showMessageDialog(null, "异常");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//申请次数
			
			ArrayList<Application> applynumber=new ArrayList<Application>();
	
	        
	        application.setUsername(username);
			
		
			applynumber = tv.select1(application);
			
		//	JOptionPane.showMessageDialog(null, applynumber.size());
			 
			int applysize=applynumber.size();
			 request.getSession().setAttribute("applysize",applysize);

			 
			
//使用次数
			ArrayList<Note> usenumber=new ArrayList<Note>();
		
		     
		        note.setUsername(username);
				
			
		        usenumber = tv.select2(note);
				
			//	JOptionPane.showMessageDialog(null, usenumber.size());
				 
				int usesize=usenumber.size();
				 request.getSession().setAttribute("usesize",usesize);
				 
				 
	//取消申请次数			 

			ArrayList<Application> cancelumber=new ArrayList<Application>();
				
				      
				        application.setUsername(username);
						
					
				        cancelumber = tv.select3(application);
						
						//JOptionPane.showMessageDialog(null, cancelumber.size());
						 
						int cancelsize=cancelumber.size();
						 request.getSession().setAttribute("cancelsize",cancelsize);
						 
						 
						 
		//违约次数				 
						 

			ArrayList<Note> treatynumber=new ArrayList<Note>();
								
					 
						  
			application.setUsername(username);
					      
					      
					      note=new Note();
										
									
					      treatynumber=tv.select4(note,application);
										
				//	JOptionPane.showMessageDialog(null, treatynumber.size());
										 
					int treatysize=treatynumber.size();
					 request.getSession().setAttribute("treatysize",treatysize);
					 
					 
					 
						//赔偿次数				 
					 

						ArrayList<Compensation> compensationnumber=new ArrayList<Compensation>();
											
								   
									  String applyusername=ce.ChineseCoding(request.getParameter("applyusesrname"));
								      compensation.setApplyusername(applyusername);
								      
								      
								      compensation=new Compensation();
													
												
								      compensationnumber=tv.select5(compensation,application);
													
								//JOptionPane.showMessageDialog(null, compensationnumber.size());
													 
								int compensationsize=compensationnumber.size();
								 request.getSession().setAttribute("compensationsize",compensationsize);
								 
								 
								
								 

									//信誉总分				 
								 

									/*ArrayList<Credit> sumcredit=new ArrayList<Credit>();*/
														
											    
											      credit=new Credit();
												
											      application.setUsername(username);
											      
											      
											      credit=new Credit();
																
															
											      ResultSet rs1 =tv.ck1(credit,application);
																
											   
											      
											      try{
											    	  
											    if(rs1.next()){	  
											     
											 request.getSession().setAttribute("creditgrade",(rs1.getString(1)));
											
										
											 if(rs1.getString(1)==null){
											 request.getSession().setAttribute("creditgrade",0);
											
										}
											 
											    }
											    
											   

											 else{
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
