package com.faisal.porag.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	private Context context;
	private SQLiteDatabase sqLiteDatabase;
	private static String DB_PATH = "";

	public DbHelper(Context context) {
		super(context, "contact_list.sqlite", null, 1);
		if (Build.VERSION.SDK_INT >= 16) {
			DB_PATH = context.getApplicationInfo().dataDir+ "/databases/";
		}else {
			DB_PATH = Environment.getDataDirectory()+ "/data/" +context.getPackageName()+ "/databases/";
		}
		this.context = context;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				
	}
	public void checkAndCopyDatabase(){
		boolean dbExist = checkData();
		if (dbExist) {
			Log.d("Check", "Db Already Exist");
		}
		else {
			this.getReadableDatabase();
		}
		try {
			copyDb();
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("Error", "Failed to copy");
		}
	}
	public boolean checkData(){
		SQLiteDatabase checkDB = null;
		String path = DB_PATH+"contact_list.sqlite";
		checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
		
		if (checkDB != null) {
			checkDB.close();
		}
		
		return checkDB != null ? true : false;
	}
	
	public void openDatabase(){
		String path = DB_PATH+"contact_list.sqlite";
		sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	public synchronized void close(){
		if (sqLiteDatabase != null) {
			sqLiteDatabase.close();
		}
		super.close();
	}
	
	public void copyDb() throws IOException{
		InputStream inputStream = context.getAssets().open("contact_list.sqlite");
		String outFile = DB_PATH + "contact_list.sqlite";
		OutputStream outputStream = new FileOutputStream(outFile);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer))> 0){
			outputStream.write(buffer, 0, length);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
	}
	
	public Cursor queryData(String query){
		
		return sqLiteDatabase.rawQuery(query, null);
	}

}
