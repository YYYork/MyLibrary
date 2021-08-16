package myFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import MySQLManager.DBmanager;
import MySQLManager.LoginState;
import MySQLManager.RegisterState;

public class LoginFrame extends JFrame{
	//��½����
	private Container c;
	private JLabel title_label;
	private JPanel login;
	private JButton login_btn;
	private JButton reg_btn;
	private JButton forgot_btn;
	private JTextField account;
	private JLabel label_account;
	private JPasswordField password;
	private JLabel label_password;
	//ע�����
	private JLabel title_reglabel;
	private JPanel register;
	private JTextField reg_account;
	private JLabel label_regaccount;
	private JPasswordField reg_password;
	private JPasswordField reg_password2;
	private JLabel label_regpassword;
	private JLabel label_regpassword2;
	private JButton reg_ensure_btn;
	private JButton reg_cancel_btn;
	
	public LoginFrame() {
		LoginFramePreSetting();
	}
	
	private void LoginFramePreSetting() {//��½�����ʼ��
		setBounds(600, 150, 550, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Ѽ��ͼ��ݡ�����½����");
		setResizable(false);
		c = getContentPane();
		c.setLayout(null);
		
		title_label = new JLabel("Ѽ��ͼ���");
		title_label.setFont(new Font("΢���ź�",Font.BOLD,30));
		title_label.setBounds(200,0,200,200);
		
		login = new JPanel();
		login.setBounds(0, 0, 550, 800);
		login.setLayout(null);
		
		login_btn = new JButton("��¼");
		login_btn.setBackground(Color.gray);
		login_btn.setBounds(128, 448, 354, 70);
		
		reg_btn = new JButton("ע��");
		reg_btn.setBackground(Color.gray);
		reg_btn.setBounds(128, 549, 352, 35);
		
		forgot_btn = new JButton("��������");
		forgot_btn.setBackground(Color.gray);
		forgot_btn.setBounds(128, 649, 352, 35);
		
		account = new JTextField();
		account.setBounds(128, 199, 354, 76);
		
		password = new JPasswordField();
		password.setBounds(128,330,354,76);
		
		label_account = new JLabel("�˺ţ�");
		label_account.setFont(new Font("΢���ź�",Font.BOLD,25));
		label_account.setBounds(21, 199, 100, 60);
		
		label_password = new JLabel("���룺");
		label_password.setFont(new Font("΢���ź�",Font.BOLD,25));
		label_password.setBounds(21,343,100,60);
		AddLoginComponentListener();
		
		login.add(login_btn);
		login.add(reg_btn);
		login.add(account);
		login.add(password);
		login.add(label_password);
		login.add(label_account);
		login.add(title_label);
		login.setBackground(Color.white);
		login.add(forgot_btn);
		login.setVisible(true);
		c.add(login);
		
		setVisible(true);
	}
	
	private void RegisterPanelPreSetting() {//ע������ʼ��
		
		title_reglabel = new JLabel("�˺�ע��");
		title_reglabel.setFont(new Font("΢���ź�",Font.BOLD,30));
		title_reglabel.setForeground(Color.BLUE);
		title_reglabel.setBounds(230,0,200,200);
		
		register = new JPanel();
		register.setBounds(0, 0, 550, 800);
		register.setLayout(null);
		
		reg_account = new JTextField();
		reg_account.setBounds(128, 199, 354, 76);
		
		label_regaccount = new JLabel("�˺ţ�");
		label_regaccount.setFont(new Font("΢���ź�",Font.BOLD,25));
		label_regaccount.setBounds(21, 199, 100, 60);
		
		reg_password = new JPasswordField();
		reg_password.setBounds(128,330,354,76);
		
		reg_password2 = new JPasswordField();
		reg_password2.setBounds(128,420,354,76);
		
		label_regpassword = new JLabel("���룺");
		label_regpassword.setFont(new Font("΢���ź�",Font.BOLD,25));
		label_regpassword.setBounds(21,343,100,60);
		
		label_regpassword2 = new JLabel("��������һ�����룺");
		label_regpassword2.setFont(new Font("΢���ź�",Font.BOLD,10));
		label_regpassword2.setBounds(21,443,100,60);
		
		reg_ensure_btn = new JButton("ע��");
		reg_ensure_btn.setBackground(Color.gray);
		reg_ensure_btn.setBounds(128, 548, 354, 70);
		
		reg_cancel_btn = new JButton("ȡ��");
		reg_cancel_btn.setBackground(Color.gray);
		reg_cancel_btn.setBounds(128, 649, 352, 35);
		AddRegisterComponentListener();
		
		register.add(reg_password2);
		register.add(label_regpassword2);
		register.add(label_regpassword);
		register.add(title_reglabel);
		register.add(reg_account);
		register.add(label_regaccount);
		register.add(reg_password);
		register.add(reg_ensure_btn);
		register.add(reg_cancel_btn);
		register.setBackground(Color.white);
		register.setVisible(false);
		c.add(register);
	}
	
	private void AddLoginComponentListener() {
		
		login_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String login_account = account.getText();
				String login_password = new String(password.getPassword());
				if(login_account.equals("") || login_password.equals("")) {
					JOptionPane.showMessageDialog(LoginFrame.this, "���������еĿգ�");//����С�Ի���
					return;
				}
				LoginState state = DBmanager.CheckLogin(login_account, login_password);
				if(LoginState.SUCCESS_LOGIN.equals(state)){
				//�ж��Ƿ�Ϊ����Ա
					boolean isAdmin = DBmanager.isAdmin(account.getText());
					String name = DBmanager.getName(login_account);
					new MainFrame(isAdmin, name);
					dispose();
			}else if(LoginState.ERROR_PASSWORD.equals(state)) {
				JOptionPane.showMessageDialog(LoginFrame.this, "�������");//����С�Ի���
			}else if(LoginState.UNEXIT_ACCOUNT.equals(state)) {
				JOptionPane.showMessageDialog(LoginFrame.this, "�˺Ų����ڣ�");//����С�Ի���
			}else if(LoginState.UNKNOWN_EXCEPTION.equals(state)) {
				JOptionPane.showMessageDialog(LoginFrame.this, "δ֪��������ϵ����Ա��");//����С�Ի���
			}
		}
			});
		
		reg_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegisterPanelPreSetting();
				login.setVisible(false);
				register.setVisible(true);
			}
		});
		
		forgot_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(LoginFrame.this, "����ϵ����Ա�޸����룡");//����С�Ի���
			}
		});
		
	}
	
	private void AddRegisterComponentListener() {
		
		reg_ensure_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(reg_password.getText().equals("") || reg_account.getText().equals("")){
					JOptionPane.showMessageDialog(LoginFrame.this, "�������������еĿգ�");//����С�Ի���
					return;
				}else if(!reg_password.getText().equals(reg_password2.getText())) {
					JOptionPane.showMessageDialog(LoginFrame.this, "������������벻һ�£�");//����С�Ի���
					return;
				}
				String register_account = reg_account.getText();
				String register_password = new String(reg_password.getPassword());
				RegisterState state = DBmanager.RegAccount(register_account, register_password);
				if(RegisterState.ACCOUNT_SUCCESS_REGISTER.equals(state)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "�˺�ע��ɹ���");//����С�Ի���
					register.setVisible(false);
					login.setVisible(true);
				}else if(RegisterState.ACCOUNT_EXITS.equals(state)){
					JOptionPane.showMessageDialog(LoginFrame.this, "�˺��Ѿ����ڣ�");//����С�Ի���
				}else {
					JOptionPane.showMessageDialog(LoginFrame.this, "δ֪��������ϵ����Ա��");//����С�Ի���
				}
			}
		});
		
		reg_cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				register.setVisible(false);
				login.setVisible(true);
			}
		});
	}
}
