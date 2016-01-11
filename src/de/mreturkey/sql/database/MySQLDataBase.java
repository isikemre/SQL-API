package de.mreturkey.sql.database;

import de.mreturkey.sql.provider.MySQL;
import de.mreturkey.sql.provider.Provider;

public class MySQLDataBase extends MySQL implements DataBase {

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
	public DataBaseType getType() {
		return DataBaseType.MYSQL;
	}
	
	//Coming soon:
	/**
	 * fromYAML() - Method to get database inforamtions from yaml-file
	 * fromYamlConfiguration() - Method to get database inforamtions from Bukkit's YamlConfiguration
	 * fromXML() - Method to get database inforamtions from XML-File
	 * fromJSON() - Method to get database inforamtions from JSON-File
	 */
	
}
