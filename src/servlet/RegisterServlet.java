package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import op.RegisterOp;
import bean.CodeExchange;

import bean.User;


public class RegisterServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
		RegisterOp r ;
	
		
		String btnReg = request.getParameter("btnReg");
		
		String username=ce.ChineseCoding(request.getParameter("username"));
		String password = ce.ChineseCoding(request.getParameter("password"));
		String ide = ce.ChineseCoding(request.getParameter("ide"));
		String phone = ce.ChineseCoding(request.getParameter("phone"));
		String identity = ce.ChineseCoding(request.getParameter("identity"));
		String sex = ce.ChineseCoding(request.getParameter("sex"));
		String registertime = ce.ChineseCoding(request.getParameter("registertime"));
		String school = ce.ChineseCoding(request.getParameter("school"));
		String name=ce.ChineseCoding(request.getParameter("name"));
		String nation=ce.ChineseCoding(request.getParameter("nation"));
		String birthday=ce.ChineseCoding(request.getParameter("birthday"));
		String hiredate=ce.ChineseCoding(request.getParameter("hiredate"));
		String address=ce.ChineseCoding(request.getParameter("address"));
		String nativepla=ce.ChineseCoding(request.getParameter("nativepla"));
		String education=ce.ChineseCoding(request.getParameter("education"));
	    r=new RegisterOp();
	    user=new User();
	    user.setUsername(username);
	    user.setIde(ide);
	    user.setPhone(phone);
		
	
	    //检验是否存在该用户名
		boolean usernameflag=r.selectusername(user);
	
		if(usernameflag){
		 response.getWriter().println(1);//存在用户名
		}else{
		 response.getWriter().println(2);//不存在该用户
		}	
		
		
	/*	 	
		 //检验是否存在该身份账号
		boolean ideflag=r.selectide(user);
		if(ideflag){
		 response.getWriter().println(1);//存在身份证号
		}else{
		 response.getWriter().println(2);//不存在
		}
		*/
	
	
		 	
	
		 //检验是否存在该手机
	/*	boolean phoneflag=r.selectphone(user);
		if(phoneflag){
		 response.getWriter().println("存在");//存在
		}else{
		 response.getWriter().println("不存在");//不存在
		}
		 */
	
	
		
		if(btnReg!=null)
		{
			
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setIde(ide);
			user.setPhone(phone);;
			user.setIdentity(identity);
			user.setSex(sex);
			user.setSchool(school);
			user.setName(name);
			user.setNation(nation);
			user.setBirthday(birthday);
			user.setHiredate(hiredate);
			user.setAddress(address);
			user.setNativepla(nativepla);
			user.setEducation(education);
    		user.setRegistertime((String)new Date().toLocaleString());

			r = new RegisterOp();
			boolean addFlag = r.add(user);
			
			if(addFlag){
				JOptionPane.showMessageDialog(null, "注册成功，返回登录");
				response.sendRedirect("index.jsp");
				
			}
			
			
			else
			{
				//JOptionPane.showMessageDialog(null, "注册失败，请重新注册");
				response.sendRedirect("register.jsp");
			}
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
