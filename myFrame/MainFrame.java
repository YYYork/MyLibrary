package myFrame;

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
	//查询菜单面板
	private JPanel bookCheckPanel;
	private JScrollPane bookCheckPanel1;
	private JLabel label_Search_Name;
	private JLabel label_Search_Publisher;
	private JLabel label_Search_Type;
	private JLabel label_Search_Result;
	private JButton btn_Search_Name;
	private JButton btn_Search_Publisher;
	private JButton btn_Search_Type;
	private JTextField field_Search_Name;
	private JTextField field_Search_Publisher;
	private JTextField field_Search_Type;
	private JTable search_Result_Table;
	private String colNames[] = {"书名","种类","出版社"};
	private String Books[][] = {{"默认","默认","默认"},{"默认","默认","默认"}};
	
	public MainFrame(boolean isAdmin,String name) {
		setBounds(300,300,1600,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		Container c =getContentPane();
		c.setLayout(null);
		
		if(isAdmin) {
			setTitle("欢迎您,管理员："+name);
			adminSetting();
		}else {
			setTitle("欢迎您,用户："+name);
			defaultSetting2();
		}
		
		defaultSetting();
		bookCheckPanelSetting();
		c.add(bookCheckPanel);
		c.add(mainPane);
		
		setVisible(true);
	}
	
	private void defaultSetting2() {
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
	
	private void defaultSetting() {
		
		mainPane = new JPanel();
		mainPane.setLayout(null);
		mainPane.setBounds(0,0,1600,800);
		mainPane.setBackground(Color.white);
		
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
	} 
	
	private void btnActionListenerAdder() {
		
		btn_BookBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_BookCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainPane.setVisible(false);
				bookCheckPanel.setVisible(true);
				
			}
		});
		
		btn_BookReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void adminSetting() {
		
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
	
	private void bookCheckPanelSetting() {
		bookCheckPanel = new JPanel();
		bookCheckPanel.setBounds(0,0,1600,800);
		bookCheckPanel.setLayout(null);
		
		bookCheckPanel1 = new JScrollPane(); 
		bookCheckPanel1.setBounds(461, 236, 1076, 472);
		
		label_Search_Name = new JLabel("书名");
		label_Search_Name.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_Search_Name.setBounds(187, 95, 74, 42);
		
		label_Search_Publisher = new JLabel("出版社");
		label_Search_Publisher.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_Search_Publisher.setBounds(177, 506, 119, 42);
		
		label_Search_Type = new JLabel("种类");
		label_Search_Type.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_Search_Type.setBounds(187, 302, 75, 42);
		
		label_Search_Result = new JLabel("书籍结果查询");
		label_Search_Result.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_Search_Result.setBounds(862, 28, 300, 200);
		label_Search_Result.setBackground(Color.GRAY);
		
		btn_Search_Name = new JButton("搜索书名");
		btn_Search_Name.setBounds(145, 218, 116, 59);
		
		btn_Search_Publisher = new JButton("搜索出版社");
		btn_Search_Publisher.setBounds(145, 631, 116, 59);
		
		btn_Search_Type = new JButton("搜索种类");
		btn_Search_Type.setBounds(145, 423, 116, 59);
		
		field_Search_Name = new JTextField();
		field_Search_Name.setBounds(49, 145, 312, 61);
		
		field_Search_Publisher = new JTextField();
		field_Search_Publisher.setBounds(49, 350, 312, 61);
		
		field_Search_Type = new JTextField();
		field_Search_Type.setBounds(49, 558, 312, 61);
		
		search_Result_Table = new JTable(Books,colNames);
		
		bookCheckPanel1.add(search_Result_Table);
		bookCheckPanel.add(label_Search_Result);
		bookCheckPanel.add(label_Search_Publisher);
		bookCheckPanel.add(label_Search_Type);
		bookCheckPanel.add(label_Search_Name);
		bookCheckPanel.add(btn_Search_Name);
		bookCheckPanel.add(btn_Search_Publisher);
		bookCheckPanel.add(btn_Search_Type);
		bookCheckPanel.add(field_Search_Name);
		bookCheckPanel.add(field_Search_Publisher);
		bookCheckPanel.add(field_Search_Type);
		bookCheckPanel.add(bookCheckPanel1);
		
		bookCheckPanel.setVisible(false);
	}
}
