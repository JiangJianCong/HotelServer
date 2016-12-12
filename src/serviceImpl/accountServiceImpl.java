package serviceImpl;

/**************************************************************
 * accountServiceImpl
 * @author ������ 151250070 
 * @Description ������ʵ���˺�ע���¼����
 *************************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import PO.hotelAccountPO;
import PO.hotelDataPO;
import PO.networkManageAccountPO;
import PO.networkManageDataPO;
import PO.userAccountPO;
import PO.userDataPO;
import RMISERVICE.MainService;
import service.accountService;
import service.basicDataService;

public class accountServiceImpl extends UnicastRemoteObject implements accountService {

	/**
	 * ���л�ʱΪ����������������ɵ�Ԥ��ֵ
	 */
	private static final long serialVersionUID = 946076354513983992L;
	private BufferedReader bf;
	private ArrayList<String> onlineHotelAccount = new ArrayList<String>();
	private ArrayList<String> onlineUserAccount = new ArrayList<String>();
	
	public accountServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*=====================================================
					�����ǽӿ�ʵ�ֵķ���
	=======================================================*/
	
	
	
	
	
	/*====================================================
	 				�����ǵ�¼��ʵ�ַ���
	 ====================================================*/
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	/*****************************************************
	 * public boolean hotelLogin(hotelAccountPO account)
	 * @Description �Ƶ��¼
	 * @param account �ͻ��˴�����hotelAccountPO
	 *****************************************************/
	public boolean hotelLogin(hotelAccountPO account) throws RemoteException {
		if(findID("hotel",account.getHotelID(),account.getHotelPassword())){
			onlineHotelAccount.add(account.getHotelID());
			return true;
		}
		return false;
	}
	
	/**-------------------------------------�ָ���-------------------------------------**/
	/*****************************************************
	 * public boolean userLogin(userAccountPO account)
	 * @Description �ͻ���¼
	 * @param account �ͻ��˴�����userAccountPO
	 *****************************************************/
	public boolean userLogin(userAccountPO account) throws RemoteException{
		if(findID("user",account.getUserID(),account.getUserPassword())){
			onlineUserAccount.add(account.getUserID());
			return true;
		}
		return false;
	}
	
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	
	
	/*====================================================
	 	 			������ע���ʵ�ַ���
	 ====================================================*/
	
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	//�Ƶ�ע��ķ������˺ű�����ʵ�֣����ݱ���ʵ��
	
	public boolean hotelSignUp(hotelAccountPO account, hotelDataPO data) throws RemoteException {
		boolean result = true;
		File f = new File("./file/account/hotelAccount.txt");
		
		try {
			
			String str = "";
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String [] s = str.split("     ");
				if(s[0].equals(account.getHotelID())){
					result = false;
					break;
				}
				
			}
			
			if(result){
				FileWriter fw = new FileWriter(f,true);
				fw.write("\r\n"+account.getHotelID()+"     " 
						+ account.getHotelPassword());
				fw.flush();
				fw.close();
				basicDataService obj = new basicDataServiceImpl();
				obj.modifyHotelData(account.getHotelID(),data);
				MainService.block.append(account.getHotelID()+" ע��ɹ�+\n");
				initHotelSignUp(account.getHotelID()); //��ʼ���˺�
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**-------------------------------------�ָ���-------------------------------------**/

	//�û�ע��ķ������˺ű�����ʵ�֣����ݱ���ʵ��
	
	
	public boolean userSignUp(userAccountPO account, userDataPO data) throws RemoteException {
		boolean result = true;
		File f = new File("./file/account/userAccount.txt");
		try {
			String str = "";
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String [] s = str.split("     ");
				if(s[0].equals(account.getUserID())){
					result = false;
					break;
				}
				
			}
			
			if(result){
				FileWriter fw = new FileWriter(f,true);
				fw.write("\r\n"+account.getUserID()+"     " 
						+ account.getUserPassword());
				fw.flush();
				fw.close();
				basicDataService obj = new basicDataServiceImpl();
				obj.modifyUserData(account.getUserID(),data);
				MainService.block.append(account.getUserID()+" ע��ɹ�+\n");
				initHotelSignUp(account.getUserID()); //��ʼ���˺�
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	


	/**-------------------------------------�ָ���-------------------------------------**/
	
	/*====================================================
			�����Ǽ���Ƿ�����˺ŵ�ʵ�ַ���
	====================================================*/
	
	/*****************************************************
	 * public boolean checkHotelIdExist(String ID)
	 * @Description ������ݿ�������˺��Ƿ���ڴ�ID
	 * @param ID �ͻ��˴���hotelID
	 *****************************************************/
	
	public boolean checkHotelIdExist(String ID) throws RemoteException {
		boolean result = true;
		String str = "";
		File f = new File("./file/account/hotelAccount.txt");
		try {
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String [] s = str.split("     ");
				if(s[0].equals(ID)){
					result = false;
					break;
				}
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
/**-------------------------------------�ָ���-------------------------------------**/

	/*****************************************************
	 * public boolean checkUserIdExist(String ID)
	 * @Description ������ݿ�������û��˺��Ƿ���ڴ�ID
	 * @param ID �ͻ��˴���userID
	 *****************************************************/
	
	public boolean checkUserIdExist(String ID) throws RemoteException {
		boolean result = true;
		String str = "";
		File f = new File("./file/account/userAccount.txt");
		try {
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String [] s = str.split("     ");
				if(s[0].equals(ID)){
					result = false;
					break;
				}
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	

	
	
	/**-------------------------------------�ָ���-------------------------------------**/

	/*****************************************************
	 * public boolean checkHotelNameExist(String hotelName)
	 * @Description ������ݿ�����ľƵ��Ƿ���ڴ�����
	 * @param hotelName �ͻ��˴���hotelName
	 *****************************************************/
	public boolean checkHotelNameExist(String hotelName) throws RemoteException {
		boolean result = true;
		String str = "";
		File f = new File("./file/basicData/hotelData.txt");
		try {
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String [] s = str.split("     ");
				if(s[1].equals(hotelName)){
					result = false;
					break;
				}
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**-------------------------------------�ָ���-------------------------------------**/

	/*====================================================
			������ע���˺ŵ�ʵ�ַ���
	====================================================*/
	
	
	/*****************************************************
	 * public boolean hotelLogout(String hotelID)
	 * @Description �Ƶ�ע��
	 * @param hotelID �ͻ��˴�����hotelID
	 *****************************************************/
	
	public boolean hotelLogout(String hotelID) throws RemoteException {
		for(int i = 0;i < onlineHotelAccount.size();i++){
			if(onlineHotelAccount.get(i).equals(hotelID)){
				onlineHotelAccount.remove(i);
				System.out.println(hotelID+" ������");
				MainService.block.append(hotelID+" ������+\n");
				return true;
			}
		}
		
		return false;
	}


	
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	
	/*****************************************************
	 * private boolean findID(String type,String ID,String password)
	 * @Description ͨ�õĵ�¼��ѯ�û�����
	 *****************************************************/
	
	private boolean findID(String type,String ID,String password){
		boolean result = false;
		String str = "";
		File f = new File("./file/account/"+type +"Account.txt");
		
		try {
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String [] s = str.split("     ");
				if(s[0].equals(ID)&&
						s[1].equals(password)){
					MainService.block.append("ID:"+ID+" �Ѿ�����\n");
					System.out.println("ID:"+ID+" �Ѿ�����");
					result = true;
					break;
				}
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;
	}
	
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	/*****************************************************
	 * private void initSignUp()
	 * @Description ע���˺ų�ʼ��
	 *****************************************************/
	
	private void initHotelSignUp(String ID){
		File  fPromotion = new File("./file/hotelPromotion/"+ID+".txt");//��������promotion���ļ�
		if(!fPromotion.exists()){
			try {
				fPromotion.createNewFile();
				FileWriter fw = new FileWriter(fPromotion,true);
				fw.write("#���     �Ƿ�����     ������     �Ƿ����     �ۿ���     ��������");
				fw.flush();
				fw.close();
				MainService.block.append(ID+"�����Ż������ĵ��ɹ�+\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**-------------------------------------�ָ���-------------------------------------**/
	/*****************************************************
	 * public boolean networkManageLogin(networkManageAccountPO account)throws RemoteException
	 * @Description ��վ�����¼
	 * @param account ��վ����˴�����networkManagerAccountPO
	 *****************************************************/
	
	public boolean networkManageLogin(networkManageAccountPO account)throws RemoteException{
		if(findID("networkManager",account.getNetworkManageID(),account.getNetworkManagePassword())){
			onlineHotelAccount.add(account.getNetworkManageID());
			return true;
		}
		return false;
	}
	
	
	
}
