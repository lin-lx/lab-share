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




import op.DeleteCommentOp;
import bean.CodeExchange;
import bean.Comment;
import bean.Page;
import bean.User;

public class DeleteCommentServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public DeleteCommentServlet() {
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
		Comment comment;
		DeleteCommentOp dc ;
		
		
		
		
		
		  String number=ce.ChineseCoding(request.getParameter("number"));
		  String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
		  String labschool=ce.ChineseCoding(request.getParameter("labschool"));
		  int id=Integer.parseInt(request.getParameter("id"));
			
		 

			  dc = new DeleteCommentOp();
				boolean delFlag = dc.delete(id);
				if(delFlag)
				{
					JOptionPane.showMessageDialog(null, "删除成功！");
				}	
				else
				{
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
					
			
	
			
			
		
		User user;
		String username=ce.ChineseCoding(request.getParameter("username"));
		
		
		

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

	
		
	
		ArrayList<Comment> comments=new ArrayList<Comment>();
		dc = new DeleteCommentOp();
		 user=new User();
		 user.setUsername(username);
		 p.setTotalcount((dc.getTotalCount(user)));	 
		 try{
		 comments = dc.quire(user,p.getCurrpageno(),p.getPagesize());
		// JOptionPane.showMessageDialog(null, labs.size());
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		HttpSession session=request.getSession();	

		ServletContext scx=session.getServletContext();	

		scx.setAttribute("comments",comments);
		request.getSession().setAttribute("username", username);
		request.setAttribute( "Page",p);
		request.getRequestDispatcher("commentlabmanager.jsp").forward(request, response);	
		 
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
