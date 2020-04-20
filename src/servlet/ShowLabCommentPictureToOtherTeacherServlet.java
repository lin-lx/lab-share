package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import op.ShowLabCommentPictureToOtherTeacherOp;
import bean.CodeExchange;
import bean.Comment;


public class ShowLabCommentPictureToOtherTeacherServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public ShowLabCommentPictureToOtherTeacherServlet() {
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
			ShowLabCommentPictureToOtherTeacherOp l ;

	String number=ce.ChineseCoding(request.getParameter("number"));
	String labschool=ce.ChineseCoding(request.getParameter("labschool"));

		Integer id=Integer.parseInt(request.getParameter("id"));
		

					ArrayList<Comment> comments=new ArrayList<Comment>();
					 l = new ShowLabCommentPictureToOtherTeacherOp();
					 comment=new Comment();
					 comment.setId(id);
					 comment.setNumber(number);
					 comment.setLabschool(labschool);
					 comments = l.quire(comment);

					

					HttpSession session=request.getSession();	

					ServletContext scx=session.getServletContext();	

					scx.setAttribute("comments",comments);	
					request.getRequestDispatcher("showlabcommentpicturetootherteacher.jsp").forward(request, response);		
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
