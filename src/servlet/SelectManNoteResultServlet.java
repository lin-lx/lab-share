package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import op.SelectManNoteResultOp;
import bean.CodeExchange;
import bean.Note;
import bean.Page;
import bean.User;

public class SelectManNoteResultServlet extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public SelectManNoteResultServlet() {
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
		doPost(request, response);
		}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sql=" and ";
		String experimenttime=null;
		String teaschool=null;
	    String number=null;


		
		experimenttime=new String(request.getParameter("experimenttime").getBytes("ISO8859_1"),"UTF-8");
		teaschool=new String(request.getParameter("teaschool").getBytes("ISO8859_1"),"UTF-8");
		number=new String(request.getParameter("number").getBytes("ISO8859_1"),"UTF-8");
	 

		

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
		

		if (number!=null&&!"".equals(number)) {
			sql+="number like '%"+number+"%' and ";
		}
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		if (experimenttime!=null&&!"".equals(experimenttime)) {
			sql+="experimenttime like '%"+experimenttime+"%' and ";
		}
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		
		if (teaschool!=null&&!"".equals(teaschool)) {
			sql+="labschool like '%"+teaschool+"%'";
		}
		
		else sql=sql.substring(0, sql.length()-4);
		
		SelectManNoteResultOp st;
		User user;
		String school=ce.ChineseCoding(request.getParameter("school"));
		ArrayList<Note> notes=new ArrayList<Note>();
		try {
			st = new SelectManNoteResultOp();
			user=new User();
			user.setSchool(school);
			p.setTotalcount((st.getTotalCount(sql,user)));
			notes=st.select(sql,user,p.getCurrpageno(), p.getPagesize());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	//	JOptionPane.showMessageDialog(null, notes.size());
		
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
