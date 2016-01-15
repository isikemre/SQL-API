package de.mreturkey.sql.query;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import de.mreturkey.sql.util.PrepareEntry;

public class InsertQuery implements Query {

	private String table;
	private final HashMap<String, String> values;
	private final QueryType type = QueryType.INSERT;
	
	private String lastSQL;
	private PrepareEntry lastPreparedSQL;
	private boolean changed = true, changedPrepared = true;
	
	public InsertQuery() {
		this.values = new HashMap<>();
	}
	
	public InsertQuery(String table) {
		this.values = new HashMap<>();
		this.table = table;
	}
	
	public InsertQuery(HashMap<String, String> values) {
		if(values == null) values = new HashMap<>();
		this.values = values;
	}
	
	public InsertQuery(String table, HashMap<String, String> values) {
		this.table = table;
		if(values == null) values = new HashMap<>();
		this.values = values;
	}
	
	@Override
	public String getTable() {
		return table;
	}

	@Override
	public QueryType getType() {
		return type;
	}
	
	public HashMap<String, String> getValues() {
		return values;
	}
	
	public void addValue(String column, Object value) {
		if(value instanceof Boolean && value != null) value = (boolean) value == true ? 1 : 0;
		values.put(column, value == null ? null : value.toString());
		changed = true;
	}

	public void replaceValue(String column, Object value) {
		if(value instanceof Boolean && value != null) value = (boolean) value == true ? 1 : 0;
		values.replace(column, value == null ? null : value.toString());
		changed = true;
	}
	
	public void removeValue(String column) {
		values.remove(column);
		changed = true;
	}
	
	@Override
	public void setTable(String table) {
		this.table = table;
		changed = true;
	}
	
	@Override
	public String toSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changed) return lastSQL; //Cache
		
		final String cols, vals, sql;
		
		if(values == null || values.isEmpty()) {
			throw new IllegalArgumentException("values cannot be null or empty");
		} else {
			String tmpCols = "(";
			String tmpVals = "(";
			for(Entry<String, String> entry : values.entrySet()) {
				tmpCols += entry.getKey() + ",";
				if(entry.getValue() == null) tmpVals += "NULL,";
				else tmpVals += "'"+entry.getValue()+ "',";
			}
			cols = tmpCols.substring(0, tmpCols.length() -1) + ")";
			vals = tmpVals.substring(0, tmpVals.length() -1) + ")";
		}
		
		sql = "INSERT INTO `" + table + "` " + cols + " VALUES " + vals;
		
		lastSQL = sql;
		changed = false;
		
		return sql;
	}
	
	public PrepareEntry toPreparedSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changedPrepared) return lastPreparedSQL; //Cache
		
		final String cols, vals;
		
		if(values == null || values.isEmpty()) {
			throw new IllegalArgumentException("values cannot be null or empty");
		} else {
			cols = "("+StringUtils.join(values.keySet(), ",")+")";
			vals = "("+StringUtils.join(Collections.nCopies(values.size(), "?"), ",")+")";
		}
		
		this.lastPreparedSQL = new PrepareEntry("INSERT INTO `" + table + "` " + cols + " VALUES " + vals, values != null ? values.values().toArray(): ArrayUtils.EMPTY_OBJECT_ARRAY);
		this.changedPrepared = false;
		return this.lastPreparedSQL;
	}

}
