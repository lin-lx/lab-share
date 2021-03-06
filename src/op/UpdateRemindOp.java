package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import bean.Remind;
import dbbean.ConnDb;

public class UpdateRemindOp {

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
	
	public ResultSet ck(Remind al)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select id,content from remind where id="+al.getId()+";";						
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

}
