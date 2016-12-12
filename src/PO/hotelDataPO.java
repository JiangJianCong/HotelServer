package PO;

import java.io.Serializable;

/*********************************
 * 
 * @author 蒋健聪 151250070
 * @Description hotel数据的PO类
 * @implements Serializable
 * 
 *********************************/
public class hotelDataPO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1462400553979411384L;
	
	private String HotelID;
	private String HotelName;
	private String HotelIntroduction;
	private String HotelTel;
	
	/****************************
	 * @Description 默认构造函数
	 * **************************/
	
	public hotelDataPO(){
		
	}
	
	/***********************************
	 * @Dsecription 设置酒店ID和得到酒店ID
	 * @param ID 酒店名字
	 **********************************/
	
	public void setHotelID(String ID){
		this.HotelID = ID;
	}
	
	public String getHotelID(){
		return this.HotelID;
	}
	
	
	/***********************************
	 * @Dsecription 设置酒店名字和得到酒店名字
	 * @param name 酒店名字
	 **********************************/
	
	public void setHotelName(String name){
		this.HotelName = name;
	}
	
	public String getHotelName(){
		return this.HotelName;
	}
	

	/***********************************
	 * @Dsecription 设置酒店介绍和得到酒店介绍
	 * @param introduction 酒店介绍
	 **********************************/
	public void setHotelIntroduction(String introduction){
		this.HotelIntroduction = introduction;
	}
	
	public String getHotelIntroduction(){
		return this.HotelIntroduction;
	}
	
	/***********************************
	 * @Dsecription 设置酒店电话和得到酒店电话
	 * @param tel 酒店电话
	 **********************************/
	public void  setHotelTel(String tel){
		this.HotelTel = tel;
	}
	
	public String getHotelTel(){
		return this.HotelTel;
	}
	
	
	
	
}
