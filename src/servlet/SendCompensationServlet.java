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


import op.SendCompensationOp;
import bean.CodeExchange;
import bean.Compensation;
import bean.Note;
import bean.Page;
import bean.User;

public class SendCompensationServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public SendCompensationServlet() {
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
			Compensation compensation;
			compensation=new Compensation();
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
					String content = tempitemFileItem.getString("utf-8");			
					
					if ("number".equals(itemNameString)) {
						compensation.setNumber(content);
					} else if ("labschool".equals(itemNameString)){
						compensation.setLabschool(content);
					}
					else if ("name".equals(itemNameString)){
						compensation.setName(content);
					}
					else if ("teaschool".equals(itemNameString)){
						compensation.setTeaschool(content);
						
					}

					
					else if ("phone".equals(itemNameString)){
						compensation.setPhone(content);
					}
					else if ("experimenttime".equals(itemNameString)){
						compensation.setExperimenttime(content);
					}
					else if ("money".equals(itemNameString)){
						compensation.setMoney(content);
					}
					else if ("illustration".equals(itemNameString)){
						compensation.setIllustration(content);
					}
					else if ("notename".equals(itemNameString)){
						compensation.setNotename(content);
						
					}
					else if ("notephone".equals(itemNameString)){
						compensation.setNotephone(content);
						
					}
					else if ("pay".equals(itemNameString)){
						compensation.setPay(content);
						
					}
					else if ("noteusername".equals(itemNameString)){
						compensation.setNoteusername(content);
						
					}
					else if ("applyusername".equals(itemNameString)){
						compensation.setApplyusername(content);
						
					}
					else if ("experimentdate".equals(itemNameString)){
						compensation.setExperimentdate(content);
						
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
					compensation.setPicture(tempFile.getCanonicalPath().substring(
							tempFile.getCanonicalPath().lastIndexOf("\\")));
					compensation.setPicture1(tempFile.getCanonicalPath().substring(
							tempFile.getCanonicalPath().lastIndexOf("\\")));
				}	
			}
			
			
			
			response.setContentType("text/html");
		boolean isAdd=false;
			try {
				
				
				SendCompensationOp s=new SendCompensationOp();
				isAdd=s.add(compensation);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isAdd) {
				JOptionPane.showMessageDialog(null, "发送成功!");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "发送失败，请稍后再来!");
				
			}
		
			
			
			
			String school=ce.ChineseCoding(request.getParameter("school"));
			
			
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

			
			

			ArrayList<Note> notes=new ArrayList<Note>();
			SendCompensationOp n;
			User user;
			 n = new SendCompensationOp();
			 user=new User();
			 user.setSchool(school);
			 p.setTotalcount((n.getTotalCount(user)));
			 try{
			 notes = n.quire(user,p.getCurrpageno(), p.getPagesize());
			
			 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

			HttpSession session=request.getSession();	

			ServletContext scx=session.getServletContext();	

			scx.setAttribute("notes",notes);	
			request.getSession().setAttribute("school", school);
			request.setAttribute( "Page",p);
			request.getRequestDispatcher("notesituationresult.jsp").forward(request, response);	

			 
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
