package de.mreturkey.sql.operator;

public enum LogicalOperator implements Operator {

	AND("AND"),
	OR("OR");
	
	private final String syntax;
	
	private LogicalOperator(String syntax) {
		this.syntax = syntax;
	}
	
	@Override
	public String getSyntax() {
		return syntax;
	}
	
	@Override
	public String toString() {
		return getSyntax();
	}
	
}
