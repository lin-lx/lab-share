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



import op.ShowUserOp;
import bean.CodeExchange;
import bean.Page;
import bean.User;

public class ShowUserServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ShowUserServlet() {
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
	
	private static final long serialVersionUID = 1L;
	
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
			String identity=ce.ChineseCoding(request.getParameter("identity"));
			User user;
			user=new User();
			user.setIdentity(identity);
			ShowUserOp u ;
		
				
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

			
			
			
			

					ArrayList<User> users=new ArrayList<User>();
				
					 u = new ShowUserOp();
						
					// System.out.println(u.getTotalCount());
						p.setTotalcount((u.getTotalCount()));
					 
					
					 try {
						 users = u.quire(p.getCurrpageno(),p.getPagesize());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					

					HttpSession session=request.getSession();	

					ServletContext scx=session.getServletContext();	


					scx.setAttribute("users",users);
					request.setAttribute( "Page",p);
					request.getRequestDispatcher("showuser.jsp").forward(request, response);		
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
