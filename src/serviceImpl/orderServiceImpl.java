package serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.orderService;

public class orderServiceImpl extends UnicastRemoteObject implements orderService {

	 public orderServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 序列化时为解决兼容性问题生成的预设值
	 */
	private static final long serialVersionUID = 785760283820106876L;
	
	
	
	/*=====================================================
	以下是接口实现的方法
	=======================================================*/
	
}
