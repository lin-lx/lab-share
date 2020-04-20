package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


import op.ResetPasswordOp;
import bean.CodeExchange;
import bean.User;


public class ResetPasswordServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public ResetPasswordServlet() {
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
		ResetPasswordOp  r ;
		
		
		String btnReset = request.getParameter("btnReset");
		String phone=ce.ChineseCoding(request.getParameter("phone"));
		
		ArrayList<User> users=new ArrayList<User>();
		user=new User();
		user.setPhone(phone);
		r = new ResetPasswordOp();
		users = r.select(user);
		//JOptionPane.showMessageDialog(null, users.size());
		
			
				/*
				if(users.size()==0){
					
					JOptionPane.showMessageDialog(null, "此手机号未注册，请输入正确的手机号！");
					response.sendRedirect("resetpassword.jsp");
					 return;
									
				}*/
				
				
		
				
				
				if(users.size()==0)
				{
					
					JOptionPane.showMessageDialog(null, "此手机号未注册，请输入正确的手机号！");
					response.sendRedirect("resetpassword.jsp");
					
					
				}
					
					else
					{
						r = new ResetPasswordOp();
						boolean AltFlag = r.update(user);
						
							JOptionPane.showMessageDialog(null, "密码修改成功,返回登录！");
							response.sendRedirect("index.jsp");
							
					}		
				
				
				
				
	/*	if(btnReset!=null)
		{
			
		
		
			String password= ce.ChineseCoding(request.getParameter("password"));
			
			user=new User();
			user.setPassword(password);
			user.setPhone(phone);
			r = new ResetPasswordOp();
			
			boolean AltFlag = r.update(user);
			if(AltFlag)
			{
				JOptionPane.showMessageDialog(null, "密码修改成功");
				response.sendRedirect("index.jsp");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "密码修改失败");
				response.sendRedirect("resetpassword.jsp");
		}	
		}*/
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
