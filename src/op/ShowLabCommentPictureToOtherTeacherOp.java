package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import bean.Comment;
import dbbean.ConnDb;

public class ShowLabCommentPictureToOtherTeacherOp {

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

	
	//查询实验室图片
	public ArrayList<Comment>  quire(Comment comment)
	{
		ArrayList<Comment> retlist=new  ArrayList<Comment>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select  * from comment where id='" +comment.getId()+ "';";
			 
		//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
			 rs=ps.executeQuery();
			 while(rs.next()){				 
				 Comment temp=new Comment();
				 temp.setNumber(rs.getString(1));
				 temp.setPicture(rs.getString(5));
				 temp.setPicture1(rs.getString(9));
				 temp.setLabschool(rs.getString(6));
				 retlist.add(temp);				 
			 }

			
		 }catch(Exception e){
			 retlist=null;
			// JOptionPane.showMessageDialog(null, "error"+e.toString());
			 e.printStackTrace();
		 }finally{
			 this.closeDb();
		 }
		
		 return retlist;
	}		 
}





