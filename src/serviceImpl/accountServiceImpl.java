package serviceImpl;

/**************************************************************
 * accountServiceImpl
 * @author 蒋健聪 151250070 
 * @Description 服务器实现账号注册登录功能
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
	 * 序列化时为解决兼容性问题生成的预设值
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
					以下是接口实现的方法
	=======================================================*/
	
	
	
	
	
	/*====================================================
	 				以下是登录的实现方法
	 ====================================================*/
	
	/**-------------------------------------分割线-------------------------------------**/
	
	/*****************************************************
	 * public boolean hotelLogin(hotelAccountPO account)
	 * @Description 酒店登录
	 * @param account 客户端传来的hotelAccountPO
	 *****************************************************/
	public boolean hotelLogin(hotelAccountPO account) throws RemoteException {
		if(findID("hotel",account.getHotelID(),account.getHotelPassword())){
			onlineHotelAccount.add(account.getHotelID());
			return true;
		}
		return false;
	}
	
	/**-------------------------------------分割线-------------------------------------**/
	/*****************************************************
	 * public boolean userLogin(userAccountPO account)
	 * @Description 客户登录
	 * @param account 客户端传来的userAccountPO
	 *****************************************************/
	public boolean userLogin(userAccountPO account) throws RemoteException{
		if(findID("user",account.getUserID(),account.getUserPassword())){
			onlineUserAccount.add(account.getUserID());
			return true;
		}
		return false;
	}
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	
	
	/*====================================================
	 	 			以下是注册的实现方法
	 ====================================================*/
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	//酒店注册的方法，账号保存已实现，数据保存实现
	
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
				MainService.block.append(account.getHotelID()+" 注册成功+\n");
				initHotelSignUp(account.getHotelID()); //初始化账号
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
	/**-------------------------------------分割线-------------------------------------**/

	//用户注册的方法，账号保存已实现，数据保存实现
	
	
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
				MainService.block.append(account.getUserID()+" 注册成功+\n");
				initHotelSignUp(account.getUserID()); //初始化账号
				
				
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
	


	/**-------------------------------------分割线-------------------------------------**/
	
	/*====================================================
			以下是检查是否存在账号的实现方法
	====================================================*/
	
	/*****************************************************
	 * public boolean checkHotelIdExist(String ID)
	 * @Description 检查数据库里面的账号是否存在此ID
	 * @param ID 客户端传来hotelID
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
	
/**-------------------------------------分割线-------------------------------------**/

	/*****************************************************
	 * public boolean checkUserIdExist(String ID)
	 * @Description 检查数据库里面的用户账号是否存在此ID
	 * @param ID 客户端传来userID
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
	
	

	
	
	/**-------------------------------------分割线-------------------------------------**/

	/*****************************************************
	 * public boolean checkHotelNameExist(String hotelName)
	 * @Description 检查数据库里面的酒店是否存在此名字
	 * @param hotelName 客户端传来hotelName
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
	
	
	
	/**-------------------------------------分割线-------------------------------------**/

	/*====================================================
			以下是注销账号的实现方法
	====================================================*/
	
	
	/*****************************************************
	 * public boolean hotelLogout(String hotelID)
	 * @Description 酒店注销
	 * @param hotelID 客户端传来的hotelID
	 *****************************************************/
	
	public boolean hotelLogout(String hotelID) throws RemoteException {
		for(int i = 0;i < onlineHotelAccount.size();i++){
			if(onlineHotelAccount.get(i).equals(hotelID)){
				onlineHotelAccount.remove(i);
				System.out.println(hotelID+" 下线了");
				MainService.block.append(hotelID+" 下线了+\n");
				return true;
			}
		}
		
		return false;
	}


	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	
	/*****************************************************
	 * private boolean findID(String type,String ID,String password)
	 * @Description 通用的登录查询用户密码
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
					MainService.block.append("ID:"+ID+" 已经上线\n");
					System.out.println("ID:"+ID+" 已经上线");
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
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	/*****************************************************
	 * private void initSignUp()
	 * @Description 注册账号初始化
	 *****************************************************/
	
	private void initHotelSignUp(String ID){
		File  fPromotion = new File("./file/hotelPromotion/"+ID+".txt");//创建保存promotion的文件
		if(!fPromotion.exists()){
			try {
				fPromotion.createNewFile();
				FileWriter fw = new FileWriter(fPromotion,true);
				fw.write("#编号     是否满减     满减数     是否打折     折扣数     政策描述");
				fw.flush();
				fw.close();
				MainService.block.append(ID+"建立优惠政策文档成功+\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**-------------------------------------分割线-------------------------------------**/
	/*****************************************************
	 * public boolean networkManageLogin(networkManageAccountPO account)throws RemoteException
	 * @Description 网站管理登录
	 * @param account 网站管理端传来的networkManagerAccountPO
	 *****************************************************/
	
	public boolean networkManageLogin(networkManageAccountPO account)throws RemoteException{
		if(findID("networkManager",account.getNetworkManageID(),account.getNetworkManagePassword())){
			onlineHotelAccount.add(account.getNetworkManageID());
			return true;
		}
		return false;
	}
	
	
	
}
