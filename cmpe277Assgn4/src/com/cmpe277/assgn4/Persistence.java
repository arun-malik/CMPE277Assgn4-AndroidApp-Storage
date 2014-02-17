package com.cmpe277.assgn4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cmpe277.dao.SqlLiteDataBaseAccessLayer;
import com.cmpe277.utility.Constants;
//import com.cmpe277.utility.ReconsituteProperties;

public class Persistence extends Activity {

	private static final String ACTIVITY_NAME="Persistence";
	private Button btnSqlLite, btnSharedPreferences;
	private String firstName, lastName, address, creditCardNumber;
	private static final String SHARED_PREFERENCES = "com.cmpe277.assgn4";
	Intent transactionStatusIntent = transactionStatusIntent = new Intent();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persistence);


		//ReconsituteProperties.setReconsituteProperties();

		btnSqlLite = (Button) findViewById(R.id.btnSQLLite);
		btnSharedPreferences = (Button) findViewById(R.id.btnSharedPreferences);

		Intent intentDataFromPersonalInfoActivity = getIntent();
		Bundle bundledDataFromPersonalInfoActivity = intentDataFromPersonalInfoActivity.getBundleExtra("PersonalInfo");

		firstName = bundledDataFromPersonalInfoActivity.get("firstName").toString();
		lastName = bundledDataFromPersonalInfoActivity.get("lastName").toString();
		address = bundledDataFromPersonalInfoActivity.get("address").toString();
		creditCardNumber = bundledDataFromPersonalInfoActivity.get("creditCardNumber").toString();

		final StringBuilder sharedPrefString = new StringBuilder("First Name: ");
		sharedPrefString.append(firstName);
		sharedPrefString.append("\nLast Name: ");
		sharedPrefString.append(lastName);
		sharedPrefString.append("\nAddress: ");
		sharedPrefString.append(address);
		sharedPrefString.append("\nCredit Card Number:");
		sharedPrefString.append(creditCardNumber);


		btnSqlLite.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_SQL_LITE);
				try{
					//SqlLiteDataBaseAccessLayer dbAccessObject = new SqlLiteDataBaseAccessLayer(Persistence.this,ReconsituteProperties.getDbame() , ReconsituteProperties.getDbVersion());
					//SqlLiteDataBaseAccessLayer dbAccessObject = new SqlLiteDataBaseAccessLayer(Persistence.this,"CMPE277" ,1);
					SqlLiteDataBaseAccessLayer dbAccessObject = new SqlLiteDataBaseAccessLayer(Persistence.this);


					ContentValues recordToSave = new ContentValues();
					recordToSave.put("first_name", firstName);
					recordToSave.put("last_name", lastName);
					recordToSave.put("address", address);
					recordToSave.put("creditcard_number", creditCardNumber);

					Log.d(ACTIVITY_NAME,recordToSave.toString());

					dbAccessObject.addPersonalDetails(recordToSave);

					setResult(RESULT_OK, transactionStatusIntent);
					Toast.makeText(Persistence.this, "Record Saved Sucessfully", Toast.LENGTH_LONG).show();

				} catch(Exception ex){
					ex.printStackTrace();
					setResult(RESULT_CANCELED, transactionStatusIntent);
					Toast.makeText(Persistence.this, "Error Saving Record", Toast.LENGTH_LONG).show();
				}
				finally{
					finish();
				}
			}
		});

		btnSharedPreferences.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_SHARED_PREFERENCES);

				try{

					SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFERENCES, Persistence.this.MODE_PRIVATE);
					SharedPreferences.Editor sharePrefEditor = sharedPrefs.edit();
					sharePrefEditor.putString(firstName, sharedPrefString.toString());

					sharePrefEditor.commit();
					Toast.makeText(Persistence.this, "Record Saved Sucessfully", Toast.LENGTH_LONG).show();

				} catch(Exception e) {
					e.printStackTrace();
					setResult(RESULT_CANCELED, transactionStatusIntent);
					Toast.makeText(Persistence.this, "Error Saving Record", Toast.LENGTH_LONG).show();

				}finally{
					finish();
				}
			}
		});

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.persistence, menu);
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
