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
	//���鼮Ԥ���Ǳ߹���������ѡ�����
	private JPanel bookAddPanel;
	private JPanel bookAddPanel_sonBottom;
	private JTextField field_bookAddPanel_bookID;
	private JLabel label_bookAddPanel_bookID;
	private JTable table;
	public static DefaultTableModel tabelModel;
	private String comName[] = {"����","����","������"};
	private String rows[][] = {{"Ĭ��","Ĭ��","Ĭ��"}};
	private JScrollPane scroPane;
	private JButton btn_addBook;
	
	private JPanel bookRemovePanel;
	private JPanel bookRemovePanel_sonLeft;
	private JPanel bookRemovePanel_sonRight;
	private JTextField field_bookRemovePanel_bookID;
	private JTextArea area_displayBookInfo;
	private JLabel label_bookRemovePanel_bookID;
	private JButton btn_removeBook;//�Ȳ��飬��ʾ����Ϣ����ʾɾ����ť
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
		tabPane.addTab("�����", bookAddPanel);
		tabPane.addTab("ɾ����", bookRemovePanel);
	}
	
	private void bookAddPanel() {
		bookAddPanel.setLayout(new GridLayout(2,1,40,40));
		bookAddPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		bookAddPanel_sonBottom = new JPanel();
		field_bookAddPanel_bookID = new JTextField();
		label_bookAddPanel_bookID = new JLabel("�鼮ID��");
		label_bookAddPanel_bookID.setFont(new Font("΢���ź�", Font.BOLD, 30));
		btn_addBook = new JButton("����鼮");
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
						JOptionPane.showMessageDialog(MainFrame.instance, "�鼮��ӳɹ�");
					}else {
						JOptionPane.showMessageDialog(MainFrame.instance, "�鼮ID�Ѵ���");
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
					JOptionPane.showMessageDialog(MainFrame.instance, "�鼮�����ڣ�");
					return;
				}else {
					if(flag&&bookconfirmID.equals(bookID)
					/*
					 * ��� �Ƿ���ȷ��ģʽ �� ����Ѱ��ID��ȷ��ģʽ�µ��鼮ID�Ƿ���ͬ
					 * ����ͬ��������ʾ�鼮��Ϣ 
					 */
							) {
						Book book = BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID).get(0);
						BookAdder.removeBook(book);
						flag = false;	//�˳�ȷ��ģʽ
						return;
					}else {
						bookconfirmID = bookID;	//����ȷ��ģʽ���鼮ID
						Book book = BookFinder.getBooks(BookFinderType.SEARCH_FOR_ID, bookID).get(0);
						area_displayBookInfo.setText("");	//����
						area_displayBookInfo.append("----------------------"+"\n");
						area_displayBookInfo.append("�鼮ID��"+book.getId());
						area_displayBookInfo.append("�鼮���ƣ�"+book.getBookName()+"\n");
						area_displayBookInfo.append("�鼮���ࣺ"+book.getBookType()+"\n");
						area_displayBookInfo.append("�鼮�����磺"+book.getPublisher()+"\n");
						area_displayBookInfo.append(" �ٰ�һ�� ɾ���� ɾ������"+"\n");
						area_displayBookInfo.append("----------------------"+"\n");
						flag = true;	//����ȷ��ģʽ
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
		label_bookRemovePanel_bookID = new JLabel("�鼮ID��");
		label_bookRemovePanel_bookID.setFont(new Font("΢���ź�", Font.BOLD, 30));
		btn_removeBook = new JButton("ɾ���鼮");//������ɾ��
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
