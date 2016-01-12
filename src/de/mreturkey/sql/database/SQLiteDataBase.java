package de.mreturkey.sql.database;

import java.io.File;

import org.sqlite.SQLiteConfig;

import de.mreturkey.sql.provider.Provider;
import de.mreturkey.sql.provider.ProviderType;
import de.mreturkey.sql.provider.SQLite;

public class SQLiteDataBase implements DataBase {

	private final File databaseFile;
	private final SQLiteConfig config;
	
	public SQLiteDataBase(File directory, String filename) {
		this(new File(directory, filename));
	}

	public SQLiteDataBase(File databaseFile) {
		this(databaseFile, new SQLiteConfig());
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
	public Provider openConnection() {
		return new SQLite(this);
	}

	@Override
	public ProviderType getType() {
		return ProviderType.SQLITE;
	}

}
