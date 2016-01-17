package de.mreturkey.sql.database;

import java.io.File;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public class SQLiteDataBase implements DataBase {

	private final File databaseFile;
	private final SQLiteConfig config;
	
	public SQLiteDataBase(File databaseFile) {
		this(databaseFile, new SQLiteConfig());
	}
	
	public SQLiteDataBase(File directory, String filename) {
		this(new File(directory, filename));
	}
	
	public SQLiteDataBase(File databaseFile, SQLiteConfig config) {
		this.databaseFile = databaseFile;
		this.config = config;
	}
	
	public File getDatabaseFile() {
		return databaseFile;
	}
	
	public SQLiteConfig getConfig() {
		return config;
	}
	
	@Override
	public Connection openConnection() throws SQLException {
		return API.getProvider(ProviderType.SQLITE).openConnection(this);
	}

	@Override
	public ProviderType getType() {
		return ProviderType.SQLITE;
	}

}
