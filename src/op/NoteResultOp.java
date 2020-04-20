package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import bean.Application;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class NoteResultOp {

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
	

	
	
	

//记录实验室使用情况
	public boolean add(Note note)throws SQLException{

		
			 cn=new ConnDb().getCon();
			 String sqlstr="insert into note(number,name,experimenttime,teaschool,labschool,detail,notetime,notename,username,compensation,environment,damage,damagedescribe,picture,picture1,treaty,experimentdate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			 this.ps = this.cn.prepareStatement(sqlstr) ;
			 this.ps.setString(1,note.getNumber()) ;
			 this.ps.setString(2,note.getName()) ;
			 this.ps.setString(3,note.getExperimenttime()) ;
			 this.ps.setString(4,note.getTeaschool()) ;
			 this.ps.setString(5,note.getLabschool()) ;
			 this.ps.setString(6,note.getDetail()) ;
			 this.ps.setString(8,note.getNotename()) ;
			 this.ps.setString(9,note.getUsername()) ;
			 this.ps.setString(10,note.getCompensation()) ;
			 this.ps.setString(11,note.getEnvironment()) ;
			 this.ps.setString(12,note.getDamage()) ;
			 this.ps.setString(13,note.getDamagedescribe()) ;
			 this.ps.setString(14,note.getPicture()) ;
			 this.ps.setString(15,note.getPicture1()) ;
			 this.ps.setString(16,note.getTreaty()) ;
			 this.ps.setString(17,note.getExperimentdate()) ;
		
			    Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String notetime=format.format(date);
				this.ps.setString(7,notetime) ;
				//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				if(this.ps.executeUpdate() > 0){
					return true ;
				}
				closeDb();
				return false ;
			}

	
	//按总条数查询所有记录实验室情况
		public int getTotalCount(User user) {
			String sqlstr="select count(*) as tc from application where ((number not in(select number from note)) or (experimenttime not in(select experimenttime from note)) or (date not in(select experimentdate from note))) and labschool='"+user.getSchool()+"' and result ='审核通过' and cancelapply='否' and date<current_date() ";
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

		
	
	//分页查询所有记录实验室使用情况
	public ArrayList<Application>  select(Note note,User user,int pageno,int pageSize)
	{
		ArrayList<Application> retlist=new  ArrayList<Application>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from application where ((number not in(select number from note)) or (experimenttime not in(select experimenttime from note)) or (date not in(select experimentdate from note))) and labschool='"+user.getSchool()+"' and result ='审核通过' and cancelapply='否' and date<current_date() order by date asc limit "+pageSize*(pageno-1)+","+pageSize+";";
			
			// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
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

	

