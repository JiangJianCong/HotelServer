package PO;
import java.io.Serializable;
import PO.hotelDataPO;
/*********************************
 * 
 * @author ���� 151250068
 * @Description networkManage���ݵ�PO��
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
	 * @Dsecription ������վ����ID�͵õ���վ����ID
	 * @param ID ��վ��������
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
