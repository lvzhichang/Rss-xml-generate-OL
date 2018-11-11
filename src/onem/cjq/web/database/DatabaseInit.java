package onem.cjq.web.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInit {
	static private DatabaseInit instance; // 声明这样一个变量，它可以完成唯一实例
	//如果instance未空，就调用构造函数，构造函数写成private,防止其他对象调用！
	static synchronized public DatabaseInit getInstance() {
		System.out.println("database init");
		if (instance == null) {
			instance = new DatabaseInit();
		}
			return instance;
		}

	private void init()
	{
		DatabaseConnection dc=new DatabaseConnection();
		Connection conn=dc.getConnection();
		Statement st=null;
		try {
			st=conn.createStatement();
			String sql = "CREATE TABLE RSS " +
			           "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
			           " XML TEXT ,"
			           + "WEB_LINK TEXT ,"
			           + "ENCODE TEXT ,"
			           + "GLOBAL_REG TEXT,"
			           + "ITEM_REG TEXT ,"
			           + "FEED_TITLE TEXT ,"
			           + "FEED_LINK TEXT ,"
			           + "FEED_DESR TEXT ,"
			           + "ITEM_TITLE_FORMAT TEXT ,"
			           + "ITEM_LINK_FORMAT TEXT ,"
			           + "ITEM_DESR_FORMAT TEXT "
			           + ")"; 
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("something error,The database table may have been created,so we do not create again");
		}finally {
			try {
				st.close();
				dc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//构造函数调用init()函数来完成初始化，init()函数完成你的一切功能
	private DatabaseInit() {
		init();
	}
}
