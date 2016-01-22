package de.mreturkey.sql.query.builder;

import java.sql.SQLException;

import de.mreturkey.sql.clausel.WhereClause;
import de.mreturkey.sql.operator.LogicalOperator;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.result.Result;
import de.mreturkey.sql.util.WhereEntry;

public class DeleteBuilder implements Builder {
	
	private String table;
	private final WhereClause whereClausel = new WhereClause();
	
	protected DeleteBuilder() {} //only Builder can access
	
	public String getTable() {
		return table;
	}
	
	public WhereClause getWhereClausel() {
		return whereClausel;
	}
	
	public DeleteBuilder from(String table) {
		this.table = table;
		return this;
	}
	
	public <V> DeleteBuilder where(WhereEntry<V> entry) {
		whereClausel.add(entry);
		return this;
	}
	
	public <V> DeleteBuilder where(String column, String operator, V value, LogicalOperator logicalOperator) {
		return where(new WhereEntry<V>(column, operator, value, logicalOperator));
	}
	
	public <V> DeleteBuilder where(String column, String operator, V value) {
		return where(new WhereEntry<V>(column, operator, value, null));
	}
	
	public Result execute(Connection connection) throws SQLException {
		final DeleteQuery dq = new DeleteQuery();
		
		dq.setTable(table);
		dq.setWhereClausel(whereClausel);
		
		return connection.delete(dq);
	}
}
