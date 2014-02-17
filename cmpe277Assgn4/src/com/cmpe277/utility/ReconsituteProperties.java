package com.cmpe277.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import android.util.Log;

public  class ReconsituteProperties {
	
	final static String ASSESTS_PROPERTIES_FILE="/Users/malik/Softwares/Installation/Spring/Workspace/cmpe277Assgn4/assets/parameters.properties";
	static InputStream inputStreamToReadPropertyFile = null;
	private final static String CLASS_NAME = "ReconsituteProperties";
	private static String dbame, tblPersonalInfo, QueryCreateTable, QuerySelectAllData, QueryDropIfExisits;
	private static int dbVersion;
	
	
	public static void setReconsituteProperties(){
		
		Log.i(CLASS_NAME,Constants.RECONSTITUTE_SET_PROPERTIES);
		//Log.i(CLASS_NAME,ReconsituteProperties.class.getClass().getResource("").getPath());

		
		Properties parameterProperties = new Properties();

		try {
			
			
			//InputStream inputStreamToReadPropertyFile = ReconsituteProperties.class.getClass().getClassLoader().getResourceAsStream(ASSESTS_PROPERTIES_FILE);
			inputStreamToReadPropertyFile = new FileInputStream(ASSESTS_PROPERTIES_FILE);
			
			parameterProperties.load(inputStreamToReadPropertyFile);

			setDbame(parameterProperties.getProperty("DATABASE_NAME"));
			setDbVersion(Integer.parseInt(parameterProperties.getProperty("DATABASE_VERSION").toString()));
			setQueryCreateTable(parameterProperties.getProperty("QUERY_CREATE_TABLE_PERSONAL_INFO"));
			setQueryDropIfExisits(parameterProperties.getProperty("QUERY_DROP_IF_EXISTS_TABLE_PERSONAL_INFO"));
			setQuerySelectAllData(parameterProperties.getProperty("QUERY_SELECT_STAR_FROM_TABLE_PERSONAL_INFO"));
			setTblPersonalInfo(parameterProperties.getProperty("TABLE_PERSONAL_INFO"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getDbame() {
		return dbame;
	}

	public static void setDbame(String pDbame) {
		dbame = pDbame;
	}

	public static int getDbVersion() {
		return dbVersion;
	}

	public static void setDbVersion(int pDbVersion) {
		dbVersion = pDbVersion;
	}

	public static String getTblPersonalInfo() {
		return tblPersonalInfo;
	}

	public  static void setTblPersonalInfo(String pTblPersonalInfo) {
		tblPersonalInfo = pTblPersonalInfo;
	}

	public static String getQueryCreateTable() {
		return QueryCreateTable;
	}

	public static void setQueryCreateTable(String queryCreateTable) {
		QueryCreateTable = queryCreateTable;
	}

	public static String getQuerySelectAllData() {
		return QuerySelectAllData;
	}

	public static void setQuerySelectAllData(String querySelectAllData) {
		QuerySelectAllData = querySelectAllData;
	}

	public static String getQueryDropIfExisits() {
		return QueryDropIfExisits;
	}

	public static void setQueryDropIfExisits(String queryDropIfExisits) {
		QueryDropIfExisits = queryDropIfExisits;
	}
	
}
