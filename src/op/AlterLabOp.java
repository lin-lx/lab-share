package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class AlterLabOp {

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
		String sqlstr="select count(*) as tc from lab where labschool='"+user.getSchool()+"' ";
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
			 
			String  sqlstr="select  * from lab where labschool='"+user.getSchool()+"' limit "+pageSize*(pageno-1)+","+pageSize+";";
			
			 //JOptionPane.showMessageDialog(null, "语句"+sqlstr);
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
				 temp.setType(rs.getString(13));
				 temp.setExpense(rs.getString(14));
				 retlist.add(temp);				 
			 }

			
		 }catch(Exception e){
			 retlist=null;
			 //JOptionPane.showMessageDialog(null, "异常"+e.toString());
			 e.printStackTrace();
		 }finally{
			 this.closeDb();
		 }
		
		 return retlist;
	}		 
	
	
	
	
	
	
	

	
//修改实验室信息
	public boolean update(User user,Lab lab)
	{
		boolean altFlag = false;
		 try{
			 cn=new ConnDb().getCon();
			
			 String sqlstr="update lab set opentime1='"+lab.getOpentime1()+"',opentime2='"+lab.getOpentime2()+"',opentime3='"+lab.getOpentime3()+"',opentime4='"+lab.getOpentime4()+"',seatnumber='"+lab.getSeatnumber()+"',equipment='"+lab.getEquipment()+"',labschool='"+lab.getLabschool()+"',lableader='"+lab.getLableader()+"',comment='"+lab.getComment()+"',picture='"+lab.getPicture()+"',picture1='"+lab.getPicture1()+"',type='"+lab.getType()+"',expense='"+lab.getExpense()+"' where number='"+lab.getNumber()+"' and labschool='"+lab.getLabschool()+"';";

			//JOptionPane.showMessageDialog(null, "语句"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);

			 int rows = ps.executeUpdate();
			 if (rows > 0) 
			 {
				 altFlag = true;
			 }	
		 }
		 catch(Exception e)
		 {
			 JOptionPane.showMessageDialog(null, "异常"+e.toString());
			 e.printStackTrace();
		 }
		 finally
		 {
			 this.closeDb();
		 }
		 return altFlag;
	}	
	
	
	
}


