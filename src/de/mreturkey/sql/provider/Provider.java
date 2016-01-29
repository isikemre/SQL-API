package de.mreturkey.sql.provider;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.bukkit.configuration.file.YamlConfiguration;
import org.xml.sax.SAXException;

import com.google.gson.JsonSyntaxException;

import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.table.Engine;
import de.mreturkey.sql.table.Table;

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
	 * Returns the type of this provider.
	 * @return type of provider
	 */
	ProviderType getType();
	
	/**
	 * Creates a table with the given name and returns that.<br>
	 * The default table engine is <code>InnoDB</code><br><br>
	 * <b>Note:</b> If you use SQLite as {@link Provider}, the table engine will be ignored, because SQLite doesn't need a table engine
	 * @param name the name of the table, cannot be null
	 * @return the created {@link Table}
	 */
	Table createTable(String name);
	
	/**
	 * Creates a table with the given name and table engine. Returns the {@link Table} finally.<br>
	 * The default table engine is <code>InnoDB</code><br><br>
	 * <b>Note:</b> If you use SQLite as {@link Provider}, the table engine will be ignored, because SQLite doesn't need a table engine
	 * @param name
	 * @param engine
	 * @return
	 */
	Table createTable(String name, Engine engine);
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}
	 * @return the opened {@link Connection}
	 * @throws SQLException An exception that provides information on a database access error or other errors.
	 */
	Connection openConnection(DataBase database) throws SQLException;
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given XML File.<br>
	 * The XML File need to have a specified format.
	 * @param file the {@link File} object of the XML File
	 * @return the opened {@link Connection}
	 * @throws SAXException Signals that the XML-DOM Parser throws an exception.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 * @throws NumberFormatException Encapsulate a general SAX error or warning. 
	 * @throws SQLException An exception that provides information on a database access error or other errors. 
	 */
	Connection fromXML(File file) throws SAXException, IOException, NumberFormatException, SQLException;
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given YAML File.<br>
	 * The YAML File need to have a specified format.
	 * @param file the {@link File} object of the YAML File
	 * @return the opened {@link Connection}
	 * @throws SQLException An exception that provides information on a database access error or other errors. 
	 */
	Connection fromYAML(File file) throws SQLException;
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given YAML File.<br>
	 * The YAML File need to have a specified format.
	 * @param yaml The {@link YamlConfiguration} of the yaml file
	 * @return the opened {@link Connection}
	 * @throws SQLException An exception that provides information on a database access error or other errors. 
	 */
	Connection fromYAML(YamlConfiguration yaml) throws SQLException;

	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given YAML File.<br>
	 * The YAML File need to have a specified format.
	 * @param yaml the {@link YamlConfiguration} of the yaml file
	 * @param prefixPath this path will be the prefix path (ex. prefixPath is <b>myServer.settings.mysql</b>. Now the method will search for <code>myServer.settings.mysql.host</code> for host, <code>myServer.settings.mysql.port</code> for port and other...)
	 * @return the opened {@link Connection}
	 * @throws SQLException An exception that provides information on a database access error or other errors.
	 */
	Connection fromYAML(YamlConfiguration yaml, String prefixPath) throws SQLException;
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given {@link Properties}.<br>
	 * The {@link Properties} need to have a specified format.
	 * @param properties the {@link Properties}
	 * @return the opened {@link Connection}
	 * @throws NumberFormatException Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.
	 * @throws SQLException An exception that provides information on a database access error or other errors. 
	 */
	Connection fromProperties(Properties properties) throws NumberFormatException, SQLException;
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given JSON File.<br>
	 * The JSON FIle need to have a specified format.
	 * @param file the JSON as {@link File}
	 * @return the opened {@link DataBase}
	 * @throws JsonSyntaxException This exception is raised when Gson attempts to read (or write) a malformed JSON element. 
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 * @throws SQLException An exception that provides information on a database access error or other errors.
	 */
	Connection fromJSON(File file) throws JsonSyntaxException, IOException, SQLException;
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}, which is defined in the given JSON File.<br>
	 * The JSON FIle need to have a specified format.
	 * @param json the JSON as {@link String}
	 * @return the opened {@link DataBase}
	 * @throws JsonSyntaxException This exception is raised when Gson attempts to read (or write) a malformed JSON element. 
	 * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 * @throws SQLException An exception that provides information on a database access error or other errors.
	 */
	Connection fromJSON(String json) throws JsonSyntaxException, IOException, SQLException;

}
