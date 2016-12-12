package RMISERVICE;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import service.accountService;
import service.basicDataService;
import service.commentService;
import service.dataService;
import service.orderService;
import service.promotionService;

import serviceImpl.accountServiceImpl;
import serviceImpl.basicDataServiceImpl;
import serviceImpl.commentServiceImpl;
import serviceImpl.dataServiceImpl;
import serviceImpl.orderServiceImpl;
import serviceImpl.promotionServiceImpl;

/**************************************************************
 * RMIServer
 * 
 * @author 蒋健聪 151250070 
 * @Description 服务器端从这里开始。启动RMI后初始化所有数据，此类已完成。
 * 
 *************************************************************/


public class MainService {
	
	public static final int PORT = 8888;
	
	MainService(){
		try {
			LocateRegistry.createRegistry(PORT);
		} catch (RemoteException e) {
			block.append(" 创建远程对象异常！请查看是否已经开启服务器\n");
			JOptionPane.showMessageDialog(null," 创建远程对象异常！\n请查看是否已经开启服务器");
			System.exit(0);
			e.printStackTrace();
		}
		
	}
	
	void open(){
		try {
			
			/*====================================
		 		创建远程对象
		 =====================================*/
		
		dataService dataServer = new dataServiceImpl();
		
		commentService commentServer = new commentServiceImpl();
		
		orderService orderServer = new orderServiceImpl();
		
		promotionService promotionServer = new promotionServiceImpl();
		
		accountService accountServer = new accountServiceImpl();
		
		basicDataService basicDataServer = new basicDataServiceImpl();
		
		/*====================================
 				注册实例
 		=====================================*/
		
		
		// 把远程对象注册到RMI注册服务器上，并命名
		// 绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略）
		

			Naming.bind("rmi://localhost:"+PORT+"/dataServer", dataServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/commentServer", commentServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/orderServer", orderServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/promotionServer", promotionServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/accountServer", accountServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/basicDataServer", basicDataServer);
			
			System.out.println(">>>>>>INFO: 远程对象绑定成功！");
		} catch (MalformedURLException | RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	void close(){
		try {
			Naming.unbind("rmi://localhost:"+PORT+"/dataServer");
			
			Naming.unbind("rmi://localhost:"+PORT+"/commentServer");
			
			Naming.unbind("rmi://localhost:"+PORT+"/orderServer");
			
			Naming.unbind("rmi://localhost:"+PORT+"/promotionServer");
			
			Naming.unbind("rmi://localhost:"+PORT+"/accountServer"); 
			
			Naming.unbind("rmi://localhost:"+PORT+"/basicDataServer");
			
			System.out.println(">>>>>INFO: 远程对象解绑成功！");
			
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static String[] buttonHint = {"点击开启服务器", "点击关闭服务器"};
	public static JTextArea block= new JTextArea();
	private static JScrollPane jScrollPane2 = new JScrollPane(block);;
	/**************************************************************
	 * public static void main(String args[])
	 * 
	 * @Description 启动服务器端，先启动RMI再初始化数据
	 * 
	 *************************************************************/
	public static void main(String args[]) {
		final MainService rmi = new MainService();
		final JButton button = new JButton(buttonHint[0]);
		JFrame frame = new JFrame("Server Controller");
		JPanel panel = new JPanel();
		
		
		setTextArea(panel);
		
		frame.setResizable(true);
		frame.setSize(500, 300); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		frame.add(panel,BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH); 
		frame.setVisible(true); 
		
		button.addActionListener(new ActionListener() {
			boolean isOpen = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isOpen) {
					isOpen = !isOpen;
					block.append("开启服务器成功\n");
					button.setText(buttonHint[1]);
					rmi.open();
				} else {
					isOpen = !isOpen;
					block.append("关闭服务器成功\n");
					button.setText(buttonHint[0]);
					rmi.close();
				}
			}
		});
		
	}
	
	static void setTextArea(JPanel panel){
		block.setEditable(false);
        block.setColumns(25);
        block.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        block.setLineWrap(true);
        block.setRows(9);
        jScrollPane2.setViewportView(block);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(jScrollPane2);
        
        
	}
	
	
	
}
