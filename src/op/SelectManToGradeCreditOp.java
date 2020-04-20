package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class SelectManToGradeCreditOp {

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
	
	//按总条数模糊查询准备给教师信誉评分事项	
	
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from note where (number not in(select number from credit) or experimenttime not in (select experimenttime from credit) or experimentdate not in (select experimentdate from  credit)) and labschool='"+user.getSchool()+"' and treaty='否' "+sql1;
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
	
//按分页查询准备给教师信誉评分事项
	public ArrayList<Note>  select(String sql1,Note note,User user,int pageno,int pageSize)throws SQLException{
	
		ArrayList<Note> retlist=new  ArrayList<Note>();
	
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from note where (number not in(select number from credit) or experimenttime not in (select experimenttime from credit) or experimentdate not in (select experimentdate from  credit)) and labschool='"+user.getSchool()+"' and treaty='否' "+sql1+ " order by experimentdate asc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
			//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Note temp=new Note();
				 temp.setNumber(rs.getString(1));
				 temp.setName(rs.getString(2));
				 temp.setExperimenttime(rs.getString(3));
				 temp.setTeaschool(rs.getString(4));
				 temp.setLabschool(rs.getString(5));
				 temp.setExperimentdate(rs.getString(17));
				 retlist.add(temp);				 
			 }

			
		
	    this.closeDb();
	
		 return retlist;
	}		 
	
	
}

	



