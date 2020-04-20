package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.JOptionPane;

import bean.Application;
import bean.User;
import dbbean.ConnDb;

public class LeaShowTeacherCreditOp {

	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public void closeDb(){
		try{
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(cn!=null){
				cn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	public ResultSet ck(Application application,User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			
			 String sqlstr3="select * from application where number='" +application.getNumber()+ "' and experimenttime='" +application.getExperimenttime()+ "'and labschool='" +user.getSchool()+ "';";
			 String sqlstr1="select  count(*) as sumapply from application where username= '" +application.getUsername()+ "';";
			 String sqlstr2="select  count(*) as sumcancelapply from application where username= '" +application.getUsername()+ "' and cancelapply='是';";
			
			 // JOptionPane.showMessageDialog(null, +sqlstr);
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr3);			 
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr1);
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr2);
			 ps=cn.prepareStatement(sqlstr3);
			 ps=cn.prepareStatement(sqlstr1);
			 ps=cn.prepareStatement(sqlstr2);
			 rs=ps.executeQuery();
		 }
			
		 catch(Exception e)
		 {
			JOptionPane.showMessageDialog(null, "异常"+e.toString());
			 e.printStackTrace();
		 }
		
		 return rs;
		}	

}

