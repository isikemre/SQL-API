package de.mreturkey.sql;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.database.MySQLDataBase;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;
import de.mreturkey.sql.result.Result;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	public static Connection SESSION_DB = null;
	
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
		long l1 = System.currentTimeMillis();
		SESSION_DB = API.getProvider(ProviderType.MYSQL).openConnection(new MySQLDataBase("localhost", 3306, "mc", "mc", "pass1234"));
		Result result = API.getBuilder().buildSelect().from("session").execute(SESSION_DB);
		
		for(Object[] row : result.getValues()) {
			for(Object col : row) System.out.println(col);
		}
		
		long l2 = System.currentTimeMillis();
		System.out.println("------" + (l2 - l1));
	}
}
