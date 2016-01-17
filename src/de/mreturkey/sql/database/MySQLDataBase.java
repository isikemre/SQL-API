package de.mreturkey.sql.database;

import java.sql.SQLException;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public class MySQLDataBase implements DataBase {

	private final String host, database, user, password;
	private final int port;
	
	public MySQLDataBase(String host, int port, String database, String user, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public Connection openConnection() throws SQLException {
		return API.getProvider(ProviderType.MYSQL).openConnection(this);
	}

	@Override
	public ProviderType getType() {
		return ProviderType.MYSQL;
	}

}
