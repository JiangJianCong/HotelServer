package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.hotelPromotionPO;

public interface promotionService extends Remote{
public ArrayList<hotelPromotionPO> getHotelPromotion(String ID) throws RemoteException;
	
	public boolean AddNewPromotion (String ID,hotelPromotionPO promotion) throws RemoteException;
	
	public boolean modifyHotelPromotion(String ID,hotelPromotionPO promotion) throws RemoteException;
	
	public boolean delNewPromotion (String ID,hotelPromotionPO promotion) throws RemoteException;
}
