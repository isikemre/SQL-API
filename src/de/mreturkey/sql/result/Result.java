package de.mreturkey.sql.result;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.mreturkey.sql.query.Query;
import de.mreturkey.sql.query.QueryType;

public interface Result {

	boolean isCached();
	
	QueryType getQueryType();
	
	ResultSet getResultSet();
	
	Query getExecutedQuery();
	
	Object[][] getValues() throws SQLException;
	
	ResultState getState();
	
	/**
	 * In SQLite you cannot check wheter there are values in the result set
	 * @return boolean
	 * @throws SQLException
	 */
	boolean isNull() throws SQLException;
}
