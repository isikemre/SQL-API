package de.mreturkey.sql.util;

public class PrepareEntry {

	private final String sql;
	private final Object[] args;
	
	public PrepareEntry(String sql, Object[] args) {
		this.sql = sql;
		this.args = args;
	}
	
	public String getSQL() {
		return sql;
	}

	public Object[] getArgs() {
		return args;
	}
	
	public boolean isEmpty() {
		return args.length <= 0;
	}
}
