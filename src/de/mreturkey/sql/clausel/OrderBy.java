package de.mreturkey.sql.clausel;

public enum OrderBy implements Clausel {
	
	ASC,
	DESC;

	@Override
	public String toSQL() {
		return this.name();
	}

}
