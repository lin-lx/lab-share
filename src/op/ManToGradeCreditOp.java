package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.JOptionPane;


import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class ManToGradeCreditOp {

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
	
	//实验室管理员给教师进行信誉评分
	public ResultSet ck(Note note,User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from note where number= '" +note.getNumber()+ "' and experimenttime= '" +note.getExperimenttime()+ "' and labschool='" +user.getSchool()+ "' and experimentdate='"+note.getExperimentdate()+"';";
			
	  //JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 

			
		 }catch(Exception e){
			
			 JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }
		
		 return rs;
	}	
	
	
	
	public ResultSet ck(User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from user where username='" +user.getUsername()+ "';";						
			
			 
			//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 
			 ps=cn.prepareStatement(sqlstr);
			 rs=ps.executeQuery();
		 }
			
		 catch(Exception e)
		 {
			JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }
		
		 return rs;
		}	
	
		
	
}

	








