package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import bean.Comment;
import bean.User;
import dbbean.ConnDb;

public class SelectTeaCommentOp {

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
	
	
	
	//按总条数模糊查询教师评论实验室	
	
		public int getTotalCount(String sql1,User user) {
			String sqlstr="select count(*) as tc from comment where username='" +user.getUsername()+ "'"+sql1;
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
	//按分页模糊教师评论的实验室
	
	public ArrayList<Comment> select(String sql1,User user,int pageno,int pageSize) throws SQLException{
		ArrayList<Comment> all=new ArrayList<Comment>();
		cn=new ConnDb().getCon();
		String sql = "select  * from comment where username='" +user.getUsername()+ "'"+sql1+ " order by commenttime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
		// JOptionPane.showMessageDialog(null, "sql:" +sql);
		 ps=cn.prepareStatement(sql);
		 rs=ps.executeQuery();

		while(rs.next()){
			Comment temp=new Comment();
			 temp.setNumber(rs.getString(1));
			 temp.setExperimenttime(rs.getString(2));
			 temp.setUsername(rs.getString(3));
			 temp.setContent(rs.getString(4));
			 temp.setPicture(rs.getString(5));
			 temp.setLabschool(rs.getString(6));
			 temp.setCommenttime(rs.getString(7));
			 temp.setId(rs.getInt(8));
			 temp.setExperimentdate(rs.getString(10));
			 all.add(temp);	
}
	closeAll();
	return all;
}
}


