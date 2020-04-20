package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import op.AlterAdministratorOp;
import op.AlterLabOp;
import op.ApplyLabOpOne;
import op.ApplyLabOpTwo;
import op.RemindOp;
import op.TeacherCancelApplyOp;
import bean.Application;
import bean.CodeExchange;
import bean.Page;
import bean.Remind;
import bean.User;

public class TeacherCancelApplyServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public TeacherCancelApplyServlet() {
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

			 
		      Application application;
			  
   	       boolean isAlt=false;
   	       
   		ArrayList<Application> applys=new ArrayList<Application>();

   		String number=ce.ChineseCoding(request.getParameter("number"));
   		String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
   		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
   		String experimentdate=ce.ChineseCoding(request.getParameter("date"));
   		int id=Integer.parseInt(request.getParameter("id"));
   		
   		
		application=new Application();
		application.setNumber(number);
		application.setExperimenttime(experimenttime);
		application.setLabschool(labschool);
		application.setDate(experimentdate);
		application.setId(id);

		TeacherCancelApplyOp tc;
		tc = new TeacherCancelApplyOp();
		applys = tc.select(application);
		//JOptionPane.showMessageDialog(null, applys.size());
		
			
				
				if(applys.size()>0){
					
					JOptionPane.showMessageDialog(null, "已超过实验时间，不可以取消申请!");
					
					
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

					
					

					ArrayList<Application> applications=new ArrayList<Application>();
					 tc = new TeacherCancelApplyOp();
					 String username=ce.ChineseCoding(request.getParameter("username"));
					 User user;
					 user=new User();
					 user.setUsername(username);
					 p.setTotalcount((tc.getTotalCount(user)));
					 try{
					 applications =tc.select(user,p.getCurrpageno(), p.getPagesize());
			
					 } catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					HttpSession session=request.getSession();	

					ServletContext scx=session.getServletContext();	


					scx.setAttribute("applications",applications);
					request.getSession().setAttribute("username", username);
					request.setAttribute( "Page",p);
					request.getRequestDispatcher("applyf.jsp").forward(request, response);	
					
					
					
					
				
					 return;
									
				}
   	       //取消申请
   	       
				try {
					String cancelapply=ce.ChineseCoding(request.getParameter("cancelapply"));
					String date=ce.ChineseCoding(request.getParameter("date"));
					
					application=new Application();
					application.setCancelapply(cancelapply);
					application.setNumber(number);
					application.setExperimenttime(experimenttime);
					application.setDate(date);
					application.setLabschool(labschool);
					Date date1=new Date();
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					String canceldate=format.format(date1);						
				    application.setCanceldate(canceldate);
					
					 tc=new TeacherCancelApplyOp();
					isAlt=tc.update(application);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (isAlt) {
					JOptionPane.showMessageDialog(null, "取消申请成功!");
					
				}
				else {
					JOptionPane.showMessageDialog(null, "取消申请失败，请稍后再来!");
					
				}
			
			
				
		
			
			
			
		
			User user;
			String username = ce.ChineseCoding(request.getParameter("username"));
			
			
			
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

			
			

			ArrayList<Application> applications=new ArrayList<Application>();
			 tc = new TeacherCancelApplyOp();
			 user=new User();
			 user.setUsername(username);
			 p.setTotalcount((tc.getTotalCount(user)));
			 try{
			 applications =tc.select(user,p.getCurrpageno(), p.getPagesize());
	
			 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			HttpSession session=request.getSession();	

			ServletContext scx=session.getServletContext();	


			scx.setAttribute("applications",applications);
			request.getSession().setAttribute("username", username);
			request.setAttribute( "Page",p);
			request.getRequestDispatcher("applyf.jsp").forward(request, response);	
		
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
