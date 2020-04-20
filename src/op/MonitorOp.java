package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class MonitorOp {

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
	


	//按总条数查询教师使用的实验室
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from application where ((number not in(select number from note)) or (experimenttime not in(select experimenttime from note)) or (date not in(select experimentdate from note))) and labschool='"+user.getSchool()+"' and result ='审核通过' and cancelapply='否' and date<current_date() ";
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

	
	
//按分页查询教师使用的实验室
	public ArrayList<Application>  select(Note note,User user,int pageno,int pageSize)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from application where ((number not in(select number from note)) or (experimenttime not in(select experimenttime from note)) or (date not in(select experimentdate from note))) and labschool='"+user.getSchool()+"' and result ='审核通过' and cancelapply='否' and date<current_date() order by date asc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
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

	

