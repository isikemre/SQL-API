package de.mreturkey.sql.result;

import java.sql.ResultSet;

import de.mreturkey.sql.query.Query;
import de.mreturkey.sql.query.QueryType;

public interface Result {

	boolean isCached();
	
	QueryType getQueryType();
	
	ResultSet getResultSet();
	
	Query getExecutedQuery();
	
}
