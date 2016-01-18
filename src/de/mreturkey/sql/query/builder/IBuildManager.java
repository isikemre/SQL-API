package de.mreturkey.sql.query.builder;

public class IBuildManager implements BuildManager {

	public IBuildManager() {}

	@Override
	public SelectBuilder select() {
		return new SelectBuilder();
	}

	@Override
	public InsertBuilder insert() {
		return new InsertBuilder();
	}

	@Override
	public UpdateBuilder update() {
		return new UpdateBuilder();
	}

	@Override
	public DeleteBuilder delete() {
		return new DeleteBuilder();
	}
	
}
