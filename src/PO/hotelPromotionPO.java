package PO;

import java.io.Serializable;

/*******************************************
 * 
 * @author 蒋健聪 151250070
 * @Description 酒店优惠策略的PO类
 * @implements Serializable
 * 
 ******************************************/


public class hotelPromotionPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3009437407497740036L;
	
	private String serial;
	private boolean isFullCut;
	private boolean isDiscount;
	private int moneyCut = 100;
	private double discount = 10;
	private String promotionDescription;
	
	public hotelPromotionPO(){
		
	}
	/************************************************
	 * @Dsecription 设置优惠政策编号和返回优惠政策编号
	 * @param des 是否为满减优惠
	 ************************************************/
	public void setSerial(String serial){
		this.serial = serial;
	}
	
	public String getSerial(){
		return this.serial;
	}
	
	
	
	/************************************************
	 * @Dsecription 设置优惠政策描述和返回优惠政策描述
	 * @param des 是否为满减优惠
	 ************************************************/
	public void setPrmotionDescription(String des){
		this.promotionDescription = des;
	}
	
	public String getPromotionDescription(){
		return this.promotionDescription;
	}
	
	
	
	/************************************************
	 * @Dsecription 设置是否满减优惠和得到是否满减优惠
	 * @param isFullCut 是否为满减优惠
	 ************************************************/
	
	public void setIsFullCut(boolean isFullCut){
		this.isFullCut = isFullCut;
	}
	
	public boolean getIsFullCut(){
		return this.isFullCut;
	}
	
	
	/***************************************************
	 * @Dsecription 设置是否为打折优惠和得到是否为打折优惠
	 * @param isDiscount 是否为打折优惠
	 ***************************************************/
	public void setIsDiscount(boolean isDiscount){
		this.isDiscount = isDiscount;
	}
	
	public boolean getIsDiscount(){
		return this.isDiscount;
	}
	/***************************************************
	 * @Dsecription 设置满100减的钱数和得到满100减的钱数
	 * @param moneyCut 满100减的钱数
	 ***************************************************/
	public void setMoneyCut(int moneyCut){
		this.moneyCut = moneyCut;
	}
	
	public int getMoneyCut(){
		return this.moneyCut;
	}
	
	/***************************************************
	 * @Dsecription 设置折扣和得到折扣
	 * @param discount 折扣
	 ***************************************************/
	
	public void setDiscount(double discount){
		this.discount = discount;
	}
	
	public double getdiscount(){
		return this.discount;
	}
	
	
}