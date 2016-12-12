package service;

import java.rmi.Remote;  
import java.rmi.RemoteException;

import PO.hotelDataPO;
import PO.userDataPO;

public interface basicDataService extends Remote{
	public void modifyHotelData(String ID,hotelDataPO data) throws RemoteException;
	
	public hotelDataPO getHotelData(String ID) throws RemoteException;
	
	public void modifyUserData(String ID,userDataPO data) throws RemoteException;
	
	public userDataPO getUserData(String ID) throws RemoteException;
}
