package de.mreturkey.sql.query.builder;

public class BuildManager {
	
	public BuildManager() {}
	
	public SelectBuilder buildSelect() {
		return new SelectBuilder();
	}
	
	public InsertBuilder buildInsert() {
		return new InsertBuilder();
	}
	
	public UpdateBuilder buildUpdate() {
		return new UpdateBuilder();
	}
	
	public DeleteBuilder buildDelete() {
		return new DeleteBuilder();
	}

}
