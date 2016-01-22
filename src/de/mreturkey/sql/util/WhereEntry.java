package de.mreturkey.sql.util;

import de.mreturkey.sql.operator.LogicalOperator;

public class WhereEntry<V> implements SQLSerializable {

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
	
	/**
	 * Returns the parsed value (from boolean to integer - if value is boolean)
	 * @return
	 */
	public Object getParsedValue() {
		return parseBoolToInt(value);
	}
	
	private Object parseBoolToInt(Object obj) {
		if(obj instanceof Boolean) {
			if((boolean) obj) return 1;
			return 0;
		}
		return obj;
	}
	
	public String toSQL(boolean ignoreLogicalOperator) {
		final String sql;
		if(ignoreLogicalOperator || logicalOperator == null) sql = "`"+column+"` "+operator + " '"+parseBoolToInt(value)+"'";
		else sql = logicalOperator +" `"+column+"` "+operator + " '"+parseBoolToInt(value)+"'";
		return sql;
	}
	
	public String toSQL() {
		return toSQL(false);
	}
	
	public String toPreparedSQL(boolean ignoreLogicalOperator) {
		final String sql;
		if(ignoreLogicalOperator || logicalOperator == null) sql = "`"+column+"` "+operator + " ?";
		else sql = logicalOperator +" `"+column+"` "+operator + " ?";
		return sql;
	}
	
	public String toPreparedSQL() {
		return toPreparedSQL(false);
	}
	
	@Override
	public String toString() {
		return toSQL();
	}
}
