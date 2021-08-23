package myFrames.systemManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	private JLabel label_updateReaderPanel_account;
	private JLabel label_updateReaderPanel_password;
	private JLabel label_updateReaderPanel_name;
	private JLabel label_updateReaderPanel_isAdmin;
	private JButton btn_updateReaderPanel_update;
	private JButton btn_updateReaderPanel_search;
	
	private JPanel removeReaderPanel;
	private JPanel panel_removeReaderPanel_displayReaderInfo;
	private JPanel panel_removeReaderPanel_account;
	private JTextField field_removeReaderPanel_account;
	private JTextArea area_removeReaderPanel_displayReaderInfo;
	private JLabel label_removeReaderPanel_account;
	private JButton btn_removeReaderPanel_search;
	private JButton btn_removeReaderPanel_remove;//先查询显示信息后显示删除按钮
	
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
 * 按钮1	显示面板1
 * 按钮2	显示面板2
 * 按钮3	显示面板3
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
		
		addTabElement();
		
		add(tabPane,BorderLayout.CENTER);
		
		setVisible(false);
	}
	
	private void addTabElement() {
		tabPane.addTab("更新用户", updateReaderPanel);
		tabPane.addTab("添加用户", addReaderPanel);
		tabPane.addTab("删除用户", removeReaderPanel);
	}
	private void updateReaderPanel() {
		updateReaderPanel.setLayout(new GridLayout(5,2,30,30));
		updateReaderPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		field_updateReaderPanel_account = new JTextField();
		field_updateReaderPanel_password = new JTextField();
		field_updateReaderPanel_name = new JTextField();
		field_updateReaderPanel_isAdmin = new JTextField();
		btn_updateReaderPanel_update = new JButton("更改信息");
		btn_updateReaderPanel_search = new JButton("查询信息");
		label_updateReaderPanel_account = new JLabel("账号：");
		label_updateReaderPanel_account.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_updateReaderPanel_password = new JLabel("密码：");
		label_updateReaderPanel_password.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_updateReaderPanel_name = new JLabel("昵称：");
		label_updateReaderPanel_name.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_updateReaderPanel_isAdmin = new JLabel("管理员：");
		label_updateReaderPanel_isAdmin.setFont(new Font("微软雅黑", Font.BOLD, 30));
		
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
		label_removeReaderPanel_account = new JLabel("账号：");
		label_removeReaderPanel_account.setFont(new Font("微软雅黑", Font.BOLD, 30));
		btn_removeReaderPanel_remove = new JButton("删除");//可以点两次删除 第一次查询 第二次删除
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
		label_addReaderPanel_account = new JLabel("账号：");
		label_addReaderPanel_account.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_addReaderPanel_password = new JLabel("密码：");
		label_addReaderPanel_password.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_field_addReaderPanel_name = new JLabel("昵称：");
		label_field_addReaderPanel_name.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label_addReaderPanel_isAdmin = new JLabel("是否是管理员：");
		label_addReaderPanel_isAdmin.setFont(new Font("微软雅黑", Font.BOLD, 30));
		btn_addReaderPanel_add = new JButton("添加用户");
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
}
