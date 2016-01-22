package de.mreturkey.sql.result;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.mreturkey.sql.query.Query;
import de.mreturkey.sql.query.QueryType;

public class CachedResult implements Result {

	@Override
	public boolean isCached() {
		return true;
	}

	@Override
	public QueryType getQueryType() {
		return null;
	}

	@Override
	public ResultSet getResultSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query getExecutedQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[][] getValues() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNull() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}