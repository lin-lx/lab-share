
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

import op.AlterLabOp;
import bean.CodeExchange;
import bean.Lab;
import bean.Page;
import bean.User;

public class AlterLabServlet extends HttpServlet {

	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public AlterLabServlet() {
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
		//request.setCharacterEncoding("utf-8");
		Lab lab;

		lab=new Lab();
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
					lab.setNumber(content);
				} else if ("opentime1".equals(itemNameString)){
					lab.setOpentime1(content);
				}
				else if ("opentime2".equals(itemNameString)){
					lab.setOpentime2(content);
				}
				else if ("opentime3".equals(itemNameString)){
					lab.setOpentime3(content);
				}
				else if ("opentime4".equals(itemNameString)){
					lab.setOpentime4(content);
				}
				else if ("seatnumber".equals(itemNameString)){
					lab.setSeatnumber(Integer.parseInt(content));
				}
				else if ("equipment".equals(itemNameString)){
					lab.setEquipment(content);
				}
				else if ("labschool".equals(itemNameString)){
					lab.setLabschool(content);
				}
				
				else if ("lableader".equals(itemNameString)){
					lab.setLableader(content);
				}
				else if ("comment".equals(itemNameString)){
					lab.setComment(content);
				}
				else if ("type".equals(itemNameString)){
					lab.setType(content);
				}
				else if ("expense".equals(itemNameString)){
					lab.setExpense(content);
				}
			}
				
			else {
				File tempFile = new File(request.getSession()
			 			.getServletContext().getRealPath("/")
						+ "uploadphotos\\"
						+ new File(tempitemFileItem.getName()).getName());
				File tempFile1 = new File(request.getSession()
			 			.getServletContext().getRealPath("/")
						+ "uploadphotos\\"
						+ new File(tempitemFileItem.getName()).getName());
				try {
					tempitemFileItem.write(tempFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
				lab.setPicture(tempFile.getCanonicalPath().substring(
						tempFile.getCanonicalPath().lastIndexOf("\\")));
				lab.setPicture1(tempFile1.getCanonicalPath().substring(
					tempFile.getCanonicalPath().lastIndexOf("\\")));
		}
		
		}
		response.setContentType("text/html");
		boolean isAlt=false;
			try {
				AlterLabOp l=new AlterLabOp();
				User user=new User();
				isAlt=l.update(user,lab);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isAlt) {
				JOptionPane.showMessageDialog(null, "修改成功!");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "修改失败!");
				
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

		
		
		
			AlterLabOp l;

		ArrayList<Lab> labs=new ArrayList<Lab>();
	User user;
	user=new User();
	String school=ce.ChineseCoding(request.getParameter("school"));
	user.setSchool(school);
		 l = new AlterLabOp();
		 p.setTotalcount((l.getTotalCount(user)));	 
			
		 try {
		 labs = l.quire(user,p.getCurrpageno(), p.getPagesize());
		// JOptionPane.showMessageDialog(null, labs.size());
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		HttpSession session=request.getSession();	
	
		ServletContext scx=session.getServletContext();	


		scx.setAttribute("labs",labs);
		request.getSession().setAttribute("school", school);
		request.setAttribute( "Page",p);
		request.getRequestDispatcher("labmanage.jsp").forward(request, response);		
		 
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
