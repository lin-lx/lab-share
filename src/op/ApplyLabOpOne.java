package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Compensation;
import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class ApplyLabOpOne {
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
	public ResultSet ck(Lab al)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from lab where number='" +al.getNumber()+ "'and labschool='" +al.getLabschool()+ "';";						
			
			 
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

	
	
	
	
	//查询申请教师个人信息是否完整
	
		public ArrayList<User>  select(User user)
		{
			ArrayList<User> retlist=new  ArrayList<User>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from user where username= '" +user.getUsername()+ "' and (name =' ' or phone =' ') ; ";
				
				// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();	
				
				 while(rs.next()){				 
					 User temp=new User();
					
					 
					
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
		
	
	
	
	//查询是否还有赔偿未支付
	
	public ArrayList<Compensation>  select(Compensation compensation,User user)
	{
		ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from compensation where applyusername= '" +user.getUsername()+ "' and pay='否';";
			
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
	
	
//查询在七天之内是否取消申请三次	
	
	public ArrayList<Application>  select(Application application,User user)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from application where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(canceldate) and cancelapply='是' and username='" +user.getUsername()+ "';";
			
		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Application temp=new Application();
				 temp.setCanceldate(rs.getString(22));
				 temp.setCancelapply(rs.getString(17));
				 
				
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
	
	
	
	
	
	//按总数查询所有实验室信息
		public int getTotalCount() {
			String sqlstr="select count(*) as tc from lab";
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
		
		public ArrayList<Lab>  quire(int pageno,int pageSize)
		{
			ArrayList<Lab> retlist=new  ArrayList<Lab>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from lab limit "+pageSize*(pageno-1)+","+pageSize+";";
				 
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
					 temp.setType(rs.getString(13));
					 
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








	
	









	







