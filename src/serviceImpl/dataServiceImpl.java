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
	 * ���л�ʱΪ����������������ɵ�Ԥ��ֵ
	 */
	private static final long serialVersionUID = 8294295926033849323L;

	public dataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/* ==============================================================
		�����ǽӿڵľ���ʵ��
	================================================================ */
	
	
	/********************************************************************
	 *����hotel��Account�õ���ID����ȡ�Ƶ����Ϣ���ص�hotelDataPO���ؿͻ��� 
	 ********************************************************************/
	
	
	public hotelDataPO getHotelData(hotelAccountPO hotel) throws RemoteException {
		hotelDataPO result = new hotelDataPO();
		result.setHotelIntroduction("good Hotel");
		result.setHotelName("HotelName");
		result.setHotelTel("123456");
		
		return result;
	}
	
	
	/********************************************************************
	 *����user��Account�õ���ID����ȡ�ͻ�����Ϣ���ص�userDataPO���ؿͻ��� 
	 ********************************************************************/	

	public userDataPO getUserData(userAccountPO userAccount) throws RemoteException{
		
		
		
		
		return null;
	}
	
	
	
	
}
