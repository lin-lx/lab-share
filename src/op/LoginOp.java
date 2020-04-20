package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Compensation;
import bean.User;
import dbbean.ConnDb;

public class LoginOp {
	private Connection cn=null;
	private PreparedStatement ps=null;
	private ResultSet  rs=null;
	public void closedb(){
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
	
	
	
	//用户登录
	public boolean checkRegister(String username,String password,String identity,String school){
		boolean loginFlag=false;
		int rowCount=0;
		try{
			cn=new ConnDb().getCon();
			String sqlstr="select * from user where username='"+username+"'and password ='"+password+"'and identity='"+identity+"'and school ='"+school+"';";
		//JOptionPane.showMessageDialog(null,"sql: "+sqlstr);
			
		ps=cn.prepareStatement(sqlstr);
		rs=ps.executeQuery();
		while (rs.next()){
			loginFlag=true;
		}
		}
		catch(Exception e){
			rowCount=0;
			e.printStackTrace();
		}
		finally{
			this.closedb();
		}
		if(rowCount>0){
			loginFlag=true;
		}
		return loginFlag;
		}
	
	
	//查询教师是否还有赔偿未支付
	public ArrayList<Compensation>  quire(User user)
	{
		ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
		try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from compensation where  applyusername='" +user.getUsername()+"' and pay='否';";
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
			 this.closedb();
		 }
		
		 return retlist;
	}	
	
	
//查询实验室负责人是否还有未审核实验室
	public ArrayList<Application>  select(User user)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where labschool= '" +user.getSchool()+ "'and result='待审核' and cancelapply='否';";
			
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
			 this.closedb();
		 }
		
		 return retlist;
	}		 
	//按总条数查询需要审核的实验室
		public int getTotalCount(User user) {
			String sqlstr="select count(*) as tc from application where labschool= '" +user.getSchool()+ "'and result='待审核' and cancelapply='否' ";
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
		

		
		//分页查询所有需要审核的实验室信息
		public ArrayList<Application>  select(User user,int pageno,int pageSize)
		{
			ArrayList<Application> retlist=new  ArrayList<Application>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from application where labschool= '" +user.getSchool()+ "'and result='待审核' and cancelapply='否' order by date desc limit "+pageSize*(pageno-1)+","+pageSize+";";
				
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
					 temp.setId(rs.getInt(25));
					 
					 retlist.add(temp);				 
				 }

				
			 }catch(Exception e){
				 retlist=null;
				 JOptionPane.showMessageDialog(null, "error"+e.toString());
				 e.printStackTrace();
			 }finally{
				 this.closedb();
			 }
			
			 return retlist;
		}		 
		
		
	}

		











