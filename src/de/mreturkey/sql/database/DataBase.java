package de.mreturkey.sql.database;

import de.mreturkey.sql.provider.Provider;
import de.mreturkey.sql.provider.ProviderType;

public interface DataBase {

	Provider openConnection();
	
	ProviderType getType();
	
}
