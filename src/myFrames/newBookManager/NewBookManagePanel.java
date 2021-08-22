package myFrames.newBookManager;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NewBookManagePanel extends JPanel{
	private JPanel newBookOrderPanel;
	private JTextField field_newBookOrderPanel_bookName;
	private JTextField field_newBookOrderPanel_publisher;
	private JButton btn_order;
	
	private JPanel checkNewBookPanel;
	private JTable table;
	private JScrollPane scroPane;
	private JTextField field_checkNewBookPanel_bookName;
	private JTextField field_checkNewBookPanel_bookType;
	private JTextField field_checkNewBookPanel_publisher;
	private JButton btn_pass;
	
	private JTabbedPane tabPane;
	/*
	 * 按钮1	显示面板1
	 * 按钮2	显示面板2
	 * */
	public NewBookManagePanel() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new BorderLayout());
		setBounds(0,0,1600,800);
		
		newBookOrderPanel = new JPanel();
		
		checkNewBookPanel = new JPanel();
		
		tabPane = new JTabbedPane();
		
		setTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	private void setTabElement() {
		tabPane.addTab("书籍预订",newBookOrderPanel);
		tabPane.addTab("书籍审核",checkNewBookPanel);
	}
}
