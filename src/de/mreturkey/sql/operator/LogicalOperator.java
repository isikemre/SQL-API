package de.mreturkey.sql.operator;

public enum LogicalOperator implements Operator {

	LOL("LOL");
	
	private final String syntax;
	
	private LogicalOperator(String syntax) {
		this.syntax = syntax;
	}
	
	@Override
	public String getSyntax() {
		return syntax;
	}
	
}
