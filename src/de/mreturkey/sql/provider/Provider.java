package de.mreturkey.sql.provider;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.Result;

/**
 * Represents a SQL-Provider
 * 
 * @author mReTurkey
 * @since 1.0
 * @see MySQL
 * @see SQLite
 *
 */
public interface Provider {

	/**
	 * Returns the name of this provider.
	 * @return the name of this provider
	 */
	String getProviderName();
	
	/**
	 * Returns the {@link Result} of the given query.<br>
	 * If <code>prepared</code> is false, the query will not executed as PreparedStatment.<br>
	 * As default it will use prepared statements.
	 * @param query - SQL Query as {@link String}
	 * @param prepared - false if you don't want to execute this query as prepared.
	 * @return the {@link Result} of the given query.
	 */
	Result query(String query, boolean prepared) throws SQLException, SQLTimeoutException;
	
	/**
	 * Returns the {@link Result} of the given query.<br>
	 * This method will execute the given query as PreparedStatment.
	 * @param query - SQL Query as {@link String}
	 * @return the {@link Result} of the given query.
	 */
	Result query(String query) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement with the given {@link SelectQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param selectQuery - 
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed PreparedStatement or the SQL statement does not return a ResultSet object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the setQueryTimeout method has been exceeded and has at least attempted to cancel the currently running Statement
	 */
	Result select(SelectQuery selectQuery) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in all columns from and given table<br>
	 * and returns the {@link Result} of the executed query.<br>
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT * FROM {table}</code></blockquote>
	 * 
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException
	 * @throws SQLTimeoutException
	 */
	Result select(String table) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given column and the given table<br>
	 * and returns the {@link Result} of the executed query.<br>
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {column} FROM {table}</code></blockquote>
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException
	 * @throws SQLTimeoutException
	 */
	Result select(String table, String column) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given columns and the the given table<br>
	 * and returns the {@link Result} of the executed query.<br>
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {column} FROM {table}</code></blockquote>
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException
	 * @throws SQLTimeoutException
	 */
	Result select(String table, String... columns) throws SQLException, SQLTimeoutException;
	
	Result select(String table, WhereClausel where) throws SQLException, SQLTimeoutException;
	
	Result select(String table, String column, WhereClausel where) throws SQLException, SQLTimeoutException;
	
	Result select(String table, WhereClausel where, String... columns) throws SQLException, SQLTimeoutException;
	
	Result insert(InsertQuery insertQuery) throws SQLException, SQLTimeoutException;
	
	Result insert(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException;
		
	Result update(UpdateQuery updateQuery) throws SQLException, SQLTimeoutException;
	
	Result update(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException;
	
	Result update(String table, String[] columns, Object[] values, WhereClausel where) throws SQLException, SQLTimeoutException;
	
	Result delete(DeleteQuery deleteQuery) throws SQLException, SQLTimeoutException;
	
	Result delete(String table) throws SQLException, SQLTimeoutException;
	
	Result delete(String table, WhereClausel where) throws SQLException, SQLTimeoutException;
	
}
