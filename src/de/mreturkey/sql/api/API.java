package de.mreturkey.sql.api;

import de.mreturkey.sql.provider.MySQL;
import de.mreturkey.sql.provider.Provider;
import de.mreturkey.sql.provider.ProviderType;
import de.mreturkey.sql.provider.SQLite;
import de.mreturkey.sql.query.builder.BuildManager;
import de.mreturkey.sql.query.builder.IBuildManager;
import de.mreturkey.sql.table.Engine;
import de.mreturkey.sql.table.Table;

public final class API {

	private final static MySQL MYSQL_PROVIDER = new MySQL();
	private final static SQLite SQLITE_PROVIDER = new SQLite();
	private final static BuildManager BUILDER = new IBuildManager();
	
	//only static use
	private API() {}
	
	/**
	 * Returns the <code>Provider</code> which allows you to create, build and execute querys.<br>
	 * If the given provider name is null or the provider doesn't exists, it will throw an <code>Exception</code>
	 * @param type
	 * @return <code>Provider</code> which has the given type
	 */
	public static Provider getProvider(final String name) {
		if(name == null) throw new NullPointerException("provider name is null");
		switch (name.toLowerCase()) {
		case "mysql":
			return MYSQL_PROVIDER;
		
		case "sqlite":
			return SQLITE_PROVIDER;
			
		default:
			throw new IllegalArgumentException("provider not found.");
		}
	}
	
	/**
	 * Returns the <code>Provider</code> which allows you to create, build and execute querys.<br>
	 * If the given provider type is null or the provider doesn't exists, it will throw an <code>Exception</code>
	 * @param type
	 * @return <code>Provider</code> which has the given type
	 */
	public static Provider getProvider(final ProviderType type) {
		if(type == null) throw new NullPointerException("provider type is null");
		switch (type) {
		case MYSQL:
			return MYSQL_PROVIDER;

		case SQLITE:
			return SQLITE_PROVIDER;
			
		default:
			throw new IllegalArgumentException("provider not found.");
		}
	}
	
	/**
	 * Returns the <code>BuildManager</code> which allows you to build SQL-Querys
	 * @return the <code>BuildManager</code>
	 */
	public static BuildManager getBuilder() {
		return BUILDER;
	}
	
	/**
	 * Creates a table for the given provider type with given arguments<br>
	 * After you edit/build the created table with arguments like name, columns or else, you need to query this table with the provider you used to create this engine.<br>
	 * <br><strong>Note:</strong> If you create a table for the <code>SQLite</code> provider the engine will be ignored, because <code>SQLite</code> doesn't need a table engine
	 * @param type The type of the Provider (ex. <code>ProviderType.MYSQL</code>)
	 * @param name name of the table (ex. <code>login</code>)
	 * @param engine the engine for the table. If engine is null, <code>InnoDB</code> will used as default engine. If the provider is <code>SQLite</code>, this argument will be ignored.
	 * @return the created table object, ready for edits.
	 */
	public static Table createTable(ProviderType type, String name, Engine engine) {
		switch (type) {
		case MYSQL:
			return MYSQL_PROVIDER.createTable(name, engine);
		case SQLITE:
			return SQLITE_PROVIDER.createTable(name, engine);
		default:
			throw new IllegalArgumentException("provider not found.");
		}
	}
	/**
	 * Creates a table for the given provider type with given arguments<br>
	 * After you edit/build the created table with arguments like name, columns or else, you need to query this table with the provider you used to create this engine.<br>
	 * The default table engine is <code>InnoDB</code>.<br>
	 * <br><strong>Note:</strong> If you create a table for the <code>SQLite</code> provider the engine will be ignored, because <code>SQLite</code> doesn't need a table engine
	 * @param type The type of the Provider (ex. <code>ProviderType.MYSQL</code>)
	 * @param name name of the table (ex. <code>login</code>)
	 * @param engine the engine for the table. If the provider is <code>SQLite</code>, this argument will be ignored.
	 * @return the created table object, ready for edits.
	 */
	public static Table createTable(ProviderType type, String name) {
		return createTable(type, name, Engine.INNO_DB);
	}
}
