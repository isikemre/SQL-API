package de.mreturkey.sql.database;

import java.sql.SQLException;

import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public interface DataBase {

	/**
	 * Opens and returns a {@link Connection} to this database
	 * @return a {@link Connection} to this database
	 * @throws SQLException
	 */
	Connection openConnection() throws SQLException;
	
	/**
	 * Returns the type of this {@link DataBase}
	 * @return the type of this DataBase
	 */
	ProviderType getType();
	
}
