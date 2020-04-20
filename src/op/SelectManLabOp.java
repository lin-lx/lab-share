package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import bean.Lab;
import bean.User;
import dbbean.ConnDb;

public class SelectManLabOp {


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
	
	
	//按总条数模糊查询实验室信息	
	
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from lab where labschool='" +user.getSchool()+ "'"+sql1;
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
	
		
//按分页模糊查询实验室信息		
	public ArrayList<Lab> select(String sql1,User user,int pageno,int pageSize) throws SQLException{
		ArrayList<Lab> all=new ArrayList<Lab>();
		cn=new ConnDb().getCon();
		String sql = "select  * from lab where labschool='" +user.getSchool()+ "'"+sql1+" limit "+pageSize*(pageno-1)+","+pageSize+";";
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
		 rs=ps.executeQuery();

		while(rs.next()){
			Lab temp=new Lab();
			 temp.setNumber(rs.getString(1));
			 temp.setNumber(rs.getString(1));
			 temp.setOpentime1(rs.getString(2));
			 temp.setSeatnumber(rs.getInt(3));
			 temp.setEquipment(rs.getString(4));
			 temp.setLabschool(rs.getString(5));
			 temp.setLableader(rs.getString(6));
			 temp.setComment(rs.getString(7));
			 temp.setPicture(rs.getString(8));
			 temp.setPicture1(rs.getString(9));
			 temp.setOpentime2(rs.getString(10));
			 temp.setOpentime3(rs.getString(11));
			 temp.setOpentime4(rs.getString(12));
			 all.add(temp);	
}
	closeAll();
	return all;
}
}



