package myFrames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame{
	private Container c;
	private JPanel mainPane;
	private JLabel label_Img;
	private JButton btn_BookBorrow;
	private JButton btn_BookCheck;
	private JButton btn_BookReturn;
	private JTextArea area_Announce;
	//管理员菜单
	private JMenuBar bar;
	private JMenuItem basic_Data_Maintain_Menu;
	private JMenuItem book_Borrow_Manager_Menu;
	private JMenuItem newbook_Order_Manager_Menu;
	private JMenuItem systrem_Maintain_Manager_Menu;
	//面板
	private BookSearchPanel searchPanel;
	private BookBorrowPanel borrowPanel;
	
	public MainFrame(boolean isAdmin,String name) {
		defaultSetting();
		if(isAdmin) {
			setTitle("欢迎您,管理员："+name);
			adminSetting();
		}else {
			setTitle("欢迎您,用户："+name);
			guestSetting();
		}
	}
	
	private void guestSetting() {//初始化用户的MenuBar
		bar = new JMenuBar();
		
		book_Borrow_Manager_Menu = new JMenuItem("图书借阅管理");
		book_Borrow_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bar.add(book_Borrow_Manager_Menu);
		
		setJMenuBar(bar);
		
	}
	
	private void defaultSetting() {//初始化主菜单各组件
		
		searchPanel = new BookSearchPanel();
		borrowPanel = new BookBorrowPanel();
		
		setBounds(300,300,1600,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		Container c =getContentPane();
		c.setLayout(null);
		
		mainPane = new JPanel();
		mainPane.setLayout(null);
		mainPane.setBounds(0,0,1600,800);
		mainPane.setBackground(Color.white);
		mainPane.setVisible(true);
		
		label_Img = new JLabel();
		label_Img.setBorder(BorderFactory.createLineBorder(Color.black));
		label_Img.setBounds(59,164,431,460);
		
		btn_BookBorrow = new JButton("图书借阅");
		btn_BookBorrow.setBackground(Color.gray);
		btn_BookBorrow.setBounds(620,131,359,105);
		
		btn_BookCheck = new JButton("图书查询");
		btn_BookCheck.setBackground(Color.gray);
		btn_BookCheck.setBounds(620,358,359,105);
		
		btn_BookReturn = new JButton("图书归还");
		btn_BookReturn.setBackground(Color.gray);
		btn_BookReturn.setBounds(620,560,359,105);
		
		area_Announce = new JTextArea();
		area_Announce.setEditable(false);
		area_Announce.setBorder(BorderFactory.createLineBorder(Color.black));
		area_Announce.setText("                          公告");
		area_Announce.setFont(new Font("微软雅黑",Font.BOLD,25));
		area_Announce.setBounds(1126, 61, 419, 658);
		
		btnActionListenerAdder();
		mainPane.add(label_Img);
		mainPane.add(btn_BookBorrow);
		mainPane.add(btn_BookCheck);
		mainPane.add(btn_BookReturn);
		mainPane.add(area_Announce);
		
		c.add(mainPane);
		c.add(searchPanel);
		c.add(borrowPanel);
		
		setVisible(true);
	} 
	
	private void btnActionListenerAdder() {//给按钮添加监听
		
		btn_BookBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainPane.setVisible(false);
				borrowPanel.setVisible(true);
			}
		});
		
		btn_BookCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainPane.setVisible(false);
				searchPanel.setVisible(true);
				
			}
		});
		
		btn_BookReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void adminSetting() {//初始化管理员的MenuBar
		
		bar = new JMenuBar();
		
		book_Borrow_Manager_Menu = new JMenuItem("图书借阅管理");
		book_Borrow_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		basic_Data_Maintain_Menu = new JMenuItem("基础数据维护");
		basic_Data_Maintain_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		newbook_Order_Manager_Menu = new JMenuItem("新书订购管理");
		newbook_Order_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		systrem_Maintain_Manager_Menu = new JMenuItem("系统维护管理");
		systrem_Maintain_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		bar.add(book_Borrow_Manager_Menu);
		bar.add(basic_Data_Maintain_Menu);
		bar.add(newbook_Order_Manager_Menu);
		bar.add(systrem_Maintain_Manager_Menu);
		
		setJMenuBar(bar);
	}	
}
