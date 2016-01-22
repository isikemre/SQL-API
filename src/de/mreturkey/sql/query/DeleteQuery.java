package de.mreturkey.sql.query;

import de.mreturkey.sql.clausel.WhereClause;
import de.mreturkey.sql.util.PrepareEntry;
import de.mreturkey.sql.util.SQLSerializable;

public class DeleteQuery implements Query, SQLSerializable {

	private String table;
	private final QueryType type = QueryType.DELETE;
	private WhereClause whereClausel;
	
	private String lastSQL;
	private PrepareEntry lastPreparedSQL;
	private boolean changed = true, changedPrepared = true;
	
	public DeleteQuery() {}
	
	public DeleteQuery(String table) {
		this.table = table;
	}
	
	public DeleteQuery(WhereClause whereClausel) {
		this.whereClausel = whereClausel;
	}
	
	public DeleteQuery(String table, WhereClause whereClausel) {
		this.table = table;
		this.whereClausel = whereClausel;
	}
	
	@Override
	public String getTable() {
		return table;
	}

	@Override
	public void setTable(String table) {
		this.table = table;
	}
	
	@Override
	public QueryType getType() {
		return type;
	}

	public WhereClause getWhereClausel() {
		return whereClausel;
	} 
	
	public void setWhereClausel(WhereClause whereClausel) {
		this.whereClausel = whereClausel;
	}
	
	@Override
	public String toSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changed && whereClausel != null && !whereClausel.isChanged()) return lastSQL; //Cache
		
		final String where, sql;
		
		if(whereClausel != null && !whereClausel.isEmpty()) {
			where = " WHERE "+whereClausel.toSQL();
		} else where = "";
		
		sql = "DELETE FROM `"+table+"`"+ where;
		
		lastSQL = sql;
		changed = false;
		
		return sql;
	}
	
	public PrepareEntry toPreparedSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changedPrepared && whereClausel != null && !whereClausel.isChanged()) return lastPreparedSQL; //Cache

		final String where, sql;
		
		if(whereClausel != null && !whereClausel.isEmpty()) {
			where = " WHERE "+whereClausel.toPreparedSQL();
		} else where = "";
		
		sql = "DELETE FROM `"+table+"`"+ where;
		
		lastPreparedSQL = new PrepareEntry(sql, whereClausel.getAllValues().toArray());
		changedPrepared = false;
		
		return lastPreparedSQL;
	}

}
