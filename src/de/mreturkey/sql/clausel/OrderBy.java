package de.mreturkey.sql.clausel;

import de.mreturkey.sql.util.SQLSerializable;

public enum OrderBy implements Clause, SQLSerializable {
	
	ASC,
	DESC;

	@Override
	public String toSQL() {
		return this.name();
	}

}
