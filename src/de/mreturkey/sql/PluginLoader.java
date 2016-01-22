package de.mreturkey.sql;

import java.io.File;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.column.SQLiteColumnType;
import de.mreturkey.sql.database.SQLiteDataBase;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;
import de.mreturkey.sql.query.builder.InsertBuilder;
import de.mreturkey.sql.result.Result;
import de.mreturkey.sql.table.ColumnSettings;
import de.mreturkey.sql.table.Table;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	public static Connection DB = null;
	
	private static DocumentBuilder documentBuilder;
	
	@Override
	public void onLoad() {
		instance = this;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}

	public static PluginLoader getInstance() {
		return instance;
	}
	
	public static DocumentBuilder getDocumentBuilder() {
		return documentBuilder;
	}
	
	private static void sqlite(String[] args) throws SQLException {
		long l1 = System.currentTimeMillis();
		DB = API.getProvider(ProviderType.SQLITE).openConnection(new SQLiteDataBase(new File("D:/test/mydb.sqlite3")));
		
		Table table = API.createTable(ProviderType.SQLITE, "lol");
		table.addColumn("id", new ColumnSettings(SQLiteColumnType.INTEGER, null, false, null, "ASDNASBN Dasl dal hsdH Sa", true));
		table.addColumn("name", new ColumnSettings(SQLiteColumnType.VARCHAR, 255, false, "My Default Name", "No Comment ^^", false));
		
		DB.queryTable(table);
		
		Result r = API.getBuilder().select().from("lol").execute(DB);
		if(r.getResultSet() != null) {
			while(r.getResultSet().next()) {
				System.out.println(r.getResultSet().getInt(1));
				System.out.println(r.getResultSet().getString(2));
			}
		}
		
		InsertBuilder insert = API.getBuilder().insert();
		insert.into("lol");
		insert.add("id", null);
		insert.add("name", "Emre").execute(DB);
		insert.add("name", "Esra").execute(DB);
		insert.add("name", "Murat").execute(DB);
		insert.add("name", "Gul").execute(DB);
		
		long l2 = System.currentTimeMillis();
		System.out.println("------" + (l2 - l1));
	}
	
	public static void main(String[] args) throws Exception {
		sqlite(null);
	}
}
