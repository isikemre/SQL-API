package de.mreturkey.sql.provider;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import de.mreturkey.sql.clausel.WhereClause;
import de.mreturkey.sql.query.DeleteQuery;
import de.mreturkey.sql.query.InsertQuery;
import de.mreturkey.sql.query.SelectQuery;
import de.mreturkey.sql.query.UpdateQuery;
import de.mreturkey.sql.result.Result;
import de.mreturkey.sql.table.Table;

/**
 * Represents a QueryProvider
 * @author mReTurkey
 * @see Provider
 *
 */
public interface Connection {

	/**
	 * Returns the {@link Result} of the given query.
	 * @param sql
	 * @param args
	 * @return the {@link Result} of the given query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 * @throws SQLTimeoutException when the driver has determined that the timeout value that was specified by the <code>setQueryTimeout</code> method has been exceeded and has at least attempted to cancel the currently running <code>Statement</code>
	 */
	Result prepareQuery(String query, Object... args) throws SQLException;
	
	/**
	 * Prepares and executes the given query.
	 * @param sql
	 * @param args
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	void prepareUpdateQuery(String query, Object... args) throws SQLException;
	
	/**
	 * Returns the {@link Result} of the given query.
	 * @param query - SQL Query as {@link String}
	 * @return the {@link Result} of the given query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result executeQuery(String query) throws SQLException;
	
	/**
	 * Only executes the given query, without a {@link Result}.
	 * @param query
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	void updateQuery(String query) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement with the given {@link SelectQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param selectQuery - 
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result select(SelectQuery selectQuery) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in all columns from and given table<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT * FROM {table}</code></blockquote>
	 * 
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException
	 */
	Result select(String table) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given column and the given table<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {column} FROM {table}</code></blockquote>
	 * @param table
	 * @param column
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result select(String table, String column) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given columns and the given table<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {columns...} FROM {table}</code></blockquote>
	 * @param table
	 * @param columns
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result select(String table, String... columns) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in all columns, the given table and the given {@link WhereClause}<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT * FROM {table} WHERE {whereClausel}</code></blockquote>
	 * @param table
	 * @param where
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result select(String table, WhereClause where) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given column, the given table and the given {@link WhereClause}<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {column} FROM {table} WHERE {whereClausel}</code></blockquote>
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result select(String table, String column, WhereClause where) throws SQLException;
	
	/**
	 * Executes a <code>SELECT</code> Statement in the given columns, the given table and the given {@link WhereClause}<br>
	 * and returns the {@link Result} of the executed query.
	 * <br><b>Query Example:</b>
	 * <blockquote><code>SELECT {columns...} FROM {table} WHERE {whereClausel}</code></blockquote>
	 * @param table
	 * @param where
	 * @param columns
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result select(String table, WhereClause where, String... columns) throws SQLException;
	
	/**
	 * Executes a <code>INSERT</code> Statement with the given {@link InsertQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param insertQuery
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result insert(InsertQuery insertQuery) throws SQLException;
	
	/**
	 * Executes a <code>INSERT</code> Statement with the given columns, values and table.<br>
	 * and returns the {@link Result} of the executed query.
	 * @param table
	 * @param columns
	 * @param values
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result insert(String table, String[] columns, Object[] values) throws SQLException;
	
	/**
	 * Executes a <code>UPDATE</code> Statement with the given {@link UpdateQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param updateQuery
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result update(UpdateQuery updateQuery) throws SQLException;
	
	/**
	 * Executes a <code>UPDATE</code> Statement with the given columns, values and table<br>
	 * and returns the {@link Result} of the executed query.
	 * @param table
	 * @param columns
	 * @param values
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result update(String table, String[] columns, Object[] values) throws SQLException;
	
	/**
	 * Executes a <code>UPDATE</code> Statement with the given columns, values, table and {@link WhereClause}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param table
	 * @param columns
	 * @param values
	 * @param where
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result update(String table, String[] columns, Object[] values, WhereClause where) throws SQLException;
	
	/**
	 * Executes a <code>DELETE</code> Statement with the given {@link DeleteQuery}<br>
	 * and returns the {@link Result} of the executed query.
	 * @param deleteQuery
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result delete(DeleteQuery deleteQuery) throws SQLException;
	
	/**
	 * Executes a <code>DELETE</code> Statement with the given table<br>
	 * and returns the {@link Result} of the executed query.<br><br>
	 * 
	 * This method will DELETE ALL entries/records in the given table
	 * @param table
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result delete(String table) throws SQLException;
	
	/**
	 * Executes a <code>DELETE</code> Statement with the given table and {@link WhereClause}<br>
	 * @param table
	 * @param where
	 * @return the {@link Result} of the executed query.
	 * @throws SQLException if a database access error occurs; this method is called on a closed <code>PreparedStatement</code> or the SQL statement does not return a <code>ResultSet</code> object
	 */
	Result delete(String table, WhereClause where) throws SQLException;

	void queryTable(Table table) throws SQLException;

	void queryTable(Table table, boolean createIfNotExists) throws SQLException;

	/**
	 * Returns the {@link java.sql.Connection} of this Connection
	 * @return the {@link java.sql.Connection} of this Connection
	 */
	java.sql.Connection getSQLConnection();

}
