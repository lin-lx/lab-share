package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.User;
import dbbean.ConnDb;

public class ShowUserOp {
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

	
	
	//按总条数查询所有用户
	public int getTotalCount() {
		String sqlstr="select count(*) as tc from user where identity!= '系统管理员' ";
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

	
	
//按页数查询所有用户	
	public ArrayList<User>  quire(int pageno,int pageSize)
	{
		ArrayList<User> retlist=new  ArrayList<User>();
		 try{
			 cn=new ConnDb().getCon();
		
			 
			String 	sqlstr="select  * from user where identity!= '系统管理员' order by registertime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
				  
		
			//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);			 
			 rs=ps.executeQuery();
			 
		
			 while(rs.next()){				 
				 User temp=new User();
				 
			
				 
				 temp.setUsername(rs.getString(1));
				 temp.setPassword(rs.getString(2));
				 temp.setIde(rs.getString(3));
				 temp.setPhone(rs.getString(4));
				 temp.setIdentity(rs.getString(5));
				 temp.setSex(rs.getString(6));
				 temp.setRegistertime(rs.getString(7));
				 temp.setName(rs.getString(8));
				 temp.setNation(rs.getString(9));
				 temp.setBirthday(rs.getString(10));
				 temp.setHiredate(rs.getString(11));
				 temp.setAddress(rs.getString(12));
				 temp.setNativepla(rs.getString(13));
				 temp.setEducation(rs.getString(14));
				 temp.setSchool(rs.getString(15));
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







