package myFrames.basicDataMatiain;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import myFrames.MainFrame;
import myObject.Book;
import mySQLManager.BookAdder;
import mySQLManager.BookFinder;
import mySQLManager.BookFinderType;

public class BookDataManagePanel extends JPanel{
	//从书籍预订那边过来的书再选择添加
	private JPanel bookAddPanel;
	private JPanel bookAddPanel_sonBottom;
	private JTextField field_bookAddPanel_bookID;
	private JLabel label_bookAddPanel_bookID;
	private JTable table;
	public static DefaultTableModel tabelModel;
	private String comName[] = {"书名","种类","出版社"};
	private String rows[][] = {{"默认","默认","默认"}};
	private JScrollPane scroPane;
	private JButton btn_addBook;
	
	private JPanel bookRemovePanel;
	private JPanel bookRemovePanel_sonLeft;
	private JPanel bookRemovePanel_sonRight;
	private JTextField field_bookRemovePanel_bookID;
	private JTextArea area_displayBookInfo;
	private JLabel label_bookRemovePanel_bookID;
	private JButton btn_removeBook;//先查书，显示书信息，显示删除按钮
	private boolean flag = false;
	private String bookconfirmID = "";
	
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
		addListener();
		
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
		tabelModel = new DefaultTableModel(rows,comName);
		table = new JTable(tabelModel);
		scroPane = new JScrollPane(table);
		
		
		bookAddPanel.add(scroPane);
		bookAddPanel.add(bookAddPanel_sonBottom);
	}
	
	private void addListener() {
		btn_addBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookID = field_bookAddPanel_bookID.getName();
				int selectRowCount = table.getSelectedRow();
				if(bookID.equals("")||selectRowCount==-1) {
					return;
				}else {
					String bookName = table.getValueAt(selectRowCount, 1).toString();
					String bookType = table.getValueAt(selectRowCount, 2).toString();
					String publisher = table.getValueAt(selectRowCount, 3).toString();
					Book book = new Book(bookName, bookType, publisher, bookID, false);
					if(BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID).isEmpty()){
						field_bookAddPanel_bookID.setText("");
						tabelModel.removeRow(selectRowCount);
						BookAdder.addBook(book);
						JOptionPane.showMessageDialog(MainFrame.instance, "书籍添加成功");
					}else {
						JOptionPane.showMessageDialog(MainFrame.instance, "书籍ID已存在");
					}
				}
			}
		});
		
		btn_removeBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookID = field_bookRemovePanel_bookID.getText();
				if(bookID.equals("")) {
					return;
				}else if(BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID).isEmpty()){
					JOptionPane.showMessageDialog(MainFrame.instance, "书籍不存在！");
					return;
				}else {
					if(flag&&bookconfirmID.equals(bookID)
					/*
					 * 检查 是否处于确认模式 和 所搜寻的ID与确认模式下的书籍ID是否相同
					 * 若不同则重新显示书籍信息 
					 */
							) {
						Book book = BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID).get(0);
						BookAdder.removeBook(book);
						flag = false;	//退出确认模式
						return;
					}else {
						bookconfirmID = bookID;	//设置确认模式的书籍ID
						Book book = BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID).get(0);
						area_displayBookInfo.setText("");	//清屏
						area_displayBookInfo.append("----------------------"+"\n");
						area_displayBookInfo.append("书籍ID："+book.getId());
						area_displayBookInfo.append("书籍名称："+book.getBookName()+"\n");
						area_displayBookInfo.append("书籍种类："+book.getBookType()+"\n");
						area_displayBookInfo.append("书籍出版社："+book.getPublisher()+"\n");
						area_displayBookInfo.append(" 再按一次 删除键 删除此书"+"\n");
						area_displayBookInfo.append("----------------------"+"\n");
						flag = true;	//进入确认模式
						return;
					}
				}
			}
		});
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
