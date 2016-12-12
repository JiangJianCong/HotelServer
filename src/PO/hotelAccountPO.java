package PO;

import java.io.Serializable;


/*******************************************
 * 
 * @author 蒋健聪 151250070
 * @Description 注册登录用的hotel的账号的PO类
 * @implements Serializable
 * 
 ******************************************/

public class hotelAccountPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153159451086616047L;
	/*****************************
	 *@Description 默认构造函数 
	 ***************************/
	public hotelAccountPO(){
		
	}
	
	/***********************************
	 * @Dsecription 设置酒店ID和得到酒店ID
	 * @param ID 酒店ID
	 **********************************/
	public void setHotelID(String ID){
		this.hotelName = ID;
	}
	public String getHotelID (){
		return this.hotelName;
	}
	
	/***********************************
	 * @Dsecription 设置酒店密码和得到酒店密码
	 * @param password 酒店密码
	 **********************************/
	public void setHotelPassword(String password){
		this.password = password;
	}
	public String getHotelPassword(){
		return this.password;
	}
	
	
	

	private String hotelName;
	private String password;
}
