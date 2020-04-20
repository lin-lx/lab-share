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
import javax.swing.JOptionPane;

import op.ShowRemindOp;
import bean.Page;
import bean.Remind;


public class ShowRemindServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowRemindServlet() {
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
		Remind remind;
		ShowRemindOp r ;
		
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

				
				
		ArrayList<Remind> reminds=new ArrayList<Remind>();
				 r = new ShowRemindOp();
				 p.setTotalcount((r.getTotalCount()));
				 try {
				 reminds = r.quire(p.getCurrpageno(), p.getPagesize());
				 } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				HttpSession session=request.getSession();		
				ServletContext scx=session.getServletContext();	
				scx.setAttribute("reminds",reminds);
				request.setAttribute( "Page",p);
				request.getRequestDispatcher("remind.jsp").forward(request, response);		
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
