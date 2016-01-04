package de.mreturkey.sql.query;

import de.mreturkey.sql.clausel.WhereClausel;

public class DeleteQuery implements Query {

	private String table;
	private final QueryType type = QueryType.DELETE;
	private WhereClausel whereClausel;
	
	private boolean changed;
	private String lastSQL;
	
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

	public WhereClausel getWhereClausel() {
		return whereClausel;
	} 
	
	public void setWhereClausel(WhereClausel whereClausel) {
		this.whereClausel = whereClausel;
	}
	
	@Override
	public String toSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changed && whereClausel != null && !whereClausel.isChanged()) return lastSQL; //Cache
		
		final String where, sql;
		
		if(whereClausel != null) {
			where = " WHERE "+whereClausel.toSQL();
		} else where = "";
		
		sql = "DELETE FROM `"+table+"`"+ where;
		
		lastSQL = sql;
		changed = false;
		
		return sql;
//		DELETE FROM session WHERE id = 1
	}

}
