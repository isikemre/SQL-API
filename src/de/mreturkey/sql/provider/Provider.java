package de.mreturkey.sql.provider;

/**
 * Represents a SQL-Provider
 * 
 * @author mReTurkey
 * @since 1.0
 * @see MySQL
 * @see SQLite
 *
 */
public interface Provider extends QueryProvider {

	/**
	 * Returns the name of this provider.
	 * @return the name of this provider
	 */
	String getProviderName();
	
}
