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
	//��ѯ�˵����
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
	private String colNames[] = {"ID","����","����","������","�Ƿ���"};
	private String Books[][] = {{"Ĭ��","Ĭ��","Ĭ��","Ĭ��","Ĭ��"},{"Ĭ��","Ĭ��","Ĭ��","Ĭ��","Ĭ��"}};
	
	public BookSearchPanel() {
			setBounds(0,0,1600,800);
			setLayout(null);
			
			label_Search_Name = new JLabel("����");
			label_Search_Name.setFont(new Font("΢���ź�", Font.BOLD, 20));
			label_Search_Name.setBounds(187, 95, 74, 42);
			
			label_Search_Publisher = new JLabel("������");
			label_Search_Publisher.setFont(new Font("΢���ź�", Font.BOLD, 20));
			label_Search_Publisher.setBounds(177, 506, 119, 42);
			
			label_Search_Type = new JLabel("����");
			label_Search_Type.setFont(new Font("΢���ź�", Font.BOLD, 20));
			label_Search_Type.setBounds(187, 302, 75, 42);
			
			label_Search_Result = new JLabel("�鼮�����ѯ");
			label_Search_Result.setFont(new Font("΢���ź�", Font.BOLD, 30));
			label_Search_Result.setBounds(862, 28, 300, 200);
			label_Search_Result.setBackground(Color.GRAY);
			
			btn_Search_Name = new JButton("��������");
			btn_Search_Name.setBounds(145, 218, 116, 59);
			
			btn_Search_Publisher = new JButton("����������");
			btn_Search_Publisher.setBounds(145, 631, 116, 59);
			
			btn_Search_Type = new JButton("��������");
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
	
	private void addListener() {//����ť��Ӽ���
		btn_Search_Name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String bookName = field_Search_Name.getText();
				if(bookName.equals(""))
					return;
				List<Book> getbooks = BookFinder.getBooks(BookFinderType.SEARCH_FOR_NAME, bookName);
				if(!getbooks.isEmpty()) {
				Books = BookToArrays.booktoArrays(getbooks);//���ñ��ģ��
				}else {
					JOptionPane.showMessageDialog(MainFrame.instance, "û�в�ѯ������Ҫ���飡");//����С�Ի���
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
					JOptionPane.showMessageDialog(MainFrame.instance, "û�в�ѯ������Ҫ�ĳ����磡");//����С�Ի���
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
					JOptionPane.showMessageDialog(MainFrame.instance, "û�в�ѯ������Ҫ�����࣡");//����С�Ի���
				}
			}
		});
	}
}
