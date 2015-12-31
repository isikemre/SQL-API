package de.mreturkey.sql;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	
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
}
