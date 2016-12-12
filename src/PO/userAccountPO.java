package PO;

import java.io.Serializable;

/*******************************************
 * 
 * @author 蒋健聪 151250070
 * @Description 注册登录用的user的账号的PO类
 * @implements Serializable
 * 
 ******************************************/


public class userAccountPO implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 4162272063361183250L;
	public userAccountPO(){
		
	}
	
	/***********************************
	 * @Dsecription 设置用户ID和得到用户ID
	 * @param ID 酒店ID
	 **********************************/
	public void setUserID(String ID){
		this.userID = ID;
	}
	public String getUserID (){
		return this.userID;
	}
	
	/***********************************
	 * @Dsecription 设置用户密码和得到用户密码
	 * @param password 酒店密码
	 **********************************/
	public void setUserPassword(String password){
		this.password = password;
	}
	public String getUserPassword(){
		return this.password;
	}
	
	
	

	private String userID;
	private String password;
}
