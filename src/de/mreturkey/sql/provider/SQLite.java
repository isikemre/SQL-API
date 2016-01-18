package de.mreturkey.sql.provider;

import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.sqlite.SQLiteDataSource;

import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.database.SQLiteDataBase;

public class SQLite implements Provider {

	@Override
	public Connection openConnection(DataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		if(!(database instanceof SQLiteDataBase)) throw new IllegalArgumentException("database must be a SQLiteDataBase Object");
		return openConnection((SQLiteDataBase) database);
	}
	
	public Connection openConnection(SQLiteDataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");

		final SQLiteDataSource sqlite = new SQLiteDataSource(database.getConfig());
		sqlite.setUrl("jdbc:sqlite:"+database.getFile().getAbsolutePath());
		
		DataSource dataSource = sqlite;
		java.sql.Connection connection = (java.sql.Connection) dataSource.getConnection();
		return new ProviderConnection(connection);
	}
	
	@Override
	public String getProviderName() {
		return "sqlite";
	}

	@Override
	public boolean fromXML(File xml) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fromYAML(File yaml) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fromProperties(Properties properties) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fromJSON(File json) {
		// TODO Auto-generated method stub
		return false;
	}

}
