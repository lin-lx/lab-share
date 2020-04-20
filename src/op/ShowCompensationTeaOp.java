package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Compensation;
import bean.User;
import dbbean.ConnDb;

public class ShowCompensationTeaOp {
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

	
	

	//按总条数查询教师待赔偿信息
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from compensation where  applyusername='" +user.getUsername()+"' and pay='否' ";
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

	
	
	
//分页查询教师待赔偿信息
	public ArrayList<Compensation>  quire(User user,int pageno,int pageSize)
	{
		ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
		try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from compensation where  applyusername='" +user.getUsername()+"' and pay='否' order by notetime asc limit "+pageSize*(pageno-1)+","+pageSize+";";
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
	
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Compensation temp=new Compensation();
				 temp.setNumber(rs.getString(1));
				 temp.setLabschool(rs.getString(2));
				 temp.setName(rs.getString(3));
				 temp.setTeaschool(rs.getString(4));
				 temp.setPhone(rs.getString(5));
				 temp.setExperimenttime(rs.getString(6));
				 temp.setMoney(rs.getString(7));
				 temp.setIllustration(rs.getString(8));
				 temp.setPicture(rs.getString(9));
				 temp.setNotename(rs.getString(10));
				 temp.setNotephone(rs.getString(11));				
				 temp.setPay(rs.getString(12));
				 temp.setNotetime(rs.getString(13));
				 temp.setNoteusername(rs.getString(14));
				 temp.setApplyusername(rs.getString(15));
				 temp.setPaytime(rs.getString(16));
				 temp.setExperimentdate(rs.getString(18));
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






