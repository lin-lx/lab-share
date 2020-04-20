package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


import bean.Compensation;
import dbbean.ConnDb;

public class ToPayCompensationOp {

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
	
	
	//返回结果教师支付赔偿费用
	public ResultSet ck(Compensation compensation)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from compensation where number= '"+compensation.getNumber()+"' and experimenttime='" +compensation.getExperimenttime()+ "' and labschool='" +compensation.getLabschool()+ "' and experimentdate='"+compensation.getExperimentdate()+"';";						
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






	


