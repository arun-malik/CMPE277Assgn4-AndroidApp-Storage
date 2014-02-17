package com.cmpe277.assgn4;

import com.cmpe277.utility.Constants;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

public class Processing extends Activity {

	private static final String ACTIVITY_NAME="Processing";
	private EditText edtData;
	private Button btnCompleteProcess;
	private RadioButton rdSuccess,rdError;
	private ProgressBar pgProcessing;
	int transactionStatus = -1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processing);
		Log.i(ACTIVITY_NAME,Constants.ON_CREATE);

		edtData = (EditText) findViewById(R.id.edtData);
		btnCompleteProcess = (Button) findViewById(R.id.btnComplete);
		rdSuccess = (RadioButton) findViewById(R.id.rdSuccess);
		rdError = (RadioButton) findViewById(R.id.rdError);
		pgProcessing = (ProgressBar) findViewById(R.id.pgBProcessing);

		btnCompleteProcess.setEnabled(false);
		rdSuccess.setEnabled(false);
		rdError.setEnabled(false);

		Intent intentDataFromPersonalInfoActivity = getIntent();
		Bundle bundledDataFromPersonalInfoActivity = intentDataFromPersonalInfoActivity.getBundleExtra("PersonalInfo");

		StringBuilder dataToDisplay = new StringBuilder();
		dataToDisplay.append("First Name: " + bundledDataFromPersonalInfoActivity.get("firstName") + "\n");
		dataToDisplay.append("Last Name: " + bundledDataFromPersonalInfoActivity.get("lastName") + "\n");
		dataToDisplay.append("Address: " + bundledDataFromPersonalInfoActivity.get("address") + "\n");
		dataToDisplay.append("Credit Card: " + bundledDataFromPersonalInfoActivity.get("creditCardNumber") + "\n");

		edtData.setText(dataToDisplay);

		new AsyncAPICalls().execute("Test"); // to send any parameter to  API call

		btnCompleteProcess.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				Log.i(ACTIVITY_NAME,Constants.ON_COMPLETE_TRANSACTION);
				
				Intent transactionStatusIntent = new Intent();

				setResult(rdSuccess.isChecked()==true?Activity.RESULT_OK:Activity.RESULT_CANCELED, transactionStatusIntent);
				finish();

			}
		});
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.processing, menu);
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

	public class AsyncAPICalls extends AsyncTask<String, Void, String>{

		//private static String URL = "http://api.openweathermap.org/data/2.5/weather?q=";
		private static final String CLASS_NAME="AsyncAPICalls extends AsyncTask ";

		@Override
		protected String doInBackground(String... params) {

			String jsonResult = null;

			Log.i(CLASS_NAME,Constants.DO_IN_BACKGROUND);

			try {

				Thread.sleep(15000); // halt for 5 seconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Log.i(CLASS_NAME,Constants.DO_IN_BACKGROUND);


			/***   Call to REST- API will be done in this function.

			String fullAPI_URL = URL + params[0];

			HttpClient httpClientObject = new DefaultHttpClient();
			HttpGet httpGetCall = new HttpGet(fullAPI_URL);



			try {

				HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();

				if (httpEntityObject != null) {

					InputStream inputStreamObject = httpEntityObject.getContent();
					Reader readerObject = new InputStreamReader(inputStreamObject);
					BufferedReader bufferedReaderObject = new BufferedReader(readerObject);

					StringBuilder jsonResultStringBuilder = new StringBuilder();
					String readLine = null;

					while ((readLine = bufferedReaderObject.readLine()) != null) {
						jsonResultStringBuilder.append(readLine + "\n");
					}

					jsonResult = jsonResultStringBuilder.toString();
				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			 **/
			return jsonResult;

		}

		protected void onPostExecute(String weatherData) {


			Log.i(CLASS_NAME,Constants.ON_POST_EXECUTE);


			btnCompleteProcess.setEnabled(true);
			rdSuccess.setEnabled(true);
			rdError.setEnabled(true);
			pgProcessing.setVisibility(View.GONE);

			/******    Post Processing of the API call. Parameter will get requested data. In this case weatherData

			DecimalFormat df = new DecimalFormat("#.##"); 

			try {
				System.out.println("Weather Data:" + weatherData);
				if(null != weatherData)
				{
					JSONObject jObj = new JSONObject(weatherData);
					JSONObject mainJsonObject = jObj.getJSONObject("main");
					JSONObject windJsonObject = jObj.getJSONObject("wind");
					//JSONObject cloudJsonObject = jObj.getJSONObject("cloud");

					String cityName = jObj.getString("name");

					Double tempInKelvin =  Double.parseDouble(mainJsonObject.getString("temp"));
					String humidity = mainJsonObject.getString("humidity");
					String atmosphericPessure = mainJsonObject.getString("pressure");

					String windSpeed = windJsonObject.getString("speed");
					//String cloudiness = cloudJsonObject.getString("all");


					Double tempInCelcius = Double.valueOf(df.format( tempInKelvin - 273.15) );
					Double tempInFahrenheit = Double.valueOf(df.format( ((tempInKelvin - 273) * 1.8 ) + 32 ));

					outputDataInCelcius = "City: "+ cityName +"\nTemp: " + tempInCelcius + DEGREE +"Celcius \nHumidity: "+ humidity + "\nWind Speed: "+ windSpeed + "\nAtmospeheric Pressure: " + atmosphericPessure ;//+"\n Cloudiness: "+ cloudiness; 
					outputDataInFarahenheit = "City: "+ cityName +"\nTemp: " + tempInFahrenheit + DEGREE +"Fahrenheit \nHumidity: "+ humidity + "\nWind Speed: "+ windSpeed + "\nAtmospeheric Pressure: " + atmosphericPessure;// +"\n Cloudiness: "+ cloudiness; 


					txtWeatherValue.setText(outputDataInCelcius);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 */

		}

	}

}
