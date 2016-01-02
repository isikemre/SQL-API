package de.mreturkey.sql.util;

import de.mreturkey.sql.clausel.OrderBy;

public class OrderByEntry {

	private final String column;
	private final OrderBy orderBy;
	
	public OrderByEntry(String column, OrderBy orderBy) {
		this.column = column;
		this.orderBy = orderBy;
	}
	
	public String getColumn() {
		return column;
	}
	
	public OrderBy getOrderBy() {
		return orderBy;
	}
}
