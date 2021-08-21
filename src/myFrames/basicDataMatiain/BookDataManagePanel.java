package myFrames.basicDataMatiain;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	private JPanel panel;
	private JButton btn_bookAddPanel;
	private JButton btn_bookRemovePanel;
	/*
	 * 按钮1	显示面板1
	 * 按钮2	显示面板2
	 * */
}
