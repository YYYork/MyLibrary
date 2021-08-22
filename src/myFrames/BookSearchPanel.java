package myFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import myObject.Book;
import mySQLManager.BookFinder;
import mySQLManager.BookFinderType;
import myUtils.BookToArrays;

public class BookSearchPanel extends JPanel{
	//查询菜单面板
	private JScrollPane bookCheckPanelTable;
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
	private String colNames[] = {"ID","书名","种类","出版社","是否借出"};
	private String Books[][] = {{"默认","默认","默认","默认","默认"},{"默认","默认","默认","默认","默认"}};
	
	public BookSearchPanel() {
			setBounds(0,0,1600,800);
			setLayout(null);
			
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
			bookCheckPanelTable = new JScrollPane(search_Result_Table); 
			bookCheckPanelTable.setBounds(461, 236, 1076, 472);
			
			addListener();
			
			add(label_Search_Result);
			add(label_Search_Publisher);
			add(label_Search_Type);
			add(label_Search_Name);
			add(btn_Search_Name);
			add(btn_Search_Publisher);
			add(btn_Search_Type);
			add(field_Search_Name);
			add(field_Search_Publisher);
			add(field_Search_Type);
			add(bookCheckPanelTable);
			
			setVisible(false);
	}
	
	private void addListener() {//给按钮添加监听
		btn_Search_Name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookName = field_Search_Name.getText();
				if(bookName.equals(""))
					return;
				List<Book> getbooks = BookFinder.getBooks(BookFinderType.SEARCH_FOR_NAME, bookName);
				if(!getbooks.isEmpty()) {
				Books = BookToArrays.booktoArrays(getbooks);//设置表格模型
				}else {
					JOptionPane.showMessageDialog(MainFrame.instance, "没有查询到您想要的书！");//弹出小对话框
				}
			}
		});
		
		btn_Search_Publisher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookPublisher = field_Search_Publisher.getText();
				if(bookPublisher.equals(""))
					return;
				List<Book> getbooks = BookFinder.getBooks(BookFinderType.SEARCH_FOR_PUBLISHER, bookPublisher);
				if(!getbooks.isEmpty()) {
				Books = BookToArrays.booktoArrays(getbooks);
				}else {
					JOptionPane.showMessageDialog(MainFrame.instance, "没有查询到您想要的出版社！");//弹出小对话框
				}
			}
		});
		
		btn_Search_Type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookType = field_Search_Type.getText();
				if(bookType.equals(""))
					return;
				List<Book> getbooks = BookFinder.getBooks(BookFinderType.SEARCH_FOR_TYPE, bookType);
				if(!getbooks.isEmpty()) {
				Books = BookToArrays.booktoArrays(getbooks);
				}else {
					JOptionPane.showMessageDialog(MainFrame.instance, "没有查询到您想要的种类！");//弹出小对话框
				}
			}
		});
	}
}
