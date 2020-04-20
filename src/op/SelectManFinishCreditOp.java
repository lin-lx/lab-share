package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import bean.Credit;
import bean.User;
import dbbean.ConnDb;

public class SelectManFinishCreditOp {


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
	

	
	//按总条数模糊查询教师信誉评分	
		
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from credit where labschool='"+user.getSchool()+"'"+sql1;
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

		
//按分页模糊查询教师信誉评分
	public ArrayList<Credit>  select(String sql1,User user,int pageno,int pageSize)throws SQLException{
	
		ArrayList<Credit> retlist=new  ArrayList<Credit>();
	
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from credit where labschool='"+user.getSchool()+"'"+sql1+ " order by notetime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
			//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Credit temp=new Credit();
					temp.setNumber(rs.getString(1));
					temp.setExperimenttime(rs.getString(4));
					temp.setExperimentdate(rs.getString(14));
					temp.setName(rs.getString(7));
					temp.setTeaschool(rs.getString(2));
					temp.setTotalcredit(rs.getString(10));
					temp.setNotetime(rs.getString(9));
					temp.setDamage(rs.getString(5));
					temp.setEnvironment(rs.getString(6));
					temp.setReason(rs.getString(15));
				 retlist.add(temp);				 
			 }

			
		
	    this.closeDb();
	
		 return retlist;
	}		 
	
	
}

	




