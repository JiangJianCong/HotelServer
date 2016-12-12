package serviceImpl;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import PO.hotelDataPO;
import service.basicDataService;
import PO.userDataPO;

/**************************************************************
 * accountServiceImpl
 * @author 蒋健聪 151250070 
 * @Description 服务器实现基本信息读写功能
 *************************************************************/

public class basicDataServiceImpl extends UnicastRemoteObject implements basicDataService{

	public basicDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6389249290859124579L;
	private BufferedReader bf;

	
	
	/*===========================================================================
								以下是接口实现的方法
	=============================================================================*/
	
	
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	/*****************************************************
	 * public void modifyHotelData(String ID, hotelDataPO data)
	 * @Description 修改酒店信息
	 * @param account 客户端传来的hotelAccountPO
	 *****************************************************/
	
	public void modifyHotelData(String ID, hotelDataPO data) throws RemoteException {
		boolean haveID = false;
		ArrayList<String[]> hotelList = new ArrayList<String[]>(); 
		File f = new File("./file/basicData/hotelData.txt");
		try {
			String str = "";
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String[] s = str.split("     ");  //数组s存ID(0),HotelName(1),hotelTel(2),Introduction(3)
				hotelList.add(s);
			}
			bf.close();
			
			int index = 0;
			while(index < hotelList.size()){
				
				if(hotelList.get(index)[0].equals(ID)){
					hotelList.get(index)[1] = data.getHotelName();
					hotelList.get(index)[2] = data.getHotelTel();
					hotelList.get(index)[3] = data.getHotelIntroduction();
					haveID = true;
					break;
				}
				index++;
			}
			if(!haveID){   //没有这个ID，新建
				String[] s = new String[4];
				s[0] = ID;
				s[1] = data.getHotelName();
				s[2] = data.getHotelTel();
				s[3] = data.getHotelIntroduction();
				hotelList.add(s);		
			}
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(hotelList.get(0)[0] + "     " + hotelList.get(0)[1] + "     " 
					+ hotelList.get(0)[2] + "     " + hotelList.get(0)[3]);
			for(int i=1;i<hotelList.size();i++){
				bw.write("\r\n" + hotelList.get(i)[0] + "     " + hotelList.get(i)[1] + "     " 
						+ hotelList.get(i)[2] + "     " + hotelList.get(i)[3]);
			
			}
			bw.flush();
			bw.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	
	/*****************************************************
	 * public hotelDataPO getHotelData(String ID)
	 * @Description 得到酒店的信息
	 * @param ID 客户端传来的酒店的ID
	 *****************************************************/
	
	public hotelDataPO getHotelData(String ID) throws RemoteException {
		hotelDataPO result = new hotelDataPO();
		File f = new File("./file/basicData/hotelData.txt");
		String str = "";
		try {
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
			String[] s = str.split("     ");  //数组s存ID(0),HotelName(1),hotelTel(2),Introduction(3)
			if(s[0].equals(ID)){
				result.setHotelID(s[0]);
				result.setHotelName(s[1]);
				result.setHotelTel(s[2]);
				result.setHotelIntroduction(s[3]);
				break;
			}
			
			
		}
		bf.close();
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
	 * public void modifyUserData(String ID, userDataPO data)
	 * @Description 修改用户信息
	 * @param account 客户端传来的userAccountPO
	 *****************************************************/
	
	public void modifyUserData(String ID, userDataPO data) throws RemoteException {
		boolean haveID = false;
		ArrayList<String[]> userList = new ArrayList<String[]>(); 
		File f = new File("./file/basicData/userData.txt");
		try {
			String str = "";
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				String[] s = str.split("     ");  //数组s存ID(0),userName(1),userTel(2),userCredit(3),userSex(4)
				userList.add(s);
			}
			bf.close();
			
			int index = 0;
			while(index < userList.size()){
				
				if(userList.get(index)[0].equals(ID)){
					userList.get(index)[1] = data.getUserName();
					userList.get(index)[2] = data.getUserTel();
					userList.get(index)[3] = String.valueOf(data.getUserCredit());
					userList.get(index)[4] = data.getUserSex();
					haveID = true;
					break;
				}
				index++;
			}
			if(!haveID){   //没有这个ID，新建
				String[] s = new String[5];
				s[0] = ID;
				s[1] = data.getUserName();
				s[2] = data.getUserTel();
				s[3] = String.valueOf(data.getUserCredit());
				s[4] = data.getUserSex();
				userList.add(s);		
			}
			
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(userList.get(0)[0] + "     " + userList.get(0)[1] + "     " 
					+ userList.get(0)[2] + "     " + userList.get(0)[3] + "     "
					+ userList.get(0)[4]);
			for(int i=1;i<userList.size();i++){
				bw.write("\r\n" + userList.get(i)[0] + "     " + userList.get(i)[1] + "     " 
						+ userList.get(i)[2] + "     " + userList.get(i)[3] + "     "
						+ userList.get(i)[4]);
			
			}
			bw.flush();
			bw.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
/**-------------------------------------分割线-------------------------------------**/
	
	
	/*****************************************************
	 * public userDataPO getUserData(String ID)
	 * @Description 得到用户的信息
	 * @param ID 客户端传来的用户的ID
	 *****************************************************/
	
	public userDataPO getUserData(String ID) throws RemoteException {
		userDataPO result = new userDataPO();
		File f = new File("./file/basicData/userData.txt");
		String str = "";
		try {
			bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
			String[] s = str.split("     ");  //数组s存ID(0),userName(1),userTel(2),userCredit(3),userSex(4)
			if(s[0].equals(ID)){
				result.setUserID(s[0]);
				result.setUserName(s[1]);
				result.setUserTel(s[2]);
				result.setUserCredit(Integer.valueOf(s[3]));
				result.setUserSex(s[4]);
				break;
			}
			
			
		}
		bf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
