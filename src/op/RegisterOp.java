package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;





import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Compensation;
import bean.User;
import dbbean.ConnDb;

public class RegisterOp {
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
	
	
	
	
	//查询用户名是否注册过
	public boolean selectusername(User user)
	{
		 boolean usernameflag=false;
		
			 cn=new ConnDb().getCon();
			 String sqlstr1="select  * from user where username= '" +user.getUsername()+ "';";
			try{
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr1);	
			
			 rs=ps.executeQuery();
			 
			 if(rs.next()){				 
				 usernameflag=true;			 
			 }

			
		 }catch(Exception e){
				e.printStackTrace();
			}finally{
				this.closeDb();
			}
			return usernameflag;
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
			
			
			
	
	
	
	
	
	
	
	//用户注册时，查询此账号是否已经注册过
	
	
	public boolean add(User user)
	
	{
		boolean addFlag=false;
	try{
		cn=new ConnDb().getCon();
		String sqlstr4="select * from user where username='"+user.getUsername()+"'or ide='"+user.getIde()+"';";
		ps=cn.prepareStatement(sqlstr4);
		rs=ps.executeQuery();
		if(rs.next()){
		JOptionPane.showMessageDialog(null,"此账户已注册过，请不要重复注册!" );
			addFlag=false;
		}
		else{
			//注册
			String sqlstr5="insert into user(username,password,ide,school,phone,identity,sex,registertime,name,nation,birthday,hiredate,address,nativepla,education) values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getIde()+"','"+user.getSchool()+"','"+user.getPhone()+"','"+user.getIdentity()+"','"+user.getSex()+"','"+user.getRegistertime()+"','"+user.getName()+"','"+user.getNation()+"','"+user.getBirthday()+"','"+user.getHiredate()+"','"+user.getAddress()+"','"+user.getNativepla()+"','"+user.getEducation()+"');";
		    ps=cn.prepareStatement(sqlstr5);
		//JOptionPane.showMessageDialog(null,"sql:" +sqlstr);
		int rows=ps.executeUpdate();
		if(rows>0){
			addFlag=true;
		}
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		this.closeDb();
	}
	return addFlag;
}
}

