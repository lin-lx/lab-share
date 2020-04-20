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

import op.DeleteManOp;
import op.DeleteTeaOp;
import bean.CodeExchange;
import bean.Page;
import bean.User;

public class DeleteManServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public DeleteManServlet() {
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
		User user;
		DeleteManOp d ;
		
		
		
		ArrayList<User> users1=new ArrayList<User>();
		String ide=ce.ChineseCoding(request.getParameter("ide"));
		user=new User();
	    user.setIde(ide);
		d = new DeleteManOp();
		users1 = d.select(user);
		//JOptionPane.showMessageDialog(null, appli.size());
		
			
				
				if(users1.size()>0){
					
					JOptionPane.showMessageDialog(null, "此实验室管理员为在职状态，不可注销账号！");
					
					
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

					
					
					
					
		

				String school = ce.ChineseCoding(request.getParameter("school"));
				ArrayList<User> users=new ArrayList<User>();
					 d = new DeleteManOp();
					 user=new User();
					user.setSchool(school);
					p.setTotalcount((d.getTotalCount(user)));
					 
					
					 try {
					 users = d.select(user,p.getCurrpageno(), p.getPagesize());
				//	 JOptionPane.showMessageDialog(null, users.size());
					 } catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					HttpSession session=request.getSession();	

					ServletContext scx=session.getServletContext();	


					scx.setAttribute("users",users);
					request.getSession().setAttribute("school", school);
					request.setAttribute( "Page",p);
					request.getRequestDispatcher("showmanagersl.jsp").forward(request, response);		
					 	
					 
	        return;
					 
									
				}
		

				ArrayList<User> users2=new ArrayList<User>();
				
				user=new User();
				user.setIde(ide);
				d= new DeleteManOp();
				users2 = d.select(user);
			//	JOptionPane.showMessageDialog(null, users2.size());
			
				
				
						if(users2.size()==0)
						
						{
						   d = new DeleteManOp();
						   boolean delFlag = d.delete(ide);
							if(delFlag)
							{
								JOptionPane.showMessageDialog(null, "注销成功！");
							}	
							else
							{
								JOptionPane.showMessageDialog(null, "注销失败！");
							}
						}	
				
				
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

				
				
				
				
	

			String school = ce.ChineseCoding(request.getParameter("school"));
			ArrayList<User> users=new ArrayList<User>();
				 d = new DeleteManOp();
				 user=new User();
				user.setSchool(school);
				p.setTotalcount((d.getTotalCount(user)));
				 
				
				 try {
				 users = d.select(user,p.getCurrpageno(), p.getPagesize());
			//	 JOptionPane.showMessageDialog(null, users.size());
				 } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				HttpSession session=request.getSession();	

				ServletContext scx=session.getServletContext();	


				scx.setAttribute("users",users);
				request.getSession().setAttribute("school", school);
				request.setAttribute( "Page",p);
				request.getRequestDispatcher("showmanagersl.jsp").forward(request, response);		
				 
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
