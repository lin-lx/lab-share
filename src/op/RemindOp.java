package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import dbbean.ConnDb;
import bean.Remind;


public class RemindOp {

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

//按总数查询公告信息	
	public int getTotalCount() {
		String sqlstr="select count(*) as tc from remind ";
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

	
	
	
	
	//按分页查询公告信息
		public ArrayList<Remind>  quire(int pageno,int pageSize)
		{
			ArrayList<Remind> retlist=new  ArrayList<Remind>();
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="select  * from remind order by date_format(date,'%m-%d') desc limit "+pageSize*(pageno-1)+","+pageSize+";";
				
				//JOptionPane.showMessageDialog(null, "sql:"+sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){				 
					 Remind temp=new Remind();
					 temp.setId(rs.getInt(1));
					 temp.setContent(rs.getString(2));
					 temp.setDate(rs.getString(3));
					
		
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
		
		

//添加公告信息
		public boolean add(Remind remind)
		{
			boolean addFlag = false;
			 try{
				 cn=new ConnDb().getCon();
				 String sqlstr="insert into remind(content,date) values('"+remind.getContent()+"','"+remind.getDate()+"') ;";

				// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 int rows = ps.executeUpdate();
				 if (rows > 0) 
				 {
					 addFlag = true;
				 }	
			 }
			 catch(Exception e)
			 {
				JOptionPane.showMessageDialog(null, "error"+e.toString());
				 e.printStackTrace();
			 }
			 finally
			 {
				 this.closeDb();
			 }
			 return addFlag;
		}	

		
		
	//	删除公告信息
		
				public boolean delete(String id)
		{
			boolean delFlag = false;
			 try{
				 cn=new ConnDb().getCon();
		
				 String sqlstr="delete from  remind  where id='"+id+"';";
			//	JOptionPane.showMessageDialog(null, "sql"+sqlstr);
				 ps=cn.prepareStatement(sqlstr);
				 int rows = ps.executeUpdate();
				 if (rows > 0) 
				 {
					 delFlag = true;
				 }	
			 }
			 catch(Exception e)
			 {
				JOptionPane.showMessageDialog(null, "error"+e.toString());
				 e.printStackTrace();
			 }
			 finally
			 {
				 this.closeDb();
			 }
			 return delFlag;
		}
	}
