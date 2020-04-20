package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import dbbean.ConnDb;

public class IdeOp {
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
	

	//查询身份证号是否注册过

	
		public boolean selectide(User user)
		{
			 boolean ideflag=false;
			
				 cn=new ConnDb().getCon();
				 String sqlstr3="select  * from user where ide= '" +user.getIde()+ "';";
				try{
				// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
				 ps=cn.prepareStatement(sqlstr3);	
				
				 rs=ps.executeQuery();
				 
				 if(rs.next()){				 
					 ideflag=true;			 
				 }

				
			 }catch(Exception e){
					e.printStackTrace();
				}finally{
					this.closeDb();
				}
				return ideflag;
			}
		
}
