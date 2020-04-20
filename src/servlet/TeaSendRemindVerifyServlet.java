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
import op.TeaSendRemindVerifyOp;
import op.TeacherCancelApplyOp;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Lab;
import bean.Page;
import bean.User;

public class TeaSendRemindVerifyServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public TeaSendRemindVerifyServlet() {
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
			
			TeaSendRemindVerifyOp t ;
			User user;
			String number = ce.ChineseCoding(request.getParameter("number"));
			String labschool=ce.ChineseCoding(request.getParameter("labschool"));
			String username = ce.ChineseCoding(request.getParameter("username"));
			String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
			String date=ce.ChineseCoding(request.getParameter("date"));
			
			
			
			
			
			
			ArrayList<Application> applications1=new ArrayList<Application>();
			;

						t = new TeaSendRemindVerifyOp();
				  		
						application=new Application();
						application.setNumber(number);
						application.setExperimenttime(experimenttime);
						application.setLabschool(labschool);
						application.setDate(date);
						applications1 = t.select1(application);
						//JOptionPane.showMessageDialog(null, compensations.size());
						
							
								
								if(applications1.size()>0){
									
									JOptionPane.showMessageDialog(null, "此实验室已审核，无法发送提醒消息！");
									
									
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


											ArrayList<Application> applys=new ArrayList<Application>();
											 t = new TeaSendRemindVerifyOp();
										
											 user=new User();
											 user.setUsername(username);
											 p.setTotalcount((t.getTotalCount(user)));
											 try{
												 applys =t.select(user,p.getCurrpageno(), p.getPagesize());
									
											 } catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}

											HttpSession session=request.getSession();	

											ServletContext scx=session.getServletContext();	


											scx.setAttribute("applys",applys);
											request.getSession().setAttribute("username", username);
											request.setAttribute( "Page",p);
											request.getRequestDispatcher("applyf.jsp").forward(request, response);	
											
											 return;
															
										}
								
			
			
			
			
			
			ArrayList<Application> applications=new ArrayList<Application>();
;

			t = new TeaSendRemindVerifyOp();
	  		
			application=new Application();
			application.setNumber(number);
			application.setExperimenttime(experimenttime);
			application.setLabschool(labschool);
			application.setDate(date);
			applications = t.select(application);
			//JOptionPane.showMessageDialog(null, compensations.size());
			
				
					
					if(applications.size()>0){
						
						JOptionPane.showMessageDialog(null, "已超过实验日期，无法发送消息！");
						
						
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


								ArrayList<Application> applys=new ArrayList<Application>();
								 t = new TeaSendRemindVerifyOp();
							
								 user=new User();
								 user.setUsername(username);
								 p.setTotalcount((t.getTotalCount(user)));
								 try{
									 applys =t.select(user,p.getCurrpageno(), p.getPagesize());
						
								 } catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								HttpSession session=request.getSession();	

								ServletContext scx=session.getServletContext();	


								scx.setAttribute("applys",applys);
								request.getSession().setAttribute("username", username);
								request.setAttribute( "Page",p);
								request.getRequestDispatcher("applyf.jsp").forward(request, response);	
								
								 return;
												
							}
					
					
					
					
				
							
							
							user=new User();
							user.setUsername(username);				
							application = new  Application();
							application.setNumber(number);
							application.setLabschool(labschool);
							application.setExperimenttime(experimenttime);
							application.setDate(date);
							t = new TeaSendRemindVerifyOp();	
							ResultSet rs = t.ck(application);
							ResultSet rs1 =t.ck(user);
						
							
								
							try {
								
							if (rs.next()&&rs1.next()) {
								JOptionPane.showMessageDialog(null, "提醒审核短信发送成功！");
								/*request.getRequestDispatcher("tearemindverify.jsp?&number=" + rs.getString(1)
										+ "&lableader=" + rs.getString(6)+ "&lableaderphone=" + rs.getString(26)+ "&date=" + rs.getString(21)+ "&name=" +rs1.getString(8)+ "&phone="+rs1.getString(4)+ "&school="+rs1.getString(15)).forward(request, response);*/
							
							
							
								
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


										ArrayList<Application> applys=new ArrayList<Application>();
										 t = new TeaSendRemindVerifyOp();
									
										 user=new User();
										 user.setUsername(username);
										 p.setTotalcount((t.getTotalCount(user)));
										 try{
											 applys =t.select(user,p.getCurrpageno(), p.getPagesize());
								
										 } catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

										HttpSession session=request.getSession();	

										ServletContext scx=session.getServletContext();	


										scx.setAttribute("applys",applys);
										request.getSession().setAttribute("username", username);
										request.setAttribute( "Page",p);
										request.getRequestDispatcher("applyf.jsp").forward(request, response);	
										
										 return;
														
									}
							
						
							else{
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
