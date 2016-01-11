package de.mreturkey.sql.database;

import java.sql.Connection;

public interface DataBase {

	/**
	 * Returns the type of this database
	 * @return the type of this database
	 */
	DataBaseType getType();
	
	/**
	 * Opens a connection with the config, which was set before this method invoked.
	 * @return {@link Connection} - via provider
	 */
	Connection openConnection();
}
