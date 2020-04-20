package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;













import op.ApplyLabOpOne;
import op.ApplyLabOpTwo;
import bean.Application;
import bean.CodeExchange;
import bean.Compensation;
import bean.Lab;
import bean.Page;
import bean.User;


public class ApplyLabServletTwo extends HttpServlet {
	CodeExchange ce = new CodeExchange();
	/**
	 * Constructor of the object.
	 */
	public ApplyLabServletTwo() {
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
	//	request.setCharacterEncoding("utf-8");
		Application application;
		ApplyLabOpTwo l ;
		application=new Application();
	    l = new ApplyLabOpTwo();		
			
	    String btnApp = request.getParameter("btnApp");
	   
		if(btnApp!=null)
		{
	        String opentime1 = ce.ChineseCoding(request.getParameter("opentime1"));
			int seatnumber = Integer.parseInt(request.getParameter("seatnumber"));
			String equipment = ce.ChineseCoding(request.getParameter("equipment"));			
			String lableader = ce.ChineseCoding(request.getParameter("lableader"));
			String username = ce.ChineseCoding(request.getParameter("username"));
			String number=ce.ChineseCoding(request.getParameter("number"));
			String experimenttime=ce.ChineseCoding(request.getParameter("experimenttime"));
			String labschool=ce.ChineseCoding(request.getParameter("labschool"));
			String date=ce.ChineseCoding(request.getParameter("date"));
			String name = ce.ChineseCoding(request.getParameter("name"));
			String phone = ce.ChineseCoding(request.getParameter("phone"));
			String type = ce.ChineseCoding(request.getParameter("type"));
			String teaschool = ce.ChineseCoding(request.getParameter("teaschool"));			
			String applytime = ce.ChineseCoding(request.getParameter("applytime"));
			String result=ce.ChineseCoding(request.getParameter("result"));
			String verifyname=ce.ChineseCoding(request.getParameter("verifyname"));
			String opentime2 = ce.ChineseCoding(request.getParameter("opentime2"));
			String opentime3 = ce.ChineseCoding(request.getParameter("opentime3"));
			String opentime4 = ce.ChineseCoding(request.getParameter("opentime4"));
			String cancelapply=ce.ChineseCoding(request.getParameter("cancelapply"));
			String canceldate=ce.ChineseCoding(request.getParameter("canceldate"));
			String expense=ce.ChineseCoding(request.getParameter("expense"));
			String pay=ce.ChineseCoding(request.getParameter("pay"));
			String reason=ce.ChineseCoding(request.getParameter("reason"));
			String lableaderphone=ce.ChineseCoding(request.getParameter("lableaderphone"));
		
			
			application= new Application();
			application.setNumber(number);
			application.setOpentime1(opentime1);
			application.setSeatnumber(seatnumber);
			application.setEquipment(equipment);
			application.setLabschool(labschool);
			application.setLableader(lableader);
			application.setName(name);
			application.setPhone(phone);
			application.setType(type);
			application.setTeaschool(teaschool);
			application.setExperimenttime(experimenttime);
			application.setUsername(username);
			application.setResult(result);
			application.setVerifyname(verifyname);
			application.setApplytime((String)new Date().toLocaleString());
			application.setOpentime2(opentime2);
			application.setOpentime3(opentime3);
			application.setOpentime4(opentime4);
			application.setExpense(expense);
			application.setPay(pay);
			application.setDate(date);
			application.setCancelapply(cancelapply);
			application.setCanceldate(canceldate);
			application.setReason(reason);
			application.setLableaderphone(lableaderphone);
			 boolean altFlag = l.update(application);
			 if(altFlag){
			JOptionPane.showMessageDialog(null, "申请成功，等待审核!");
			}		 	
			else
			{
				//JOptionPane.showMessageDialog(null, "申请失败，请等会再来!");
				
			}
		}
		
	
	
		
		
		
			User user;
			String username = ce.ChineseCoding(request.getParameter("username"));
			
			
			
			String currpageno=null;
			String pageSize=null;
		    currpageno =request.getParameter("page");
		    pageSize = request.getParameter("pageSize");
			
			
			
			
			
			Page p=new Page();
			
			if(pageSize!=null&&!"".equals(pageSize)) {
				p.setPagesize(Integer.parseInt(pageSize.trim()));
			}
			
			if(currpageno==null||"".equals(currpageno)) {
				p.setCurrpageno(1);
			}else {
				p.setCurrpageno(Integer.parseInt(currpageno.trim()));
			}

			
			
			
			

			ArrayList<Application> applications=new ArrayList<Application>();
			l = new ApplyLabOpTwo();
			user=new User();
			user.setUsername(username);
			p.setTotalcount((l.getTotalCount(user)));
			 try {
			 applications =l.select(user,p.getCurrpageno(), p.getPagesize());
			 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			HttpSession session=request.getSession();	

			ServletContext scx=session.getServletContext();	
		

			
		
			scx.setAttribute("applications",applications);
			
			request.getSession().setAttribute("username", username);
		
			request.setAttribute( "Page",p);
			
			request.getRequestDispatcher("applyf.jsp").forward(request, response);			
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
