package com.faisal.porag.db;


public class TableCreateQuery {
	
	 
	// TABLE CREATE STATEMENTS
	
	public static String CREATE_TABLE_CONTACT(){
		StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		builder.append(" contact ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
		
		builder.append(" name  VARCHAR(30), ");
		builder.append(" phone  VARCHAR(30) );");
		
		return builder.toString();
	}
	
	
  
	
}
