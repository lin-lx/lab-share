package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class ShowLabOp {
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

	

	//按总条数查询所有实验室信息
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from lab where labschool='" +user.getSchool()+ "' ";
		PreparedStatement ps;
		int totalcount = 0;
		try {
			cn=new ConnDb().getCon();
			
			 ps=cn.prepareStatement(sqlstr);			 
			 rs=ps.executeQuery();
			 
			while(rs.next()) {
				totalcount=rs.getInt("tc");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalcount;
	}

	//按分页查询所有实验室信息
	public ArrayList<Lab>  quire(User user,int pageno,int pageSize)
	{
		ArrayList<Lab> retlist=new  ArrayList<Lab>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from lab where labschool='" +user.getSchool()+ "' limit "+pageSize*(pageno-1)+","+pageSize+";";
			 
			 //JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Lab temp=new Lab();
				 temp.setNumber(rs.getString(1));
				 temp.setOpentime1(rs.getString(2));
				 temp.setSeatnumber(rs.getInt(3));
				 temp.setEquipment(rs.getString(4));
				 temp.setLabschool(rs.getString(5));
				 temp.setLableader(rs.getString(6));
				 temp.setComment(rs.getString(7));
				 temp.setPicture(rs.getString(8));
				 temp.setPicture1(rs.getString(9));
				 temp.setOpentime2(rs.getString(10));
				 temp.setOpentime3(rs.getString(11));
				 temp.setOpentime4(rs.getString(12));
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




