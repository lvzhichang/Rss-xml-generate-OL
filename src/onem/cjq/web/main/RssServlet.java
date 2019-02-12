package onem.cjq.web.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import onem.cjq.mod.RssMatchResult;
import onem.cjq.rss_generator.SafeRssRegex;
import onem.cjq.rss_generator.SimpleRssGenerateXML;
import onem.cjq.web.database.DatabaseInit;
import onem.cjq.web.database.RssDaoProxy;
import onem.cjq.web.mod.RssEntry;
import onem.cjq.web.thread.RssMainThread;

public class RssServlet extends HttpServlet{
	RssMainThread rmt=null;
	
	public RssServlet() {
		// TODO Auto-generated constructor stub
		DatabaseInit.getInstance();
		rmt=new RssMainThread();
		rmt.start();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/json; charset=utf-8");
		
		RssHandleType reqType=null;
		String returnResult[]=null;
		String web_source=null;
		String reqArray[]=req.getParameterValues("array");
		RssEntry re=null;
		reqType=RssHandleType.getRssEnumByCode(new Integer(reqArray[0]));
		
		if((re=(RssEntry) req.getSession().getAttribute("RssEntry"))==null) {
			re=new RssEntry();
		}
			
		switch(reqType)
		{
			case RSS_LINK:
				String result[]=new String[3];
				re.setWEB_LINK(reqArray[1]);
				re.setENCODE(reqArray[2]);
				web_source=new RssHandleLink().handle(re).get()[0];
				req.getSession().setAttribute("web_source", web_source);
				String web_info[]=new SafeRssRegex().exportWebInfo(web_source);
				result[0]=web_source;
				System.arraycopy(web_info, 0, result, 1, web_info.length);
				returnResult=result;
				break;
			case RSS_EXTRACT:
				RssHandleExtract rhe=null;
				web_source=(String) req.getSession().getAttribute("web_source");
				if(web_source==null || web_source=="") {
					returnResult=new String[] {"Error"};
				}else{
					rhe=new RssHandleExtract();
					rhe.setParam(new String[] {web_source});
					re.setGLOBAL_REG(reqArray[1]);
					re.setITEM_REG(reqArray[2]);
					rhe.handle(re);
					returnResult=rhe.get();
					req.getSession().setAttribute("web_mainList",rhe.getRaw());
				}
				break;
			case RSS_GENERATE:
				RssHandleGenerate rhg=null;
				@SuppressWarnings("unchecked") 
				List<RssMatchResult> rmr=(List<RssMatchResult>) req.getSession().getAttribute("web_mainList");
				if(rmr==null) {
					returnResult=new String[] {"Error"};
				}else {
					rhg=new RssHandleGenerate();
					rhg.setParam(new Object[] {rmr});
					re.setFEED_TITLE(reqArray[1]);
					re.setFEED_LINK(reqArray[2]);
					re.setFEED_DESR(reqArray[3]);
					re.setITEM_TITLE_FORMAT(reqArray[4]);
					re.setITEM_LINK_FORMAT(reqArray[5]);
					re.setITEM_DESR_FORMAT(reqArray[6]);
					rhg.handle(re);
					returnResult=rhg.get();
				}
				String rss_xml=new SimpleRssGenerateXML().generate(rmr, 
						new String[] {re.getITEM_TITLE_FORMAT(),re.getITEM_LINK_FORMAT(),re.getITEM_DESR_FORMAT()}, 
						new String[] {re.getFEED_TITLE(),re.getFEED_DESR()});
				re.setXML(rss_xml);
				
				try {
					int openID=(int) req.getSession().getAttribute("openID");
					if(openID>-1) {
						System.out.println("update all");
						new RssDaoProxy().updateAllById(openID, re);
						rmt.myNotify("build", openID);
					}else {
						System.out.println("add one");
						int id=new RssDaoProxy().add(re);
						if(id>-1)
							rmt.myNotify("build", id);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case RSS_DELETE:
				boolean flag;
				try {
					rmt.myNotify("del", new Integer(reqArray[1]));
					flag=new RssDaoProxy().deleteById(new Integer(reqArray[1]));
					if(flag)					
						returnResult=new String[] {"true"};
					else
						returnResult=new String[] {"false"};
				}catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}		
		req.getSession().setAttribute("RssEntry", re);
		JSONArray jsonResult=JSONArray.fromObject(returnResult);
		resp.getWriter().print(jsonResult);
		resp.getWriter().flush();  
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
