package de.mreturkey.sql.clausel;

import java.util.ArrayList;
import java.util.List;

import de.mreturkey.sql.util.WhereEntry;

public class WhereClausel implements Clausel {

	private final List<WhereEntry<?>> entries;
	
	public WhereClausel(WhereEntry<?>... entries) {
		this.entries = new ArrayList<>();
		for(WhereEntry<?> we : entries) {
			this.add(we);
		}
	}
	
	public <V> WhereClausel(String column, String operator, V value) {
		this(new WhereEntry<V>(column, operator, value));
	}

	public WhereClausel() {
		this.entries = new ArrayList<>();
	}
	
	public <V> void add(WhereEntry<V> entry) {
		entries.add(entry);
	}
	
	public <V> void add(String column, String operator, V value) {
		entries.add(new WhereEntry<V>(column, operator, value));
	}
	
	public WhereEntry<?> get(int index) {
		return entries.get(index);
	}
	
	@Override
	public String toString() {
		String res = "";
		for(WhereEntry<?> e : entries) {
			System.out.println(e.toSQL());
		}
		return res;
	}
	
	/* TESTS */
	//17303	 <-> 17919 Millis | mit <V>
	//17934 <-> 17956
}
