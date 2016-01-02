package de.mreturkey.sql.query;

import java.util.HashMap;

import de.mreturkey.sql.clausel.WhereClausel;

public class InsertQuery implements Query {

	private String table;
	private final HashMap<String, String> values = new HashMap<>();
	private WhereClausel whereClausel;
	
	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toSQL() {
		// TODO Auto-generated method stub
		return null;
	}

}
