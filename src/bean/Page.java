package bean;

import java.util.List;

public class Page {
	
	private int totalPagecount=1;//总页数
	private  int pagesize=10;//每一页显示的记录数；
	private int totalcount=0;//记录总数；
	private int currpageno=1;//当前页码；
	List<User> usersList;//实体集合
	List<Application> applicationsList;//实体集合
	List<Comment> commentsList;//实体集合
	List<Compensation> compensationsList;//实体集合
	List<Credit> creditsList;//实体集合
	List<Lab> labsList;//实体集合
	List<Note> notesList;//实体集合
	List<Remind> remindsList;//实体集合
	public int getTotalPagecount() {
		return totalPagecount;
	}
	public void setTotalPagecount(int totalPagecount) {
		this.totalPagecount = totalPagecount;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		if(totalcount>0)
			this.totalcount = totalcount;
		//计算总页数
		totalPagecount=totalcount%pagesize==0?(totalcount/pagesize)
				:totalcount/pagesize+1;
	}
	public int getCurrpageno() {
		return currpageno;
	}
	public void setCurrpageno(int currpageno) {
		this.currpageno = currpageno;
	}
	public List<User> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
	public List<Application> getApplicationsList() {
		return applicationsList;
	}
	public void setApplicationsList(List<Application> applicationsList) {
		this.applicationsList = applicationsList;
	}
	public List<Comment> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}
	public List<Compensation> getCompensationsList() {
		return compensationsList;
	}
	public void setCompensationsList(List<Compensation> compensationsList) {
		this.compensationsList = compensationsList;
	}
	public List<Credit> getCreditsList() {
		return creditsList;
	}
	public void setCreditsList(List<Credit> creditsList) {
		this.creditsList = creditsList;
	}
	public List<Lab> getLabsList() {
		return labsList;
	}
	public void setLabsList(List<Lab> labsList) {
		this.labsList = labsList;
	}
	public List<Note> getNotesList() {
		return notesList;
	}
	public void setNotesList(List<Note> notesList) {
		this.notesList = notesList;
	}
	public List<Remind> getRemindsList() {
		return remindsList;
	}
	public void setRemindsList(List<Remind> remindsList) {
		this.remindsList = remindsList;
	}
	
	


}
