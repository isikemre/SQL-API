package de.mreturkey.sql.util;

public class Database {

	private final String host;
	private final int port;
	private final String database;
	private final String user;
	private final String password;
	
	public Database(String host, int port, String database, String user, String password) {
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
	
	
	
	//Coming soon:
	/**
	 * fromYAML() - Method to get database inforamtions from yaml-file
	 * fromYamlConfiguration() - Method to get database inforamtions from Bukkit's YamlConfiguration
	 * fromXML() - Method to get database inforamtions from XML-File
	 * fromJSON() - Method to get database inforamtions from JSON-File
	 */
	
}
