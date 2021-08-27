package myFrames.systemManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileDescriptor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import myFrames.MainFrame;
import myObject.Reader;
import mySQLManager.DBmanager;
import mySQLManager.WayOfGetReader;

public class UpdateReader extends JPanel{
	private JPanel updateReaderPanel;
	private JTextField field_updateReaderPanel_account;
	private JTextField field_updateReaderPanel_password;
	private JTextField field_updateReaderPanel_name;
	private JTextField field_updateReaderPanel_isAdmin;
	private JLabel label_updateReaderPanel_account;
	private JLabel label_updateReaderPanel_password;
	private JLabel label_updateReaderPanel_name;
	private JLabel label_updateReaderPanel_isAdmin;
	private JButton btn_updateReaderPanel_update;
	private JButton btn_updateReaderPanel_search;
	private boolean flag_updateReaderPanel = false;//�Ƿ��ѯ��
	private String flag_account = "";//��ѯģʽ�µ��û�
	
	private JPanel removeReaderPanel;
	private JPanel panel_removeReaderPanel_displayReaderInfo;
	private JPanel panel_removeReaderPanel_account;
	private JTextField field_removeReaderPanel_account;
	private JTextArea area_removeReaderPanel_displayReaderInfo;
	private JLabel label_removeReaderPanel_account;
	private JButton btn_removeReaderPanel_remove;//�Ȳ�ѯ��ʾ��Ϣ����ʾɾ����ť
	private boolean flag_removeReaderPanel = false;
	private String flag_removeReaderPanel_account = "";
	
	private JPanel addReaderPanel;
	private JTextField field_addReaderPanel_account;
	private JTextField field_addReaderPanel_password;
	private JTextField field_addReaderPanel_name;
	private JTextField field_addReaderPanel_isAdmin;
	private JLabel label_addReaderPanel_account;
	private JLabel label_addReaderPanel_password;
	private JLabel label_field_addReaderPanel_name;
	private JLabel label_addReaderPanel_isAdmin;
	private JLabel nullLabel;
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
		setBounds(0,0,1585,735);
		
		tabPane = new JTabbedPane();
		
		updateReaderPanel = new JPanel();
		updateReaderPanel();
		
		removeReaderPanel = new JPanel();
		removeReaderPanel();
		
		addReaderPanel = new JPanel();
		addReaderPanel();
		
		addListener();
		addTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void addTabElement() {
		tabPane.addTab("�����û�", updateReaderPanel);
		tabPane.addTab("����û�", addReaderPanel);
		tabPane.addTab("ɾ���û�", removeReaderPanel);
	}
	private void updateReaderPanel() {
		updateReaderPanel.setLayout(new GridLayout(5,2,30,30));
		updateReaderPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_updateReaderPanel_account = new JTextField();
		field_updateReaderPanel_password = new JTextField();
		field_updateReaderPanel_name = new JTextField();
		field_updateReaderPanel_isAdmin = new JTextField();
		btn_updateReaderPanel_update = new JButton("������Ϣ");
		btn_updateReaderPanel_search = new JButton("��ѯ��Ϣ");
		label_updateReaderPanel_account = new JLabel("�˺ţ�");
		label_updateReaderPanel_account.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_updateReaderPanel_password = new JLabel("���룺");
		label_updateReaderPanel_password.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_updateReaderPanel_name = new JLabel("�ǳƣ�");
		label_updateReaderPanel_name.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_updateReaderPanel_isAdmin = new JLabel("����Ա��");
		label_updateReaderPanel_isAdmin.setFont(new Font("΢���ź�", Font.BOLD, 30));
		
		updateReaderPanel.add(label_updateReaderPanel_account);
		updateReaderPanel.add(field_updateReaderPanel_account);
		updateReaderPanel.add(label_updateReaderPanel_password);
		updateReaderPanel.add(field_updateReaderPanel_password);
		updateReaderPanel.add(label_updateReaderPanel_name);
		updateReaderPanel.add(field_updateReaderPanel_name);
		updateReaderPanel.add(label_updateReaderPanel_isAdmin);
		updateReaderPanel.add(field_updateReaderPanel_isAdmin);
		updateReaderPanel.add(btn_updateReaderPanel_search);
		updateReaderPanel.add(btn_updateReaderPanel_update);
	}
	
	private void removeReaderPanel() {
		removeReaderPanel.setLayout(new GridLayout(1,2,30,30));
		removeReaderPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_removeReaderPanel_account = new JTextField();
		area_removeReaderPanel_displayReaderInfo = new JTextArea();
		label_removeReaderPanel_account = new JLabel("�˺ţ�");
		label_removeReaderPanel_account.setFont(new Font("΢���ź�", Font.BOLD, 30));
		btn_removeReaderPanel_remove = new JButton("ɾ��");//���Ե�����ɾ�� ��һ�β�ѯ �ڶ���ɾ��
		panel_removeReaderPanel_displayReaderInfo = new JPanel();
		panel_removeReaderPanel_displayReaderInfo.setLayout(new BorderLayout());
		panel_removeReaderPanel_displayReaderInfo.add(area_removeReaderPanel_displayReaderInfo,BorderLayout.CENTER);
		panel_removeReaderPanel_account = new JPanel();
		panel_removeReaderPanel_account.setLayout(new GridLayout(3,1,100,100));
		panel_removeReaderPanel_account.add(label_removeReaderPanel_account);
		panel_removeReaderPanel_account.add(field_removeReaderPanel_account);
		panel_removeReaderPanel_account.add(btn_removeReaderPanel_remove);
		
		removeReaderPanel.add(panel_removeReaderPanel_account);
		removeReaderPanel.add(panel_removeReaderPanel_displayReaderInfo);
	}

	private void addReaderPanel() {
		addReaderPanel.setLayout(new GridLayout(5,2,30,30));
		addReaderPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_addReaderPanel_account = new JTextField();
		field_addReaderPanel_password = new JTextField();
		field_addReaderPanel_name = new JTextField();
		field_addReaderPanel_isAdmin = new JTextField();
		label_addReaderPanel_account = new JLabel("�˺ţ�");
		label_addReaderPanel_account.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_addReaderPanel_password = new JLabel("���룺");
		label_addReaderPanel_password.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_field_addReaderPanel_name = new JLabel("�ǳƣ�");
		label_field_addReaderPanel_name.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label_addReaderPanel_isAdmin = new JLabel("�Ƿ��ǹ���Ա��");
		label_addReaderPanel_isAdmin.setFont(new Font("΢���ź�", Font.BOLD, 30));
		btn_addReaderPanel_add = new JButton("����û�");
		nullLabel = new JLabel();
		
		addReaderPanel.add(label_addReaderPanel_account);
		addReaderPanel.add(field_addReaderPanel_account);
		addReaderPanel.add(label_addReaderPanel_password);
		addReaderPanel.add(field_addReaderPanel_password);
		addReaderPanel.add(label_field_addReaderPanel_name);
		addReaderPanel.add(field_addReaderPanel_name);
		addReaderPanel.add(label_addReaderPanel_isAdmin);
		addReaderPanel.add(field_addReaderPanel_isAdmin);
		addReaderPanel.add(nullLabel);
		addReaderPanel.add(btn_addReaderPanel_add);
	}
	
	private void addListener() {
		btn_addReaderPanel_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(	  field_addReaderPanel_account.getText().equals("")
					||field_addReaderPanel_name.getText().equals("")
					||field_addReaderPanel_isAdmin.getText().equals("")
					||field_addReaderPanel_password.getText().equals("")) {
					JOptionPane.showMessageDialog(MainFrame.instance, "���������еĿգ�");
					return;
				}
				
				String account = field_addReaderPanel_account.getText();
				if(!(DBmanager.getReader(account, WayOfGetReader.SEARCH_FOR_ACCOUNT)==null)) {
					JOptionPane.showMessageDialog(MainFrame.instance, "�˺��Ѵ��ڣ�");
					return;
				}else {
					Reader reader = new Reader(
							 field_addReaderPanel_account.getText()
							,field_addReaderPanel_name.getText()
							,Boolean.valueOf(field_addReaderPanel_isAdmin.getText()).booleanValue()
							,field_addReaderPanel_password.getText());
					DBmanager.addReader(reader);
					field_addReaderPanel_account.setText("");
					field_addReaderPanel_name.setText("");
					field_addReaderPanel_isAdmin.setText("");
					field_addReaderPanel_password.setText("");
					JOptionPane.showMessageDialog(MainFrame.instance, "�û���ӳɹ���");
				}
			}
		});
		
		btn_removeReaderPanel_remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account = field_removeReaderPanel_account.getText();
				if(account.equals("")) {
					return;
				}
				Reader reader = DBmanager.getReader(account, WayOfGetReader.SEARCH_FOR_ACCOUNT);
				if(reader == null) {
					JOptionPane.showMessageDialog(MainFrame.instance, "�û������ڣ�");
					return;
				}
				if(flag_removeReaderPanel&&account.equals(flag_removeReaderPanel_account)
						/*
						 * ��� �Ƿ����ȷ��ģʽ �� ��ǰ������˺��Ƿ���ո���������ƥ��
						 * �������ǣ���ʼ�����˺� �� �趨 �ո����������˺�
						 */
						) {
					DBmanager.removeReader(reader);
					JOptionPane.showMessageDialog(MainFrame.instance, "�û�ɾ���ɹ���");
					area_removeReaderPanel_displayReaderInfo.setText("");
					flag_removeReaderPanel = false; //�˳�����ģʽ
				}else {
					flag_removeReaderPanel_account = account; //�趨 �ո����������˺�
					area_removeReaderPanel_displayReaderInfo.setText("");
					area_removeReaderPanel_displayReaderInfo.append("-------------------"+"\n");
					area_removeReaderPanel_displayReaderInfo.append("�û��˺ţ�"+reader.getAccount()+"\n");
					area_removeReaderPanel_displayReaderInfo.append("�û��ǳƣ�"+reader.getName()+"\n");
					area_removeReaderPanel_displayReaderInfo.append("�Ƿ��ǹ���Ա��"+reader.isAdmin()+"\n");
					area_removeReaderPanel_displayReaderInfo.append("�ٰ�һ�� ɾ���� ɾ���û�"+"\n");
					area_removeReaderPanel_displayReaderInfo.append("-------------------"+"\n");
					flag_removeReaderPanel = true; //��������ģʽ
				}
			}
		});
		
		btn_updateReaderPanel_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account = field_updateReaderPanel_account.getText();
				if(account.equals("")) {
					return;
				}else {
					Reader reader = DBmanager.getReader(account, WayOfGetReader.SEARCH_FOR_ACCOUNT);
					if( reader == null) {
						JOptionPane.showMessageDialog(MainFrame.instance, "���û�������");
						return;
					}else {
						field_addReaderPanel_password.setText("");
						field_updateReaderPanel_name.setText(reader.getName());
						field_updateReaderPanel_isAdmin.setText(reader.isAdmin()+"");
						flag_account = account; //���� �ո����������û��˺�
						flag_updateReaderPanel = true; //�����ɹ�
						return;
					}
				}
			}
		});
		
		btn_updateReaderPanel_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account = field_updateReaderPanel_account.getText();
				if(!flag_updateReaderPanel) {
					JOptionPane.showMessageDialog(MainFrame.instance, "��������һ�θ��û�ID��Ȼ���ٽ��и��£�");
					return;
				}else if(!account.equals(flag_account)){
					JOptionPane.showMessageDialog(MainFrame.instance, "��д����˺���ո������Ĳ�ͬ����������һ�δ��˺Űɣ�");
					flag_updateReaderPanel = false;
					return;
				}
				/*
				 * ��� �Ƿ��ѯ�� �� �ող�ѯ���˺� �� ��ǰд����˺� �Ƿ���ͬ 
				 * ����ͬ���򷵻�
				 */
				else {
				String password = field_updateReaderPanel_password.getText();
				String name = field_updateReaderPanel_name.getText();
				String str_isAdmin = field_updateReaderPanel_isAdmin.getText();
				boolean isAdmin = Boolean.valueOf(str_isAdmin).booleanValue();
				if(account.equals("")||password.equals("")||name.equals("")||str_isAdmin.equals("")) {
					JOptionPane.showMessageDialog(MainFrame.instance, "���������еĿ�");
					return;
				}else {
					Reader reader = new Reader(account, name, isAdmin,password);
					DBmanager.setReader(reader);
					field_updateReaderPanel_account.setText("");
					field_updateReaderPanel_password.setText("");
					field_updateReaderPanel_name.setText("");
					field_updateReaderPanel_isAdmin.setText("");
					JOptionPane.showMessageDialog(MainFrame.instance, "�޸ĳɹ���");
				}
			  }
			}
		});
	}
}
