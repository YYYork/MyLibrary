package myFrames.newBookManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import myFrames.MainFrame;
import myFrames.basicDataMatiain.BookDataManagePanel;
import myObject.Book;
import mySQLManager.BookAdder;
import mySQLManager.BookFinder;
import mySQLManager.BookFinderType;

public class NewBookManagePanel extends JPanel{
	private JPanel newBookOrderPanel;
	private JTextField field_newBookOrderPanel_bookName;
	private JTextField field_newBookOrderPanel_publisher;
	private JLabel label_newBookOrderPanel_bookName;
	private JLabel label_newBookOrderPanel_publisher;
	private JLabel nullLabel;
	private JButton btn_order;
	
	private JPanel checkNewBookPanel;
	private JPanel checkNewBookPanel_sonRight;
	private JTable table;
	private String comName[] = {"书名","出版社"};
	private String rows[][] = {{"",""}};
	private DefaultTableModel tableModel;
	private JScrollPane scroPane;
	private JTextField field_checkNewBookPanel_bookType;
	private JLabel label_checkNewBookPanel_bookType;
	private JButton btn_pass;
	private JButton btn_remove;
	
	private JTabbedPane tabPane;
	/*
	 * 按钮1	显示面板1
	 * 按钮2	显示面板2
	 * */
	public NewBookManagePanel() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new BorderLayout());
		setBounds(0,0,1585,735);
		
		newBookOrderPanel = new JPanel();
		newBookOrderPanel();
		
		checkNewBookPanel = new JPanel();
		checkNewBookPanel();
		
		addListener();
		
		tabPane = new JTabbedPane();
		
		setTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void setTabElement() {
		tabPane.addTab("书籍预订",newBookOrderPanel);
		tabPane.addTab("书籍审核",checkNewBookPanel);
	}
	
	private void newBookOrderPanel() {
		newBookOrderPanel.setLayout(new GridLayout(3,2,30,30));
		newBookOrderPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_newBookOrderPanel_bookName = new JTextField();
		field_newBookOrderPanel_publisher = new JTextField();
		label_newBookOrderPanel_bookName = new JLabel("书名：");
		label_newBookOrderPanel_bookName.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_newBookOrderPanel_publisher = new JLabel("出版社：");
		label_newBookOrderPanel_publisher.setFont(new Font("微软雅黑", Font.BOLD, 30));
		nullLabel = new JLabel();
		btn_order = new JButton("预订书籍");
		
		newBookOrderPanel.add(label_newBookOrderPanel_bookName);
		newBookOrderPanel.add(field_newBookOrderPanel_bookName);
		newBookOrderPanel.add(label_newBookOrderPanel_publisher);
		newBookOrderPanel.add(field_newBookOrderPanel_publisher);
		newBookOrderPanel.add(nullLabel);
		newBookOrderPanel.add(btn_order);
	}
	
	private void checkNewBookPanel() {
		checkNewBookPanel.setLayout(new GridLayout(1,2,30,30));
		checkNewBookPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		tableModel = new DefaultTableModel(rows, comName);
		table = new JTable(tableModel);
		scroPane = new JScrollPane(table);
		field_checkNewBookPanel_bookType = new JTextField();
		label_checkNewBookPanel_bookType = new JLabel("书籍种类：");
		label_checkNewBookPanel_bookType.setFont(new Font("微软雅黑", Font.BOLD, 30));
		btn_pass = new JButton("通过审核");
		btn_remove = new JButton("未通过审核");
		checkNewBookPanel_sonRight = new JPanel();
		checkNewBookPanel_sonRight.setLayout(new GridLayout(2,2,60,60));
		checkNewBookPanel_sonRight.add(label_checkNewBookPanel_bookType);
		checkNewBookPanel_sonRight.add(field_checkNewBookPanel_bookType);
		checkNewBookPanel_sonRight.add(btn_pass);
		checkNewBookPanel_sonRight.add(btn_remove);
		
		checkNewBookPanel.add(scroPane);
		checkNewBookPanel.add(checkNewBookPanel_sonRight);
	}
	
	private void addListener() {
		
		btn_order.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookName = field_newBookOrderPanel_bookName.getText();
				String publisher = field_newBookOrderPanel_publisher.getText();
				if(bookName.equals("")||publisher.equals("")) {
					return;
				}else {
					field_newBookOrderPanel_bookName.setText("");
					field_newBookOrderPanel_publisher.setText("");
					String book[] = {bookName,publisher};
					tableModel.addRow(book);
					JOptionPane.showMessageDialog(MainFrame.instance, "预订成功！");
				}
			}
		});
		
		btn_pass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookType = field_checkNewBookPanel_bookType.getText();
				int rowC = table.getSelectedRow();
				if(rowC==-1||rowC==0||bookType.equals("")) {
					return;
				}else {
					String bookName = table.getValueAt(rowC, 1).toString();
					String publisher = table.getValueAt(rowC, 2).toString();
					if(BookFinder.getBooks(BookFinderType.SEARCH_FOR_NAME, bookName).isEmpty()) {
						field_checkNewBookPanel_bookType.setText("");
						tableModel.removeRow(rowC);
						String book[] = {bookName,bookType,publisher};
						BookDataManagePanel.tabelModel.addRow(book);
					}else {
						JOptionPane.showMessageDialog(MainFrame.instance, "书籍已存在！");
					}
				}
			}
		});
		
		btn_remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rowC = table.getSelectedRow();
				if(rowC==-1||rowC==0) {
					return;
				}else {
					tableModel.removeRow(rowC);
					JOptionPane.showMessageDialog(MainFrame.instance, "书籍已删除！");
				}
			}
		});
	}
}
