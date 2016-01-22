package de.mreturkey.sql.table;

import java.util.ArrayList;

import de.mreturkey.sql.util.Entry;

public class SQLiteTable implements Table {

	private final String name;
	private final ArrayList<Entry<String, ColumnSettings>> columns = new ArrayList<>();
	private ArrayList<String> extras;
	
	private boolean primaryKey;
	
	private String lastSQL;
	private boolean changed;
	
	public SQLiteTable(String name) {
		this.name = name;
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

	/**
	 * Will be ignored (because SQLite)
	 */
	@Override
	public void primary(String columnName) {
		//ignore
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
			+ (settings.isAutoIncrement() ? " PRIMARY KEY" : "")
			+ (settings.isNotNull() ? " NOT NULL" : " NULL")
			+ (settings.getDefaultValue() == null ? "" : (settings.getDefaultValue().equalsIgnoreCase("NULL") ? " DEFAULT NULL" : " DEFAULT '"+settings.getDefaultValue()+"'"));
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
		
		this.lastSQL = begin + name + " (" + body + ");";
		this.changed = false;
		
		return this.lastSQL;
	}
	
	
}
