package de.mreturkey.sql.column;

public enum MySQLColumnType implements ColumnType {
	
	VARCHAR("VARCHAR"),
	TEXT("TEXT"),
	BIGTEXT("BIGTEXT"),
	BOOLEAN("BOOLEAN"),
	INT("INT"),
	TINYINT("TINYINT"),
	SMALLINT("SMALLINT"),
	BIT("BIT"),
	FLOAT("FLOAT"),
	TIME("TIME"),
	DATETIME("DATETIME"),
	DATE("DATE"),
	CHAR("CHAR"),
	BINARY("BINARY"),
	VARBINARY("VARBINARY"),
	IMAGE("IMAGE"),
	DECIMAL("DECIMAL"),
	MONEY("MONEY"),
	SMALLMONEY("SMALLMONEY"),
	MEDIUMINT("MEDIUMINT"),
	YEAR("YEAR"),
	BIGINT("BIGINT"),
	TIMESTAMP("TIMESTAMP");
	
	
	private final String sql;
	
	private MySQLColumnType(String sql) {
		this.sql = sql;
	}

	@Override
	public String getSQL(Object parameter) {
		if(parameter == null) return sql;
		else return sql + "("+parameter.toString()+")";
	}

}
