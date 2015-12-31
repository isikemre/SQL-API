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
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result query(String query, boolean prepared) throws SQLException, SQLTimeoutException;
	
	/**
	 * Returns the {@link Result} of the given query.<br>
	 * This method will execute the given query as PreparedStatment.
	 * @param query - SQL Query as {@link String}
	 * @return the {@link Result} of the given query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result query(String query) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement with the given {@link SelectQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param selectQuery - 
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result select(SelectQuery selectQuery) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in all columns from and given table<br>
	 * and returns the {@link Result} of the executed query.
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
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {column} FROM {table}</code></blockquote>
	 * @param table
	 * @param column
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result select(String table, String column) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given columns and the given table<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {columns...} FROM {table}</code></blockquote>
	 * @param table
	 * @param columns
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result select(String table, String... columns) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in all columns, the given table and the given {@link WhereClausel}<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT * FROM {table} WHERE {whereClausel}</code></blockquote>
	 * @param table
	 * @param where
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result select(String table, WhereClausel where) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given column, the given table and the given {@link WhereClausel}<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {column} FROM {table} WHERE {whereClausel}</code></blockquote>
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result select(String table, String column, WhereClausel where) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given columns, the given table and the given {@link WhereClausel}<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {columns...} FROM {table} WHERE {whereClausel}</code></blockquote>
	 * @param table
	 * @param where
	 * @param columns
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result select(String table, WhereClausel where, String... columns) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>INSERT</code> Statement with the given {@link InsertQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param insertQuery
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result insert(InsertQuery insertQuery) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>INSERT</code> Statement with the given columns, values and table.<br>
	 * and returns the {@link Result} of the executed query.
	 * @param table
	 * @param columns
	 * @param values
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result insert(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>UPDATE</code> Statement with the given {@link UpdateQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param updateQuery
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result update(UpdateQuery updateQuery) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>UPDATE</code> Statement with the given columns, values and table<br>
	 * and returns the {@link Result} of the executed query.
	 * @param table
	 * @param columns
	 * @param values
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result update(String table, String[] columns, Object[] values) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>UPDATE</code> Statement with the given columns, values, table and {@link WhereClausel}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param table
	 * @param columns
	 * @param values
	 * @param where
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result update(String table, String[] columns, Object[] values, WhereClausel where) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>DELETE</code> Statement with the given {@link DeleteQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param deleteQuery
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result delete(DeleteQuery deleteQuery) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>DELETE</code> Statement with the given table<br>
	 * and returns the {@link Result} of the executed query.<br><br>
	 * 
	 * This method will DELETE ALL entries/records in the given table
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result delete(String table) throws SQLException, SQLTimeoutException;
	
	/**
	 * Executes a <code>DELETE</code> Statement with the given table and {@link WhereClausel}<br>
	 * @param table
	 * @param where
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result delete(String table, WhereClausel where) throws SQLException, SQLTimeoutException;
	
}
