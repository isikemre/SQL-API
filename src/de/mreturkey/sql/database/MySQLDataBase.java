package de.mreturkey.sql.database;

import java.sql.SQLException;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public class MySQLDataBase implements DataBase {

	private final String host, database, user, password;
	private final int port;
	
	public MySQLDataBase(String host, int port, String database, String user, String password) {
		if(host == null) throw new NullPointerException("host cannot be null");
		if(port <= 0 || port > 65535) throw new IllegalArgumentException("port number need to be in the range of 1 to 65535");
		if(database == null) throw new NullPointerException("database cannot be null");
		
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
