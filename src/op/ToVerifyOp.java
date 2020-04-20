package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Compensation;
import bean.Credit;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class ToVerifyOp {
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
	
	
	


	
	
//是否超过实验时间	
	public ArrayList<Application>  select(Application application,User user)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where number='" +application.getNumber()+ "' and labschool='"+user.getSchool()+"' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"' and result='待审核' and cancelapply='否' and date<current_date();";
			
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
				 temp.setOpentime2(rs.getString(18));
				 temp.setOpentime3(rs.getString(19));
				 temp.setOpentime4(rs.getString(20));
				 temp.setDate(rs.getString(21));
				 temp.setId(rs.getInt(25));
				 
				 retlist.add(temp);				 
			 }

			
		 }catch(Exception e){
			 retlist=null;
			 //JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }finally{
			 this.closeDb();
		 }
		
		 return retlist;
	}	
	
	
	
	
	//按总条数查询需要审核的实验室
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from application where labschool= '" +user.getSchool()+ "'and result='待审核' and cancelapply='否' ";
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
	

	
	//分页查询所有需要审核的实验室信息
	public ArrayList<Application>  select(User user,int pageno,int pageSize)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where labschool= '" +user.getSchool()+ "'and result='待审核' and cancelapply='否' order by date desc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
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
	
	
	
//返回结果	
	public ResultSet ck(Application application,User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from application where number='" +application.getNumber()+ "' and labschool='"+user.getSchool()+"' and experimenttime='"+application.getExperimenttime()+"' and date='"+application.getDate()+"';";						
		//  JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				
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
	
	
	//查询审核的用户信息
	public ResultSet ck(User user)
	{
		
		 try{
			 cn=new ConnDb().getCon();
			 
			 String sqlstr="select * from user where username='" +user.getUsername()+ "';";						
			
			 
			//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 
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
	
	
	
	//教师申请次数
	
	public ArrayList<Application>  select1(Application application)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where username= '" +application.getUsername()+ "';";
			
	//	JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Application temp=new Application();
				
				 temp.setUsername(rs.getString(13));
				 
				 
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
	
	
	//教师使用次数
	public ArrayList<Note>  select2(Note note)
	{
		ArrayList<Note> retlist=new  ArrayList<Note>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from note where username= '" +note.getUsername()+ "' and treaty='否' ;";
			
		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Note temp=new Note();
				
				 temp.setUsername(rs.getString(8));
				 
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
	
	
	
	//教师取消申请次数
	public ArrayList<Application>  select3(Application application)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from application where username= '" +application.getUsername()+ "' and cancelapply='是';";
			
		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Application temp=new Application();
				 
				 temp.setUsername(rs.getString(13));
				
				 
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
	
	
	
	//教师违约次数
	public ArrayList<Note>  select4(Note note,Application application)
	{
		ArrayList<Note> retlist=new  ArrayList<Note>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from note where username= '" +application.getUsername()+ "' and treaty='是';";
			
		 //JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Note temp=new Note();
				
				 temp.setUsername(rs.getString(9)); 
				 
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
	
	
	
	//教师赔偿次数
	public ArrayList<Compensation>  select5(Compensation compensation,Application application)
	{
		ArrayList<Compensation> retlist=new  ArrayList<Compensation>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from compensation where applyusername= '" +application.getUsername()+ "' ;";
			
		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Compensation temp=new Compensation();
				
				 temp.setApplyusername(rs.getString(15));
				 
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
	
	
	//教师信誉总分
	
	
		public ResultSet ck1(Credit credit,Application application)
		{
			
			 try{
				 cn=new ConnDb().getCon();
				 
				 String sqlstr="select sum(totalcredit) as teachercredit  from credit where username='"+application.getUsername()+"';";						
			 //   JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
					
			 ps=cn.prepareStatement(sqlstr);
			 rs=ps.executeQuery();
			 }
				
			 catch(Exception e)
			 {
				JOptionPane.showMessageDialog(null, "error"+e.toString());
				 e.printStackTrace();
			 }
			 return rs;
			 //return rs==null?"":rs;
			}	
		
		
	
	
}




	








