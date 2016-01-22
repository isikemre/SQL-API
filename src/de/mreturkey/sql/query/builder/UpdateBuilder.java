package de.mreturkey.sql.query.builder;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.HashMap;

import de.mreturkey.sql.clausel.WhereClause;
import de.mreturkey.sql.operator.LogicalOperator;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.Result;
import de.mreturkey.sql.util.WhereEntry;

public class UpdateBuilder implements Builder {

	private String table;
	private final HashMap<String, String> values = new HashMap<>();
	private final WhereClause whereClausel = new WhereClause();
	
	protected UpdateBuilder() {} //only Builder can access
	
	public String getTable() {
		return table;
	}
	
	public HashMap<String, String> getValues() {
		return values;
	}
	
	public WhereClause getWhereClausel() {
		return whereClausel;
	}
	
	public UpdateBuilder table(String table) {
		this.table = table;
		return this;
	}
	
	public <V> UpdateBuilder update(String column, V value) {
		if(value instanceof Boolean && value != null) values.put(column, (boolean) value == true ? "1" : "0");
		else values.put(column, value == null ? null : value.toString());	
		return this;
	}
	
	public <V> UpdateBuilder where(WhereEntry<V> entry) {
		whereClausel.add(entry);
		return this;
	}
	
	public <V> UpdateBuilder where(String column, String operator, V value, LogicalOperator logicalOperator) {
		return where(new WhereEntry<V>(column, operator, value, logicalOperator));
	}
	
	public <V> UpdateBuilder where(String column, String operator, V value) {
		return where(new WhereEntry<V>(column, operator, value, null));
	}
	
	public Result execute(Connection connection) throws SQLTimeoutException, SQLException {
		final UpdateQuery uq = new UpdateQuery(values);
		
		uq.setTable(table);
		uq.setWhereClausel(whereClausel);
		
		return connection.update(uq);
	}
}
