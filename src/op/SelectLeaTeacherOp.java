package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import bean.User;
import dbbean.ConnDb;

public class SelectLeaTeacherOp {


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
	
	
	
	//按总条数模糊查询本校教师	
	
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from user where school='" +user.getSchool()+ "' and identity='教师' "+sql1;
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

	//按分页模糊查询本校所有教师信息
	public ArrayList<User> select(String sql1,User user,int pageno,int pageSize) throws SQLException{
		ArrayList<User> all=new ArrayList<User>();
		cn=new ConnDb().getCon();
		String sql = "select  * from user where school='" +user.getSchool()+ "' and identity='教师' "+sql1+ " limit "+pageSize*(pageno-1)+","+pageSize+";";
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
		 rs=ps.executeQuery();
		while(rs.next()){
			User temp=new User();
			 temp.setUsername(rs.getString(1));
			 temp.setName(rs.getString(8));
			 temp.setSex(rs.getString(6));
			 temp.setIde(rs.getString(3));
			 temp.setPhone(rs.getString(4));
			 temp.setNation(rs.getString(9));
			 temp.setBirthday(rs.getString(10));
			 temp.setHiredate(rs.getString(11));
			 temp.setAddress(rs.getString(12));
			 temp.setNativepla(rs.getString(13));
			 temp.setEducation(rs.getString(14));
			 temp.setState(rs.getString(16));
			 all.add(temp);	
}
	closeAll();
	return all;
}
}


