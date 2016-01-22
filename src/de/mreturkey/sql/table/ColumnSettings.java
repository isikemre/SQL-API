package de.mreturkey.sql.table;

import de.mreturkey.sql.column.ColumnType;

public class ColumnSettings {

	private final ColumnType type;
	private final Object parameter;
	private final boolean notNull;
	private final String defaultValue;
	private final String comment;
	private final boolean autoIncrement;
	
	public ColumnSettings(ColumnType type, Object parameter, boolean notNull, String defaultValue, String comment, boolean autoIncrement) {
		this.type = type;
		this.parameter = parameter;
		this.notNull = notNull;
		this.defaultValue = defaultValue;
		this.comment = comment;
		this.autoIncrement = autoIncrement;
	}
	
	public ColumnSettings(ColumnType type, Object parameter) {
		this(type, parameter, true, null, null, false);
	}
	
	public ColumnSettings(ColumnType type) {
		this(type, null, true, null, null, false);
	}

	public ColumnType getType() {
		return type;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getComment() {
		return comment;
	}
	
	public Object getParameter() {
		return parameter;
	}
	
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
}
