package de.mreturkey.sql.database;

import java.io.File;
import java.sql.Connection;

import org.sqlite.SQLiteConfig;

import de.mreturkey.sql.provider.SQLite;

public class SQLiteDataBase extends SQLite implements DataBase {

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
	
	@Override
	public final DataBaseType getType() {
		return DataBaseType.SQLITE;
	}
	
	public File getDatabaseFile() {
		return databaseFile;
	}
	
	public SQLiteConfig getConfig() {
		return config;
	}

	@Override
	public Connection openConnection() {
		super.openConnection(database)
	}

}
