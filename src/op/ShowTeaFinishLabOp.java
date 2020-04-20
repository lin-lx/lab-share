package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Application;
import bean.User;
import dbbean.ConnDb;

public class ShowTeaFinishLabOp {
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
	

	
	

	//按总条数查询教师所有完成实验的实验室
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from application a,note n where a.username='"+user.getUsername()+"' and a.result='审核通过' and a.date<current_date() and a.cancelapply='否' and a.number=n.number and a.experimenttime=n.experimenttime and a.labschool=n.labschool and a.date=n.experimentdate";
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

	
	
	
//按页数查询教师所有使用的实验室
	public ArrayList<Application>  select(User user,int pageno,int pageSize)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select a.number,a.labschool,a.type,a.date,a.experimenttime,a.applytime from application a,note n where a.username='"+user.getUsername()+"' and a.result='审核通过' and a.date<current_date() and a.cancelapply='否' and a.number=n.number and a.experimenttime=n.experimenttime and a.labschool=n.labschool and a.date=n.experimentdate order by date desc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Application temp=new Application();
				temp.setNumber(rs.getString(1));
				temp.setLabschool(rs.getString(2));
				temp.setType(rs.getString(3));
				temp.setDate(rs.getString(4));
				temp.setExperimenttime(rs.getString(5));
				temp.setApplytime(rs.getString(6));
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
