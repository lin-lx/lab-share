package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import dbbean.ConnDb;

public class PhoneOp {
	
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
	
	//查询手机号是否注册过
	
			public boolean selectphone(User user)
			{
				 boolean phoneflag=false;
				
					 cn=new ConnDb().getCon();
					 String sqlstr2="select  * from user where phone= '" +user.getPhone()+ "';";
					try{
					// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
					 ps=cn.prepareStatement(sqlstr2);	
					
					 rs=ps.executeQuery();
					 
					 if(rs.next()){				 
						 phoneflag=true;			 
					 }

					
				 }catch(Exception e){
						e.printStackTrace();
					}finally{
						this.closeDb();
					}
					return phoneflag;
				}
		
		

}
