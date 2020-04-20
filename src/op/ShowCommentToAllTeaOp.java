package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Comment;
import dbbean.ConnDb;

public class ShowCommentToAllTeaOp {
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
	

	//按总条数查询所有实验室评论
		public int getTotalCount(Comment comment) {
			String sqlstr="select count(*) as tc from comment where number='" +comment.getNumber()+ "' and labschool='" +comment.getLabschool()+ "' ";
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

	
	
	//按分页查询所有实验室评论
	public ArrayList<Comment>  quire(Comment comment,int pageno,int pageSize)
	{
		ArrayList<Comment> retlist=new  ArrayList<Comment>();
		try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from comment where number='" +comment.getNumber()+ "' and labschool='" +comment.getLabschool()+ "' order by commenttime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
	
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Comment temp=new Comment();
				 temp.setNumber(rs.getString(1));
				 temp.setUsername(rs.getString(3));
				 temp.setContent(rs.getString(4));
				 temp.setPicture(rs.getString(5));
				 temp.setLabschool(rs.getString(6));
				 temp.setCommenttime(rs.getString(7));
				 temp.setId(rs.getInt(8));
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

	



