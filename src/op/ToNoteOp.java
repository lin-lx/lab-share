package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class ToNoteOp {
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
	

	
	//记录实验室使用情况	
	public ResultSet ck(Application application,User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where number= '" +application.getNumber()+ "' and experimenttime= '" +application.getExperimenttime()+ "' and labschool= '" +application.getLabschool()+ "' and date='"+application.getDate()+"';";
			
		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
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

	
	
	
	public ArrayList<Note>  select(Note note)
	{
		ArrayList<Note> retlist=new  ArrayList<Note>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from note where number= '" +note.getNumber()+ "' and labschool='" +note.getLabschool()+ "' and experimenttime='" +note.getExperimenttime()+ "' and experimentdate='"+note.getExperimentdate()+"';";
			
		// JOptionPane.showMessageDialog(null, "sql：" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Note temp=new Note();
				 temp.setNumber(rs.getString(1));
				 temp.setLabschool(rs.getString(5));
				 temp.setExperimenttime(rs.getString(3));
				 temp.setExperimentdate(rs.getString(17));
				
				 retlist.add(temp);				 
			 }

			
		 }catch(Exception e){
			 retlist=null;
			 JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }finally{
			 this.closeDb();
		 }
		
		 return retlist;
	}		 
	
	
	
	
}

	







