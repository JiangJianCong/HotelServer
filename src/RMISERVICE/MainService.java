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
 * @author ������ 151250070 
 * @Description �������˴����￪ʼ������RMI���ʼ���������ݣ���������ɡ�
 * 
 *************************************************************/


public class MainService {
	
	public static final int PORT = 8888;
	
	MainService(){
		try {
			LocateRegistry.createRegistry(PORT);
		} catch (RemoteException e) {
			block.append(" ����Զ�̶����쳣����鿴�Ƿ��Ѿ�����������\n");
			JOptionPane.showMessageDialog(null," ����Զ�̶����쳣��\n��鿴�Ƿ��Ѿ�����������");
			System.exit(0);
			e.printStackTrace();
		}
		
	}
	
	void open(){
		try {
			
			/*====================================
		 		����Զ�̶���
		 =====================================*/
		
		dataService dataServer = new dataServiceImpl();
		
		commentService commentServer = new commentServiceImpl();
		
		orderService orderServer = new orderServiceImpl();
		
		promotionService promotionServer = new promotionServiceImpl();
		
		accountService accountServer = new accountServiceImpl();
		
		basicDataService basicDataServer = new basicDataServiceImpl();
		
		/*====================================
 				ע��ʵ��
 		=====================================*/
		
		
		// ��Զ�̶���ע�ᵽRMIע��������ϣ�������
		// �󶨵�URL��׼��ʽΪ��rmi://host:port/name(����Э��������ʡ�ԣ�
		

			Naming.bind("rmi://localhost:"+PORT+"/dataServer", dataServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/commentServer", commentServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/orderServer", orderServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/promotionServer", promotionServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/accountServer", accountServer);
			
			Naming.bind("rmi://localhost:"+PORT+"/basicDataServer", basicDataServer);
			
			System.out.println(">>>>>>INFO: Զ�̶���󶨳ɹ���");
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
			
			System.out.println(">>>>>INFO: Զ�̶�����ɹ���");
			
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static String[] buttonHint = {"�������������", "����رշ�����"};
	public static JTextArea block= new JTextArea();
	private static JScrollPane jScrollPane2 = new JScrollPane(block);;
	/**************************************************************
	 * public static void main(String args[])
	 * 
	 * @Description �����������ˣ�������RMI�ٳ�ʼ������
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
					block.append("�����������ɹ�\n");
					button.setText(buttonHint[1]);
					rmi.open();
				} else {
					isOpen = !isOpen;
					block.append("�رշ������ɹ�\n");
					button.setText(buttonHint[0]);
					rmi.close();
				}
			}
		});
		
	}
	
	static void setTextArea(JPanel panel){
		block.setEditable(false);
        block.setColumns(25);
        block.setFont(new java.awt.Font("΢���ź�", 0, 18)); // NOI18N
        block.setLineWrap(true);
        block.setRows(9);
        jScrollPane2.setViewportView(block);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(jScrollPane2);
        
        
	}
	
	
	
}
