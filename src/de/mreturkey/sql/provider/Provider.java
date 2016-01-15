package de.mreturkey.sql.provider;

import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

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
	
	boolean fromXML(File xml);
	
	boolean fromYAML(File yaml);
	
	boolean fromProperties(Properties properties);
	
	boolean fromJSON(File json);

}
