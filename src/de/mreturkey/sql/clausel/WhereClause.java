package de.mreturkey.sql.clausel;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.mreturkey.sql.operator.ComparisonOperator;
import de.mreturkey.sql.operator.LogicalOperator;
import de.mreturkey.sql.util.SQLSerializable;
import de.mreturkey.sql.util.WhereEntry;

public class WhereClause implements Clause, SQLSerializable {
	
	/**
	 * The first (index:0) entry in this list is the main WhereEntry.<br>
	 * That means, that all other entries need a LogicalOperator, but the main WhereEntry not (LogicalOperator is there 'null').
	 */
	private final List<WhereEntry<?>> entries;
	private String lastSQL, lastPreparedSQL;
	private boolean changed = true, changedPrepared = true;
	
	/**
	 * Constructs a <code>WhereClause</code> with the given "where entries"<br>
	 * You can still add more entries, if you want to.
	 * @param entries 
	 */
	public WhereClause(WhereEntry<?>... entries) {
		this.entries = new ArrayList<>();
		for(WhereEntry<?> we : entries) this.add(we);
	}
	
	/**
	 * Constructs a <code>WhereClause</code> with the given arguments<br>
	 * You can still add more entries, if you want to.
	 * @param column
	 * @param operator
	 * @param value
	 */
	public <V> WhereClause(String column, String operator, V value) {
		this(new WhereEntry<V>(column, operator, value, null));
	}

	/**
	 * Constructs a <code>WhereClause</code> which allows you to add entries<br>
	 */
	public WhereClause() {
		this.entries = new ArrayList<>();
	}
	
	/**
	 * Appends the given <code>WhereEntry</code>, with the specified class type, to the list of entries<br>
	 * The first added entry will be used as main entry.
	 * @param entry the entry which contains all informations about a part of the whole <code>WHERE</code> Clause
	 */
	public <V> void add(WhereEntry<V> entry) {
		if(entries.isEmpty()) {
			entries.add(entry);
		} else {
			if(entry.getLogicalOperator() == null) throw new NullPointerException("logicalOperator is null (Use OR or AND)");
			entries.add(entry);
		}
		changed = true;
	}
	
	/**
	 * Constructs at first a <code>WhereEntry</code> with all given arguments and then appends the created entry to the list of entries.<br>
	 * The first added entry will be used as main entry.
	 * @param column name of the column (ex. <code>id</code>)
	 * @param operator the operator which compares the value in the query with the given value
	 * (ex. <code>=</code>, <code>LIKE</code> or <code>></code>)
	 * You can use the {@link ComparisonOperator} enum
	 * @param value the value which will be compared
	 * @param logicalOperator if you added more than one entry, you need to add a {@link LogicalOperator} (ex. <code>AND</code>). If this argument is null, it will be the main entry.
	 */
	public <V> void add(String column, String operator, V value, LogicalOperator logicalOperator) {
		add(new WhereEntry<V>(column, operator, value, logicalOperator));
	}
	
	/**
	 * Adds the main entry to the list of entries.<br>
	 * The main entry is the first entry in the list. You don't need a {@link LogicalOperator} for the main entry.
	 * @param entry
	 */
	public <V> void main(WhereEntry<V> entry) {
		if(entries.isEmpty()) {
			entries.add(entry);
		} else {
			entries.set(0, entry);
		}
		changed = true;
	}
	
	/**
	 * Constructs at first a <code>WhereEntry</code> with all the given arguments and then sets the created entry as main entry to the list of entries.<br>
	 * The main entry is the first entry in the list. You don't need a {@link LogicalOperator} for the main entry.
	 * @param column name of the column (ex. <code>id</code>)
	 * @param operator the operator which compares the value in the query with the given value
	 * (ex. <code>=</code>, <code>LIKE</code> or <code>></code>)
	 * You can use the {@link ComparisonOperator} enum
	 * @param value the value which will be compared
	 */
	public <V> void main(String column, String operator, V value) {
		main(new WhereEntry<V>(column, operator, value, null));
	}
	
	/**
	 * Appends the given <code>WhereEntry</code>, with the specified class type, to the list of entries<br>
	 * The {@link LogicalOperator} of the entry must be <code>LogicalOperator.AND</code>. Otherwise it will throw an {@link IllegalArgumentException}
	 * @param entry the entry which contains all informations about a part of the whole <code>WHERE</code> Clause
	 */
	public <V> void and(WhereEntry<V> entry) {
		checkMainEntry();
		if(entry.getLogicalOperator() != LogicalOperator.AND) throw new IllegalArgumentException("LogicalOperator is not AND");
		entries.add(entry);
		changed = true;
	}
	
	/**
	 * Constructs at first a <code>WhereEntry</code> with all given arguments and then appends the created entry to the list of entries.<br>
	 * The {@link LogicalOperator} of the entry must be <code>LogicalOperator.AND</code>. Otherwise it will throw an {@link IllegalArgumentException}
	 * @param column name of the column (ex. <code>id</code>)
	 * @param operator the operator which compares the value in the query with the given value
	 * (ex. <code>=</code>, <code>LIKE</code> or <code>></code>)
	 * You can use the {@link ComparisonOperator} enum
	 * @param value the value which will be compared
	 */
	public <V> void and(String column, String operator, V value) {
		and(new WhereEntry<V>(column, operator, value, LogicalOperator.AND));
	}
	
	/**
	 * Appends the given <code>WhereEntry</code>, with the specified class type, to the list of entries<br>
	 * The {@link LogicalOperator} of the entry must be <code>LogicalOperator.OR</code>. Otherwise it will throw an {@link IllegalArgumentException}
	 * @param entry the entry which contains all informations about a part of the whole <code>WHERE</code> Clause
	 */
	public <V> void or(WhereEntry<V> entry) {
		checkMainEntry();
		if(entry.getLogicalOperator() != LogicalOperator.OR) throw new IllegalArgumentException("LogicalOperator is not OR");
		entries.add(entry);
		changed = true;
	}
	
	/**
	 * Constructs at first a <code>WhereEntry</code> with all given arguments and then appends the created entry to the list of entries.<br>
	 * The {@link LogicalOperator} of the entry must be <code>LogicalOperator.OR</code>. Otherwise it will throw an {@link IllegalArgumentException}
	 * @param column name of the column (ex. <code>id</code>)
	 * @param operator the operator which compares the value in the query with the given value
	 * (ex. <code>=</code>, <code>LIKE</code> or <code>></code>)
	 * You can use the {@link ComparisonOperator} enum
	 * @param value the value which will be compared
	 */
	public <V> void or(String column, String operator, V value) {
		or(new WhereEntry<V>(column, operator, value, LogicalOperator.OR));
	}
	
	/**
	 * Checks the main entry.<br>
	 * If the main entry doesn't exist (list is empty) it will be added with <code>null</code>.<br>
	 * Why? Because if I add a entry to index:1 it will throw a {@link IndexOutOfBoundsException}. Yep :)
	 */
	private void checkMainEntry() {
		if(entries.isEmpty()) entries.add(null);
	}
	
	/**
	 * Returns the <code>WhereEntry</code> of the list of entries which has the given index.
	 * @param index
	 * @return
	 */
	public WhereEntry<?> get(int index) {
		return entries.get(index);
	}
	
	/**
	 * Returns only all values of the entries as <code>List&lt;Object&gt;</code>
	 * @return all values as <code>List&lt;Object&gt;</code>
	 */
	public List<Object> getAllValues() {
		final List<Object> tmp = new ArrayList<>();
		for(WhereEntry<?> we : entries) {
			tmp.add(we.getParsedValue());
		}
		return tmp;
	}
	
	/**
	 * Returns true if something changed since it was cached.<br>
	 * If false, the cached SQL (which generated by <code>.toSQL()</code>) can be used.<br><br>
	 * <b>Note: </b>This method is used by {@link SQLSerializable}
	 * @return true if something changed since it was cached.
	 */
	public boolean isChanged() {
		return changed;
	}
	
	/**
	 * Returns true if the list of entries is empty.
	 * @return true if the list of entries is empty.
	 */
	public boolean isEmpty() {
		return entries.isEmpty();
	}
	
	@Override
	public String toSQL() {
		if(entries == null || entries.isEmpty()) return "";
		if(!changed) return lastSQL;
		if(!entries.isEmpty() && entries.get(0) == null) throw new NullPointerException("the first entry (main) is missing");
		String res = "";
		int i = 0;
		for(WhereEntry<?> e : entries) {
			res += e.toSQL(i == 0 ? true : false) + " ";
			i++;
		}
		lastSQL = res == "" ? "" : res.substring(0, res.length() -1);
		changed = false;
		return lastSQL;
	}
	
	/**
	 * Generates a SQL Query <b>for a PreparedStatement</b> with all data in this object and returns the SQL Query as String.<br>
	 * If you want a SQL Query for the normal {@link Statement} you need to invoke <code>.toSQL()</code>, if exists.
	 * @return the SQL Query as String
	 */
	public String toPreparedSQL() {
		if(entries == null || entries.isEmpty()) return "";
		if(!changedPrepared) return lastPreparedSQL;
		if(!entries.isEmpty() && entries.get(0) == null) throw new NullPointerException("the first entry (main) is missing");
		String res = "";
		int i = 0;
		for(WhereEntry<?> e : entries) {
			res += e.toPreparedSQL(i == 0 ? true : false) + " ";
			i++;
		}
		lastPreparedSQL = res == "" ? "" : res.substring(0, res.length() -1);
		changedPrepared = false;
		return lastPreparedSQL;
	}
	
	/**
	 * Same effect like <code>toSQL()</code>
	 */
	@Override
	public String toString() {
		return toSQL();
	}
	
	/* TESTS */
	//17303	 <-> 17919 Millis | mit <V>
	//17934 <-> 17956
}
