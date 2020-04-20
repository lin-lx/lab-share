package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.User;
import dbbean.ConnDb;

public class DeleteManOp {

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
	

	
	
	//查询实验室管理员在离职状态
		public ArrayList<User>  select(User user)
		{
			ArrayList<User> retlist=new  ArrayList<User>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from user where ide= '" +user.getIde()+ "' and state='在职';";
				
			 //JOptionPane.showMessageDialog(null, "sql"+sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 User temp=new User();
					 temp.setIde(rs.getString(3));
					 
					
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
		
	
		
	
//实验室负责人注销实验室管理员账号
	public boolean delete(String ide)
	{
		boolean delFlag = false;
		 try{
			 cn=new ConnDb().getCon();
	
			 String sqlstr="delete from  user  where ide='"+ide+"';";
			 //JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);

			 int rows = ps.executeUpdate();
			 if (rows > 0) 
			 {
				 delFlag = true;
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
		 return delFlag;
	}
	
	
	
	//按总条数查询所有用户
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from user where school='" +user.getSchool() +"' and identity='实验室管理员'";
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

//按分页查询本校所有实验室管理员信息
	public ArrayList<User>  select(User user,int pageno,int pageSize)
	{
		ArrayList<User> retlist=new  ArrayList<User>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from user where school='" +user.getSchool() +"' and identity='实验室管理员' limit "+pageSize*(pageno-1)+","+pageSize+";";
			 
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 User temp=new User();
				 temp.setUsername(rs.getString(1));
				 temp.setName(rs.getString(8));
				 temp.setSex(rs.getString(6));
				 temp.setIde(rs.getString(3));
				 temp.setPhone(rs.getString(4));
				 temp.setNation(rs.getString(9));
				 temp.setBirthday(rs.getString(10));
				 temp.setHiredate(rs.getString(11));
				 temp.setAddress(rs.getString(12));
				 temp.setNativepla(rs.getString(13));
				 temp.setEducation(rs.getString(14));
				 temp.setState(rs.getString(16));
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









