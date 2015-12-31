package de.mreturkey.sql.util;

public class WhereEntry<V> {

	private final String column, operator;
	private final V value;
	
	public WhereEntry(String column, String operator, V value) {
		this.column = column;
		this.operator = operator;
		this.value = value;
	}
	
	public String getColumn() {
		return column;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public V getValue() {
		return value;
	}
	
	public String toSQL() {
		return "`"+column+"` "+operator + " '"+value+"'";
	}
}
