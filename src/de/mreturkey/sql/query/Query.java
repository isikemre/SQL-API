package de.mreturkey.sql.query;

public interface Query {

	/**
	 * Returns the name of table which is used to execute this query
	 * @return the name of table which is used to execute this query
	 */
	String getTable();
	
	void setTable(String table);
	
	/**
	 * Returns the type of the query
	 * @return the type of the query
	 */
	QueryType getType();
	
	/**
	 * TODO junge
	 * @return
	 */
	String toSQL();
}
