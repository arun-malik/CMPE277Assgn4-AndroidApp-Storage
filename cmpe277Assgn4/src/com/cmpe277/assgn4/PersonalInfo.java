package com.cmpe277.assgn4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmpe277.utility.Constants;

public class PersonalInfo extends Activity {

	// <editor-fold defaultstate="collapsed" desc="Variables">

	private static final String ACTIVITY_NAME="Personal Info";
	private static final String PROCESS_DATA="Process Data";
	private static final String PROCESSED="Transaction Processed";
	private static final String PROCESSING_ERROR="Transaction Error";
	private static final String SAVED="Data Saved Sucessfully";
	private static final String SAVE_ERROR="Data Saving Error";
	private EditText edtFirstName, edtLastName, edtAddress, edtCreditCardNumber;
	private Button btnProcess, btnSave, btnReport, btnClose;
	private String errorMessageValidation = "Please enter all fields";
	private int processingTransactionStatus = 99, persistenceTransactionStatus = 100;

	
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Events">

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		Log.i(ACTIVITY_NAME,Constants.ON_CREATE);

		edtFirstName = (EditText) findViewById(R.id.edtFirstName);
		edtLastName = (EditText) findViewById(R.id.edtLastName);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		edtCreditCardNumber = (EditText) findViewById(R.id.edtCardNumber);

		btnProcess = (Button) findViewById(R.id.btnProcessData);
		btnSave = (Button) findViewById(R.id.btnSaveData);
		btnReport = (Button) findViewById(R.id.btnReportData);
		btnClose = (Button) findViewById(R.id.btnClose);

		btnProcess.setText(PROCESS_DATA);

		// <editor-fold defaultstate="collapsed" desc="Button Click Events">

		/**** Button Close Click Listener  ****/
		btnClose.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_CLOSE_BUTTON_CLICK);
				if (view == btnClose)
				{
					finish();
				}
			}
		});

		/**** Button Process Data Click Listener  ****/
		btnProcess.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_CLOSE_BUTTON_CLICK);

				if( (view == btnProcess) && isValid())
				{
					Intent intentForPassingValuesToProcessingActivity = new Intent(PersonalInfo.this,com.cmpe277.assgn4.Processing.class);

					// now we can directly use extra or we can bundle, im using bundle and then will add it to intent put extra
					intentForPassingValuesToProcessingActivity.putExtra("PersonalInfo", getBundledDataForIntent());

					startActivityForResult(intentForPassingValuesToProcessingActivity,processingTransactionStatus);

				}
				else
				{
					Toast errFieldsNotEntered = Toast.makeText(PersonalInfo.this,errorMessageValidation, Toast.LENGTH_LONG);
					errFieldsNotEntered.show();
				}
			}
		});

		/**** Button Process Data Click Listener  ****/
		btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_CLOSE_BUTTON_CLICK);

				if( (view == btnSave)  && isValid())
				{
					Intent intentForPassingValuesToProcessingActivity = new Intent(PersonalInfo.this,com.cmpe277.assgn4.Persistence.class);

					// now we can directly use extra or we can bundle, im using bundle and then will add it to intent put extra
					intentForPassingValuesToProcessingActivity.putExtra("PersonalInfo", getBundledDataForIntent());

					startActivityForResult(intentForPassingValuesToProcessingActivity,persistenceTransactionStatus);

				}
				else
				{
					Toast errFieldsNotEntered = Toast.makeText(PersonalInfo.this,errorMessageValidation, Toast.LENGTH_LONG);
					errFieldsNotEntered.show();
				}
			}
		});
		
		/**** Button Process Data Click Listener  ****/
		btnReport.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_CLOSE_BUTTON_CLICK);
				Intent intentReportDataActivity = new Intent(PersonalInfo.this,com.cmpe277.assgn4.ReportData.class);
				startActivity(intentReportDataActivity);
				
			}
		});

		// </editor-fold>	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_info, menu);
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


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	super.onActivityResult(requestCode, resultCode, data);

		Log.i(ACTIVITY_NAME,Constants.ON_ACTIVITY_RESULT);

		if(requestCode==99){ // response call from processing activity 
			if(resultCode==Activity.RESULT_OK){
				btnProcess.setText(PROCESSED);
				btnProcess.setBackgroundColor(Color.rgb(51, 51, 255));
			}
			else if (resultCode==Activity.RESULT_CANCELED){
				btnProcess.setText(PROCESSING_ERROR);
				btnProcess.setBackgroundColor(0xFFFF0000);
			}
		}

		if(requestCode==100){ // response call from persistence activity 
			if(resultCode==Activity.RESULT_OK){
				btnSave.setText(SAVED);
				btnProcess.setBackgroundColor(Color.rgb(51, 51, 255));
			}
			else if (resultCode==Activity.RESULT_CANCELED) {
				btnSave.setText(SAVE_ERROR);
				btnSave.setBackgroundColor(0xFFFF0000);
			}
		}
		
		//Toast.makeText(PersonalInfo.this, "onActivityResult: "+ resultCode, Toast.LENGTH_LONG).show();

	}


	// </editor-fold>




	// <editor-fold defaultstate="collapsed" desc="Getter - Setters">

	public String getFirstName(){
		return edtFirstName.getText().toString().trim();
	}

	public String getLastName(){
		return edtLastName.getText().toString().trim();
	}

	public String getAddress(){
		return edtAddress.getText().toString().trim();
	}

	public String getCreditCardNumber(){
		return edtCreditCardNumber.getText().toString().trim();
	}

	// </editor-fold>	

	// <editor-fold defaultstate="collapsed" desc="Getter - Setters">

	public Bundle getBundledDataForIntent(){

		Bundle bundledData = new Bundle();
		bundledData.putString("firstName", getFirstName());
		bundledData.putString("lastName", getLastName());
		bundledData.putString("address", getAddress());
		bundledData.putString("creditCardNumber", getCreditCardNumber());

		return bundledData;
	}

	public boolean isValid(){
		boolean valid = false;

		if((!TextUtils.isEmpty(getFirstName())) && (!TextUtils.isEmpty(getLastName())) && (!TextUtils.isEmpty(getAddress())) && (!TextUtils.isEmpty(getCreditCardNumber())) && null!= getFirstName() && null!= getLastName() && null!= getAddress() && null!= getCreditCardNumber() )
		{
			valid = true;
		}
		return valid;
	}

	// </editor-fold>	

}
