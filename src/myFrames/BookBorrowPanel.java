package myFrames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import myObject.Book;
import mySQLManager.BookAdder;
import mySQLManager.BookFinder;
import mySQLManager.BookFinderType;
import myUtils.BookToArrays;

public class BookBorrowPanel extends JPanel{
	private JScrollPane tablePanel;
	private JPanel bottomPanel;
	private JButton btn_Search;
	private JButton btn_Borrow;
	private JTextField field_bookID;
	private JTable table;
	private JSplitPane splitPane;
	private String colNames[] = {"ID","书名","种类","出版社","是否借出"};
	private String Books[][] = {{"默认","默认","默认","默认","默认"}};
	private Book getbooks[];
	
	public BookBorrowPanel() {
		setBounds(0,0,1600,800);
		setLayout(null);
		
		table = new JTable(Books,colNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tablePanel = new JScrollPane(table);
		
		btn_Search = new JButton("搜索ID");
		
		btn_Borrow = new JButton("借书");
		btn_Borrow.setEnabled(false);
		
		field_bookID = new JTextField();
		field_bookID.setToolTipText("输入书的ID");
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3,30,30));
		bottomPanel.add(field_bookID);
		bottomPanel.add(btn_Search);
		bottomPanel.add(btn_Borrow);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		addListener();
		
		splitPane.setBounds(0, 0, 1600, 750);
		splitPane.setRightComponent(bottomPanel);
		splitPane.setLeftComponent(tablePanel);
		splitPane.setDividerLocation(600);
		splitPane.setContinuousLayout(true);
		splitPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		add(splitPane);
		
		setVisible(false);
	}
	
	private void addListener() {
		btn_Search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = field_bookID.getText();
				if(id.equals(""))
					return;
				getbooks = BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, id);
				if(getbooks == null) {
					JOptionPane.showMessageDialog(MainFrame.instance, "查无此书！");//弹出小对话框
					return;
				}
				Books = BookToArrays.booktoArrays(getbooks);
				btn_Borrow.setEnabled(true);
			}
		});
		
		btn_Borrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = table.getSelectedRow();
				if(index == -1) {
					return;
				}
				Book book = getbooks[index];
				if(!book.getisBorrowed()) {
				BookAdder.setBookBorrowState(book, true);
				BookAdder.addBookBorrowRecord(MainFrame.instance.getReader(),book);
				}else {
					JOptionPane.showMessageDialog(MainFrame.instance, "此书已借出！");//弹出小对话框
				}
				btn_Borrow.setEnabled(false);
			}
		});
	}
}
