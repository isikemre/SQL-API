package de.mreturkey.sql;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.provider.MySQL;
import de.mreturkey.sql.query.InsertQuery;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	private static MySQL mysql = (MySQL) API.getProvider("mysql");
	
	@Override
	public void onLoad() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		mysql.insert(new InsertQuery());
		InsertQuery iq = new InsertQuery();
	}
	
	@Override
	public void onDisable() {
		
	}

	public static PluginLoader getInstance() {
		return instance;
	}
}
