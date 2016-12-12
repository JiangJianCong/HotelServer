package serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.commentService;

public class commentServiceImpl extends UnicastRemoteObject implements commentService {

	public commentServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 序列化时为解决兼容性问题生成的预设值
	 */
	private static final long serialVersionUID = -4792545651999369827L;
	
	
	/*=====================================================
	以下是接口实现的方法
	=======================================================*/

}
