package de.mreturkey.sql.query;

import java.util.ArrayList;

import de.mreturkey.sql.clausel.WhereClausel;
import de.mreturkey.sql.util.OrderByEntry;

public class SelectQuery implements Query {

	private String table;
	private ArrayList<String> columns;
	private WhereClausel whereClausel;
	private int offset = -1, limit = -1;
	private OrderByEntry orderBy;
	
	private final QueryType type = QueryType.SELECT;
	
	private String lastSQL;
	private boolean changed = true;
	
	@Override
	public String getTable() {
		return table;
	}

	@Override
	public QueryType getType() {
		return type;
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}

	public WhereClausel getWhereClausel() {
		return whereClausel;
	}

	public int getLimit() {
		return limit;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public OrderByEntry getOrderBy() {
		return orderBy;
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	@Override
	public void setTable(String table) {
		this.table = table;
		changed = true;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
		changed = true;
	}
	
	public void addColumn(String column) {
		if(columns == null) this.columns = new ArrayList<>();
		this.columns.add(column);
		changed = true;
	}

	public void setWhereClausel(WhereClausel whereClausel) {
		this.whereClausel = whereClausel;
		changed = true;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
		changed = true;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
		changed = true;
	}
	
	public void setOrderBy(OrderByEntry orderBy) {
		this.orderBy = orderBy;
		changed = true;
	}
	
	@Override
	public String toSQL() {
		if(table == null) throw new NullPointerException("table is null");
		if(!changed && whereClausel != null && !whereClausel.isChanged()) return lastSQL; //Cache
		
		String col;
		
		final String select;
		final String where;
		String extras = "";
		
		if(columns == null || columns.isEmpty()) {
			col = "*";
		} else {
			col = "";
			for(String column : columns) col += "`" + column + "`,";
			col = col.substring(0, col.length() -1);
		}
		
		select = "SELECT " + col + " FROM `" + table + "`";
		
		if(whereClausel != null) {
			where = " WHERE "+whereClausel.toSQL();
		} else where = "";
		
		if(limit != -1) {
			extras += " LIMIT " +limit;
		}
		
		if(offset != -1) {
			extras += " OFFSET " +offset;
		}
		
		final String sql = select + where + extras;
		
		this.lastSQL = sql;
		this.changed = false;
		
		return sql;
	}
}
