package de.mreturkey.sql.provider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.HashMap;

import de.mreturkey.sql.clausel.WhereClause;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.ExecutedResult;
import de.mreturkey.sql.result.Result;
import de.mreturkey.sql.table.Table;
import de.mreturkey.sql.util.PrepareEntry;

public class ProviderConnection implements Connection {

	private final java.sql.Connection connection;
	
	/**
	 * Creates a {@link ProviderConnection} with the {@link java.sql.jdbc.Connection} object
	 * @param connection
	 */
	public ProviderConnection(java.sql.Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Result prepareQuery(String query, Object... args) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query);
		int i = 1;
		for(Object o : args) {
			ps.setObject(i, o);
			i++;
		}
		return new ExecutedResult(ps.executeQuery(), null);
	}
	
	@Override
	public void prepareUpdateQuery(String query, Object... args) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query);
		int i = 1;
		for(Object o : args) {
			ps.setObject(i, o);
			i++;
		}
		ps.executeUpdate();
	}
	
	@Override
	public Result executeQuery(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		return new ExecutedResult(stmt.executeQuery(query), null);
	}
	
	@Override
	public void updateQuery(String query) throws SQLException, SQLTimeoutException {
		connection.createStatement().executeUpdate(query);
	}

	@Override
	public Result select(final SelectQuery selectQuery) throws SQLException, SQLTimeoutException {
		final PrepareEntry pe = selectQuery.toPreparedSQL();
		final PreparedStatement ps = connection.prepareStatement(pe.getSQL());
		int i = 1;
		for(Object o : pe.getArgs()) ps.setObject(i++, o);
		return new ExecutedResult(ps.executeQuery(), selectQuery);
	}

	@Override
	public Result select(String table) throws SQLException, SQLTimeoutException {
		return select(new SelectQuery(table));
	}

	@Override
	public Result select(String table, String column) throws SQLException, SQLTimeoutException {
		final SelectQuery sq = new SelectQuery(table);
		sq.addColumn(column);
		return select(sq);
	}

	@Override
	public Result select(String table, String... columns) throws SQLException, SQLTimeoutException {
		final SelectQuery sq = new SelectQuery(table);
		for(String col : columns) sq.addColumn(col);
		return select(sq);
	}

	@Override
	public Result select(String table, WhereClause where) throws SQLException, SQLTimeoutException {
		final SelectQuery sq = new SelectQuery(table);
		sq.setWhereClausel(where);
		return select(sq);
	}

	@Override
	public Result select(String table, String column, WhereClause where) throws SQLException, SQLTimeoutException {
		final SelectQuery sq = new SelectQuery(table);
		sq.addColumn(column);
		sq.setWhereClausel(where);
		return select(sq);
	}

	@Override
	public Result select(String table, WhereClause where, String... columns) throws SQLException, SQLTimeoutException {
		final SelectQuery sq = new SelectQuery(table);
		sq.setWhereClausel(where);
		for(String col : columns) sq.addColumn(col);
		return select(sq);
	}

	@Override
	public Result insert(InsertQuery insertQuery) throws SQLException, SQLTimeoutException {
		final PrepareEntry pe = insertQuery.toPreparedSQL();
		final PreparedStatement ps = connection.prepareStatement(pe.getSQL());
		
		int i = 1;
		for(Object o : pe.getArgs()) ps.setObject(i++, o);
		ps.executeUpdate();
		return new ExecutedResult(null, insertQuery);
	}

	@Override
	public Result insert(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException {
		if(columns.length != values.length) throw new IllegalArgumentException("The length of the given columns and values doesn't match! There are columns or values missing");
		final HashMap<String, String> map = new HashMap<>();
		for(int i = 0; i < values.length; i++) map.put(columns[i], values[i].toString());
		return insert(new InsertQuery(table, map));
	}

	@Override
	public Result update(UpdateQuery updateQuery) throws SQLException, SQLTimeoutException {
		final PrepareEntry pe = updateQuery.toPreparedSQL();
		final PreparedStatement ps = connection.prepareStatement(pe.getSQL());
		
		int i = 1;
		for(Object o : pe.getArgs()) ps.setObject(i++, o);
		ps.executeUpdate();
		return new ExecutedResult(null, updateQuery);
	}

	@Override
	public Result update(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException {
		if(columns.length != values.length) throw new IllegalArgumentException("The length of the given columns and values doesn't match! There are columns or values missing");
		final HashMap<String, String> map = new HashMap<>();
		for(int i = 0; i < values.length; i++) map.put(columns[i], values[i].toString());
		return update(new UpdateQuery(table, map));
	}

	@Override
	public Result update(String table, String[] columns, Object[] values, WhereClause where) throws SQLException, SQLTimeoutException {
		if(columns.length != values.length) throw new IllegalArgumentException("The length of the given columns and values doesn't match! There are columns or values missing");
		final HashMap<String, String> map = new HashMap<>();
		for(int i = 0; i < values.length; i++) map.put(columns[i], values[i].toString());
		return update(new UpdateQuery(table, map, where));
	}
	
	@Override
	public Result delete(DeleteQuery deleteQuery) throws SQLException, SQLTimeoutException {
		final PrepareEntry pe = deleteQuery.toPreparedSQL();
		final PreparedStatement ps = connection.prepareStatement(pe.getSQL());
		
		int i = 1;
		for(Object o : pe.getArgs()) ps.setObject(i++, o);
		ps.executeUpdate();
		return new ExecutedResult(null, deleteQuery);
	}

	@Override
	public Result delete(String table) throws SQLException, SQLTimeoutException {
		return delete(new DeleteQuery(table));
	}

	@Override
	public Result delete(String table, WhereClause where) throws SQLException, SQLTimeoutException {
		return delete(new DeleteQuery(table, where));
	}

	@Override
	public java.sql.Connection getSQLConnection() {
		return connection;
	}

	@Override
	public void queryTable(Table table) throws SQLException {
		this.updateQuery(table.toSQL());
	}

	@Override
	public void queryTable(Table table, boolean createIfNotExists) throws SQLException {
		this.updateQuery(table.toSQL(createIfNotExists));
	}

	@Override
	public void close() throws SQLException {
		this.connection.close();
	}

}
