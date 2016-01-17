package de.mreturkey.sql.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Deprecated //Wait for a nice idea. :)
public final class CacheManager {

	private static final ExecutorService fixedPool = Executors.newFixedThreadPool(1);
	private static final CacheChecker checker = new CacheChecker();
	private static boolean running = false;
	
	protected final static List<Object> CACHES = Collections.synchronizedList(new ArrayList<>(250));
	
	private CacheManager() {/* only static */} 
	
	public static void startCaching() {
		if(!running) {
			fixedPool.execute(checker);
			running = true;
		}
	}
	
	public static void stopCaching() {
		if(running) {
			fixedPool.shutdownNow();
			running = false;
		}
	}
	
	public static Cache createCache() {
		if(!running) return null;
		final Cache cache = new Cache(CACHES.size());
		synchronized (CACHES) {
			CACHES.add(cache);
		}
		return cache;
	}
}

class CacheChecker implements Runnable {
	
	private final long toWait = 1000;
	
	protected CacheChecker() {}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(toWait);
				synchronized (CacheManager.CACHES) {
					for(Object cache : CacheManager.CACHES) {
						
					}
				}
			}
		} catch (Exception e) {
			//do nothing
		}
	}
}

