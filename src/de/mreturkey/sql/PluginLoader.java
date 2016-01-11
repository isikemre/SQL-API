package de.mreturkey.sql;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.provider.MySQL;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	private static final MySQL
	@Override
	public void onLoad() {
		instance = this;
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
