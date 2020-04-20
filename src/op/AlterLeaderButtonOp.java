package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import bean.User;
import dbbean.ConnDb;

public class AlterLeaderButtonOp {
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
	
	
	
	
	
	public ResultSet ck(User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from user where ide="+user.getIde()+";";						
			 //JOptionPane.showMessageDialog(null, "语句"+sqlstr);
			 
			 ps=cn.prepareStatement(sqlstr);
			 rs=ps.executeQuery();
		 }
			
		 catch(Exception e)
		 {
			JOptionPane.showMessageDialog(null, "异常"+e.toString());
			 e.printStackTrace();
		 }
		
		 return rs;
		}	

}






