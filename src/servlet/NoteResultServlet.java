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


import op.NoteResultOp;
import bean.Application;
import bean.CodeExchange;
import bean.Note;
import bean.Page;
import bean.User;

public class NoteResultServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();

	/**
	 * Constructor of the object.
	 */
	public NoteResultServlet() {
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
		Note note;
		note=new Note();
		Application application;
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
					note.setNumber(content);
				} else if ("experimenttime".equals(itemNameString)){
					note.setExperimenttime(content);
				}
				else if ("name".equals(itemNameString)){
					note.setName(content);
				}
				else if ("teaschool".equals(itemNameString)){
					note.setTeaschool(content);
					
				}

				
				else if ("notename".equals(itemNameString)){
					note.setNotename(content);
				}
				else if ("detail".equals(itemNameString)){
					note.setDetail(content);
				}
				else if ("labschool".equals(itemNameString)){
					note.setLabschool(content);
				}
				else if ("username".equals(itemNameString)){
					note.setUsername(content);
				}
				else if ("compensation".equals(itemNameString)){
					note.setCompensation(content);
					
				}
				else if ("environment".equals(itemNameString)){
					note.setEnvironment(content);
					
				}
				else if ("damage".equals(itemNameString)){
					note.setDamage(content);
					
				}
				else if ("damagedescribe".equals(itemNameString)){
					note.setDamagedescribe(content);
					
				}
				else if ("treaty".equals(itemNameString)){
					note.setTreaty(content);
					
				}
				else if ("experimentdate".equals(itemNameString)){
				   note.setExperimentdate(content);
					
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
				note.setPicture(tempFile.getCanonicalPath().substring(
						tempFile.getCanonicalPath().lastIndexOf("\\")));
				note.setPicture1(tempFile.getCanonicalPath().substring(
						tempFile.getCanonicalPath().lastIndexOf("\\")));
			}	
		}
		
		
		
		response.setContentType("text/html");
	boolean isAdd=false;
		try {
			
			
			NoteResultOp n=new NoteResultOp();
			isAdd=n.add(note);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isAdd) {
			JOptionPane.showMessageDialog(null, "记录成功!");
			
		}
		else {
			JOptionPane.showMessageDialog(null, "记录失败，请稍后再来!");
			
		}
	
	
		
		String school=ce.ChineseCoding(request.getParameter("school"));
		String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
		String number=ce.ChineseCoding(request.getParameter("number"));
		String experimentdate=ce.ChineseCoding(request.getParameter("experimentdate"));
		String date=ce.ChineseCoding(request.getParameter("date"));
		String labschool=ce.ChineseCoding(request.getParameter("labschool"));
				
		
		
		
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

		
		
		
		ArrayList<Application> applications=new ArrayList<Application>();
		NoteResultOp n;
		 n = new NoteResultOp();
		 User user;
		user=new User();
		user.setSchool(school);
		note=new Note();
		note.setExperimenttime(experimenttime);
		note.setNumber(number);
		note.setExperimentdate(experimentdate);
		application=new Application();
		application.setNumber(number);
		application.setExperimenttime(experimenttime);
		application.setDate(date);
		application.setLabschool(labschool);
		
		p.setTotalcount((n.getTotalCount(user)));
		 try {
		applications =n.select(note,user,p.getCurrpageno(), p.getPagesize());
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		HttpSession session=request.getSession();	

		ServletContext scx=session.getServletContext();	


		scx.setAttribute("applications",applications);
		request.getSession().setAttribute("school", school);
		request.setAttribute( "Page",p);
		request.getRequestDispatcher("monitor.jsp").forward(request, response);			
		 
	}

		
/*
					ArrayList<Note> notes=new ArrayList<Note>();
					NoteResultOp n;
					User user;
					 n = new NoteResultOp();
					 user=new User();
					 user.setSchool(school);
					 notes = n.quire(user);

					

					HttpSession session=request.getSession();	

					ServletContext scx=session.getServletContext();	

					scx.setAttribute("notes",notes);	
					request.getRequestDispatcher("notesituationresult.jsp").forward(request, response);	
		
	}*/

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
