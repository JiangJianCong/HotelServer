package PO;

import java.io.Serializable;

/*********************************
*
* @author 蒋健聪 151250070
* @Description user数据的PO类
* @implements Serializable
*  
*********************************/


public class userDataPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607656028579187134L;
	
	
	/*******************************
	 * @Description userDataPO的默认构造方法
	 *******************************/
	public userDataPO(){
		
	}
	
	/***********************************
	 * @Dsecription 设置用户ID和得到用户ID
	 * @param ID 用户ID
	 **********************************/
	public void setUserID(String ID){
		this.userID = ID;
	}
	public String getUserID(){
		return this.userID;
	}
	
	
	/***********************************
	 * @Dsecription 设置用户名字和得到用户名字
	 * @param name 用户名字
	 **********************************/
	public void setUserName(String name){
		this.userName = name;
	}
	public String getUserName(){
		return this.userName;
	}
	
	/***********************************
	 * @Dsecription 设置用户电话和得到用户电话
	 * @param tel 用户电话
	 **********************************/
	public void setUserTel(String tel){
		this.UserTel = tel;
	}
	public String getUserTel(){
		return this.UserTel;
	}
	
	/***********************************
	 * @Dsecription 设置用户信用值和得到用户信用值
	 * @param credit 用户信用值
	 **********************************/
	public void setUserCredit(int credit){
		this.UserCredit = credit;
	}
	
	public int getUserCredit(){
		return this.UserCredit;
	}
	
	/***********************************
	 * @Dsecription 设置用户性别和得到用户性别
	 * @param sex 用户性别
	 **********************************/
	public void setUserSex(String sex){
		this.userSex = sex;
	}
	
	public String getUserSex(){
		return this.userSex;
	}
	
	
	private String userID;
	private String userName;
	private String UserTel;
	private int UserCredit;
	private String userSex;
	
}
