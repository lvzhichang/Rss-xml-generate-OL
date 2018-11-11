package onem.cjq.web.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onem.cjq.web.database.RssDaoProxy;
import onem.cjq.web.mod.RssEntry;

public class RssBroServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=new Integer(req.getParameter("id"));
		resp.setContentType("text/xml; charset=utf-8");
		resp.setHeader("Date", new Date().toString());
		
		try {
			RssEntry re=new RssDaoProxy().findById(id);
			resp.setCharacterEncoding("utf-8");
			PrintWriter pw=resp.getWriter();
			if(re==null){
				pw.print("Rss For "+id+"Is Not Exists!");
			}else
			{
				pw.print(re.getXML());
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
