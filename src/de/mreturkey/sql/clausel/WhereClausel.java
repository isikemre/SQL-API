package de.mreturkey.sql.clausel;

import java.util.ArrayList;
import java.util.List;

import de.mreturkey.sql.operator.LogicalOperator;
import de.mreturkey.sql.util.WhereEntry;

public class WhereClausel implements Clausel {
	
	/**
	 * The first (index:0) entry in this list is the main WhereEntry.<br>
	 * That means, that all other entries need a LogicalOperator, but the main WhereEntry not (LogicalOperator is there 'null').
	 */
	private final List<WhereEntry<?>> entries;
	private String lastSQL, lastPreparedSQL;
	private boolean changed = true, changedPrepared = true;
	
	public WhereClausel(WhereEntry<?>... entries) {
		this.entries = new ArrayList<>();
		for(WhereEntry<?> we : entries) this.add(we);
	}
	
	public <V> WhereClausel(String column, String operator, V value) {
		this(new WhereEntry<V>(column, operator, value, null));
	}

	public WhereClausel() {
		this.entries = new ArrayList<>();
	}
	
	public <V> void add(WhereEntry<V> entry) {
		if(entries.isEmpty()) {
			entries.add(entry);
		} else {
			if(entry.getLogicalOperator() == null) throw new NullPointerException("logicalOperator is null (Use OR or AND)");
			entries.add(entry);
		}
		changed = true;
	}
	public <V> void add(String column, String operator, V value, LogicalOperator logicalOperator) {
		add(new WhereEntry<V>(column, operator, value, logicalOperator));
	}
	
	
	public <V> void main(WhereEntry<V> entry) {
		if(entries.isEmpty()) {
			entries.add(entry);
		} else {
			entries.set(0, entry);
		}
		changed = true;
	}
	public <V> void main(String column, String operator, V value) {
		main(new WhereEntry<V>(column, operator, value, null));
	}
	
	public <V> void and(WhereEntry<V> entry) {
		checkMainEntry();
		if(entry.getLogicalOperator() != LogicalOperator.AND) throw new IllegalArgumentException("LogicalOperator is not AND");
		entries.add(entry);
		changed = true;
	}
	public <V> void and(String column, String operator, V value) {
		and(new WhereEntry<V>(column, operator, value, LogicalOperator.AND));
	}
	
	public <V> void or(WhereEntry<V> entry) {
		checkMainEntry();
		if(entry.getLogicalOperator() != LogicalOperator.OR) throw new IllegalArgumentException("LogicalOperator is not OR");
		entries.add(entry);
		changed = true;
	}
	public <V> void or(String column, String operator, V value) {
		or(new WhereEntry<V>(column, operator, value, LogicalOperator.OR));
	}
	
	private void checkMainEntry() {
		if(entries.isEmpty()) entries.add(null);
	}
	
	public WhereEntry<?> get(int index) {
		return entries.get(index);
	}
	
	public List<WhereEntry<?>> getEntries() {
		return entries;
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	public boolean isEmpty() {
		return entries.isEmpty();
	}
	
	@Override
	public String toSQL() {
		if(entries == null || entries.isEmpty()) return "";
		if(!changed) return lastSQL;
		if(!entries.isEmpty() && entries.get(0) == null) throw new NullPointerException("the first entry (main) is missing");
		String res = "";
		int i = 0;
		for(WhereEntry<?> e : entries) {
			res += e.toSQL(i == 0 ? true : false) + " ";
			i++;
		}
		lastSQL = res == "" ? "" : res.substring(0, res.length() -1);
		changed = false;
		return lastSQL;
	}
	
	public String toPreparedSQL() {
		if(entries == null || entries.isEmpty()) return "";
		if(!changedPrepared) return lastPreparedSQL;
		if(!entries.isEmpty() && entries.get(0) == null) throw new NullPointerException("the first entry (main) is missing");
		String res = "";
		int i = 0;
		for(WhereEntry<?> e : entries) {
			res += e.toPreparedSQL(i == 0 ? true : false) + " ";
			i++;
		}
		lastPreparedSQL = res == "" ? "" : res.substring(0, res.length() -1);
		changedPrepared = false;
		return lastPreparedSQL;
	}
	
	@Override
	public String toString() {
		return toSQL();
	}
	
	/* TESTS */
	//17303	 <-> 17919 Millis | mit <V>
	//17934 <-> 17956
}
