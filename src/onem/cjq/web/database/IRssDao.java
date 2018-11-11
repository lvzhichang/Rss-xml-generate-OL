package onem.cjq.web.database;

import java.util.List;

import onem.cjq.web.mod.RssEntry;

public interface IRssDao {
	public int add(RssEntry entry) throws Exception;
	public boolean deleteById(int i) throws Exception;
	public boolean updateAllById(int i,RssEntry entry) throws Exception;
	public RssEntry findById(int i) throws Exception; 
	public List<RssEntry> findAll() throws Exception;

}
