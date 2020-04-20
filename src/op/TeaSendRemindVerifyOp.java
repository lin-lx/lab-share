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

public class TeaSendRemindVerifyOp {
	
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
	
	
	
	
	
	//返回结果
	public ResultSet ck(Application application)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from application where number='" +application.getNumber()+ "'and labschool='" +application.getLabschool()+ "' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"';";						
			
			 
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

	
	
//查询用户信息	
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

	
	//超过实验室时间的实验室不可以发送提醒审核消息		
	public ArrayList<Application>  select(Application application)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			    String sqlstr="select * from application where number='"+application.getNumber()+"' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"' and labschool='"+application.getLabschool()+"' and date<current_date() and result='待审核'";
			
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
				 temp.setReason(rs.getString(15));
				 temp.setVerifyname(rs.getString(16));
				 temp.setOpentime2(rs.getString(18));
				 temp.setOpentime3(rs.getString(19));
				 temp.setOpentime4(rs.getString(20));
				 temp.setDate(rs.getString(21));
				 temp.setId(rs.getInt(25));
				 
				
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
	
	
	
	
	//已审核的实验室不可以发送提醒审核消息		
		public ArrayList<Application>  select1(Application application)
		{
			ArrayList<Application> retlist=new  ArrayList<Application>();
			 try{
				 cn=new ConnDb().getCon();
				    String sqlstr="select * from application where number='"+application.getNumber()+"' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"' and labschool='"+application.getLabschool()+"' and date<current_date() and (result='审核通过' or result='审核未通过')";
				
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
					 temp.setReason(rs.getString(15));
					 temp.setVerifyname(rs.getString(16));
					 temp.setOpentime2(rs.getString(18));
					 temp.setOpentime3(rs.getString(19));
					 temp.setOpentime4(rs.getString(20));
					 temp.setDate(rs.getString(21));
					 temp.setId(rs.getInt(25));
					 
					
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
		
		
	
	
	//按总条数查询所有申请的实验室	
	
			public int getTotalCount(User user) {
				String sqlstr="select count(*) as tc from  application where username= '" +user.getUsername()+ "' and cancelapply='否'";
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

		

	//按分页查询所有申请的实验室
				public ArrayList<Application>  select(User user,int pageno,int pageSize)
				{
					ArrayList<Application> retlist=new  ArrayList<Application>();
					 try{
						 
						 cn=new ConnDb().getCon();
										
						 String sqlstr="select  * from application where username= '" +user.getUsername()+ "' and cancelapply='否' limit "+pageSize*(pageno-1)+","+pageSize+";";
						
				//JOptionPane.showMessageDialog(null, "sql:"  +sqlstr);
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
							 temp.setReason(rs.getString(15));
							 temp.setVerifyname(rs.getString(16));
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
		}




