package serviceImpl;

/**************************************************************
 * accountServiceImpl
 * @author ������ 151250070 
 * @Description �Ƶ�������ԵĹ���ʵ�ַ����࣬���������
 *************************************************************/
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
import PO.hotelPromotionPO;
import RMISERVICE.MainService;
import service.promotionService;

public class promotionServiceImpl extends UnicastRemoteObject implements promotionService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6357343275990472482L;


	public promotionServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/*===========================================================================
							�����ǽӿ�ʵ�ֵķ���
	=============================================================================*/
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	
	/*****************************************************************************
	 * public ArrayList<hotelPromotionPO> getHotelPromotion(String ID)
	 * @Description �õ��Ƶ�Ĵ�������
	 * @param ID �ͻ��˴����ľƵ��ID
	 *****************************************************************************/
	
	public ArrayList<hotelPromotionPO> getHotelPromotion(String ID) throws RemoteException {
		ArrayList<hotelPromotionPO> result= new ArrayList<hotelPromotionPO>();
		File f = new File("./file/hotelPromotion/"+ID+".txt");
		String str = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				if(str.charAt(0)!='#'){
					String[] s = str.split("     ");  //����s��ID(0),HotelName(1),hotelTel(2),Introduction(3)
					hotelPromotionPO promo = new hotelPromotionPO();
					promo.setSerial(s[0]);
					promo.setIsFullCut(s[1].equals("��"));
					promo.setMoneyCut(Integer.parseInt(s[2]));
					promo.setIsDiscount(s[3].equals("��"));
					promo.setDiscount(Double.parseDouble(s[4]));
					promo.setPrmotionDescription(s[5]);
					result.add(promo);
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
		
		
		
		MainService.block.append(ID+" ��ѯpromotion�ɹ�\n");
		
		return result;
	}
	
	
	
	
	/**-------------------------------------�ָ���-------------------------------------**/

	
	
	/*****************************************************************************
	 * public boolean AddNewPromotion(String ID,hotelPromotionPO promotion)
	 * @Description ����һ���µĴ�������
	 * @param ID �ͻ��˴����ľƵ��ID
	 * @param promotion �ͻ��˴�����hotelPromotionPO��
	 *****************************************************************************/
	
	public boolean AddNewPromotion(String ID,hotelPromotionPO promotion) throws RemoteException {
		File f = new File("./file/hotelPromotion/"+ID+".txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f,true);
			
			BufferedReader bf = new BufferedReader(new FileReader(f));
			String str = "";
			while((str = bf.readLine())!=null){     //�Ż����߳���3��ֱ�ӷ���false���������
				String [] s = str.split("     ");
				if(s[0].equals("3")){
					return false;
				}
			}
			bf.close();
			fw.write("\r\n"+promotion.getSerial()+"     ");
			
			if(promotion.getIsFullCut())
				fw.write("��     " + promotion.getMoneyCut()+"     ");
			else
				fw.write("��     " + promotion.getMoneyCut()+"     ");
			
			if(promotion.getIsDiscount())
				fw.write("��     " + promotion.getdiscount() + "     ");
			else
				fw.write("��     " + promotion.getdiscount() + "     ");
			
			fw.write(promotion.getPromotionDescription());
			
			
			fw.flush();
			fw.close();
			MainService.block.append("�Ƶ�ID: " + ID + "�������"+ promotion.getSerial()+"�Ż����߳ɹ�\n");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	
	/*****************************************************************************
	 * public boolean modifyHotelPromotion(String ID,hotelPromotionPO promotion)
	 * @Description �޸�һ���Ĵ�������
	 * @param ID �ͻ��˴����ľƵ��ID
	 * @param promotion �ͻ��˴�����hotelPromotionPO��
	 *****************************************************************************/
	public boolean modifyHotelPromotion(String ID,hotelPromotionPO promotion) throws RemoteException {
		boolean existThisPromotion = false;
		ArrayList<String[]> savePromotion = new ArrayList<String[]>();
		
		File f = new File("./file/hotelPromotion/"+ID+".txt");
			try {
				
				BufferedReader bf = new BufferedReader(new FileReader(f));
				String str = "";
				while((str = bf.readLine())!=null){     //�Ż����߳���3��ֱ�ӷ���false���������
					String [] s = str.split("     ");
					if(s[0].equals(promotion.getSerial())){
						if(promotion.getIsFullCut()){
							s[1] = "��"; 
						}else{
							s[1] = "��";
						}
						s[2] = Integer.toString(promotion.getMoneyCut());
						if(promotion.getIsDiscount()){
							s[3] = "��"; 
						}else{
							s[3] = "��";
						}
						s[4] = Double.toString(promotion.getdiscount());
						s[5] = promotion.getPromotionDescription();
						existThisPromotion = true;
					}
					
					savePromotion.add(s);
					
				}
				bf.close();
				if(existThisPromotion){
					BufferedWriter bw = new BufferedWriter(new FileWriter(f));
					bw.write("#���     �Ƿ�����     ������     �Ƿ����     �ۿ���     ��������");
					for(int i = 1; i < savePromotion.size(); i++){
						bw.write("\r\n" + savePromotion.get(i)[0]);
						for(int j = 1; j < savePromotion.get(i).length;j++){
							bw.write("     " + savePromotion.get(i)[j]);
						}
					}
				
					bw.flush();
					bw.close();
					MainService.block.append("�Ƶ�ID: " + ID + "�޸��Ż�����"+promotion.getSerial()+"�ɹ�\n");
					return true;
				}else{
					MainService.block.append("�Ƶ�ID: " + ID + "�޸��Ż�����"+promotion.getSerial()+"ʧ��,ԭ��:��û����Ż�����\n");
					return false;
				}	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return existThisPromotion;
		
	}

	/**-------------------------------------�ָ���-------------------------------------**/

	/*****************************************************************************
	 * public boolean delNewPromotion(String ID, hotelPromotionPO promotion)
	 * @Description ɾ��һ���Ĵ�������
	 * @param ID �ͻ��˴����ľƵ��ID
	 * @param promotion �ͻ��˴�����hotelPromotionPO��
	 *****************************************************************************/
	public boolean delNewPromotion(String ID, hotelPromotionPO promotion) throws RemoteException {
		ArrayList<String[]> savePromotion = new ArrayList<String[]>();
		
		File f = new File("./file/hotelPromotion/"+ID+".txt");
		
		String str = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){     
				String [] s = str.split("     ");
				if(!s[0].equals(promotion.getSerial())){
					savePromotion.add(s);
				}else{
					MainService.block.append("�Ƶ�ID: " + ID + "ɾ���Ż�����"+promotion.getSerial()+"�ɹ�\n");
				}	
			}
			
			bf.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("#���     �Ƿ�����     ������     �Ƿ����     �ۿ���     ��������");
			for(int i = 1; i < savePromotion.size(); i++){
				bw.write("\r\n" + savePromotion.get(i)[0]);
				for(int j = 1; j < savePromotion.get(i).length;j++){
					bw.write("     " + savePromotion.get(i)[j]);
				}
			}
		
			bw.flush();
			bw.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**-------------------------------------�ָ���-------------------------------------**/
	
	

}
