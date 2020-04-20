package servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;








import op.SelectRemindOp;
import bean.Page;
import bean.Remind;


public class SelectRemindServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectRemindServlet() {
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
			String sql=" where ";
			String id=null;
			String content=null;
			String date=null;
			id=new String(request.getParameter("id").getBytes("ISO8859_1"),"UTF-8");
			content=new String(request.getParameter("content").getBytes("ISO8859_1"),"UTF-8");
			date=new String(request.getParameter("date").getBytes("ISO8859_1"),"UTF-8");
			String splitStr="\\.|\\*|\\||\\:|\\^|@|,|#| ";
		
			
			
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

		
			
		
			if (id!=null&&!"".equals(id)) {
				sql+="id="+id+" and ";
			}
			// JOptionPane.showMessageDialog(null, "sql:" +sql);
			if (content!=null&&!"".equals(content)) {
				sql+="content like '%"+content+"%' and ";
			}
			// JOptionPane.showMessageDialog(null, "sql:" +sql);
			if (date!=null&&!"".equals(date)) {
				sql+="date like '%"+date+"%'";
			}
			
			else sql=sql.substring(0, sql.length()-4);
			
			SelectRemindOp sr;
			
			ArrayList<Remind> reminds=new ArrayList<Remind>();
			try {
				sr = new SelectRemindOp();
				p.setTotalcount((sr.getTotalCount(sql)));
				reminds=sr.select(sql,p.getCurrpageno(), p.getPagesize());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		//	JOptionPane.showMessageDialog(null, reminds.size());
			
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
