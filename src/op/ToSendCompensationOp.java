package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import bean.Compensation;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class ToSendCompensationOp {
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
	
	
	//返回结果实验室管理员发送赔偿消息到教师
	public ResultSet ck(Note note)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from note where number= '"+note.getNumber()+"' and experimenttime='" +note.getExperimenttime()+ "'and experimentdate='" +note.getExperimentdate()+ "' and labschool='" +note.getLabschool()+ "' and compensation='是';";						
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 
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
	
	
	//
	public ArrayList<Compensation>  select(Compensation compensation)
	{
		ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from compensation where number= '" +compensation.getNumber()+ "' and experimenttime='" +compensation.getExperimenttime()+ "'and labschool='" +compensation.getLabschool()+ "' and experimentdate='"+compensation.getExperimentdate()+"';";
			
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Compensation temp=new Compensation();
				 temp.setPay(rs.getString(12));
				 
				
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






	


