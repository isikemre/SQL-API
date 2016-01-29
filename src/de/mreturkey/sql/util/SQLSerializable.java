package de.mreturkey.sql.util;

import java.sql.PreparedStatement;

public interface SQLSerializable {
	
	/**
	 * Generates a SQL Query with all data in this object and returns the SQL Query as String.<br>
	 * This method doesn't support prepared statements.<br>
	 * If you want a SQL Query for the {@link PreparedStatement} you need to invoke <code>.toPreparedSQL()</code>, if exists.
	 * @return the SQL Query as String
	 */
	String toSQL();
}
