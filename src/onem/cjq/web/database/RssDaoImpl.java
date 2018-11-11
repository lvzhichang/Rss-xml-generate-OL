package onem.cjq.web.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringEscapeUtils;

import onem.cjq.web.mod.RssEntry;

public class RssDaoImpl implements IRssDao{
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private static Lock lock=new ReentrantLock();
	
	public RssDaoImpl(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}
	
	@Override
	public int add(RssEntry entry) throws Exception {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("add start");
		String sql="insert into RSS (XML,WEB_LINK,ENCODE,GLOBAL_REG,ITEM_REG," + 
				"FEED_TITLE,FEED_LINK,FEED_DESR," + 
				"ITEM_TITLE_FORMAT,ITEM_LINK_FORMAT,ITEM_DESR_FORMAT) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		this.pstmt=this.conn.prepareStatement(sql);
		//this.pstmt.setInt(1,1);
		this.pstmt.setString(1,StringEscapeUtils.escapeSql(entry.getXML()));
		this.pstmt.setString(2,StringEscapeUtils.escapeSql(entry.getWEB_LINK()));
		this.pstmt.setString(3,StringEscapeUtils.escapeSql(entry.getENCODE()));
		this.pstmt.setString(4,StringEscapeUtils.escapeSql(entry.getGLOBAL_REG()));
		this.pstmt.setString(5,StringEscapeUtils.escapeSql(entry.getITEM_REG()));
		this.pstmt.setString(6,StringEscapeUtils.escapeSql(entry.getFEED_TITLE()));
		this.pstmt.setString(7,StringEscapeUtils.escapeSql(entry.getFEED_LINK()));
		this.pstmt.setString(8,StringEscapeUtils.escapeSql(entry.getFEED_DESR()));
		this.pstmt.setString(9,StringEscapeUtils.escapeSql(entry.getITEM_TITLE_FORMAT()));
		this.pstmt.setString(10,StringEscapeUtils.escapeSql(entry.getITEM_LINK_FORMAT()));
		this.pstmt.setString(11,StringEscapeUtils.escapeSql(entry.getITEM_DESR_FORMAT()));
		if(this.pstmt.executeUpdate()>0){
			sql="select last_insert_rowid() from RSS";
			this.pstmt=this.conn.prepareStatement(sql);
			ResultSet rs =this.pstmt.executeQuery();
			if(rs.next())
			{
				lock.unlock();
				System.out.println("add end");
				int i=rs.getInt(1);
				this.pstmt.close();	
				return i;
			}	
		}
		this.pstmt.close();	
		System.out.println("add end");
		lock.unlock();
		return -1;
	}

	@Override
	public boolean deleteById(int i) throws SQLException {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("delete start");
		boolean flag=false;
		String sql="DELETE FROM RSS WHERE ID=?";
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, i);
		if(this.pstmt.executeUpdate()>0){
			flag=true;
		}
		this.pstmt.close();	
		System.out.println("delete end");
		lock.unlock();
		return flag;
	}

	@Override
	public boolean updateAllById(int i, RssEntry entry) throws SQLException {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("update start");
		boolean flag=false;
		String sql="UPDATE RSS SET XML=?,WEB_LINK=?,ENCODE=?,GLOBAL_REG=?,ITEM_REG=?,"
				+ "FEED_TITLE=?,FEED_LINK=?,FEED_DESR=?,"
				+ "ITEM_TITLE_FORMAT=?,ITEM_LINK_FORMAT=?,ITEM_DESR_FORMAT=?"
				+ "WHERE ID = ?";
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1,StringEscapeUtils.escapeSql(entry.getXML()));
		this.pstmt.setString(2,StringEscapeUtils.escapeSql(entry.getWEB_LINK()));
		this.pstmt.setString(3,StringEscapeUtils.escapeSql(entry.getENCODE()));
		this.pstmt.setString(4,StringEscapeUtils.escapeSql(entry.getGLOBAL_REG()));
		this.pstmt.setString(5,StringEscapeUtils.escapeSql(entry.getITEM_REG()));
		this.pstmt.setString(6,StringEscapeUtils.escapeSql(entry.getFEED_TITLE()));
		this.pstmt.setString(7,StringEscapeUtils.escapeSql(entry.getFEED_LINK()));
		this.pstmt.setString(8,StringEscapeUtils.escapeSql(entry.getFEED_DESR()));
		this.pstmt.setString(9,StringEscapeUtils.escapeSql(entry.getITEM_TITLE_FORMAT()));
		this.pstmt.setString(10,StringEscapeUtils.escapeSql(entry.getITEM_LINK_FORMAT()));
		this.pstmt.setString(11,StringEscapeUtils.escapeSql(entry.getITEM_DESR_FORMAT()));
		this.pstmt.setInt(12, i);
		if(this.pstmt.executeUpdate()>0){
			flag=true;
		}
		this.pstmt.close();	
		System.out.println("update end");
		lock.unlock();
		return flag;
	}

	@Override
	public RssEntry findById(int i) throws SQLException {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("find id start");
		RssEntry re=null;
		String sql="SELECT * from RSS WHERE ID =?";
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setInt(1,i);
		ResultSet rs =this.pstmt.executeQuery();
		if(rs.next()) {
			re=new RssEntry();
			re.setID(rs.getInt(1));
			re.setXML(rs.getString(2));
			re.setWEB_LINK(rs.getString(3));
			re.setENCODE(rs.getString(4));
			re.setGLOBAL_REG(rs.getString(5));
			re.setITEM_REG(rs.getString(6));
			re.setFEED_TITLE(rs.getString(7));
			re.setFEED_LINK(rs.getString(8));
			re.setFEED_DESR(rs.getString(9));
			re.setITEM_TITLE_FORMAT(rs.getString(10));
			re.setITEM_LINK_FORMAT(rs.getString(11));
			re.setITEM_DESR_FORMAT(rs.getString(12));
		}
		this.pstmt.close() ;
		System.out.println("find id end");
		lock.unlock();
		return re;
	}

	@Override
	public List<RssEntry> findAll() throws SQLException {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("find all start");
		List<RssEntry> list=new ArrayList<RssEntry>();
		String sql="SELECT * from RSS";
		this.pstmt=this.conn.prepareStatement(sql);
		ResultSet rs =this.pstmt.executeQuery();
		while(rs.next()) {
			RssEntry re=new RssEntry();
			re.setID(rs.getInt(1));
			re.setXML(rs.getString(2));
			re.setWEB_LINK(rs.getString(3));
			re.setENCODE(rs.getString(4));
			re.setGLOBAL_REG(rs.getString(5));
			re.setITEM_REG(rs.getString(6));
			re.setFEED_TITLE(rs.getString(7));
			re.setFEED_LINK(rs.getString(8));
			re.setFEED_DESR(rs.getString(9));
			re.setITEM_TITLE_FORMAT(rs.getString(10));
			re.setITEM_LINK_FORMAT(rs.getString(11));
			re.setITEM_DESR_FORMAT(rs.getString(12));
			list.add(re);
		}
		this.pstmt.close() ;
		System.out.println("find all end");
		lock.unlock();
		return list;
	}
}
