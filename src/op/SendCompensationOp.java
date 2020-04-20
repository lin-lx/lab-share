package op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import bean.Compensation;
import bean.Note;
import bean.User;
import dbbean.ConnDb;

public class SendCompensationOp {

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
	


	
	

//发送赔偿消息
	public boolean add(Compensation compensation)throws SQLException{

		 cn=new ConnDb().getCon();
			 String sqlstr="insert into compensation(number,labschool,name,teaschool,phone,experimenttime,money,illustration,picture,notename,notephone,pay,notetime,noteusername,applyusername,picture1,experimentdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;

		// JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
			 this.ps = this.cn.prepareStatement(sqlstr) ;
			 this.ps.setString(1,compensation.getNumber()) ;
			 this.ps.setString(2,compensation.getLabschool()) ;
			 this.ps.setString(3,compensation.getName()) ;
			 this.ps.setString(4,compensation.getTeaschool()) ;
			 this.ps.setString(5,compensation.getPhone()) ;
			 this.ps.setString(6,compensation.getExperimenttime()) ;
			 this.ps.setString(7,compensation.getMoney()) ;
			 this.ps.setString(8,compensation.getIllustration()) ;
			 this.ps.setString(9,compensation.getPicture()) ;
			 this.ps.setString(10,compensation.getNotename()) ;
			 this.ps.setString(11,compensation.getNotephone()) ;
			 this.ps.setString(12,compensation.getPay()) ;
			 this.ps.setString(14,compensation.getNoteusername()) ;
			 this.ps.setString(15,compensation.getApplyusername()) ;
			 this.ps.setString(16,compensation.getPicture1()) ;
			 this.ps.setString(17,compensation.getExperimentdate()) ;
		
			    Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String notetime=format.format(date);
				this.ps.setString(13,notetime) ;
				//JOptionPane.showMessageDialog(null, "sql:" +sqlstr);
				if(this.ps.executeUpdate() > 0){
					return true ;
				}
				closeDb();
				return false ;
			}
	
	
	
		
	
	
	//按总条数查询所有需要给教师发送赔偿的消息
	public int getTotalCount(User user) {
		String sqlstr="select count(*) as tc from note where ((number not in (select number from compensation)) or (experimenttime not in (select experimenttime from compensation)) or (experimentdate not in (select experimentdate from compensation))) and labschool='"+user.getSchool()+"' and compensation='是' ";
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
	
	
		
	
	
//按分页查询所有需要给教师发送的赔偿消息
	public ArrayList<Note>  quire(User user,int pageno,int pageSize)
	{
		ArrayList<Note> retlist=new  ArrayList<Note>();
		 try{
			 cn=new ConnDb().getCon();
			 String sqlstr="select * from note where ((number not in (select number from compensation)) or (experimenttime not in (select experimenttime from compensation)) or (experimentdate not in (select experimentdate from compensation))) and labschool='"+user.getSchool()+"' and compensation='是' order by notetime desc limit "+pageSize*(pageno-1)+","+pageSize+";";
			 
			// JOptionPane.showMessageDialog(null, "sql"+sqlstr);
			 ps=cn.prepareStatement(sqlstr);
			 
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
                 temp.setDamage(rs.getString(12));
                 temp.setDamagedescribe(rs.getString(13));
                 temp.setExperimentdate(rs.getString(17));
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



