package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;


import bean.Credit;


import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class ManGradeCreditOp {

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
	

	
	
	

//教师信誉评分
	public boolean add(Credit credit)throws SQLException{

		 cn=new ConnDb().getCon();
			 String sqlstr="insert into credit(number,experimenttime,teaschool,labschool,username,damage,environment,totalcredit,name,notename,notetime,picture1,picture2,experimentdate,reason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;

		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 this.ps = this.cn.prepareStatement(sqlstr) ;
			 this.ps.setString(1,credit.getNumber()) ;
			 this.ps.setString(2,credit.getExperimenttime()) ;
			 this.ps.setString(3,credit.getTeaschool()) ;
			 this.ps.setString(4,credit.getLabschool()) ;
			 this.ps.setString(5,credit.getUsername()) ;
			 this.ps.setString(6,credit.getDamage()) ;
			 this.ps.setString(7,credit.getEnvironment()) ;
			 this.ps.setString(8,credit.getTotalcredit()) ;
			 this.ps.setString(9,credit.getName()) ;
			 this.ps.setString(10,credit.getNotename()) ;
			 this.ps.setString(12,credit.getPicture1()) ;
			 this.ps.setString(13,credit.getPicture2()) ;
			 this.ps.setString(14,credit.getExperimentdate()) ;
			 this.ps.setString(15,credit.getReason()) ;
			
			    Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String notetime=format.format(date);
				this.ps.setString(11,notetime) ;
				//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				if(this.ps.executeUpdate() > 0){
					return true ;
				}
				closeDb();
				return false ;
			}
	
	

	
	//按总条数查询所有教师信誉评分
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from note where (number not in(select number from credit) or experimenttime not in (select experimenttime from credit) or experimentdate not in (select experimentdate from  credit)) and labschool='"+user.getSchool()+"' ";
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
	
	
	//按分页查询所有教师信誉评分
	public ArrayList<Note>  select(Note note,User user,Credit credit,int pageno,int pageSize)
	{
		ArrayList<Note> retlist=new  ArrayList<Note>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from note where (number not in(select number from credit) or experimenttime not in (select experimenttime from credit) or experimentdate not in (select experimentdate from  credit)) and labschool='"+user.getSchool()+"' order by experimentdate asc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Note temp=new Note();
				 temp.setNumber(rs.getString(1));
				 temp.setName(rs.getString(2));
				 temp.setExperimenttime(rs.getString(3));
				 temp.setTeaschool(rs.getString(4));
				 temp.setExperimentdate(rs.getString(17));
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

	

	



	
