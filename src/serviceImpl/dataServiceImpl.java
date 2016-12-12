package serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import PO.hotelAccountPO;
import PO.hotelDataPO;
import PO.userAccountPO;
import PO.userDataPO;
import service.dataService;

public class dataServiceImpl extends UnicastRemoteObject implements dataService{

	

	/**
	 * 序列化时为解决兼容性问题生成的预设值
	 */
	private static final long serialVersionUID = 8294295926033849323L;

	public dataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/* ==============================================================
		以下是接口的具体实现
	================================================================ */
	
	
	/********************************************************************
	 *根据hotel的Account得到的ID，读取酒店的信息加载到hotelDataPO返回客户端 
	 ********************************************************************/
	
	
	public hotelDataPO getHotelData(hotelAccountPO hotel) throws RemoteException {
		hotelDataPO result = new hotelDataPO();
		result.setHotelIntroduction("good Hotel");
		result.setHotelName("HotelName");
		result.setHotelTel("123456");
		
		return result;
	}
	
	
	/********************************************************************
	 *根据user的Account得到的ID，读取客户的信息加载到userDataPO返回客户端 
	 ********************************************************************/	

	public userDataPO getUserData(userAccountPO userAccount) throws RemoteException{
		
		
		
		
		return null;
	}
	
	
	
	
}
