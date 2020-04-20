package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import op.SelectLeaTeacherOp;
import bean.CodeExchange;
import bean.Page;
import bean.User;

public class SelectLeaTeacherServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public SelectLeaTeacherServlet() {
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


		doPost(request, response);
		}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sql=" and ";
		String name=null;
		String sex=null;
		name=new String(request.getParameter("name").getBytes("ISO8859_1"),"UTF-8");
		sex=new String(request.getParameter("sex").getBytes("ISO8859_1"),"UTF-8");
	
	
		

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
		
		
	
		if (name!=null&&!"".equals(name)) {
			sql+="name like '%"+name+"%' and ";
		}
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		if (sex!=null&&!"".equals(sex)) {
			sql+="sex='"+sex+"'";
		}
		
		else sql=sql.substring(0, sql.length()-4);
		
		SelectLeaTeacherOp sl;
		User user;
		String school=ce.ChineseCoding(request.getParameter("school"));
		
		ArrayList<User> users=new ArrayList<User>();
		try {
			sl = new SelectLeaTeacherOp();
			user=new User();
			user.setSchool(school);
			p.setTotalcount((sl.getTotalCount(sql,user)));
			users=sl.select(sql,user,p.getCurrpageno(), p.getPagesize());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, users.size());
		
		HttpSession session=request.getSession();	

		ServletContext scx=session.getServletContext();	


		scx.setAttribute("users",users);
		request.getSession().setAttribute("school", school);
		request.setAttribute( "Page",p);
		request.getRequestDispatcher("showteachersl.jsp").forward(request, response);				
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
