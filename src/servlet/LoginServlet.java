package servlet;

import java.io.IOException;
import java.util.ArrayList;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;











import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Page;
import bean.User;
import op.LoginOp;
import op.VerifyApplyOp;


public class LoginServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				String identity=request.getParameter("identity");
				String school=request.getParameter("school");
			
				
				LoginOp l=new LoginOp();
				
				
				
					
				request.getSession().setAttribute("username",username);
				request.getSession().setAttribute("school",school);
				request.getSession().setAttribute("identity",identity);
			
			
				boolean loginFlag=l.checkRegister(username, password,identity,school);
				
				if(loginFlag)
				{			

					if("系统管理员".equals(identity)){
					response.sendRedirect("admfirst.jsp");
					
					
					}
					
					
					else if("教师".equals(identity)){
						response.sendRedirect("teafirst.jsp");
						
						
	

						
						ArrayList<Compensation> compensations=new ArrayList<Compensation>();
									User user;
									user=new User();
									user.setUsername(username);
									
									
									 l=new LoginOp();
									
									 compensations = l.quire(user);
									 int size=compensations.size();
									 request.getSession().setAttribute("size",size);
					
						
					}
					
					
					else if("实验室管理员".equals(identity)){
						response.sendRedirect("manfirst.jsp");
						}
					else if("实验室负责人".equals(identity)){
						//response.sendRedirect("VerifyApplyServlet?");
						
						response.sendRedirect("leafirst.jsp");
						Application application;
						
						
					    User user;
	
					    /*
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

						
						
						
						

						ArrayList<Application> apply=new ArrayList<Application>();
						l=new LoginOp();
						user=new User();
						user.setSchool(school);
						p.setTotalcount((l.getTotalCount(user)));
						 try {
						 apply =l.select(user,p.getCurrpageno(), p.getPagesize());

						 
						 } catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						 
						 
						HttpSession session=request.getSession();	

						ServletContext scx=session.getServletContext();	


						scx.setAttribute("apply",apply);	
						request.getSession().setAttribute("school", school);
						request.setAttribute( "Page",p);
						//response.sendRedirect("verifyapply.jsp");
						request.getRequestDispatcher("verifyapply.jsp").forward(request, response);		
						 */
						
						
						
						ArrayList<Application> applications=new ArrayList<Application>();
									
									user=new User();
									user.setSchool(school);
									
									 l=new LoginOp();
									
									applications=l.select(user);
									 int applysize=applications.size();
									 request.getSession().setAttribute("applysize",applysize);
					
						
				}				
				
				
				}
				
				else
				{
					 JOptionPane.showMessageDialog(null, "错误的登录信息，请重新登录！");
					response.sendRedirect("index.jsp");
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
