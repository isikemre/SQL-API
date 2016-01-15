package de.mreturkey.sql.provider;

import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.database.MySQLDataBase;

public final class MySQL implements Provider {

	@Override
	public Connection openConnection(final DataBase database) throws SQLException {
		if(!(database instanceof MySQLDataBase)) throw new IllegalArgumentException("database argument is not a type of MySQLDataBase");
		return this.openConnection((MySQLDataBase) database);
	}
	
	public Connection openConnection(final MySQLDataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		
		final MysqlDataSource mysql = new MysqlDataSource();
		mysql.setURL("jdbc:mysql://"+database.getHost()+":"+database.getPort()+"/"+database.getDatabase());
		mysql.setUser(database.getUser());
		mysql.setPassword(database.getPassword());
		
		DataSource dataSource = mysql;
		java.sql.Connection connection = (java.sql.Connection) dataSource.getConnection();
		
		return new ProviderConnection(connection);
	}

	@Override
	public String getProviderName() {
		return "mysql";
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
