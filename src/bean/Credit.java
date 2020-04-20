package bean;

public class Credit {
	private String number;
	private String experimenttime;
	private String teaschool;
	private String labschool;
	private String username;
	private String damage;
	private String environment;
	private String totalcredit;
	private String name;
	private String notename;
	private String notetime;
	private String picture1;
	private String picture2;
	private String experimentdate;
	private String reason;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExperimenttime() {
		return experimenttime;
	}
	public void setExperimenttime(String experimenttime) {
		this.experimenttime = experimenttime;
	}
	public String getTeaschool() {
		return teaschool;
	}
	public void setTeaschool(String teaschool) {
		this.teaschool = teaschool;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getNotename() {
		return notename;
	}
	public void setNotename(String notename) {
		this.notename = notename;
	}
	public String getNotetime() {
		return notetime;
	}
	public void setNotetime(String notetime) {
		this.notetime = notetime;
	}
	public String getTotalcredit() {
		return totalcredit==null?"":totalcredit;
	}
	public void setTotalcredit(String totalcredit) {
		this.totalcredit = totalcredit;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getLabschool() {
		return labschool;
	}
	public void setLabschool(String labschool) {
		this.labschool = labschool;
	}
	public String getExperimentdate() {
		return experimentdate;
	}
	public void setExperimentdate(String experimentdate) {
		this.experimentdate = experimentdate;
	}
	public String getReason() {
		//return reason;
		return reason==null?"":reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
