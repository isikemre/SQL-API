package de.mreturkey.sql.clausel;

import de.mreturkey.sql.util.SQLSerializable;

public enum OrderBy implements Clause, SQLSerializable {
	
	ASC,
	DESC;

	/**
	 * Returns the SQL Syntax for this <code>OrderBy</code> type
	 */
	@Override
	public String toSQL() {
		return this.name();
	}

}
