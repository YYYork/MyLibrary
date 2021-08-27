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
	private boolean flag_updateReaderPanel = false;//是否查询过
	private String flag_account = "";//查询模式下的用户
	
	private JPanel removeReaderPanel;
	private JPanel panel_removeReaderPanel_displayReaderInfo;
	private JPanel panel_removeReaderPanel_account;
	private JTextField field_removeReaderPanel_account;
	private JTextArea area_removeReaderPanel_displayReaderInfo;
	private JLabel label_removeReaderPanel_account;
	private JButton btn_removeReaderPanel_remove;//先查询显示信息后显示删除按钮
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
		
		addListener();
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
	
	private void addListener() {
		btn_addReaderPanel_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(	  field_addReaderPanel_account.getText().equals("")
					||field_addReaderPanel_name.getText().equals("")
					||field_addReaderPanel_isAdmin.getText().equals("")
					||field_addReaderPanel_password.getText().equals("")) {
					JOptionPane.showMessageDialog(MainFrame.instance, "请填上所有的空！");
					return;
				}
				
				String account = field_addReaderPanel_account.getText();
				if(!(DBmanager.getReader(account, WayOfGetReader.SEARCH_FOR_ACCOUNT)==null)) {
					JOptionPane.showMessageDialog(MainFrame.instance, "账号已存在！");
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
					JOptionPane.showMessageDialog(MainFrame.instance, "用户添加成功！");
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
					JOptionPane.showMessageDialog(MainFrame.instance, "用户不存在！");
					return;
				}
				if(flag_removeReaderPanel&&account.equals(flag_removeReaderPanel_account)
						/*
						 * 检查 是否进入确认模式 和 当前输入的账号是否与刚刚搜索的相匹配
						 * 如若不是，则开始搜索账号 并 设定 刚刚搜索过的账号
						 */
						) {
					DBmanager.removeReader(reader);
					JOptionPane.showMessageDialog(MainFrame.instance, "用户删除成功！");
					area_removeReaderPanel_displayReaderInfo.setText("");
					flag_removeReaderPanel = false; //退出搜索模式
				}else {
					flag_removeReaderPanel_account = account; //设定 刚刚搜索过的账号
					area_removeReaderPanel_displayReaderInfo.setText("");
					area_removeReaderPanel_displayReaderInfo.append("-------------------"+"\n");
					area_removeReaderPanel_displayReaderInfo.append("用户账号："+reader.getAccount()+"\n");
					area_removeReaderPanel_displayReaderInfo.append("用户昵称："+reader.getName()+"\n");
					area_removeReaderPanel_displayReaderInfo.append("是否是管理员："+reader.isAdmin()+"\n");
					area_removeReaderPanel_displayReaderInfo.append("再按一次 删除键 删除用户"+"\n");
					area_removeReaderPanel_displayReaderInfo.append("-------------------"+"\n");
					flag_removeReaderPanel = true; //进入搜索模式
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
						JOptionPane.showMessageDialog(MainFrame.instance, "该用户不存在");
						return;
					}else {
						field_addReaderPanel_password.setText("");
						field_updateReaderPanel_name.setText(reader.getName());
						field_updateReaderPanel_isAdmin.setText(reader.isAdmin()+"");
						flag_account = account; //设置 刚刚搜索过的用户账号
						flag_updateReaderPanel = true; //搜索成功
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
					JOptionPane.showMessageDialog(MainFrame.instance, "请先搜索一次该用户ID，然后再进行更新！");
					return;
				}else if(!account.equals(flag_account)){
					JOptionPane.showMessageDialog(MainFrame.instance, "您写入的账号与刚刚搜索的不同，重新搜索一次此账号吧！");
					flag_updateReaderPanel = false;
					return;
				}
				/*
				 * 检查 是否查询过 和 刚刚查询的账号 和 当前写入的账号 是否相同 
				 * 若不同，则返回
				 */
				else {
				String password = field_updateReaderPanel_password.getText();
				String name = field_updateReaderPanel_name.getText();
				String str_isAdmin = field_updateReaderPanel_isAdmin.getText();
				boolean isAdmin = Boolean.valueOf(str_isAdmin).booleanValue();
				if(account.equals("")||password.equals("")||name.equals("")||str_isAdmin.equals("")) {
					JOptionPane.showMessageDialog(MainFrame.instance, "请填上所有的空");
					return;
				}else {
					Reader reader = new Reader(account, name, isAdmin,password);
					DBmanager.setReader(reader);
					field_updateReaderPanel_account.setText("");
					field_updateReaderPanel_password.setText("");
					field_updateReaderPanel_name.setText("");
					field_updateReaderPanel_isAdmin.setText("");
					JOptionPane.showMessageDialog(MainFrame.instance, "修改成功！");
				}
			  }
			}
		});
	}
}
