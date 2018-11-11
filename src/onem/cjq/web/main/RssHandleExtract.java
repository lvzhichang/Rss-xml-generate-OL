package onem.cjq.web.main;

import java.util.List;

import onem.cjq.mod.RssMatchResult;
import onem.cjq.rss_generator.SafeRssRegex;
import onem.cjq.web.mod.RssEntry;

public class RssHandleExtract implements RssReqHandle{
	private String global_reg;
	private String item_reg;
	private String web_source;
	private List<RssMatchResult> list_raw;
	
	@Override
	public RssReqHandle handle(RssEntry re) {
		// TODO Auto-generated method stub
		this.global_reg=re.getGLOBAL_REG();
		this.item_reg=re.getITEM_REG();
		return this;
	}

	@Override
	public String[] get() {
		String r_r="";
		List<RssMatchResult> g_r=new SafeRssRegex().exportMatchContent(web_source, global_reg);
		for(RssMatchResult rmr:g_r)
			r_r+=rmr.getMatchContent();
		List<RssMatchResult> i_r=new SafeRssRegex().exportMatchContent(r_r, item_reg);
		r_r="";
		list_raw=i_r;
		for(RssMatchResult rmr:i_r)
			r_r=r_r+"{%"+rmr.getMatchIndex()+"}"+rmr.getMatchContent()+"\n";
		return new String[] {r_r};
	}
	
	public List<RssMatchResult> getRaw() {
		return this.list_raw;
	}

	@Override
	public void setParam(Object[] param) {
		// TODO Auto-generated method stub
		this.web_source=(String) param[0];
	}

}
