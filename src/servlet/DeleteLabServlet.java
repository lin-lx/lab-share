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


import op.DeleteLabOp;
import bean.Application;
import bean.CodeExchange;
import bean.Lab;
import bean.Page;
import bean.User;

public class DeleteLabServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public DeleteLabServlet() {
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
		Lab lab;
		User user;
		Application application;
		DeleteLabOp l ;

		String number= ce.ChineseCoding(request.getParameter("number"));
		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		String school=ce.ChineseCoding(request.getParameter("school"));	
		
		
		
		
		
		ArrayList<Application> appli=new ArrayList<Application>();
		user=new User();
		
		application=new Application();
	    application.setNumber(number);
	    application.setLabschool(labschool);
		l = new DeleteLabOp();
		appli = l.select(application);
		//JOptionPane.showMessageDialog(null, appli.size());
		
			
				
				if(appli.size()>0){
					
					JOptionPane.showMessageDialog(null, "实验室正在使用中，不能删除，请稍后再来！");
					
					
					
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
			 l = new DeleteLabOp();
			 user=new User();
			 user.setSchool(school);
			 
			 p.setTotalcount((l.getTotalCount(user)));	 
			 try{
			 labs = l.quire(user,p.getCurrpageno(),p.getPagesize());
		//JOptionPane.showMessageDialog(null, labs.size());
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
		
		
		
		
		
		
			
				ArrayList<Application> applications=new ArrayList<Application>();
				
				application=new Application();
				application.setNumber(number);
				application.setLabschool(labschool);
				l = new DeleteLabOp();
				applications = l.select(application);
				//JOptionPane.showMessageDialog(null, applications.size());
				
					
						if(applications.size()==0)
						{
						   l = new DeleteLabOp();
							boolean delFlag = l.delete(number,labschool);
							
								JOptionPane.showMessageDialog(null, "删除成功！");
						}
							
							else
							{
								JOptionPane.showMessageDialog(null, "删除成功！");
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
			
					
						
				ArrayList<Lab> labs=new ArrayList<Lab>();
				 l = new DeleteLabOp();
				 user=new User();
				 user.setSchool(school);
				 
				 p.setTotalcount((l.getTotalCount(user)));	 
				 try{
				 labs = l.quire(user,p.getCurrpageno(),p.getPagesize());
			//JOptionPane.showMessageDialog(null, labs.size());
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
