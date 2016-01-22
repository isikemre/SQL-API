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
	
	Table createTable(String name);
	
	Table createTable(String name, Engine engine);
	
	/**
	 * Opens a connection to the database
	 */
	Connection openConnection(DataBase database) throws SQLException;
	
	Connection fromXML(File file) throws SAXException, IOException, NumberFormatException, SQLException;
	
	Connection fromYAML(File file) throws SQLException;
	
	Connection fromYAML(YamlConfiguration yaml) throws SQLException;

	Connection fromYAML(YamlConfiguration yaml, String prefixPath) throws SQLException;
	
	Connection fromProperties(Properties properties) throws NumberFormatException, SQLException;
	
	Connection fromJSON(File file) throws JsonSyntaxException, IOException, SQLException;
	
	Connection fromJSON(String json) throws JsonSyntaxException, IOException, SQLException;

}
