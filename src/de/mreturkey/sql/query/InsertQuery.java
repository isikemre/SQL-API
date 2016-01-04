package de.mreturkey.sql.query;

import java.util.HashMap;
import java.util.Map.Entry;

public class InsertQuery implements Query {

	private String table;
	private final HashMap<String, String> values = new HashMap<>();
	private final QueryType type = QueryType.INSERT;
	
	private boolean changed = true;
	private String lastSQL;
	
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
		values.put(column, value == null ? null : value.toString());
		changed = true;
	}

	public void replaceValue(String column, Object value) {
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
		
		sql = "INSERT INTO " + table + " " + cols + " VALUES " + vals;
		
		lastSQL = sql;
		changed = false;
		
		return sql;
	}
	
	public String toPreparedSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changed) return lastSQL; //Cache
		
		final String cols, vals;
		
		if(values == null || values.isEmpty()) {
			throw new IllegalArgumentException("values cannot be null or empty");
		} else {
			String tmpCols = "(";
			String tmpVals = "(";
			for(Entry<String, String> entry : values.entrySet()) {
				tmpCols += entry.getKey() + ", ";
				tmpVals += "?,";
			}
			cols = tmpCols.substring(0, tmpCols.length() -2) + ")";
			vals = tmpVals.substring(0, tmpVals.length() -1) + ")";
		}
		
		return "INSERT INTO `" + table + "` " + cols + " VALUES " + vals;
	}

}
