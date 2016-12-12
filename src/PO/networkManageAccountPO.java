package PO;

import java.io.Serializable;

/*******************************************
 * 
 * @author 贾莛    151250068
 * @Description 注册登录用的hotel的账号的PO类
 * @implements Serializable
 * 
 ******************************************/

public class networkManageAccountPO implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7784573623949624332L;
	public  networkManageAccountPO(){
		
	}
	

	/***********************************
	 * @Dsecription 设置网站管理ID和得到网站管理ID
	 * @param ID 网站管理ID
	 **********************************/
	
	public void setNetworkManageID(String ID){
		this.networkManageID=ID;
	}
	public String getNetworkManageID(){
		return this.networkManageID;
	}
	
	/***********************************
	 * @Dsecription 设置网络管理密码和得到网络管理密码
	 * @param password 网络管理密码
	 **********************************/
	public void setNetworkManagePassword(String password){
		this.networkManagePassword=password;
	}
	
	public String getNetworkManagePassword(){
		return this.networkManagePassword;
	}
	
	
	private String networkManageID;
	private String networkManagePassword;
}
