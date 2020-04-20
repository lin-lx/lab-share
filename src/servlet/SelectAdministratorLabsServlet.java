package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import op.SelectAdministratorLabsOp;
import op.SelectTeaLabOp;
import bean.Lab;
import bean.Page;

public class SelectAdministratorLabsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectAdministratorLabsServlet() {
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

		String sql=" where ";
		String opentime=null;
		String labschool=null;
		String equipment=null;
		
		
		opentime=new String(request.getParameter("opentime").getBytes("ISO8859_1"),"UTF-8");
		labschool=new String(request.getParameter("labschool").getBytes("ISO8859_1"),"UTF-8");
		equipment=new String(request.getParameter("equipment").getBytes("ISO8859_1"),"UTF-8");
	 
	
		
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

		
	
		if (labschool!=null&&!"".equals(labschool)) {
			sql+="labschool like '%"+labschool+"%' and ";
		}
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		if (opentime!=null&&!"".equals(opentime)) {
			sql+="opentime1 like '%"+opentime+"%' or ";
		}
		if (opentime!=null&&!"".equals(opentime)) {
			sql+="opentime2 like '%"+opentime+"%' or ";
		}
		if (opentime!=null&&!"".equals(opentime)) {
			sql+="opentime3 like '%"+opentime+"%' or ";
		}
		if (opentime!=null&&!"".equals(opentime)) {
			sql+="opentime4 like '%"+opentime+"%' and ";
		}
		//JOptionPane.showMessageDialog(null, "sql:" +sql);
		
		if (equipment!=null&&!"".equals(equipment)) {
			sql+="equipment like '%"+equipment+"%'";
		}
		
		else sql=sql.substring(0, sql.length()-4);
		
		SelectAdministratorLabsOp sa;
		
		ArrayList<Lab> labs=new ArrayList<Lab>();
		try {
			sa = new SelectAdministratorLabsOp();
			p.setTotalcount((sa.getTotalCount(sql)));
			labs=sa.select(sql,p.getCurrpageno(), p.getPagesize());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	//	JOptionPane.showMessageDialog(null, labs.size());
		
		HttpSession session=request.getSession();	

		ServletContext scx=session.getServletContext();	



		scx.setAttribute("labs",labs);	
		request.setAttribute( "Page",p);
		request.getRequestDispatcher("administratorshowlabs.jsp").forward(request, response);		
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
