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

import op.ManGradeCreditOp;
import bean.Application;
import bean.CodeExchange;
import bean.Credit;
import bean.Note;
import bean.Page;
import bean.User;

public class ManGradeCreditServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ManGradeCreditServlet() {
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
			Credit credit;
			credit=new Credit();
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
						credit.setNumber(content);
					} else if ("experimenttime".equals(itemNameString)){
						credit.setExperimenttime(content);
					}
					else if ("teaschool".equals(itemNameString)){
						credit.setTeaschool(content);
					}
					else if ("labschool".equals(itemNameString)){
						credit.setLabschool(content);
						
					}

					
					else if ("username".equals(itemNameString)){
						credit.setUsername(content);
					}
					else if ("damage".equals(itemNameString)){
						credit.setDamage(content);
					}
					else if ("environment".equals(itemNameString)){
						credit.setEnvironment(content);
					}
					else if ("totalcredit".equals(itemNameString)){
						credit.setTotalcredit(content);
					}
					else if ("name".equals(itemNameString)){
						credit.setName(content);
						
					}
					else if ("notename".equals(itemNameString)){
						credit.setNotename(content);
						
					}
					else if ("notetime".equals(itemNameString)){
						credit.setNotetime(content);
						
					}
					
					else if ("experimentdate".equals(itemNameString)){
						credit.setExperimentdate(content);
						
					}
					else if ("reason".equals(itemNameString)){
						credit.setReason(content);
						
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
					credit.setPicture1(tempFile.getCanonicalPath().substring(
							tempFile.getCanonicalPath().lastIndexOf("\\")));
					credit.setPicture2(tempFile.getCanonicalPath().substring(
							tempFile.getCanonicalPath().lastIndexOf("\\")));
				}	
			}
			
			
			
			response.setContentType("text/html");
		boolean isAdd=false;
			try {
				
				
				ManGradeCreditOp m=new ManGradeCreditOp();
				isAdd=m.add(credit);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isAdd) {
				JOptionPane.showMessageDialog(null, "评分成功!");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "评分失败，请稍后再来!");
				
			}
		
			
			
			
			Application application;
			
			User user;
			Note note;
			String school = ce.ChineseCoding(request.getParameter("school"));
			String notetime=ce.ChineseCoding(request.getParameter("notetime"));
			
			
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
			
			user=new User();
			user.setSchool(school);
			note=new Note();
			credit=new Credit();
			credit.setNotetime(notetime);
			ManGradeCreditOp m=new ManGradeCreditOp();
			p.setTotalcount((m.getTotalCount(user)));
			 try {
			notes =m.select(note,user,credit,p.getCurrpageno(), p.getPagesize());
			 
			 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			HttpSession session=request.getSession();	

			ServletContext scx=session.getServletContext();	


			scx.setAttribute("notes",notes);
			request.getSession().setAttribute("school", school);
			request.setAttribute( "Page",p);
			request.getRequestDispatcher("showmantogradecredit.jsp").forward(request, response);		
			 
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
