package de.mreturkey.sql.provider;

import java.sql.SQLException;

import de.mreturkey.sql.database.DataBase;

/**
 * Represents a SQL-Provider
 * 
 * @author mReTurkey
 * @since 1.0
 * @see MySQL
 * @see SQLite
 *
 */
public interface Provider {

	/**
	 * Returns the name of this provider.
	 * @return the name of this provider
	 */
	String getProviderName();
	
	/**
	 * Opens a connection to the sql database
	 */
	Connection openConnection(DataBase database) throws SQLException;

}
