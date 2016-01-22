package de.mreturkey.sql.provider;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import de.mreturkey.sql.PluginLoader;
import de.mreturkey.sql.database.DataBase;
import de.mreturkey.sql.database.MySQLDataBase;
import de.mreturkey.sql.table.Engine;
import de.mreturkey.sql.table.MySQLTable;
import de.mreturkey.sql.table.Table;

public final class MySQL implements Provider {

	@Override
	public Connection openConnection(final DataBase database) throws SQLException {
		if(!(database instanceof MySQLDataBase)) throw new IllegalArgumentException("database argument is not a type of MySQLDataBase");
		return this.openConnection((MySQLDataBase) database);
	}
	
	/**
	 * Opens a connection to the database
	 * 
	 * @param database
	 * @return
	 * @throws SQLException
	 */
	public Connection openConnection(final MySQLDataBase database) throws SQLException {
		if(database == null) throw new NullPointerException("database cannot be null");
		
		final MysqlDataSource mysql = new MysqlDataSource();
		mysql.setURL("jdbc:mysql://"+database.getHost()+":"+database.getPort()+"/"+database.getDatabase());
		mysql.setUser(database.getUser());
		mysql.setPassword(database.getPassword());
		
		DataSource dataSource = mysql;
		java.sql.Connection connection = (java.sql.Connection) dataSource.getConnection();
		
		return new ProviderConnection(connection);
	}

	@Override
	public final String getProviderName() {
		return "mysql";
	}
	
	@Override
	public final ProviderType getType() {
		return ProviderType.MYSQL;
	}

	@Override
	public Connection fromXML(File file) throws SAXException, IOException, NumberFormatException, SQLException {
		Connection con = null;
		Document doc = PluginLoader.getDocumentBuilder().parse(file);
		org.w3c.dom.Node mysql = doc.getElementsByTagName("mysql").item(0);
		
		if(mysql.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
			final org.w3c.dom.Element element = (Element) mysql;
			final String host = element.getElementsByTagName("host").item(0).getTextContent();
			final String port = element.getElementsByTagName("port").item(0).getTextContent();
			final String database = element.getElementsByTagName("database").item(0).getTextContent();
			final String user = element.getElementsByTagName("user").item(0).getTextContent();
			final String password = element.getElementsByTagName("password").item(0).getTextContent();
			
			con = this.openConnection(new MySQLDataBase(host, Integer.parseInt(port), database, user, password));
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
		final String host;
		final int port;
		final String database;
		final String user;
		final String password;
		
		if(prefixPath == null || prefixPath == "") {
			host = yaml.getString("DataSource.host");
			port = yaml.getInt("DataSource.port");
			database = yaml.getString("DataSource.database");
			user = yaml.getString("DataSource.user");
			password = yaml.getString("DataSource.password");
		} else {
			host = yaml.getString(prefixPath + ".host");
			port = yaml.getInt(prefixPath + ".port");
			database = yaml.getString(prefixPath + ".database");
			user = yaml.getString(prefixPath + ".user");
			password = yaml.getString(prefixPath + ".password");
		}
		
		return this.openConnection(new MySQLDataBase(host, port, database, user, password));
	}
	
	@Override
	public Connection fromProperties(Properties properties) throws NumberFormatException, SQLException {
		if(properties == null) throw new NullPointerException("properties cannot be null");
		return this.openConnection(new MySQLDataBase(
				properties.getProperty("host"),
				Integer.parseInt(properties.getProperty("port")),
				properties.getProperty("database"),
				properties.getProperty("user"),
				properties.getProperty("password")));
	}

	@Override
	public Connection fromJSON(File file) throws JsonSyntaxException, IOException, SQLException {
		return fromJSON(FileUtils.readFileToString(file));
	}
	
	@Override
	public Connection fromJSON(String json) throws JsonSyntaxException, IOException, SQLException {
		return this.openConnection(new Gson().fromJson(json, MySQLDataBase.class));
	}

	@Override
	public Table createTable(String name) {
		return new MySQLTable(name);
	}

	@Override
	public Table createTable(String name, Engine engine) {
		return new MySQLTable(name, engine);
	}
}
