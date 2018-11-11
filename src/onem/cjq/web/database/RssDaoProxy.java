package onem.cjq.web.database;

import java.util.List;
import onem.cjq.web.mod.RssEntry;

public class RssDaoProxy implements IRssDao{
	private DatabaseConnection dbc=null;
	private RssDaoImpl dao=null;
	
	public RssDaoProxy() {
		// TODO Auto-generated constructor stub
		this.dbc=new DatabaseConnection();
		this.dao=new RssDaoImpl(this.dbc.getConnection());
	}
	
	@Override
	public int add(RssEntry entry) throws Exception {
		// TODO Auto-generated method stub
		int id=-1;
		try{
			id=this.dao.add(entry);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return id;
	}

	@Override
	public boolean deleteById(int i) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try{
			flag=this.dao.deleteById(i);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean updateAllById(int i, RssEntry entry) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try{
			flag=this.dao.updateAllById(i,entry);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public RssEntry findById(int i) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		RssEntry re = null ;
		try{
			re = this.dao.findById(i) ;
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return re ;
	}

	@Override
	public List<RssEntry> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<RssEntry> all = null ;
		try{
			all = this.dao.findAll() ;
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return all ;
	}
}
