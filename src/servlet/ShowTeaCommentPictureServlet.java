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

import op.ShowLabPictureOp;
import op.ShowTeaCommentPictureOp;
import bean.CodeExchange;
import bean.Comment;
import bean.Lab;

public class ShowTeaCommentPictureServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ShowTeaCommentPictureServlet() {
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
			Comment comment;
			ShowTeaCommentPictureOp l ;

 int id=Integer.parseInt(request.getParameter("id"));

				
			

					ArrayList<Comment> comments=new ArrayList<Comment>();
					 l = new ShowTeaCommentPictureOp();
					 comment=new Comment();
				     comment.setId(id);
				     comments = l.quire(comment);

					

					HttpSession session=request.getSession();	

					ServletContext scx=session.getServletContext();	

					scx.setAttribute("comments",comments);	
					request.getRequestDispatcher("showteacommentpicture.jsp").forward(request, response);		
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
