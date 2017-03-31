package com.faisal.porag.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	SQLiteDatabase sqLiteDatabase;

	public DataBaseHelper(Context context) {
		super(context, "contact_list", null, 1);

		Log.d("CHECK", "DATABASE IS CREATED");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		try {
			db.beginTransaction();
			db.execSQL(TableCreateQuery.CREATE_TABLE_CONTACT());
					
				db.setTransactionSuccessful();
			db.endTransaction();

			// Check database created or not
			Log.d("DataBase", "Table is Created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			
			onCreate(db);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getDBversion(){
		
		this.sqLiteDatabase = this.getReadableDatabase();
		int version = this.sqLiteDatabase.getVersion();
		return version;
	}
	

	// Insert
	public long ADD(ContentValues values, String TABLE_NAME) {
		SQLiteDatabase database = this.getWritableDatabase();
		return database.insert(TABLE_NAME, null, values);
	}
	
	// METHOD FOR UPDATE SINGLE ROW
	public int UPDATE(ContentValues values, String TABLE_NAME, long id) {
		sqLiteDatabase = this.getWritableDatabase();

		return sqLiteDatabase.update(TABLE_NAME, values," id = ?", new String[] { String.valueOf(id) });
	}
 
	public Cursor GETALL(String TABLE_NAME) {
		Cursor cursor;
		String query = "SELECT  * FROM " + TABLE_NAME + " ;";
		sqLiteDatabase = this.getWritableDatabase();
		cursor = sqLiteDatabase.rawQuery(query, null);		
		return cursor;
	}
	
	public Cursor ConditionalReadQuery(String query){

		sqLiteDatabase = this.getReadableDatabase();
		
		return sqLiteDatabase.rawQuery(query, null);
		
	}
	
	public long STATEMENT_INSERT(SQLiteStatement statement){
		return statement.executeInsert();
	}
	public int STATEMENT_UPDATE(SQLiteStatement statement){
		return statement.executeUpdateDelete();
	}
	

}
