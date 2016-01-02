package de.mreturkey.sql.util;

import de.mreturkey.sql.operator.LogicalOperator;

public class WhereEntry<V> {

	private final String column, operator;
	private final LogicalOperator logicalOperator;
	private final V value;
	
	public WhereEntry(String column, String operator, V value, LogicalOperator logicalOperator) {
		this.column = column;
		this.operator = operator;
		this.value = value;
		this.logicalOperator = logicalOperator;
	}
	
	public String getColumn() {
		return column;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public LogicalOperator getLogicalOperator() {
		return logicalOperator;
	}
	
	public V getValue() {
		return value;
	}
	
	public String toSQL() {
		final String sql;
		if(logicalOperator == null) sql = "`"+column+"` "+operator + " '"+value+"'";
		else sql = logicalOperator +" `"+column+"` "+operator + " '"+value+"'";
		return sql;
	}
	
	@Override
	public String toString() {
		return toSQL();
	}
}
