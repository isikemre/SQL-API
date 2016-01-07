package de.mreturkey.sql;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.operator.LogicalOperator;
import de.mreturkey.sql.query.builder.Builder;
import de.mreturkey.sql.query.builder.DeleteBuilder;

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
	
	public static void main(String[] args) throws Exception {
		DeleteBuilder delete = Builder.buildDelete();
		
		String lol = delete.from("jaja")
				.where("uuid", "=", true)
				.where("name", "=", "mReTurkey", LogicalOperator.AND)
				.test();
		
		System.out.println(lol);
	}
}
