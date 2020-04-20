package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import op.RemindOp;
import bean.CodeExchange;
import bean.Page;
import bean.Remind;



public class RemindServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public RemindServlet() {
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
		Remind remind;
		RemindOp r ;
		
		
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

		
		String btnAdd = request.getParameter("btnAdd");

		if(btnAdd!=null)
		{
			
			String content= ce.ChineseCoding(request.getParameter("content"));
			String date = ce.ChineseCoding(request.getParameter("date"));
			
			remind= new Remind();
			
			remind.setContent(content);
			
			remind.setDate((String)new Date().toLocaleString());
			
			
		
			
			r = new RemindOp();
			
			boolean addFlag = r.add(remind);
			if(addFlag)
			{
				JOptionPane.showMessageDialog(null, "发布成功！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "发布失败！");
			}
		}
		

		
		
		  String id=request.getParameter("id");
		if(id!=null)
		{

		   r = new RemindOp();
			boolean delFlag = r.delete(id);
			if(delFlag)
			{
				JOptionPane.showMessageDialog(null, "删除成功");
			}	
			else
			{
				JOptionPane.showMessageDialog(null, "删除失败");
			}
		}		
		
					
		ArrayList<Remind> reminds=new ArrayList<Remind>();
		 r = new RemindOp();
		 p.setTotalcount((r.getTotalCount()));
		 try {
		 reminds = r.quire(p.getCurrpageno(), p.getPagesize());
		// JOptionPane.showMessageDialog(null, reminds.size());
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
