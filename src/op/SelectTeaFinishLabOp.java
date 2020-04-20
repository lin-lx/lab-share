package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import bean.Application;
import bean.User;
import dbbean.ConnDb;

public class SelectTeaFinishLabOp {


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
	
	
	
	
	
	//按总条数模糊查询教师完成实验的实验室	
		
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from application where username= '" +user.getUsername()+ "' and result='审核通过'and date<current_date()" +sql1;
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

	
	//按分页模糊查询教师完成实验的实验室
	
	public ArrayList<Application> select(String sql1,User user,int pageno,int pageSize) throws SQLException{
		ArrayList<Application> all=new ArrayList<Application>();
		cn=new ConnDb().getCon();
		String sql = "select  * from application where username= '" +user.getUsername()+ "' and result='审核通过'and date<current_date()" +sql1+ " order by date desc limit "+pageSize*(pageno-1)+","+pageSize+";";
	//JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
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
			 all.add(temp);		
}
	closeAll();
	return all;
}
}



