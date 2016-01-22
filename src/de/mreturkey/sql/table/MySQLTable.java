package de.mreturkey.sql.table;

import java.util.ArrayList;

import de.mreturkey.sql.util.Entry;

public class MySQLTable implements Table {

	private final String name;
	private final ArrayList<Entry<String, ColumnSettings>> columns = new ArrayList<>();
	private ArrayList<String> extras;
	private final Engine engine;
	
	private boolean primaryKey;
	
	private String lastSQL;
	private boolean changed;
	
	public MySQLTable(String name) {
		this(name, Engine.INNO_DB);
	}
	
	public MySQLTable(String name, Engine engine) {
		this.name = name;
		this.engine = engine;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public ArrayList<Entry<String, ColumnSettings>> getColumns() {
		this.changed = true;
		return columns;
	}

	@Override
	public void addColumn(String columnName, ColumnSettings settings) {
		if(settings.isAutoIncrement()) {
			if(primaryKey) throw new IllegalArgumentException("a column has already a PRIMARY KEY");
			addExtra("PRIMARY KEY (`"+columnName+"`)");
			this.primaryKey = true;
		}
		columns.add(new Entry<String, ColumnSettings>(columnName, settings));
		this.changed = true;
	}
	
	@Override
	public void unique(String uniqueName, String columnName) {
		addExtra("UNIQUE `"+uniqueName+"` (`"+columnName+"`)");
	}

	@Override
	public void unique(String columnName) {
		addExtra("UNIQUE (`"+columnName+"`)");
	}

	@Override
	public void primary(String columnName) {
		if(primaryKey) throw new IllegalArgumentException("a column has already a PRIMARY KEY");
		addExtra("PRIMARY KEY (`"+columnName+"`)");
	}
	
	@Override
	public void addExtra(String sql) {
		if(extras == null) extras = new ArrayList<>();
		extras.add(sql);
	}

	@Override
	public String toSQL() {
		return toSQL(true);
	}
	
	private String parseSettings(ColumnSettings settings) {
		return settings.getType().getSQL(settings.getParameter())
				+ (settings.isNotNull() ? " NOT NULL" : " NULL")
				+ (settings.isAutoIncrement() ? " AUTO_INCREMENT" : "")
				+ (settings.isAutoIncrement() || settings.getDefaultValue() == null ? "" : (settings.getDefaultValue().equalsIgnoreCase("NULL") ? " DEFAULT NULL" : " DEFAULT '"+settings.getDefaultValue()+"'"))
				+ (settings.getComment() == null ? "" : " COMMENT '"+settings.getComment()+"'");
	}
	
	@Override
	public String toSQL(boolean createIfNotExists) {
		if(this.columns == null) throw new NullPointerException("no columns defined");
		if(this.changed == false && this.lastSQL != null) return lastSQL;
		
		String begin = "CREATE TABLE ";
		String body = "";
		if(createIfNotExists) {
			begin += "IF NOT EXISTS ";
		}
		
		for(Entry<String, ColumnSettings> column : this.columns) {
			body += "`"+column.getKey()+"` " + parseSettings(column.getValue()) + ", ";
		}
		
		if(extras != null) {
			for(String extra : extras) {
				body += extra + ", ";
			}
		}
		
		body = body.substring(0, body.length() -2);
		
		this.lastSQL = begin + name + " (" + body + ") ENGINE = " + engine.getSQL() + ";";
		this.changed = false;

		return this.lastSQL;
	}
}