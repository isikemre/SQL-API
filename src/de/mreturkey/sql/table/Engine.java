package de.mreturkey.sql.table;

public enum Engine {

	CSV,
	INNO_DB("InnoDB"),
	MEMORY,
	MY_ISAM("MyISAM"),
	MRG_MY_ISAM("MRG_MyISAM"),
	ARIA("Aria");
	
	private final String sql;

	private Engine(String sql) {
		this.sql = sql;
	}
	
	private Engine() {
		this.sql = this.name();
	}
	
	public String getSQL() {
		return sql;
	}
}
