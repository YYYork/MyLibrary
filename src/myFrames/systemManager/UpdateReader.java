package myFrames.systemManager;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdateReader extends JPanel{
	private JPanel updateReaderPanel;
	private JTextField field_updateReaderPanel_account;
	private JTextField field_updateReaderPanel_password;
	private JTextField field_updateReaderPanel_name;
	private JTextField field_updateReaderPanel_isAdmin;
	private JButton btn_updateReaderPanel_update;
	
	private JPanel removeReaderPanel;
	private JTextField field_removeReaderPanel_account;
	private JTextArea area_removeReaderPanel_displayReaderInfo;
	private JButton btn_removeReaderPanel_search;
	private JButton btn_removeReaderPanel_remove;//�Ȳ�ѯ��ʾ��Ϣ����ʾɾ����ť
	
	private JPanel addReaderPanel;
	private JTextField field_addReaderPanel_account;
	private JTextField field_addReaderPanel_password;
	private JTextField field_addReaderPanel_name;
	private JTextField field_addReaderPanel_isAdmin;
	private JButton btn_addReaderPanel_add;
	
	private JTabbedPane tabPane;
/*
 * ��ť1	��ʾ���1
 * ��ť2	��ʾ���2
 * ��ť3	��ʾ���3
 * */
	
	public UpdateReader() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new BorderLayout());
		setBounds(0,0,1600,800);
		
		tabPane = new JTabbedPane();
		
		updateReaderPanel = new JPanel();
		
		removeReaderPanel = new JPanel();
		
		addReaderPanel = new JPanel();
		
		addTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void addTabElement() {
		tabPane.addTab("�����û�", updateReaderPanel);
		tabPane.addTab("����û�", addReaderPanel);
		tabPane.addTab("ɾ���û�", removeReaderPanel);
	}
}
