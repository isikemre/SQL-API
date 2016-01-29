package de.mreturkey.sql.database;

import java.sql.SQLException;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public class MySQLDataBase implements DataBase {

	private final String host, database, user, password;
	private final int port;
	
	/**
	 * Constructs a MySQL Database with all given arguments.<br>
	 * You need a {@link DataBase} to work with MySQL.
	 * @param host the host of the mysql server (ex. <code>localhost</code> or <code>78.192.21.85</code>)
	 * @param port the port of the mysql server (ex. <code>3306</code>)
	 * @param database the database name
	 * @param user the username for the authentification 
	 * @param password
	 */
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

	/**
	 * Returns the host of the mysql server
	 * @return the host of the mysql server
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Returns the port of the mysql server
	 * @return the port of the mysql server
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Returns the name of the mysql database
	 * @return the name of the mysql database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * Returns the name of the mysql user
	 * @return the name of the mysql user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Returns the password of the mysql user
	 * @return the password of the mysql user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Opens and returns a {@link Connection} to this mysql database.<br>
	 */
	@Override
	public Connection openConnection() throws SQLException {
		return API.getProvider(ProviderType.MYSQL).openConnection(this);
	}

	@Override
	public ProviderType getType() {
		return ProviderType.MYSQL;
	}

}
