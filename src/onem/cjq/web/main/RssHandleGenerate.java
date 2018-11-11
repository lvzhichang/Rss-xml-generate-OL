package onem.cjq.web.main;

import java.util.List;

import onem.cjq.mod.RssEachItem;
import onem.cjq.mod.RssMatchResult;
import onem.cjq.rss_generator.SafeRssRegex;
import onem.cjq.web.mod.RssEntry;

public class RssHandleGenerate implements RssReqHandle{
	private List<RssMatchResult>  list;
	private String itemTitleFormat;
	private String itemLinkFormat;
	private String itemDesrFormat;
	
	@Override
	public RssReqHandle handle(RssEntry re) {
		this.itemTitleFormat=re.getITEM_TITLE_FORMAT();
		this.itemLinkFormat=re.getITEM_LINK_FORMAT();
		this.itemDesrFormat=re.getITEM_DESR_FORMAT();
		return this;
	}

	@Override
	public String[] get() {
		String result="";
		List<RssEachItem> rei=new SafeRssRegex().exportRssMainList(list, new String[] {itemTitleFormat,itemLinkFormat,itemDesrFormat});
		for(RssEachItem reii:rei)
			result+=reii.getTitle()+"\n"+reii.getLink()+"\n"+reii.getDesr()+"\n";
		return new String[] {result};
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setParam(Object[] param) {
		this.list=(List<RssMatchResult>) param[0];
	}

}
