package de.mreturkey.sql.cache;

import java.util.HashMap;

import de.mreturkey.sql.result.CachedResult;

public class Cache {

	private final int id;
	private final HashMap<String, CachedResult> cachedResults = new HashMap<>();
	
	protected Cache(int id) {
		this.id = id;
	}
	
	public int getCacheID() {
		return id;
	}
	
	public void add(String query, CachedResult cachedResult) {
		cachedResults.put(query, cachedResult);
	}
	
	public void remove(String query) {
		cachedResults.remove(query);
	}
	
	public void contains(String query) {
		cachedResults.containsKey(query);
	}
	
	public void contains(CachedResult cachedResult) {
		cachedResults.containsValue(cachedResult);
	}
	
}
