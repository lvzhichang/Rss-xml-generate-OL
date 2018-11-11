package onem.cjq.web.thread;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import onem.cjq.mod.RssEachItem;
import onem.cjq.mod.RssMatchResult;
import onem.cjq.rss_generator.SafeRssRegex;
import onem.cjq.rss_generator.WebGetter;
import onem.cjq.web.database.RssDaoProxy;
import onem.cjq.web.mod.RssEntry;

public class RssSonThread extends Thread{
	
	private int i;
	public RssSonThread(int i) {
		// TODO Auto-generated constructor stub
		this.i=i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(i+"被唤醒");
			String temp="";
			String web_src="";
			String rss_xml="";
			RssEntry re=new RssDaoProxy().findById(i);
			web_src=new WebGetter().get(re.getWEB_LINK(), re.getENCODE());
			List<RssMatchResult> g_r=new SafeRssRegex().exportMatchContent(web_src, re.getGLOBAL_REG());
			for(RssMatchResult rmr:g_r)
				temp+=rmr.getMatchContent();
			List<RssMatchResult> i_r=new SafeRssRegex().exportMatchContent(temp, re.getITEM_REG());	
			String[] webInfo=new SafeRssRegex().exportWebInfo(web_src);
			List<RssEachItem> mainList=new SafeRssRegex().exportRssMainList(i_r, 
					new String[] {re.getITEM_TITLE_FORMAT(),re.getITEM_LINK_FORMAT(),re.getITEM_DESR_FORMAT()});
			
			rss_xml="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";
			rss_xml+="<rss version=\"2.0\">\r\n";
			rss_xml+="<channel>\r\n";
			rss_xml+="<title><![CDATA["+webInfo[0]+"]]></title>\r\n";
			rss_xml+="<description><![CDATA["+webInfo[1]+"]]></description>\r\n";
				for(RssEachItem rei:mainList)
				{
					rss_xml+="<item>\r\n";
					rss_xml+="<title><![CDATA["+rei.getTitle()+"]]></title>\r\n";
					rss_xml+="<link>"+StringEscapeUtils.escapeXml(rei.getLink())+"</link>\r\n";
					rss_xml+="<description><![CDATA["+rei.getDesr()+"]]></description>\r\n";
					rss_xml+="</item>\r\n";
				}
			rss_xml+="</channel>\r\n";
			rss_xml+="</rss>\r\n";
		
			re.setXML(rss_xml);
			new RssDaoProxy().updateAllById(i, re);
			System.out.println(i+"执行完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
