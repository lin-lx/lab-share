package bean;

public class CodeExchange {
	public String ChineseCoding(String str){
		if(str==null) str="";
		try{
			str=new String (str.getBytes("ISO8859_1"),"utf-8");
			
		}
		catch (Exception e){
			str="";
			e.printStackTrace();
		}
		return str;
	}
}



