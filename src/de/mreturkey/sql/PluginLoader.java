package de.mreturkey.sql;

import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import de.mreturkey.sql.clausel.OrderBy;
import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.operator.LogicalOperator;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.util.OrderByEntry;

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
		SelectQuery sq = new SelectQuery();
		WhereClausel wc = new WhereClausel();
		
		wc.main("uudi", "=", UUID.randomUUID().toString());
		wc.add("destroyed", "=", true, LogicalOperator.OR);

		sq.setTable("the_table");
		sq.setWhereClausel(wc);
		
		sq.setLimit(10);
		sq.setOffset(5);
		sq.setOrderBy(new OrderByEntry("name", OrderBy.DESC));
		System.out.println(sq.toSQL());
	}
}
