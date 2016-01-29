package de.mreturkey.sql.database;

import java.io.File;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public class SQLiteDataBase implements DataBase {
	
	private final String filePath;
	private final File databaseFile;
	private final SQLiteConfig config;
	
	/**
	 * Constructs a SQLite Database (file) with all given arguments.<br>
	 * You need a {@link DataBase} to work with SQLite.
	 * @param filePath the file path with the filename (ex. <code>data/mydb.sqlite3</code> or <code>D:/myServer/data/mydb.sqlite3</code>)
	 */
	public SQLiteDataBase(String filePath) {
		this(new File(filePath));
	}
	
	/**
	 * Constructs a SQLite Database (file) with all given arguments.<br>
	 * You need a {@link DataBase} to work with SQLite.
	 * @param databaseFile the {@link File} object of your sqlite database file
	 */
	public SQLiteDataBase(File databaseFile) {
		this(databaseFile, new SQLiteConfig());
	}
	
	/**
	 * Constructs a SQLite Database (file) with all given arguments.<br>
	 * You need a {@link DataBase} to work with SQLite.
	 * @param directory the {@link File} of the directory which contains or will contain the database file
	 * @param filename the file name as {@link String}
	 */
	public SQLiteDataBase(File directory, String filename) {
		this(new File(directory, filename));
	}
	
	/**
	 * Constructs a SQLite Database (file) with all given arguments.<br>
	 * You need a {@link DataBase} to work with SQLite.
	 * @param databaseFile the {@link File} of the database file
	 * @param config the {@link SQLiteConfig} of <code>org.sqlite</code> - can be null
	 */
	public SQLiteDataBase(File databaseFile, SQLiteConfig config) {
		this.databaseFile = databaseFile;
		this.config = config;
		this.filePath = databaseFile.getAbsolutePath();
	}
	
	/**
	 * Returns the database {@link File} object
	 * @return the database {@link File} object
	 */
	public File getFile() {
		return databaseFile;
	}
	
	/**
	 * Returns the {@link SQLiteConfig}, can be null
	 * @return the {@link SQLiteConfig}, can be null
	 */
	public SQLiteConfig getConfig() {
		return config;
	}
	
	/**
	 * Returns the filePath of the database file
	 * @return the filePath of the database file
	 */
	public String getFilePath() {
		return filePath;
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
