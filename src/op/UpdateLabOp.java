package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class UpdateLabOp {

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
	
	
	//查询实验室是否在使用中
	public ArrayList<Application>  select(Application application)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where number='"+application.getNumber()+"' and labschool='"+application.getLabschool()+"' and date>current_date() and cancelapply='否' and (result='审核通过' or result='待审核');";
			
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Application temp=new Application();
				 temp.setNumber(rs.getString(1));
				 temp.setOpentime1(rs.getString(2));
				 temp.setSeatnumber(rs.getInt(3));
				 temp.setEquipment(rs.getString(4));
				 temp.setLabschool(rs.getString(5));
				 temp.setLableader(rs.getString(6));
				 temp.setName(rs.getString(7));
				 temp.setPhone(rs.getString(8));
				 temp.setType(rs.getString(9));
				 temp.setTeaschool(rs.getString(11));
				 temp.setExperimenttime(rs.getString(10));
				 temp.setApplytime(rs.getString(12));
				 temp.setUsername(rs.getString(13));
				 temp.setResult(rs.getString(14));
				 temp.setOpentime2(rs.getString(18));
				 temp.setOpentime3(rs.getString(19));
				 temp.setOpentime4(rs.getString(20));
				 temp.setDate(rs.getString(21));
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

	
	
	//修改实验室信息
	public ResultSet ck(Lab al)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from lab where number='" +al.getNumber()+ "'and labschool='"+al.getLabschool()+"';";						
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 
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
				 String sqlstr="select  * from lab where labschool='" +user.getSchool()+ "' limit "+pageSize*(pageno-1)+","+pageSize+";";
				 //JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
		
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
				JOptionPane.showMessageDialog(null, "error"+e.toString());
				 e.printStackTrace();
			 }finally{
				 this.closeDb();
			 }
			
			 return retlist;
		}		 
		
	}


