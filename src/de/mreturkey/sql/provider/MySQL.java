package de.mreturkey.sql.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.database.MySQLDataBase;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.Result;

public class MySQL implements Provider {

	private DataSource dataSource;
	private Connection connection;
	
	public MySQL() {
		
	}
	
	public MySQL(MySQLDataBase database) {
		//TODO --
	}
	
	@Override
	public Connection openConnection(DataBase database) throws SQLException {
		if(!(database instanceof MySQLDataBase)) throw new IllegalArgumentException("database argument is not a type of MySQLDataBase");
		return this.openConnection((MySQLDataBase) database);
	}
	
	public Connection openConnection(MySQLDataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		
		final MysqlDataSource mysql = new MysqlDataSource();
		mysql.setURL("jdbc:mysql://"+database.getHost()+":"+database.getPort()+"/"+database.getDatabase());
		mysql.setUser(database.getUser());
		mysql.setPassword(database.getPassword());
		
		this.dataSource = mysql;
		this.connection = dataSource.getConnection();
		
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
}
