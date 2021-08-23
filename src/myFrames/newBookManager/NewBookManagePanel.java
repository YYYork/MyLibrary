package myFrames.newBookManager;

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
import javax.swing.JTextField;

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
	private JScrollPane scroPane;
	private JTextField field_checkNewBookPanel_bookType;
	private JLabel label_checkNewBookPanel_bookType;
	private JButton btn_pass;
	private JButton btn_remove;
	
	private JTabbedPane tabPane;
	/*
	 * ∞¥≈•1	œ‘ æ√Ê∞Â1
	 * ∞¥≈•2	œ‘ æ√Ê∞Â2
	 * */
	public NewBookManagePanel() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new BorderLayout());
		setBounds(0,0,1585,735);
		
		newBookOrderPanel = new JPanel();
		newBookOrderPanel();
		
		checkNewBookPanel = new JPanel();
		checkNewBookPanel();
		
		tabPane = new JTabbedPane();
		
		setTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void setTabElement() {
		tabPane.addTab(" ÈºÆ‘§∂©",newBookOrderPanel);
		tabPane.addTab(" ÈºÆ…Û∫À",checkNewBookPanel);
	}
	
	private void newBookOrderPanel() {
		newBookOrderPanel.setLayout(new GridLayout(3,2,30,30));
		newBookOrderPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_newBookOrderPanel_bookName = new JTextField();
		field_newBookOrderPanel_publisher = new JTextField();
		label_newBookOrderPanel_bookName = new JLabel(" È√˚£∫");
		label_newBookOrderPanel_bookName.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 30));
		label_newBookOrderPanel_publisher = new JLabel("≥ˆ∞Ê…Á£∫");
		label_newBookOrderPanel_publisher.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 30));
		nullLabel = new JLabel();
		btn_order = new JButton("‘§∂© ÈºÆ");
		
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
		
		table = new JTable();
		scroPane = new JScrollPane(table);
		field_checkNewBookPanel_bookType = new JTextField();
		label_checkNewBookPanel_bookType = new JLabel(" ÈºÆ÷÷¿‡£∫");
		label_checkNewBookPanel_bookType.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 30));
		btn_pass = new JButton("Õ®π˝…Û∫À");
		btn_remove = new JButton("Œ¥Õ®π˝…Û∫À");
		checkNewBookPanel_sonRight = new JPanel();
		checkNewBookPanel_sonRight.setLayout(new GridLayout(2,2,60,60));
		checkNewBookPanel_sonRight.add(label_checkNewBookPanel_bookType);
		checkNewBookPanel_sonRight.add(field_checkNewBookPanel_bookType);
		checkNewBookPanel_sonRight.add(btn_pass);
		checkNewBookPanel_sonRight.add(btn_remove);
		
		checkNewBookPanel.add(scroPane);
		checkNewBookPanel.add(checkNewBookPanel_sonRight);
	}
}
