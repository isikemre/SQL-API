package de.mreturkey.sql.query.builder;

public interface BuildManager {
	
	SelectBuilder select();
	
	InsertBuilder insert();
	
	UpdateBuilder update();
	
	DeleteBuilder delete();
	
}
