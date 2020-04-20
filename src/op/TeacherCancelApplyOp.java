 package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import bean.Application;
import bean.User;
import dbbean.ConnDb;

public class TeacherCancelApplyOp {
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
	
	
	
	//按总条数查询所有申请的实验室	
	
		public int getTotalCount(User user) {
			String sqlstr="select count(*) as tc from  application where username= '" +user.getUsername()+ "' and cancelapply='否'";
			PreparedStatement ps;
			int totalcount = 0;
			try {
				cn=new ConnDb().getCon();
				
				 ps=cn.prepareStatement(sqlstr);			 
				 rs=ps.executeQuery();
				 
				while(rs.next()) {
					totalcount=rs.getInt("tc");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return totalcount;
		}

	

//按分页查询所有申请的实验室
			public ArrayList<Application>  select(User user,int pageno,int pageSize)
			{
				ArrayList<Application> retlist=new  ArrayList<Application>();
				 try{
					 
					 cn=new ConnDb().getCon();
									
					 String sqlstr="select  * from application where username= '" +user.getUsername()+ "' and cancelapply='否' order by date desc limit "+pageSize*(pageno-1)+","+pageSize+";";
					
			//JOptionPane.showMessageDialog(null, "sql:"  +sqlstr);
					 ps=cn.prepareStatement(sqlstr);
					 
					 rs=ps.executeQuery();
					 while(rs.next()){				 
						 Application temp=new Application();
						 temp.setNumber(rs.getString(1));
						 temp.setOpentime1(rs.getString(2));
						 temp.setSeatnumber(rs.getInt(3));
						 temp.setEquipment(rs.getString(4));
						 temp.setLabschool(rs.getString(5));
						 temp.setLableader(rs.getString(6));
						 temp.setName(rs.getString(7));
						 temp.setPhone(rs.getString(8));
						 temp.setType(rs.getString(9));
						 temp.setTeaschool(rs.getString(11));
						 temp.setExperimenttime(rs.getString(10));
						 temp.setApplytime(rs.getString(12));
						 temp.setUsername(rs.getString(13));
						 temp.setResult(rs.getString(14));
						 temp.setReason(rs.getString(15));
						 temp.setVerifyname(rs.getString(16));
						 temp.setOpentime2(rs.getString(18));
						 temp.setOpentime3(rs.getString(19));
						 temp.setOpentime4(rs.getString(20));
						 temp.setDate(rs.getString(21));
						 temp.setLableaderphone(rs.getString(26));
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
	
			
	//科研研究实验室在实验开始前三天取消扣除50%费用		
			public boolean update(Application application)
			{
				boolean altFlag = false;
				 try{
					 cn=new ConnDb().getCon();
					 
					 
					    String sqlstr1="select * from application where number='"+application.getNumber()+"' and experimenttime='"+application.getExperimenttime()+"' and labschool='"+application.getLabschool()+"' and DATE_SUB(CURDATE(), INTERVAL 3 DAY) <= date(date) and type='科研研究';";
						ps=cn.prepareStatement(sqlstr1);
						rs=ps.executeQuery();
						//JOptionPane.showMessageDialog(null, "语句:" +sqlstr1);
						if(rs.next()){
						JOptionPane.showMessageDialog(null,"离实验时间少于3天,取消申请将收取您50%费用!" );
						altFlag=true;
						}
						

					
						
		//取消申请			
					 String sqlstr="update application set cancelapply= '是',canceldate='"+application.getCanceldate()+"' where number='"+application.getNumber()+"' and experimenttime='" +application.getExperimenttime()+ "' and labschool='" +application.getLabschool()+ "' and date='"+application.getDate()+"';";
 
					//JOptionPane.showMessageDialog(null, "语句:" +sqlstr);				
					  ps=cn.prepareStatement(sqlstr);
					  
						

					 int rows = ps.executeUpdate();
					 if (rows > 0) 
					 {
						 altFlag = true;
					 }	
				 }
				 catch(Exception e)
				 {
					 JOptionPane.showMessageDialog(null, "异常"+e.toString());
					 e.printStackTrace();
				 }
				 finally
				 {
					 this.closeDb();
				 }
				 return altFlag;
			}	
			
		
	//超过实验室时间的实验室不可以取消申请实验室		
			public ArrayList<Application>  select(Application application)
			{
				ArrayList<Application> retlist=new  ArrayList<Application>();
				 try{
					 cn=new ConnDb().getCon();
					    String sqlstr="select * from application where number='"+application.getNumber()+"' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"' and labschool='"+application.getLabschool()+"' and date<current_date()";
					
					// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
					 ps=cn.prepareStatement(sqlstr);
					 
					 rs=ps.executeQuery();
					 while(rs.next()){				 
						 Application temp=new Application();
						 temp.setNumber(rs.getString(1));
						 temp.setOpentime1(rs.getString(2));
						 temp.setSeatnumber(rs.getInt(3));
						 temp.setEquipment(rs.getString(4));
						 temp.setLabschool(rs.getString(5));
						 temp.setLableader(rs.getString(6));
						 temp.setName(rs.getString(7));
						 temp.setPhone(rs.getString(8));
						 temp.setType(rs.getString(9));
						 temp.setTeaschool(rs.getString(11));
						 temp.setExperimenttime(rs.getString(10));
						 temp.setApplytime(rs.getString(12));
						 temp.setUsername(rs.getString(13));
						 temp.setResult(rs.getString(14));
						 temp.setReason(rs.getString(15));
						 temp.setVerifyname(rs.getString(16));
						 temp.setOpentime2(rs.getString(18));
						 temp.setOpentime3(rs.getString(19));
						 temp.setOpentime4(rs.getString(20));
						 temp.setDate(rs.getString(21));
						 temp.setId(rs.getInt(25));
						 
						
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
