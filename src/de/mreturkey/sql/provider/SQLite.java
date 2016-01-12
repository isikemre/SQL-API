package de.mreturkey.sql.provider;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.sql.DataSource;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.database.SQLiteDataBase;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.Result;

public class SQLite implements Provider {

	private DataSource dataSource;
	private Connection connection;
	
	public SQLite() {
		// TODO Auto-generated constructor stub	
	}
	
	public SQLite(SQLiteDataBase dataBase) {
		// TODO
	}
	
	public Connection openConnection(DataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		
		final SQLiteConfig config = new SQLiteConfig();
		
		final SQLiteDataSource sqlite = new SQLiteDataSource();
		
//		dataSource = mysql;
//		connection = dataSource.getConnection();
		
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
		return "sqlite";
	}

}
