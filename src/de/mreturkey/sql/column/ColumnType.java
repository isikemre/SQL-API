package de.mreturkey.sql.column;

public interface ColumnType {

	String getSQL(Object parameter);
	
}
