package de.mreturkey.sql.database;

import java.sql.SQLException;

import de.mreturkey.sql.provider.Connection;
import de.mreturkey.sql.provider.ProviderType;

public interface DataBase {

	Connection openConnection() throws SQLException;
	
	ProviderType getType();
	
}
