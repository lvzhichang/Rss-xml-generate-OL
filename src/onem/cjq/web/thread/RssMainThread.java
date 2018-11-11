package onem.cjq.web.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import onem.cjq.web.database.RssDaoProxy;
import onem.cjq.web.mod.RssEntry;

public class RssMainThread extends Thread{
	Map<Integer, Object> map;
	ScheduledExecutorService es;
	String method="";
	int id;
	
	public RssMainThread() {
		// TODO Auto-generated constructor stub
		System.out.println("主线程初始化");
	}
	public synchronized void myNotify(String method,int id){
		this.method=method;
		this.id=id;
		notifyAll();
	}
	
	public synchronized void closeAllThread(){
		es.shutdown();
		Thread.currentThread().interrupt();
	}
	
	public synchronized void run(){
		es=Executors.newScheduledThreadPool(100);
		map=new HashMap<Integer,Object>();
		
		try {
			List<RssEntry> feed_list=new RssDaoProxy().findAll();
			Iterator<RssEntry> feed_list_it=feed_list.iterator();
			System.out.println("正在从数据库中调出需要执行的rss任务~~~");
			while(feed_list_it.hasNext()){
				RssEntry re=feed_list_it.next();
				map.put(re.getID(), es.scheduleAtFixedRate(new RssSonThread(re.getID()), 0, 30, TimeUnit.MINUTES));
				System.out.println(re.getID()+"  正在被执行。。。。。。等待五秒");
				wait(5000);
			}
			System.out.println("数据库任务执行完成，开始进入等待状态。");
			wait();
			ScheduledFuture<?> sf;
			while(true){
				System.out.println("后台线程被唤醒了，执行任务"+method+"    "+id);
				switch(method){
				case "del":
					sf=(ScheduledFuture<?>) map.get(id);
					if(sf!=null){
						if(sf.cancel(true)){
							System.out.println(id+"  已经被删除!");
							map.remove(id);
						}
					}
					break;
				case "build":
					sf=(ScheduledFuture<?>) map.get(id);
					if(sf!=null){
						if(sf.cancel(true)){
							System.out.println(id+"  旧任务已经被删除!");
							map.remove(id);
						}
						map.put(id, es.scheduleAtFixedRate(new RssSonThread(id), 0, 30, TimeUnit.MINUTES));
						System.out.println(id+"  新任务已经被建立!");
					}else{
						map.put(id, es.scheduleAtFixedRate(new RssSonThread(id), 0, 30, TimeUnit.MINUTES));
					}
					break;
				}
				System.out.println("本次任务执行完成，进入等待。。。。。。");
				wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
