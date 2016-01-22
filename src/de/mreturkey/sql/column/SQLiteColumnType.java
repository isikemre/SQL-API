package de.mreturkey.sql.column;

public enum SQLiteColumnType implements ColumnType {

	NULL("NULL"),
	INTEGER("INTEGER"),
	REAL("REAL"),
	TEXT("TEXT"),
	BLOB("BLOB"),
	INT("INT"),
	TINYINT("TINYINT"),
	SMALLINT("SMALLINT"),
	MEDIUMINT("MEDIUMINT"),
	BIGINT("BIGINT"),
	INT2("INT2"),
	INT8("INT8"),
	CHARACTER("CHARACTER"),
	VARCHAR("VARCHAR"),
	NCHAR("NCHAR"),
	CLOB("CLOB"),
	DOUBLE("DOUBLE"),
	FLOAT("FLOAT"),
	DECIMAL("DECIMAL"),
	BOOLEAN("BOOLEAN"),
	DATE("DATE"),
	DATETIME("DATETIME");
	
	private final String sql;
	
	private SQLiteColumnType(String sql) {
		this.sql = sql;
	}

	@Override
	public String getSQL(Object parameter) {
		if(parameter == null) return sql;
		else return sql + "("+parameter.toString()+")";
	}
}
