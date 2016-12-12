package service;

/**************************************************************
 * accountService
 * @author 蒋健聪 151250070 
 * @Description 服务器实现账号注册登录功能的接口
 *************************************************************/

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.hotelAccountPO;
import PO.hotelDataPO;
import PO.networkManageAccountPO;
import PO.userAccountPO;
import PO.userDataPO;

public interface accountService extends Remote {
	/*====================================================
	  				以下是hotel账号的相关功能的接口
	 ====================================================*/
	public boolean hotelLogin(hotelAccountPO account) throws RemoteException;
	
	public boolean hotelSignUp(hotelAccountPO account,hotelDataPO data) throws RemoteException;
	
	public boolean checkHotelIdExist(String ID) throws RemoteException;
	
	public boolean checkHotelNameExist(String hotelName) throws RemoteException;
	
	public boolean hotelLogout(String hotelID) throws RemoteException;
	
	/*====================================================
					以下是user账号的相符功能的接口
	====================================================*/
	
	public boolean userLogin(userAccountPO account) throws RemoteException;
	
	public boolean userSignUp(userAccountPO account,userDataPO data) throws RemoteException;
	
	public boolean checkUserIdExist(String ID) throws RemoteException;
	
	/*====================================================
	                                           以下是networkManage账号的相符功能的接口
    ====================================================*/

	public boolean networkManageLogin(networkManageAccountPO account)throws RemoteException;
}
