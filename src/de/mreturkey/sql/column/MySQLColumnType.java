package de.mreturkey.sql.column;

public enum MySQLColumnType implements ColumnType {

	/**
	 * A variable-length (0-65,535) string, the effective maximum length is subject to the maximum row size
	 */
	VARCHAR("VARCHAR"),
	/**
	 * A TEXT column with a maximum length of 65,535 (2^16 - 1) characters, stored with a two-byte prefix indicating the length of the value in bytes
	 */
	TEXT("TEXT"),
	/**
	 * A TEXT column with a maximum length of 4,294,967,295 or 4GiB (2^32 - 1) characters, stored with a four-byte prefix indicating the length of the value in bytes
	 */
	LONGTEXT("LONGTEXT"),
	/**
	 * An alias for BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE
	 */
	SERIAL("SERIAL"),
	/**
	 * A synonym for TINYINT(1), a value of zero is considered false, nonzero values are considered true
	 */
	BOOLEAN("BOOLEAN"),
	/**
	 * A 4-byte integer, signed range is -2,147,483,648 to 2,147,483,647, unsigned range is 0 to 4,294,967,295
	 */
	INT("INT"),
	/**
	 * A 1-byte integer, signed range is -128 to 127, unsigned range is 0 to 255
	 */
	TINYINT("TINYINT"),
	/**
	 * A 2-byte integer, signed range is -32,768 to 32,767, unsigned range is 0 to 65,535
	 */
	SMALLINT("SMALLINT"),
	/**
	 * A bit-field type (M), storing M of bits per value (default is 1, maximum is 64)
	 */
	BIT("BIT"),
	/**
	 * A small floating-point number, allowable values are -3.402823466E+38 to -1.175494351E-38, 0, and 1.175494351E-38 to 3.402823466E+38
	 */
	FLOAT("FLOAT"),
	/**
	 * A double-precision floating-point number, allowable values are -1.7976931348623157E+308 to -2.2250738585072014E-308, 0, and 2.2250738585072014E-308 to 1.7976931348623157E+308
	 */
	DOUBLE("DOUBLE"),
	/**
	 * Synonym for DOUBLE (exception: in REAL_AS_FLOAT SQL mode it is a synonym for FLOAT)
	 */
	REAL("REAL"),
	/**
	 * A time, range is -838:59:59 to 838:59:59
	 */
	TIME("TIME"),
	/**
	 * A date and time combination, supported range is 1000-01-01 00:00:00 to 9999-12-31 23:59:59
	 */
	DATETIME("DATETIME"),
	/**
	 * A date, supported range is 1000-01-01 to 9999-12-31
	 */
	DATE("DATE"),
	/**
	 * A fixed-length (0-255, default 1) string that is always right-padded with spaces to the specified length when stored
	 */
	CHAR("CHAR"),
	/**
	 * A TEXT column with a maximum length of 255 (2^8 - 1) characters, stored with a one-byte prefix indicating the length of the value in bytes
	 */
	TINYTEXT("TINYTEXT"),
	/**
	 * A TEXT column with a maximum length of 16,777,215 (2^24 - 1) characters, stored with a three-byte prefix indicating the length of the value in bytes
	 */
	MEDIUMTEXT("MEDIUMTEXT"),
	/**
	 * Similar to the CHAR type, but stores binary byte strings rather than non-binary character strings
	 */
	BINARY("BINARY"),
	/**
	 * Similar to the VARCHAR type, but stores binary byte strings rather than non-binary character strings
	 */
	VARBINARY("VARBINARY"),
	/**
	 * A fixed-point number (M, D) - the maximum number of digits (M) is 65 (default 10), the maximum number of decimals (D) is 30 (default 0)
	 */
	DECIMAL("DECIMAL"),
	/**
	 * A 3-byte integer, signed range is -8,388,608 to 8,388,607, unsigned range is 0 to 16,777,215
	 */
	MEDIUMINT("MEDIUMINT"),
	/**
	 * A year in four-digit (4, default) or two-digit (2) format, the allowable values are 70 (1970) to 69 (2069) or 1901 to 2155 and 0000
	 */
	YEAR("YEAR"),
	/**
	 * An 8-byte integer, signed range is -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807, unsigned range is 0 to 18,446,744,073,709,551,615
	 */
	BIGINT("BIGINT"),
	/**
	 * A timestamp, range is 1970-01-01 00:00:01 UTC to 2038-01-09 03:14:07 UTC, stored as the number of seconds since the epoch (1970-01-01 00:00:00 UTC)
	 */
	TIMESTAMP("TIMESTAMP"),
	/**
	 * A BLOB column with a maximum length of 255 (2^8 - 1) bytes, stored with a one-byte prefix indicating the length of the value
	 */
	TINYBLOB("TINYBLOB"),
	/**
	 * A BLOB column with a maximum length of 16,777,215 (2^24 - 1) bytes, stored with a three-byte prefix indicating the length of the value
	 */
	MEDIUMBLOB("MEDIUMBLOB"),
	/**
	 * A BLOB column with a maximum length of 65,535 (2^16 - 1) bytes, stored with a two-byte prefix indicating the length of the value
	 */
	BLOB("BLOB"),
	/**
	 * A BLOB column with a maximum length of 4,294,967,295 or 4GiB (2^32 - 1) bytes, stored with a four-byte prefix indicating the length of the value
	 */
	LONGBLOB("LONGBLOB"),
	/**
	 * An enumeration, chosen from the list of up to 65,535 values or the special '' error value
	 */
	ENUM("ENUM"),
	/**
	 * A single value chosen from a set of up to 64 members
	 */
	SET("SET");
	
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
