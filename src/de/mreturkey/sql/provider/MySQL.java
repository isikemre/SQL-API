package de.mreturkey.sql.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.Result;
import de.mreturkey.sql.util.Database;

public class MySQL implements Provider {

	private DataSource dataSource;
	private Connection connection;
	
	public MySQL() {
		
	}
	
	public Connection openConnection(Database database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		
		final MysqlDataSource mysql = new MysqlDataSource();
		mysql.setURL("jdbc:mysql://"+database.getHost()+":"+database.getPort()+"/"+database.getDatabase());
		mysql.setUser(database.getUser());
		mysql.setPassword(database.getPassword());
		
		dataSource = mysql;
		connection = dataSource.getConnection();
		
		return connection;
	}
	
	public void close() throws SQLException {
		if(isConnected()) {
			connection.close();
			connection = null;				
		}
	}
	
	public boolean isConnected() {
		try {
			return connection != null && !connection.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}
	
	@Override
	public String getProviderName() {
		return "mysql";
	}

	@Override
	public Result query(String query, boolean prepared) throws SQLException, SQLTimeoutException {
		
	}

	@Override
	public Result query(String query) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(SelectQuery selectQuery) throws SQLException, SQLTimeoutException {
		// TODO Auto-generated method stub
		return null;
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

}
