package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import op.CommentLabOp;
import bean.CodeExchange;
import bean.Comment;
import bean.Page;
import bean.User;

public class CommentLabServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public CommentLabServlet() {
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

			
			request.setCharacterEncoding("utf-8");
			Comment comment;
			comment=new Comment();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items=null;
			try {
				items = (List<FileItem>)upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO �Զ���ɵ� catch ��
				e1.printStackTrace();
			}
			Iterator<FileItem> it = items.iterator();
			while (it.hasNext()) {
				FileItem tempitemFileItem = it.next();
				String itemNameString = tempitemFileItem.getFieldName();
				
				if (tempitemFileItem.isFormField()) {
					String contentlab = tempitemFileItem.getString("utf-8");			
					
					if ("number".equals(itemNameString)) {
						comment.setNumber(contentlab);
					} else if ("experimenttime".equals(itemNameString)){
						comment.setExperimenttime(contentlab);
					}

					else if ("labschool".equals(itemNameString)){
						comment.setLabschool(contentlab);
					}
					else if ("username".equals(itemNameString)){
						comment.setUsername(contentlab);
					}
					else if ("content".equals(itemNameString)){
						comment.setContent(contentlab);
						
					}
					else if ("id".equals(itemNameString)){
						comment.setId(Integer.parseInt(contentlab));
						
					}
					
					else if ("experimentdate".equals(itemNameString)){
						comment.setExperimentdate(contentlab);
						
					}
				}
					
				else {
					File tempFile = new File(request.getSession()
							.getServletContext().getRealPath("/")
							+ "uploadphotos\\"
							+ new File(tempitemFileItem.getName()).getName());
					try {
						tempitemFileItem.write(tempFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
					comment.setPicture(tempFile.getCanonicalPath().substring(
							tempFile.getCanonicalPath().lastIndexOf("\\")));
					comment.setPicture1(tempFile.getCanonicalPath().substring(
							tempFile.getCanonicalPath().lastIndexOf("\\")));
				}	
			}
			
			
			
			response.setContentType("text/html");
		boolean isAdd=false;
			try {
				
				
				CommentLabOp cl=new CommentLabOp();
				isAdd=cl.add(comment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isAdd) {
				JOptionPane.showMessageDialog(null, "评论成功!");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "评论失败，请稍后再来!");
				
			}
	
			
			
			
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

			
			
			
			
			
			User user;
			String username=ce.ChineseCoding(request.getParameter("username"));		
			ArrayList<Comment> comments=new ArrayList<Comment>();
			CommentLabOp cl=new CommentLabOp();
			 user=new User();
			 user.setUsername(username);
			 p.setTotalcount((cl.getTotalCount(user)));
			 try {
			 comments = cl.quire(user,p.getCurrpageno(), p.getPagesize());
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
