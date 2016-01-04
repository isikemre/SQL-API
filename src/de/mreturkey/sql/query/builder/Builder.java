package de.mreturkey.sql.query.builder;

public interface Builder {

	public static SelectBuilder buildSelect() {
		return new SelectBuilder();
	}
	
}
