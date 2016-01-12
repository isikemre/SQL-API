package de.mreturkey.sql.provider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.ExecutedResult;
import de.mreturkey.sql.result.Result;

public class ProviderConnection implements Connection {

	private final java.sql.Connection connection;
	
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
	public Result select(SelectQuery selectQuery) throws SQLException, SQLTimeoutException {
		
	}

	@Override
	public Result select(String table) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String table, String column) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String table, String... columns) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String table, WhereClausel where) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String table, String column, WhereClausel where) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String table, WhereClausel where, String... columns) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result insert(InsertQuery insertQuery) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result insert(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(UpdateQuery updateQuery) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(String table, String[] columns, Object[] values, WhereClausel where)
			throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteQuery deleteQuery) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(String table) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(String table, WhereClausel where) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.sql.Connection getSQLConnection() {
		return connection;
	}

}
