package myFrames.basicDataMatiain;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookDataManagePanel extends JPanel{
	//从书籍预订那边过来的书再选择添加
	private JPanel bookAddPanel;
	private JPanel bookAddPanel_sonBottom;
	private JTextField field_bookAddPanel_bookID;
	private JLabel label_bookAddPanel_bookID;
	private JTable table;
	private JScrollPane scroPane;
	private JButton btn_addBook;
	
	private JPanel bookRemovePanel;
	private JPanel bookRemovePanel_sonLeft;
	private JPanel bookRemovePanel_sonRight;
	private JTextField field_bookRemovePanel_bookID;
	private JTextArea area_displayBookInfo;
	private JLabel label_bookRemovePanel_bookID;
	private JButton btn_findBook;
	private JButton btn_removeBook;//先查书，显示书信息，显示删除按钮
	
	private JTabbedPane tabPane;
	
	public BookDataManagePanel() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new BorderLayout());
		setBounds(0,0,1585,735);
		
		tabPane = new JTabbedPane();
		
		bookRemovePanel = new JPanel();
		bookRemovePanel();
		
		bookAddPanel = new JPanel();
		bookAddPanel();
		
		setTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void setTabElement() {
		tabPane.addTab("添加书", bookAddPanel);
		tabPane.addTab("删除书", bookRemovePanel);
	}
	
	private void bookAddPanel() {
		bookAddPanel.setLayout(new GridLayout(2,1,40,40));
		bookAddPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		bookAddPanel_sonBottom = new JPanel();
		field_bookAddPanel_bookID = new JTextField();
		label_bookAddPanel_bookID = new JLabel("书籍ID：");
		label_bookAddPanel_bookID.setFont(new Font("微软雅黑", Font.BOLD, 30));
		btn_addBook = new JButton("添加书籍");
		bookAddPanel_sonBottom.setLayout(new GridLayout(1,3,30,30));
		bookAddPanel_sonBottom.add(label_bookAddPanel_bookID);
		bookAddPanel_sonBottom.add(field_bookAddPanel_bookID);
		bookAddPanel_sonBottom.add(btn_addBook);
		table = new JTable();
		scroPane = new JScrollPane(table);
		
		
		bookAddPanel.add(scroPane);
		bookAddPanel.add(bookAddPanel_sonBottom);
	}
	
	private void bookRemovePanel() {
		bookRemovePanel.setLayout(new GridLayout(1,2,40,40));
		bookRemovePanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_bookRemovePanel_bookID = new JTextField();
		area_displayBookInfo = new JTextArea();
		label_bookRemovePanel_bookID = new JLabel("书籍ID：");
		label_bookRemovePanel_bookID.setFont(new Font("微软雅黑", Font.BOLD, 30));
		btn_removeBook = new JButton("删除书籍");//点两次删除
		bookRemovePanel_sonLeft = new JPanel();
		bookRemovePanel_sonLeft.setLayout(new GridLayout(3,1,60,60));
		bookRemovePanel_sonLeft.add(label_bookRemovePanel_bookID);
		bookRemovePanel_sonLeft.add(field_bookRemovePanel_bookID);
		bookRemovePanel_sonLeft.add(btn_removeBook);
		bookRemovePanel_sonRight = new JPanel();
		bookRemovePanel_sonRight.setLayout(new BorderLayout());
		bookRemovePanel_sonRight.add(area_displayBookInfo,BorderLayout.CENTER);
		
		bookRemovePanel.add(bookRemovePanel_sonLeft);
		bookRemovePanel.add(bookRemovePanel_sonRight);	
	}
}
