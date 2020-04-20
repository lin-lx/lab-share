package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import bean.Compensation;
import dbbean.ConnDb;

public class ShowTeaFinishCompensationPictureOp {

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

	
	//查询教师完成赔偿的图片
	public ArrayList<Compensation>  quire(Compensation compensation)
	{
		ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from compensation where number='" +compensation.getNumber()+ "' and labschool='" +compensation.getLabschool()+ "' and experimenttime='" +compensation.getExperimenttime()+ "' and experimentdate='"+compensation.getExperimentdate()+"';";
			 
		//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Compensation temp=new Compensation();
				 temp.setPicture(rs.getString(9));
				 temp.setPicture1(rs.getString(17));
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






