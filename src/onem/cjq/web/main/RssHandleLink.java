package onem.cjq.web.main;

import onem.cjq.rss_generator.WebGetter;
import onem.cjq.web.mod.RssEntry;

public class RssHandleLink implements RssReqHandle{
	private String webPath;
	private String encode;
	
	@Override
	public RssReqHandle handle(RssEntry re) {
		// TODO Auto-generated method stub
		this.webPath=re.getWEB_LINK();
		this.encode=re.getENCODE();
		return this;
	}

	@Override
	public String[] get() {
		// TODO Auto-generated method stub
		try {
			String result=new WebGetter().get(webPath, encode);
			result=result.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
			return new String[]{result};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setParam(Object[] param) {
		// TODO Auto-generated method stub
		
	}

}
