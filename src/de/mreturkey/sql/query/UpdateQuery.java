package de.mreturkey.sql.query;

import java.util.HashMap;
import java.util.Map.Entry;

import de.mreturkey.sql.clausel.WhereClausel;

public class UpdateQuery implements Query {

	private String table;
	private final HashMap<String, String> values;
	private WhereClausel whereClausel;
	
	private String lastSQL;
	private boolean changed = true;
	
	private final QueryType type = QueryType.UPDATE;
	
	public UpdateQuery() {
		this.values = new HashMap<>();
	}
	
	public UpdateQuery(HashMap<String, String> values) {
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
				tmp += entry.getKey() + "='"+entry.getValue()+"',";
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
	
	//ohne sysout (1.000.000)
	// 914 Millis | without CACHE
	// 79 Millis | mit Cache
	
	//mit sysout... (100.000)
	// 10575 | ohne Cache
	// 10828 | mit Cache (komisch das printen alleine nimmt viel)
	
}
