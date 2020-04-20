package bean;

public class Lab {
	private String number;
	private String opentime1;
	private String opentime2;
	private String opentime3;
	private String opentime4;
	private int seatnumber;
	private String equipment;
	private String labschool;
	private String lableader;
	private String comment;
	private String picture;
	private String picture1;
	private String type;
	private String expense;
	private String lableaderphone;
	

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public int getSeatnumber() {
		return seatnumber;
	}
	public void setSeatnumber(int seatnumber) {
		this.seatnumber = seatnumber;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getLabschool() {
		return labschool;
	}
	public void setLabschool(String labschool) {
		this.labschool = labschool;
	}
	public String getLableader() {
		return lableader;
	}
	public void setLableader(String lableader) {
		this.lableader = lableader;
	}
	public String getComment() {
		//return comment;
		return comment==null?"":comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getOpentime1() {
		//return opentime1;
		return opentime1==null?"":opentime1;
	}
	public void setOpentime1(String opentime1) {
		this.opentime1 = opentime1;
	}
	public String getOpentime2() {
		//return opentime2;
		return opentime2==null?"":opentime2; 
	}
	public void setOpentime2(String opentime2) {
		this.opentime2 = opentime2;
	}
	public String getOpentime3() {
		//return opentime3;
		return opentime3==null?"":opentime3;
		
	}
	public void setOpentime3(String opentime3) {
		this.opentime3 = opentime3;
	}
	public String getOpentime4() {
		//return opentime4;
		return opentime4==null?"":opentime4;
	}
	public void setOpentime4(String opentime4) {
		this.opentime4 = opentime4;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public String getLableaderphone() {
		return lableaderphone;
	}
	public void setLableaderphone(String lableaderphone) {
		this.lableaderphone = lableaderphone;
	}
	

}
