package serviceImpl;

/**************************************************************
 * accountServiceImpl
 * @author 蒋健聪 151250070 
 * @Description 酒店促销策略的功能实现方法类，此类已完成
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
							以下是接口实现的方法
	=============================================================================*/
	
	/**-------------------------------------分割线-------------------------------------**/
	
	
	/*****************************************************************************
	 * public ArrayList<hotelPromotionPO> getHotelPromotion(String ID)
	 * @Description 得到酒店的促销策略
	 * @param ID 客户端传来的酒店的ID
	 *****************************************************************************/
	
	public ArrayList<hotelPromotionPO> getHotelPromotion(String ID) throws RemoteException {
		ArrayList<hotelPromotionPO> result= new ArrayList<hotelPromotionPO>();
		File f = new File("./file/hotelPromotion/"+ID+".txt");
		String str = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			while((str = bf.readLine())!=null){
				if(str.charAt(0)!='#'){
					String[] s = str.split("     ");  //数组s存ID(0),HotelName(1),hotelTel(2),Introduction(3)
					hotelPromotionPO promo = new hotelPromotionPO();
					promo.setSerial(s[0]);
					promo.setIsFullCut(s[1].equals("是"));
					promo.setMoneyCut(Integer.parseInt(s[2]));
					promo.setIsDiscount(s[3].equals("是"));
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
		
		
		
		MainService.block.append(ID+" 查询promotion成功\n");
		
		return result;
	}
	
	
	
	
	/**-------------------------------------分割线-------------------------------------**/

	
	
	/*****************************************************************************
	 * public boolean AddNewPromotion(String ID,hotelPromotionPO promotion)
	 * @Description 新增一个新的促销策略
	 * @param ID 客户端传来的酒店的ID
	 * @param promotion 客户端传来的hotelPromotionPO类
	 *****************************************************************************/
	
	public boolean AddNewPromotion(String ID,hotelPromotionPO promotion) throws RemoteException {
		File f = new File("./file/hotelPromotion/"+ID+".txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f,true);
			
			BufferedReader bf = new BufferedReader(new FileReader(f));
			String str = "";
			while((str = bf.readLine())!=null){     //优惠政策超过3个直接返回false，不能添加
				String [] s = str.split("     ");
				if(s[0].equals("3")){
					return false;
				}
			}
			bf.close();
			fw.write("\r\n"+promotion.getSerial()+"     ");
			
			if(promotion.getIsFullCut())
				fw.write("是     " + promotion.getMoneyCut()+"     ");
			else
				fw.write("否     " + promotion.getMoneyCut()+"     ");
			
			if(promotion.getIsDiscount())
				fw.write("是     " + promotion.getdiscount() + "     ");
			else
				fw.write("否     " + promotion.getdiscount() + "     ");
			
			fw.write(promotion.getPromotionDescription());
			
			
			fw.flush();
			fw.close();
			MainService.block.append("酒店ID: " + ID + "新增编号"+ promotion.getSerial()+"优惠政策成功\n");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	/**-------------------------------------分割线-------------------------------------**/
	
	
	/*****************************************************************************
	 * public boolean modifyHotelPromotion(String ID,hotelPromotionPO promotion)
	 * @Description 修改一个的促销策略
	 * @param ID 客户端传来的酒店的ID
	 * @param promotion 客户端传来的hotelPromotionPO类
	 *****************************************************************************/
	public boolean modifyHotelPromotion(String ID,hotelPromotionPO promotion) throws RemoteException {
		boolean existThisPromotion = false;
		ArrayList<String[]> savePromotion = new ArrayList<String[]>();
		
		File f = new File("./file/hotelPromotion/"+ID+".txt");
			try {
				
				BufferedReader bf = new BufferedReader(new FileReader(f));
				String str = "";
				while((str = bf.readLine())!=null){     //优惠政策超过3个直接返回false，不能添加
					String [] s = str.split("     ");
					if(s[0].equals(promotion.getSerial())){
						if(promotion.getIsFullCut()){
							s[1] = "是"; 
						}else{
							s[1] = "否";
						}
						s[2] = Integer.toString(promotion.getMoneyCut());
						if(promotion.getIsDiscount()){
							s[3] = "是"; 
						}else{
							s[3] = "否";
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
					bw.write("#编号     是否满减     满减数     是否打折     折扣数     政策描述");
					for(int i = 1; i < savePromotion.size(); i++){
						bw.write("\r\n" + savePromotion.get(i)[0]);
						for(int j = 1; j < savePromotion.get(i).length;j++){
							bw.write("     " + savePromotion.get(i)[j]);
						}
					}
				
					bw.flush();
					bw.close();
					MainService.block.append("酒店ID: " + ID + "修改优惠政策"+promotion.getSerial()+"成功\n");
					return true;
				}else{
					MainService.block.append("酒店ID: " + ID + "修改优惠政策"+promotion.getSerial()+"失败,原因:还没这个优惠政策\n");
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

	/**-------------------------------------分割线-------------------------------------**/

	/*****************************************************************************
	 * public boolean delNewPromotion(String ID, hotelPromotionPO promotion)
	 * @Description 删除一个的促销策略
	 * @param ID 客户端传来的酒店的ID
	 * @param promotion 客户端传来的hotelPromotionPO类
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
					MainService.block.append("酒店ID: " + ID + "删除优惠政策"+promotion.getSerial()+"成功\n");
				}	
			}
			
			bf.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("#编号     是否满减     满减数     是否打折     折扣数     政策描述");
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
	
	/**-------------------------------------分割线-------------------------------------**/
	
	

}
