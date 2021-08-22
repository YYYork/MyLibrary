package Demo;

import myFrames.LoginFrame;

public class Demo {
	public static void main(String[] args) {
		try {
			Class.forName("mySQLManager.DBmanager");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LoginFrame();
	}
}
/*
 * author: yyyork email: 2264117533@qq.com website: yyyork.github.io QQ:
 * 2264117533
 */
