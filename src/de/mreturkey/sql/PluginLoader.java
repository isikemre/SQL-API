package de.mreturkey.sql;

import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.database.MySQLDataBase;
import de.mreturkey.sql.provider.MySQL;
import de.mreturkey.sql.query.SelectQuery;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	public static MySQL SESSION_DB = null;
	
	@Override
	public void onLoad() {
		instance = this;
		try {
			API.getProvider("mysql").openConnection(new MySQLDataBase("", 0, null, null, null));
			API.getProvider(null).select(new SelectQuery());
		} catch (SQLException e) {
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
	
	public static void main(String[] args) throws Exception {
		
	}
}
