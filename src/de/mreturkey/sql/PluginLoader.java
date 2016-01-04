package de.mreturkey.sql;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.clausel.OrderBy;
import de.mreturkey.sql.query.builder.Builder;
import de.mreturkey.sql.query.builder.SelectBuilder;

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
		SelectBuilder select = Builder.buildSelect();
		
		String lol = select
				.from("session")
				.where("uuid", "=", "adjabdjasbd-adasjdasda-adasdnasjdna-adasdad")
				.limit(5)
				.orderBy("id", OrderBy.DESC)
				.test();
		System.out.println(lol);
	}
}
