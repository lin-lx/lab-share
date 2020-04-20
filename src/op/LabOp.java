package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;



import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class LabOp {
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
	


	
//实验室管理员添加实验室信息

	public boolean add(Lab lab)
	{
		boolean addFlag = false;
		 try{
			 cn=new ConnDb().getCon();
			 
			 cn=new ConnDb().getCon();
				String sqlstr1="select * from lab where number='"+lab.getNumber()+"' and labschool='"+lab.getLabschool()+"';";
				ps=cn.prepareStatement(sqlstr1);
				rs=ps.executeQuery();
				if(rs.next()){
				JOptionPane.showMessageDialog(null,"此实验室已经添加过了,请不要重复添加!" );
					addFlag=false;
				}
				
				else{
					String sqlstr="insert into lab(number,opentime1,seatnumber,equipment,labschool,lableader,comment,picture,picture1,opentime2,opentime3,opentime4,type,expense,lableaderphone) values('"+lab.getNumber()+"','"+lab.getOpentime1()+"','"+lab.getSeatnumber()+"','"+lab.getEquipment()+"','"+lab.getLabschool()+"','"+lab.getLableader()+"','"+lab.getComment()+"','"+lab.getPicture()+"','"+lab.getPicture1()+"','" +lab.getOpentime2()+"','"+lab.getOpentime3()+"','"+lab.getOpentime4()+"','"+lab.getType()+"','"+lab.getExpense()+"','"+lab.getLableaderphone()+"');";
				

		//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);

			 int rows = ps.executeUpdate();
			 if (rows > 0) 
			 {
				 addFlag = true;
			 }	
				}
		 }
		 catch(Exception e)
		 {
			JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }
		 finally
		 {
			 this.closeDb();
		 }
		 return addFlag;
	}	

	
	

	//按总条数查询实验室信息
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
				// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
		
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
				JOptionPane.showMessageDialog(null, "error"+e.toString());
				 e.printStackTrace();
			 }finally{
				 this.closeDb();
			 }
			
			 return retlist;
		}	
		
		
		
}



