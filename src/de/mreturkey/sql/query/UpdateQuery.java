package de.mreturkey.sql.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.util.PrepareEntry;

public class UpdateQuery implements Query {

	private String table;
	private final HashMap<String, String> values;
	private WhereClausel whereClausel;
	
	private String lastSQL;
	private PrepareEntry lastPreparedSQL;
	private boolean changed = true, changedPrepared = true;
	
	private final QueryType type = QueryType.UPDATE;
	
	public UpdateQuery() {
		this.values = new HashMap<>();
	}
	
	public UpdateQuery(String table) {
		this.values = new HashMap<>();
		this.table = table;
	}
	
	public UpdateQuery(HashMap<String, String> values) {
		if(values == null) values = new HashMap<>();
		this.values = values;
	}
	
	public UpdateQuery(String table, HashMap<String, String> values) {
		if(values == null) values = new HashMap<>();
		this.values = values;
		this.table = table;
	}
	
	public UpdateQuery(String table, HashMap<String, String> values, WhereClausel whereClausel) {
		if(values == null) values = new HashMap<>();
		this.values = values;
		this.table = table;
		this.whereClausel = whereClausel;
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

	public WhereClausel getWhereClausel() {
		return whereClausel;
	}

	@Override
	public void setTable(String table) {
		this.table = table;
		changed = true;
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

	public void setWhereClausel(WhereClausel whereClausel) {
		this.whereClausel = whereClausel;
		changed = true;
	}

	@Override
	public String toSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changed && !whereClausel.isChanged()) return lastSQL; //Cache
		
		final String set, where, sql;
		
		if(values == null || values.isEmpty()) {
			throw new IllegalArgumentException("values cannot be null or empty");
		} else {
			String tmp = "SET ";
			for(Entry<String, String> entry : values.entrySet()) {
				tmp += "`"+entry.getKey() + "` = '"+entry.getValue()+"',";
			}
			set = tmp.substring(0, tmp.length() -1);
		}
		
		if(whereClausel != null && !whereClausel.isEmpty()) {
			where = "WHERE "+whereClausel.toSQL();
		} else where = "";
		
		sql = "UPDATE `"+table+"` " + set + " " + where;
		
		lastSQL = sql;
		changed = false;
		
		return sql;
	}
	
	public PrepareEntry toPreparedSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changedPrepared && !whereClausel.isChanged()) return lastPreparedSQL; //Cache
		
		final String set, where, sql;
		
		final ArrayList<String> valsEnd = new ArrayList<>(values.size());
		
		if(values == null || values.isEmpty()) {
			throw new IllegalArgumentException("values cannot be null or empty");
		} else {
			String tmp = " SET ";
			for(Entry<String, String> entry : values.entrySet()) {
				tmp += "`"+entry.getKey() + "` = ?,";
				valsEnd.add(entry.getValue());
			}
			set = tmp.substring(0, tmp.length() -1);
		}
		
		if(whereClausel != null && !whereClausel.isEmpty()) {
			where = " WHERE "+whereClausel.toSQL();
		} else where = "";
		
		sql = "UPDATE `"+table+"`" + set + where;
		
		lastPreparedSQL = new PrepareEntry(sql, valsEnd.toArray());
		changedPrepared = false;
		
		return lastPreparedSQL;
	}
	
}
