package de.mreturkey.sql.query.builder;

import java.sql.SQLException;
import java.util.HashMap;

import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.result.Result;

public class InsertBuilder implements Builder {

	private String table;
	private final HashMap<String, String> values = new HashMap<>();

	protected InsertBuilder() {} //only Builder can access
	
	public String getTable() {
		return table;
	}
	
	public HashMap<String, String> getValues() {
		return values;
	}
	
	public InsertBuilder into(String table) {
		this.table = table;
		return this;
	}
	
	public <V> InsertBuilder add(String column, V value) {
		if(value instanceof Boolean && value != null) values.put(column, (boolean) value == true ? "1" : "0");
		else values.put(column, value == null ? null : value.toString());	
		return this;
	}
	
	public InsertBuilder remove(String column) {
		values.remove(column);
		return this;
	}
	
	public <V> InsertBuilder replace(String column, V value) {
		if(value instanceof Boolean && value != null) values.replace(column, (boolean) value == true ? "1" : "0");
		else values.replace(column, value == null ? null : value.toString());	
		return this;
	}
	
	public Result execute(Connection connection) throws SQLException {
		final InsertQuery iq = new InsertQuery(values);
		
		iq.setTable(table);
		
		return connection.insert(iq);
	}
}
