package de.mreturkey.sql.table;

import java.util.ArrayList;

import de.mreturkey.sql.util.Entry;
import de.mreturkey.sql.util.SQLSerializable;

public interface Table extends SQLSerializable {

	String getName();
	
	ArrayList<Entry<String, ColumnSettings>> getColumns();
	
	void addColumn(String columnName, ColumnSettings settings);
	
	void addExtra(String sql);
	
	void unique(String uniqueName, String columnName);
	
	void unique(String columnName);
	
	void primary(String columnName);
	
	String toSQL(boolean createIfNotExists);
}
