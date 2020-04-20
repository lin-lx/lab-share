package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class ShowManLabNumberOp {

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
	
	
	
	//查询本校所有实验室号信息
	
	
	
		public ArrayList<Lab>  quire(User user)
		{
			ArrayList<Lab> retlist=new  ArrayList<Lab>();
			try{
				 cn=new ConnDb().getCon();
			 String sqlstr="select  number from lab where labschool='" +user.getSchool()+ "' ;";
			//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
		
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Lab temp=new Lab();
					 temp.setNumber(rs.getString(1));
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
