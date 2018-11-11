package onem.cjq.web.main;

import onem.cjq.web.mod.RssEntry;

public interface RssReqHandle {
	public RssReqHandle handle(RssEntry re);
	public String[] get();
	public void setParam(Object param[]);
}
