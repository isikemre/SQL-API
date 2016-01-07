package de.mreturkey.sql.query.builder;

public interface Builder {

	public static SelectBuilder buildSelect() {
		return new SelectBuilder();
	}
	
	public static InsertBuilder buildInsert() {
		return new InsertBuilder();
	}
	
	public static UpdateBuilder buildUpdate() {
		return new UpdateBuilder();
	}
	
	public static DeleteBuilder buildDelete() {
		return new DeleteBuilder();
	}
	
}
