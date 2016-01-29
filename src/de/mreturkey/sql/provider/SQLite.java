package de.mreturkey.sql.provider;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.sqlite.SQLiteDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import de.mreturkey.sql.PluginLoader;
import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.database.SQLiteDataBase;
import de.mreturkey.sql.table.Engine;
import de.mreturkey.sql.table.SQLiteTable;
import de.mreturkey.sql.table.Table;

/**
 * Represents the SQLite Provider
 */
public class SQLite implements Provider {

	@Override
	public Connection openConnection(DataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		if(!(database instanceof SQLiteDataBase)) throw new IllegalArgumentException("database must be a SQLiteDataBase Object");
		return openConnection((SQLiteDataBase) database);
	}
	
	/**
	 * Opens a {@link Connection} to the {@link DataBase}
	 * @param database the {@link SQLiteDataBase}
	 * @return the opened {@link Connection}
	 * @throws SQLException An exception that provides information on a database access error or other errors.
	 */
	public Connection openConnection(SQLiteDataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");

		final SQLiteDataSource sqlite = new SQLiteDataSource(database.getConfig());
		sqlite.setUrl("jdbc:sqlite:"+database.getFile().getAbsolutePath());
		
		DataSource dataSource = sqlite;
		java.sql.Connection connection = (java.sql.Connection) dataSource.getConnection();
		return new ProviderConnection(connection);
	}
	
	@Override
	public String getProviderName() {
		return "sqlite";
	}

	@Override
	public ProviderType getType() {
		return ProviderType.SQLITE;
	}

	@Override
	public Connection fromXML(File file) throws SAXException, IOException, NumberFormatException, SQLException {
		Connection con = null;
		Document doc = PluginLoader.getDocumentBuilder().parse(file);
		org.w3c.dom.Node mysql = doc.getElementsByTagName("mysql").item(0);
		
		if(mysql.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
			final org.w3c.dom.Element element = (Element) mysql;
			final String filePath = element.getElementsByTagName("filePath").item(0).getTextContent();
			
			con = this.openConnection(new SQLiteDataBase(new File(filePath)));
		}
		
		return con;
	}

	@Override
	public Connection fromYAML(File file) throws SQLException {
		return fromYAML(YamlConfiguration.loadConfiguration(file), null);
	}

	@Override
	public Connection fromYAML(YamlConfiguration yaml) throws SQLException {
		return fromYAML(yaml, null);
	}

	@Override
	public Connection fromYAML(YamlConfiguration yaml, String prefixPath) throws SQLException {
		if(yaml == null) throw new NullPointerException("yaml cannot be null"); 
		final String filePath;
		
		if(prefixPath == null || prefixPath == "") {
			filePath = yaml.getString("DataSource.filePath");
		} else {
			filePath = yaml.getString(prefixPath + ".filePath");
		}
		
		return this.openConnection(new SQLiteDataBase(new File(filePath)));
	}

	@Override
	public Connection fromProperties(Properties properties) throws NumberFormatException, SQLException {
		if(properties == null) throw new NullPointerException("properties cannot be null");
		return this.openConnection(new SQLiteDataBase(new File(properties.getProperty("filePath"))));
	}

	@Override
	public Connection fromJSON(File file) throws JsonSyntaxException, IOException, SQLException {
		return fromJSON(FileUtils.readFileToString(file));
	}

	@Override
	public Connection fromJSON(String json) throws JsonSyntaxException, IOException, SQLException {
		return this.openConnection(new Gson().fromJson(json, SQLiteDataBase.class));
	}
	
	@Override
	public Table createTable(String name) {
		return new SQLiteTable(name);
	}

	@Override
	public Table createTable(String name, Engine engine) {
		return new SQLiteTable(name); //engine ignore
	}
}
