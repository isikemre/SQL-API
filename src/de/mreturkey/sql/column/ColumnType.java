package de.mreturkey.sql.column;

public interface ColumnType {

	/**
	 * Generates the SQL Syntax with the ColumnType and the given parameter.<br>
	 * If parameter it will return only the type.<br><br>
	 * 
	 * <b>Example:</b>
	 * 		<blockquote>
	 * 			With Parameter (not null) = <code>VARCHAR(255)</code><br>
	 * 			Without Parameter (null)  = <code>BOOLEAN</code>
	 *		</blockquote>
	 * @param parameter only integer, string or boolean
	 * @return the generated SQL
	 */
	String getSQL(Object parameter);
	
}
