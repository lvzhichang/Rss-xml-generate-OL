package onem.cjq.web.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseConnection {
	private Connection conn ;
	private static Lock lock=new ReentrantLock();
	
    public DatabaseConnection() {
		// TODO Auto-generated constructor stub
	}
    
    public Connection getConnection(){
    	lock.lock();
    	Connection c = null;
    	System.out.println("connection start");
        try {
		  Class.forName("org.sqlite.JDBC");
		  c = DriverManager.getConnection("jdbc:sqlite:rss.db");
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}
    
	public void close() throws Exception {
		
		System.out.println("connection end");
		if(this.conn != null){
			try{
				this.conn.close() ;
			}catch(Exception e){
				throw e ;
			}
		}
		lock.unlock();
	}
}
