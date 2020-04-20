package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import bean.Application;
import dbbean.ConnDb;

public class ToCommentOp {

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
	
	
	
	//返回结果数据，教师评论实验室
	public ResultSet ck(Application application)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from application where number='" +application.getNumber()+ "' and labschool='" +application.getLabschool()+ "';";						
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 
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

}






