package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import javax.swing.JOptionPane;

import dbbean.ConnDb;
import bean.Remind;



public class SelectRemindOp {

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
	
	
//按总数查询公告消息
	public int getTotalCount(String sql1) {
		String sqlstr="select count(*) as tc from remind "+sql1;
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

	
	
	//按分页查询公告消息
	public ArrayList<Remind> select(String sql1,int pageno,int pageSize) throws SQLException{
		ArrayList<Remind> all=new ArrayList<Remind>();
		cn=new ConnDb().getCon();
		String sql = "select * from remind "+sql1+" order by date_format(date,'%m-%d') desc limit "+pageSize*(pageno-1)+","+pageSize+";";
	//JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
		 rs=ps.executeQuery();
		Remind remind=null;
		while(rs.next()){
		remind=new Remind();
			remind.setId(rs.getInt("Id"));
			remind.setContent(rs.getString("content"));
			remind.setDate(rs.getString("date"));			
			all.add(remind);
}
	closeAll();
	return all;
}
}
