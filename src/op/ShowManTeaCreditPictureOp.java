package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import bean.Credit;
import bean.User;
import dbbean.ConnDb;

public class ShowManTeaCreditPictureOp {

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

	
	//查询教师信誉评分图片
	public ArrayList<Credit>  quire(Credit credit,User user)
	{
		ArrayList<Credit> retlist=new  ArrayList<Credit>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from credit where number='" +credit.getNumber()+ "' and labschool='" +user.getSchool()+ "' and experimenttime='" +credit.getExperimenttime()+ "' and experimentdate='"+credit.getExperimentdate()+"';";
			 
		//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Credit temp=new Credit();
				 temp.setPicture1(rs.getString(11));
				 temp.setPicture2(rs.getString(12));
				 retlist.add(temp);				 
			 }

			
		 }catch(Exception e){
			 retlist=null;
			// JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }finally{
			 this.closeDb();
		 }
		
		 return retlist;
	}		 
}






