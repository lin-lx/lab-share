package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import bean.Lab;
import dbbean.ConnDb;

public class ShowTeaLabPictureOp {

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

	
	
	//查询实验室图片
	public ArrayList<Lab>  quire(Lab lab)
	{
		ArrayList<Lab> retlist=new  ArrayList<Lab>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from lab where number='" +lab.getNumber()+ "' and labschool='" +lab.getLabschool()+ "';";
			 
	//	JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Lab temp=new Lab();
				 temp.setPicture(rs.getString(8));
				 temp.setPicture1(rs.getString(9));
				 retlist.add(temp);				 
			 }

			
		 }catch(Exception e){
			 retlist=null;
			// JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }finally{
			 this.closeDb();
		 }
		
		 return retlist;
	}		 
}





