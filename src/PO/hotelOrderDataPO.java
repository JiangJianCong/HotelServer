package PO;

import java.io.Serializable;
/*********************************
 *
 * @author 蒋健聪 151250070
 * @Description hotel数据的PO类
 * @implements Serializable
 *  
 *********************************/
	
public class hotelOrderDataPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8305144572818261830L;
	
	
	/***********************************
	 * @Dsecription 设置用户名字和得到用户名字
	 * @param userName 用户名字
	 **********************************/
	public void setuserName(String userName){
		this.userName = userName;
	}
	public String getuserName(){
		return this.userName;
	}
	
	/***********************************
	 * @Dsecription 设置用户电话和得到用户电话
	 * @param userTel 用户电话
	 **********************************/
	public void setuserTel(String userTel){
		this.userTel = userTel;
	}
	public String getuserTel(){
		return this.userTel;
	}
	
	/***********************************
	 * @Dsecription 设置房间类型和得到房间类型
	 * @param roomType 房间类型
	 **********************************/
	public void setroomType(String roomType){
		this.roomType = roomType;
	}
	public String getroomType(){
		return this.roomType;
	}
	
	/***********************************
	 * @Dsecription 设置入住时间和得到入住时间
	 * @param checkInTimes 入住时间
	 **********************************/
	public void setcheckInTimes(String checkInTimes){
		this.checkInTimes = checkInTimes;
	}
	public String getcheckInTimes(){
		return this.checkInTimes;
	}
	
	/***********************************
	 * @Dsecription 设置退房时间和得到退房时间
	 * @param checkOutTime 退房时间
	 **********************************/
	public void setcheckOutTime(String checkOutTime){
		this.checkOutTime = checkOutTime;
	}
	public String getcheckOutTime(){
		return this.checkOutTime;
	}
	
	/***********************************
	 * @Dsecription 设置是否完成入住和得到是否完成入住
	 * @param fiinishCheckIn 是或者否
	 **********************************/
	public void setfinishCheckIn(String finishCheckIn){
		this.finishCheckIn = finishCheckIn;
	}
	public String getfinishCheckIn(){
		return this.finishCheckIn;
	}
	

	
	private String userName;
	private String userTel;
	private String roomType;
	private String checkInTimes;
	private String checkOutTime;
	private String finishCheckIn;
	
	
	
}
