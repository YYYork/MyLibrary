package myFrames.basicDataMatiain;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookDataManagePanel extends JPanel{
	//从书籍预订那边过来的书再选择添加
	private JPanel bookAddPanel;
	private JTextField field_bookAddPanel_bookID;
	private JTable table;
	private JScrollPane scroPane;
	private JButton btn_addBook;
	
	private JPanel bookRemovePanel;
	private JTextField field_bookRemovePanel_bookID;
	private JTextArea area_displayBookInfo;
	private JButton btn_findBook;
	private JButton btn_removeBook;//先查书，显示书信息，显示删除按钮
	
	private JTabbedPane tabPane;
	
	public BookDataManagePanel() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new BorderLayout());
		setBounds(0,0,1600,800);
		
		tabPane = new JTabbedPane();
		
		bookRemovePanel = new JPanel();
		
		bookAddPanel = new JPanel();
		
		setTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void setTabElement() {
		tabPane.addTab("添加书", bookAddPanel);
		tabPane.addTab("删除书", bookRemovePanel);
	}
}
