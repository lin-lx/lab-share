package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Compensation;
import bean.Lab;
import bean.Remind;
import bean.User;
import dbbean.ConnDb;

public class ApplyLabOpTwo {
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
	
	
	
	


	

//按总数查询教师申请的所有实验室
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from application where username= '" +user.getUsername()+ "' and cancelapply='否' ";
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

	
	
	
//按分页查询教师申请的所有实验室
	public ArrayList<Application>  select(User user,int pageno,int pageSize)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where username= '" +user.getUsername()+ "' and cancelapply='否'  order by date desc  limit "+pageSize*(pageno-1)+","+pageSize+";";
			
		//	JOptionPane.showMessageDialog(null, "sql:"+sqlstr);
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
				 temp.setLableader(rs.getString(6));
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
	
	

	

	
	
//查询此时间段实验室是否有被教师申请过，如果有则此时间段的实验室不能被再申请
	public boolean update(Application application)
	{
		boolean altFlag = false;
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr1="select  * from application where number= '" +application.getNumber()+ "' and labschool='"+application.getLabschool()+"' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"'and cancelapply='否' and date>current_date();";
			//JOptionPane.showMessageDialog(null, "sql1:" +sqlstr1);
			 ps=cn.prepareStatement(sqlstr1);
				rs=ps.executeQuery();
				if(rs.next()){
				JOptionPane.showMessageDialog(null,"此实验室此时间段已经有人申请过啦，请申请其他时间段实验室！" );
				altFlag=false;
				}
				else{
				//查询此日期此时间段教师是否有申请过其他实验室，如果有，则不可以再申请此日期此时间段的实验室
				 String sqlstr2="select  * from application where  experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"' and username='"+application.getUsername()+"' and cancelapply='否';";
					//JOptionPane.showMessageDialog(null, "sql2:" +sqlstr2);
					 ps=cn.prepareStatement(sqlstr2);
						rs=ps.executeQuery();
						if(rs.next()){
						JOptionPane.showMessageDialog(null,"此日期此时间段您已经有申请其他实验室啦，请申请其他时间段的实验室！" );
						altFlag=false;
						}
				
				//如果没有则可以申请
				else{
				String sqlstr="insert into application(number,opentime1,seatnumber,equipment,labschool,lableader,name,phone,type,teaschool,experimenttime,applytime,result,reason,username,verifyname,opentime2,opentime3,opentime4,date,expense,pay,lableaderphone) values ('"+application.getNumber()+"','"+application.getOpentime1()+"','"+application.getSeatnumber()+"','"+application.getEquipment()+"','"+application.getLabschool()+"','"+application.getLableader()+"','"+application.getName()+"','"+application.getPhone()+"','"+application.getType()+"','"+application.getTeaschool()+"','"+application.getExperimenttime()+"','"+application.getApplytime()+"','" +application.getResult()+ "','" +application.getReason()+ "','" +application.getUsername()+ "','" +application.getVerifyname()+ "','"+application.getOpentime2()+"','"+application.getOpentime3()+"','"+application.getOpentime4()+"','"+application.getDate()+"','"+application.getExpense()+"','"+application.getPay()+"','"+application.getLableaderphone()+"');";

			    // JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			      ps=cn.prepareStatement(sqlstr);

			 int rows = ps.executeUpdate();
			 if (rows > 0) 
			 {
				 altFlag = true;
			 }	
				}
		 }
		 }
				catch(Exception e)
		 {
			JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }
		 finally
		 {
			 this.closeDb();
		 }
		 return altFlag;
	}	
	
	
	
	
}		
	






