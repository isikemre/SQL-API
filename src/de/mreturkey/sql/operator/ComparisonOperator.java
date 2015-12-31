package de.mreturkey.sql.operator;

public enum ComparisonOperator implements Operator {

	EQUALS("="),
	NOT_EQUALS("!="),
	LEFT_OPERAND_GREATER(">"),
	LEFT_OPERAND_LESS("<"),
	LEFT_OPERAND_GREATER_OR_EQUALS(">="),
	LEFT_OPERAND_LESS_OR_EQUALS("<=");
	
	private final String syntax;
	
	private ComparisonOperator(String syntax) {
		this.syntax = syntax;
	}

	@Override
	public String getSyntax() {
		return syntax;
	}
	
}
