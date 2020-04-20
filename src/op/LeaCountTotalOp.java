package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Compensation;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class LeaCountTotalOp {

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
	

	//申请次数
	
		public ArrayList<Application>  select1(Application application,User user)
		{
			ArrayList<Application> retlist=new  ArrayList<Application>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from application where labschool= '" +user.getSchool()+ "';";
				
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Application temp=new Application();
					
					 temp.setLabschool(rs.getString(5));
					 
					 
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
		
		
		//使用次数
		public ArrayList<Note>  select2(Note note,User user)
		{
			ArrayList<Note> retlist=new  ArrayList<Note>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from note where labschool= '" +user.getSchool()+ "' and treaty='否';";
				
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Note temp=new Note();
					
					 temp.setLabschool(rs.getString(4));
					 
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
		
		
		
		//取消申请次数
		public ArrayList<Application>  select3(Application application,User user)
		{
			ArrayList<Application> retlist=new  ArrayList<Application>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from application where labschool= '" +user.getSchool()+ "' and cancelapply='是';";
				
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Application temp=new Application();
					 
					 temp.setLabschool(rs.getString(5));
					 
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
		
		//违约次数
		public ArrayList<Note>  select4(Note note,User user)
		{
			ArrayList<Note> retlist=new  ArrayList<Note>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from note where labschool= '" +user.getSchool()+ "' and treaty='是';";
				
			 //JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Note temp=new Note();
					
					 temp.setLabschool(rs.getString(5)); 
					 
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
		
		//故障次数
		public ArrayList<Compensation>  select5(Compensation compensation,User user)
		{
			ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from compensation where labschool= '" +user.getSchool()+ "' ;";
				
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Compensation temp=new Compensation();
					
					 temp.setLabschool(rs.getString(2)); 
					 
					 
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

	



