package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;




import bean.User;
import dbbean.ConnDb;

public class ResetPasswordOp {
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
	
	
	//重置密码
	public boolean update(User user)
	{
		boolean altFlag = false;
		 try{
			 cn=new ConnDb().getCon();
			
			 String sqlstr="update user set  password='"+user.getPassword()+"'where phone='"+user.getPhone()+"';";
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);

			 int rows = ps.executeUpdate();
			 if (rows > 0) 
			 {
				 altFlag = true;
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
		 return altFlag;
	}	



//查询要重置密码的手机号是否存在
public ArrayList<User>  select(User user)
{
	ArrayList<User> retlist=new  ArrayList<User>();
	 try{
		 cn=new ConnDb().getCon();
		 String sqlstr="select * from user where phone='"+user.getPhone()+"';";
		
	// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
		 ps=cn.prepareStatement(sqlstr);
		 
		 rs=ps.executeQuery();
		 while(rs.next()){				 
			 User temp=new User();
			 temp.setPhone(rs.getString(4));
			
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








