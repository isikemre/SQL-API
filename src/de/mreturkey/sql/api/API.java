package de.mreturkey.sql.api;

import de.mreturkey.sql.provider.MySQL;
import de.mreturkey.sql.provider.Provider;
import de.mreturkey.sql.provider.ProviderType;
import de.mreturkey.sql.provider.SQLite;
import de.mreturkey.sql.query.builder.BuildManager;
import de.mreturkey.sql.query.builder.IBuildManager;

public final class API {

	private final static MySQL MYSQL_PROVIDER = new MySQL();
	private final static SQLite SQLITE_PROVIDER = new SQLite();
	private final static BuildManager BUILDER = new IBuildManager();
	
	private API() {}
	
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
	
	public static BuildManager getBuilder() {
		return BUILDER;
	}
	
}
