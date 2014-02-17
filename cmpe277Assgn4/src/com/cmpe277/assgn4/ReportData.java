package com.cmpe277.assgn4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cmpe277.dao.SqlLiteDataBaseAccessLayer;
import com.cmpe277.utility.Constants;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.ToggleButton;

public class ReportData extends Activity {

	private static final String ACTIVITY_NAME="ReportData";
	private ToggleButton tglDataSource;
	private static final String SHARED_PREFERENCES = "com.cmpe277.assgn4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_data);

		tglDataSource = (ToggleButton) findViewById(R.id.tglDataSource);
		final ListView llRecordList = (ListView)findViewById(R.id.lstRecords);
		//call this in async task 
		SqlLiteDataBaseAccessLayer sqlDalObj = new SqlLiteDataBaseAccessLayer(this);
		List<String> allSqlLiteRecords = sqlDalObj.getAllRecords();

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                 this, 
                 android.R.layout.simple_list_item_1,
                 allSqlLiteRecords );
		llRecordList.setAdapter(arrayAdapter);
		
		
		SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFERENCES, ReportData.this.MODE_PRIVATE);
		HashMap<String,String> allEntries = (HashMap<String,String>)sharedPrefs.getAll();
		List<String> allRecords = new ArrayList<String>();
		
		for (Map.Entry<String, String> entry : allEntries.entrySet())
		{
			allRecords.add(entry.getValue());
		}
		
		final ArrayAdapter<String> arraySharedPrefAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                allRecords );
		

		tglDataSource.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(tglDataSource.isChecked()){
					llRecordList.setAdapter(arraySharedPrefAdapter);
				}
				else
				{
					llRecordList.setAdapter(arrayAdapter);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report_data, menu);
		return true;
	}


	@Override
	protected void onStart() {
		super.onStart();
		Log.i(ACTIVITY_NAME,Constants.ON_START);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(ACTIVITY_NAME,Constants.ON_RESTART);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(ACTIVITY_NAME,Constants.ON_RESUME);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(ACTIVITY_NAME,Constants.ON_PAUSE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(ACTIVITY_NAME,Constants.ON_STOP);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(ACTIVITY_NAME,Constants.ON_DESTROY);
	}

}
