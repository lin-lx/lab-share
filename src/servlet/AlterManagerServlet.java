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


import op.AlterManagerOp;
import bean.CodeExchange;
import bean.User;

public class AlterManagerServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public AlterManagerServlet() {
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
		//request.setCharacterEncoding("utf-8");
		User user;
		AlterManagerOp aa ;
		
		
		String btnAlt = request.getParameter("btnAlt");
	

	

		if(btnAlt!=null)
		{
			String name=ce.ChineseCoding(request.getParameter("name"));
			String sex=ce.ChineseCoding(request.getParameter("sex"));
			String ide=ce.ChineseCoding(request.getParameter("ide"));
			String phone=ce.ChineseCoding(request.getParameter("phone"));
			String education=ce.ChineseCoding(request.getParameter("education"));
			String nation=ce.ChineseCoding(request.getParameter("nation"));
			String birthday=ce.ChineseCoding(request.getParameter("birthday"));
			String hiredate=ce.ChineseCoding(request.getParameter("hiredate"));
			String address=ce.ChineseCoding(request.getParameter("address"));
			String nativepla=ce.ChineseCoding(request.getParameter("nativepla"));
		    String school=ce.ChineseCoding(request.getParameter("school"));
			
			user= new User();
		    user.setName(name);
		    user.setSex(sex);
		    user.setIde(ide);
		    user.setPhone(phone);
		    user.setEducation(education);
		    user.setNation(nation);
		    user.setBirthday(birthday);
		    user.setHiredate(hiredate);
		    user.setAddress(address);
		    user.setNativepla(nativepla);
		    user.setSchool(school);
		    
		    
			aa = new AlterManagerOp();
			boolean altFlag = aa.update(user);
			if(altFlag)
			{
				JOptionPane.showMessageDialog(null, "修改成功!");
			}		 	
			else
			{
				JOptionPane.showMessageDialog(null, "修改失败!");
			
		}	
			
			
		
		
			
			

			ArrayList<User> users=new ArrayList<User>();
			aa = new AlterManagerOp();
			user=new User();
			user.setIde(ide);
			 users = aa.select(user);
			// JOptionPane.showMessageDialog(null, users.size());
			

			HttpSession session=request.getSession();	

			ServletContext scx=session.getServletContext();	


			scx.setAttribute("users",users);	
			response.sendRedirect("showmanage.jsp");		
			 
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
