package onem.cjq.web.mod;

public class RssEntry {
	private int ID;
	private String XML;
	private String WEB_LINK;
	private String ENCODE;
	private String GLOBAL_REG;
	private String ITEM_REG;
	private String FEED_TITLE;
	private String FEED_LINK;
	private String FEED_DESR;
	private String ITEM_TITLE_FORMAT;
	private String ITEM_LINK_FORMAT;
	private String ITEM_DESR_FORMAT;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getXML() {
		return XML;
	}
	public void setXML(String xML) {
		XML = xML;
	}
	public String getWEB_LINK() {
		return WEB_LINK;
	}
	public void setWEB_LINK(String wEB_LINK) {
		WEB_LINK = wEB_LINK;
	}
	public String getENCODE() {
		return ENCODE;
	}
	public void setENCODE(String eNCODE) {
		ENCODE = eNCODE;
	}
	public String getGLOBAL_REG() {
		return GLOBAL_REG;
	}
	public void setGLOBAL_REG(String gLOBAL_REG) {
		GLOBAL_REG = gLOBAL_REG;
	}
	public String getITEM_REG() {
		return ITEM_REG;
	}
	public void setITEM_REG(String iTEM_REG) {
		ITEM_REG = iTEM_REG;
	}
	public String getFEED_TITLE() {
		return FEED_TITLE;
	}
	public void setFEED_TITLE(String fEED_TITLE) {
		FEED_TITLE = fEED_TITLE;
	}
	public String getFEED_LINK() {
		return FEED_LINK;
	}
	public void setFEED_LINK(String fEED_LINK) {
		FEED_LINK = fEED_LINK;
	}
	public String getFEED_DESR() {
		return FEED_DESR;
	}
	public void setFEED_DESR(String fEED_DESR) {
		FEED_DESR = fEED_DESR;
	}
	public String getITEM_TITLE_FORMAT() {
		return ITEM_TITLE_FORMAT;
	}
	public void setITEM_TITLE_FORMAT(String iTEM_TITLE_FORMAT) {
		ITEM_TITLE_FORMAT = iTEM_TITLE_FORMAT;
	}
	public String getITEM_LINK_FORMAT() {
		return ITEM_LINK_FORMAT;
	}
	public void setITEM_LINK_FORMAT(String iTEM_LINK_FORMAT) {
		ITEM_LINK_FORMAT = iTEM_LINK_FORMAT;
	}
	public String getITEM_DESR_FORMAT() {
		return ITEM_DESR_FORMAT;
	}
	public void setITEM_DESR_FORMAT(String iTEM_DESR_FORMAT) {
		ITEM_DESR_FORMAT = iTEM_DESR_FORMAT;
	}
	public RssEntry(int iD, String xML, String wEB_LINK, String eNCODE, String gLOBAL_REG, String iTEM_REG,
			String fEED_TITLE, String fEED_LINK, String fEED_DESR, String iTEM_TITLE_FORMAT, String iTEM_LINK_FORMAT,
			String iTEM_DESR_FORMAT) {
		super();
		ID = iD;
		XML = xML;
		WEB_LINK = wEB_LINK;
		ENCODE = eNCODE;
		GLOBAL_REG = gLOBAL_REG;
		ITEM_REG = iTEM_REG;
		FEED_TITLE = fEED_TITLE;
		FEED_LINK = fEED_LINK;
		FEED_DESR = fEED_DESR;
		ITEM_TITLE_FORMAT = iTEM_TITLE_FORMAT;
		ITEM_LINK_FORMAT = iTEM_LINK_FORMAT;
		ITEM_DESR_FORMAT = iTEM_DESR_FORMAT;
	}
	public RssEntry() {
		super();
	}
	@Override
	public String toString() {
		return "RssEntry [ID=" + ID + ", XML=" + XML + ", WEB_LINK=" + WEB_LINK + ", ENCODE=" + ENCODE + ", GLOBAL_REG="
				+ GLOBAL_REG + ", ITEM_REG=" + ITEM_REG + ", FEED_TITLE=" + FEED_TITLE + ", FEED_LINK=" + FEED_LINK
				+ ", FEED_DESR=" + FEED_DESR + ", ITEM_TITLE_FORMAT=" + ITEM_TITLE_FORMAT + ", ITEM_LINK_FORMAT="
				+ ITEM_LINK_FORMAT + ", ITEM_DESR_FORMAT=" + ITEM_DESR_FORMAT + "]";
	}
	
}
