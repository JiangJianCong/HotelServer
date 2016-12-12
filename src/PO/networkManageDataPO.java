package PO;
import java.io.Serializable;
import PO.hotelDataPO;
/*********************************
 * 
 * @author 贾莛 151250068
 * @Description networkManage数据的PO类
 * @implements Serializable
 * 
 *********************************/
public class networkManageDataPO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6271975342067058914L;
	public networkManageDataPO(){
		
	}
	
	/***********************************
	 * @Dsecription 设置网站管理ID和得到网站管理ID
	 * @param ID 网站管理名字
	 **********************************/
	public void setNetworkManageID(String ID){
		this.networkManageID=ID;
	}
	public String getNetworkManageID(){
		return this.networkManageID;
	}
	
	
	private String networkManageID;
	private String data;
	private String HotelName;
	private hotelDataPO DataPO;
}
