package onem.cjq.web.main;

public enum RssHandleType {
	RSS_LINK(1),RSS_EXTRACT(2),RSS_GENERATE(3),RSS_DELETE(4);
	
	@SuppressWarnings("unused")
	private final int value;
    private RssHandleType(int value) {
        this.value = value;
    }
    private int getValue(){
    	return this.value;
    }
    
    public static RssHandleType getRssEnumByCode(int type){
        for(RssHandleType rssenum : RssHandleType.values()){
          if(type==rssenum.getValue()){
            return rssenum;
          }
        }
        return null;
      }
}
