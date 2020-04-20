 
package servlet;

import java.io.IOException;
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
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Lab;
import bean.Page;
import bean.User;

public class ApplyLabServletOne extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ApplyLabServletOne() {
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
		Compensation compensation;
		Application application;
		ApplyLabOpOne l ;
		User user;
		String number = ce.ChineseCoding(request.getParameter("number"));
		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		String username = ce.ChineseCoding(request.getParameter("username"));
		String cancelapply=ce.ChineseCoding(request.getParameter("cancelapply"));
		String canceldate=ce.ChineseCoding(request.getParameter("canceldate"));
		
					
		ArrayList<Compensation> compensations=new ArrayList<Compensation>();
		user=new User();
		user.setUsername(username);
		compensation=new Compensation();
		l = new ApplyLabOpOne();
		compensations = l.select(compensation,user);
		//JOptionPane.showMessageDialog(null, compensations.size());
		
			
				
				if(compensations.size()>0){
					
					JOptionPane.showMessageDialog(null, "您还有赔偿未支付，请先支付！");
					
					
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

					
							ArrayList<Lab> appli=new ArrayList<Lab>();
							l = new ApplyLabOpOne();
							String school=ce.ChineseCoding(request.getParameter("school"));
							user=new User();
						
							user.setSchool(school);
							p.setTotalcount((l.getTotalCount()));
							 try {
								 appli=l.quire(p.getCurrpageno(), p.getPagesize());
							 
							 } catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
																
							
							HttpSession session=request.getSession();	

							ServletContext scx=session.getServletContext();	

							scx.setAttribute("appli",appli);	
							request.getSession().setAttribute("school", school);
							request.setAttribute( "Page",p);
					
					
							request.getRequestDispatcher("teashowlab.jsp").forward(request, response);	
					       return;
									
				}
				
				
				ArrayList<Application> applications=new ArrayList<Application>();
				user=new User();
				user.setUsername(username);
				application=new Application();
				application.setCancelapply(cancelapply);
				application.setCanceldate(canceldate);
				l = new ApplyLabOpOne();
				applications = l.select(application,user);
				//JOptionPane.showMessageDialog(null, applications.size());
				
					
						
						if(applications.size()>=3){
							
							JOptionPane.showMessageDialog(null, "本周您已取消申请超过三次了，不能再申请实验室了，请下周再来！");
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

							
									ArrayList<Lab> appli=new ArrayList<Lab>();
									l = new ApplyLabOpOne();
									String school=ce.ChineseCoding(request.getParameter("school"));
									user=new User();
								
									user.setSchool(school);
									p.setTotalcount((l.getTotalCount()));
									 try {
										 appli=l.quire(p.getCurrpageno(), p.getPagesize());
									 
									 } catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
																		
									
									HttpSession session=request.getSession();	

									ServletContext scx=session.getServletContext();	

									scx.setAttribute("appli",appli);	
									request.getSession().setAttribute("school", school);
									request.setAttribute( "Page",p);
							
							
									request.getRequestDispatcher("teashowlab.jsp").forward(request, response);	
							       return;
							 
											
						}
						
						
						
						
						
						ArrayList<User> users=new ArrayList<User>();
						user=new User();
						user.setUsername(username);
						l = new ApplyLabOpOne();
						users = l.select(user);
						//JOptionPane.showMessageDialog(null, users.size());
						
							
								
								if(users.size()>0){
									
									JOptionPane.showMessageDialog(null, "您的信息不完善，请先去完善个人信息再来申请实验室！");
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

									
											ArrayList<Lab> appli=new ArrayList<Lab>();
											l = new ApplyLabOpOne();
											String school=ce.ChineseCoding(request.getParameter("school"));
											user=new User();
										
											user.setSchool(school);
											p.setTotalcount((l.getTotalCount()));
											 try {
												 appli=l.quire(p.getCurrpageno(), p.getPagesize());
											 
											 } catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
																				
											
											HttpSession session=request.getSession();	

											ServletContext scx=session.getServletContext();	

											scx.setAttribute("appli",appli);	
											request.getSession().setAttribute("school", school);
											request.setAttribute( "Page",p);
									
									
											request.getRequestDispatcher("teashowlab.jsp").forward(request, response);	
									       return;
									 
													
								}
						
						
			
				
				
				user=new User();
				user.setUsername(username);				
				lab = new  Lab();
				lab.setNumber(number);
				lab.setLabschool(labschool);
				l = new ApplyLabOpOne();		
				ResultSet rs = l.ck(lab);
				ResultSet rs1 = l.ck(user);
			
				
					
				try {
					
				if (rs.next()&&rs1.next()) {
					JOptionPane.showMessageDialog(null, "请填写申请表！");
					request.getRequestDispatcher("apply.jsp?&number=" + rs.getString(1)
							+ "&opentime1=" + rs.getString(2)+ "&seatnumber=" + rs.getInt(3)+ "&equipment=" + rs.getString(4)+ "&labschool=" + rs.getString(5)+ "&lableader=" + rs.getString(6)+ "&comment=" + rs.getString(7)+ "&picture=" + rs.getString(8)+ "&opentime2=" + rs.getString(10)+ "&opentime3=" + rs.getString(11)+ "&opentime4=" + rs.getString(12)+ "&type=" + rs.getString(13)+ "&expense=" + rs.getString(14)+ "&name=" +rs1.getString(8)+ "&phone="+rs1.getString(4)+ "&school="+rs1.getString(15)+ "&lableaderphone="+rs.getString(15)).forward(request, response);
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
