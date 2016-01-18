package de.mreturkey.sql;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.api.API;
import de.mreturkey.sql.database.SQLiteDataBase;
import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;
import de.mreturkey.sql.result.Result;

public class PluginLoader extends JavaPlugin {

	private static PluginLoader instance = null;
	public static Connection DB = null;
	
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
		DB = API.getProvider(ProviderType.SQLITE).openConnection(new SQLiteDataBase(new File("D:/test/mydb.sqlite3")));
		Result result = API.getBuilder().select().from("authme").execute(DB);
		
		System.out.println(result.getValues()[0][1]);
		
		long l2 = System.currentTimeMillis();
		System.out.println("------" + (l2 - l1));
	}
}
