package PO;

import java.io.Serializable;
/*********************************
 *
 * @author ������ 151250070
 * @Description hotel���ݵ�PO��
 * @implements Serializable
 *  
 *********************************/
	
public class hotelOrderDataPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8305144572818261830L;
	
	
	/***********************************
	 * @Dsecription �����û����ֺ͵õ��û�����
	 * @param userName �û�����
	 **********************************/
	public void setuserName(String userName){
		this.userName = userName;
	}
	public String getuserName(){
		return this.userName;
	}
	
	/***********************************
	 * @Dsecription �����û��绰�͵õ��û��绰
	 * @param userTel �û��绰
	 **********************************/
	public void setuserTel(String userTel){
		this.userTel = userTel;
	}
	public String getuserTel(){
		return this.userTel;
	}
	
	/***********************************
	 * @Dsecription ���÷������ͺ͵õ���������
	 * @param roomType ��������
	 **********************************/
	public void setroomType(String roomType){
		this.roomType = roomType;
	}
	public String getroomType(){
		return this.roomType;
	}
	
	/***********************************
	 * @Dsecription ������סʱ��͵õ���סʱ��
	 * @param checkInTimes ��סʱ��
	 **********************************/
	public void setcheckInTimes(String checkInTimes){
		this.checkInTimes = checkInTimes;
	}
	public String getcheckInTimes(){
		return this.checkInTimes;
	}
	
	/***********************************
	 * @Dsecription �����˷�ʱ��͵õ��˷�ʱ��
	 * @param checkOutTime �˷�ʱ��
	 **********************************/
	public void setcheckOutTime(String checkOutTime){
		this.checkOutTime = checkOutTime;
	}
	public String getcheckOutTime(){
		return this.checkOutTime;
	}
	
	/***********************************
	 * @Dsecription �����Ƿ������ס�͵õ��Ƿ������ס
	 * @param fiinishCheckIn �ǻ��߷�
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
