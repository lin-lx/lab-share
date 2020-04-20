package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class SelectTeaNoteResultOp {

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
	
	
	
	//按总条数模糊查询教师实验室使用情况	
	
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from note where username='" +user.getUsername()+ "' and treaty='否' "+sql1;
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
	
	//按分页查询教师实验室使用情况
	public ArrayList<Note> select(String sql1,User user,int pageno,int pageSize) throws SQLException{
		ArrayList<Note> all=new ArrayList<Note>();
		cn=new ConnDb().getCon();
		String sql = "select  * from note where username='" +user.getUsername()+ "' and treaty='否' "+sql1+ " order by notetime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
		 rs=ps.executeQuery();

		while(rs.next()){
			 Note temp=new Note();
			 temp.setNumber(rs.getString(1));
			 temp.setName(rs.getString(2));
             temp.setExperimenttime(rs.getString(3));
             temp.setTeaschool(rs.getString(4));
             temp.setLabschool(rs.getString(5));
             temp.setDetail(rs.getString(6));
             temp.setNotetime(rs.getString(7));
             temp.setNotename(rs.getString(8));
             temp.setEnvironment(rs.getString(11));
             temp.setDamage(rs.getString(12));
             temp.setDamagedescribe(rs.getString(13));
             temp.setExperimentdate(rs.getString(17));
			 all.add(temp);	
			 
				
}
	closeAll();
	return all;
}
}



