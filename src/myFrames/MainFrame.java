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

import myFrames.basicDataMatiain.BookDataManagePanel;
import myFrames.newBookManager.NewBookManagePanel;
import myFrames.systemManager.UpdateReader;
import myObject.Reader;

public class MainFrame extends JFrame{
	public static MainFrame instance;
	private Reader reader;
	private Container c;
	private JPanel mainPane;
	private JLabel label_Img;
	private JButton btn_BookBorrow;
	private JButton btn_BookCheck;
	private JButton btn_BookReturn;
	private JTextArea area_Announce;
	//����Ա�˵�
	private JMenuBar bar;
	private JMenuItem basic_Data_Maintain_Menu;
	private JMenuItem book_Borrow_Manager_Menu;
	private JMenuItem newbook_Order_Manager_Menu;
	private JMenuItem systrem_Maintain_Manager_Menu;
	//���
	private BookSearchPanel searchPanel;
	private BookBorrowPanel borrowPanel;
	private BookReturnPanel returnPanel;
	private BookDataManagePanel bookDataManagePanel;
	private NewBookManagePanel newBookManagePanel;
	private UpdateReader updateReaderPanel;
	
	public MainFrame(Reader reader) {
		instance = this;
		this.reader = reader;
		defaultSetting();
		if(reader.isAdmin()) {
			setTitle("��ӭ��,����Ա��" + reader.getName());
			adminSetting();
		}else {
			setTitle("��ӭ��,�û���" + reader.getName());
			guestSetting();
		}
	}
	
	public Reader getReader() {
		return reader;
	}

	private void guestSetting() {//��ʼ���û���MenuBar
		bar = new JMenuBar();
		
		book_Borrow_Manager_Menu = new JMenuItem("ͼ����Ĺ���");
		book_Borrow_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNowVisibleFalse();
				mainPane.setVisible(true);
				}
		});
		
		bar.add(book_Borrow_Manager_Menu);
		
		setJMenuBar(bar);
		
	}
	
	private void defaultSetting() {//��ʼ�����˵������
		
		searchPanel = new BookSearchPanel();
		borrowPanel = new BookBorrowPanel();
		returnPanel = new BookReturnPanel();
		
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
		
		btn_BookBorrow = new JButton("ͼ�����");
		btn_BookBorrow.setBackground(Color.gray);
		btn_BookBorrow.setBounds(620,131,359,105);
		
		btn_BookCheck = new JButton("ͼ���ѯ");
		btn_BookCheck.setBackground(Color.gray);
		btn_BookCheck.setBounds(620,358,359,105);
		
		btn_BookReturn = new JButton("ͼ��黹");
		btn_BookReturn.setBackground(Color.gray);
		btn_BookReturn.setBounds(620,560,359,105);
		
		area_Announce = new JTextArea();
		area_Announce.setEditable(false);
		area_Announce.setBorder(BorderFactory.createLineBorder(Color.black));
		area_Announce.setText("                          ����");
		area_Announce.setFont(new Font("΢���ź�",Font.BOLD,25));
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
		c.add(returnPanel);
		
		setVisible(true);
	} 
	
	private void btnActionListenerAdder() {//����ť��Ӽ���
		
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
				mainPane.setVisible(false);
				returnPanel.setVisible(true);
			}
		});
		
	}
	
	private void adminSetting() {//��ʼ������Ա��MenuBar
		
		updateReaderPanel = new UpdateReader();
		bookDataManagePanel = new BookDataManagePanel();
		newBookManagePanel = new NewBookManagePanel();
		
		add(updateReaderPanel);
		add(bookDataManagePanel);
		add(newBookManagePanel);
		
		bar = new JMenuBar();
		
		book_Borrow_Manager_Menu = new JMenuItem("ͼ����Ĺ���");
		book_Borrow_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNowVisibleFalse();
				mainPane.setVisible(true);
			}
		});
		
		basic_Data_Maintain_Menu = new JMenuItem("��������ά��");
		basic_Data_Maintain_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNowVisibleFalse();
				bookDataManagePanel.setVisible(true);
			}
		});
		
		newbook_Order_Manager_Menu = new JMenuItem("���鶩������");
		newbook_Order_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNowVisibleFalse();
				newBookManagePanel.setVisible(true);
			}
		});
		
		systrem_Maintain_Manager_Menu = new JMenuItem("ϵͳά������");
		systrem_Maintain_Manager_Menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setNowVisibleFalse();
				updateReaderPanel.setVisible(true);
			}
		});
		
		bar.add(book_Borrow_Manager_Menu);
		bar.add(basic_Data_Maintain_Menu);
		bar.add(newbook_Order_Manager_Menu);
		bar.add(systrem_Maintain_Manager_Menu);
		
		setJMenuBar(bar);
	}	
	
	private void setNowVisibleFalse() {
		if(mainPane.isVisible()) {mainPane.setVisible(false);}
		if(searchPanel.isVisible()) {searchPanel.setVisible(false);}
		if(borrowPanel.isVisible()) {borrowPanel.setVisible(false);}
		if(returnPanel.isVisible()) {returnPanel.setVisible(false);}
		
		if(!reader.isAdmin()) {//��ֹ��ָ���쳣
			return;
		}
		
		if(bookDataManagePanel.isVisible()) {bookDataManagePanel.setVisible(false);}
		if(newBookManagePanel.isVisible()) {newBookManagePanel.setVisible(false);}
		if(updateReaderPanel.isVisible()) {updateReaderPanel.setVisible(false);}
	}
}
