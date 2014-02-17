package com.cmpe277.dao;

import java.util.ArrayList;
import java.util.List;

import com.cmpe277.utility.Constants;
import com.cmpe277.utility.ReconsituteProperties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteDataBaseAccessLayer extends SQLiteOpenHelper {
	
	private String CLASS_NAME = "SqlLiteDataBaseAccessLayer";
	
	public SqlLiteDataBaseAccessLayer(Context context) {
        super(context, "CMPE277", null, 1);
    }
	
//	public SqlLiteDataBaseAccessLayer(Context context, String DbName, int DbVersion) {
//        super(context, DbName, null, DbVersion);
//        Log.i(CLASS_NAME,Constants.SQLLITE_DAL_ON_CONSTRUCTOR);     
//    }
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(CLASS_NAME,Constants.SQLLITE_DAL_ON_CREATE);
		//db.execSQL(ReconsituteProperties.getQueryCreateTable());
		db.execSQL("CREATE TABLE [tbl_personal_info] ( [person_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, [first_name] TEXT NOT NULL, [last_name] TEXT NOT NULL, [address] TEXT NOT NULL, [creditcard_number] INTEGER NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(CLASS_NAME,Constants.SQLLITE_DAL_ON_UPGRADE);
		//db.execSQL(ReconsituteProperties.getQueryDropIfExisits());
		db.execSQL("DROP TABLE IF EXISTS [tbl_personal_info]");
		onCreate(db);
	}
	

	public void addPersonalDetails(ContentValues pRecord) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    //db.insert(ReconsituteProperties.getTblPersonalInfo(), null, pRecord);
	    db.insert("tbl_personal_info", null, pRecord);
	    db.close(); 
	}
	
	 
	 public List<String> getAllRecords() {
	    List<String> allRecords = new ArrayList<String>();
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    //Cursor cursor = db.rawQuery(ReconsituteProperties.getQuerySelectAllData(), null);
	    Cursor cursor = db.rawQuery("SELECT  * FROM [tbl_personal_info]", null);
	    
	    if (cursor.moveToFirst()) {
	        do {
	        	StringBuilder recordsToReturn = new StringBuilder();
	        	
	        	recordsToReturn.append("First Name: " +cursor.getString(0) + "\n");
	        	recordsToReturn.append("Last Name: " + cursor.getString(1) + "\n");
	        	recordsToReturn.append("Address: " + cursor.getString(2) + "\n");
	        	recordsToReturn.append("Credit Card: " + cursor.getString(3) + "\n");
	        	
	        	allRecords.add(recordsToReturn.toString());
	        } while (cursor.moveToNext());
	    }
	    
	    return allRecords;
	}

	

}
