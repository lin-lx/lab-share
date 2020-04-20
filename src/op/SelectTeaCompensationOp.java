package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import bean.Compensation;
import bean.User;
import dbbean.ConnDb;

public class SelectTeaCompensationOp {

	private Connection cn = null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;

	public void closeAll(){
		try {
			if (cn!=null) {
				cn.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (rs!=null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	//按总条数模糊查询教师待赔偿信息	
	
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from compensation where  applyusername='" +user.getUsername()+"' and pay='否' "+sql1;
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



//按页数模糊查询教师待赔偿信息		
	public ArrayList<Compensation> select(String sql1,User user,int pageno,int pageSize) throws SQLException{
		ArrayList<Compensation> all=new ArrayList<Compensation>();
		cn=new ConnDb().getCon();
		String sql = "select  * from compensation where  applyusername='" +user.getUsername()+"' and pay='否' "+sql1+ " order by notetime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
		 rs=ps.executeQuery();
		while(rs.next()){
			Compensation temp=new Compensation();
			temp.setNumber(rs.getString(1));
			 temp.setLabschool(rs.getString(2));
			 temp.setName(rs.getString(3));
			 temp.setTeaschool(rs.getString(4));
			 temp.setPhone(rs.getString(5));
			 temp.setExperimenttime(rs.getString(6));
			 temp.setMoney(rs.getString(7));
			 temp.setIllustration(rs.getString(8));
			 temp.setPicture(rs.getString(9));
			 temp.setNotename(rs.getString(10));
			 temp.setNotephone(rs.getString(11));				
			 temp.setPay(rs.getString(12));
			 temp.setNotetime(rs.getString(13));
			 temp.setNoteusername(rs.getString(14));
			 temp.setApplyusername(rs.getString(15));
			 temp.setPaytime(rs.getString(16));
			 temp.setExperimentdate(rs.getString(18));
			 all.add(temp);		
				
}
	closeAll();
	return all;
}
}




