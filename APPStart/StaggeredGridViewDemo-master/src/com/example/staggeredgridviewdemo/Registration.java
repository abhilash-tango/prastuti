package com.example.staggeredgridviewdemo;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.staggeredgridviewdemo.database.DatabaseHandler;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity implements OnClickListener {
	EditText username, coLLege, eemail;
	Button signin;
	String nameuser, email, uid;
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	DatabaseHandler db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		if(new UserFunction().isUserLoggedIn(getApplicationContext()))
		{
			startActivity(new Intent(this, MainActivity.class));
		    finish();
		}
		setContentView(R.layout.signup);
		ActionBar bar = getActionBar();
		//bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DCDCDC")));
		bar.setTitle("REGISTER");

		username = (EditText) findViewById(R.id.name);
		eemail = (EditText) findViewById(R.id.email);
		coLLege = (EditText) findViewById(R.id.college);
		signin = (Button) findViewById(R.id.button1);
		signin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		nameuser = username.getText().toString();
		email = eemail.getText().toString();
		uid = Secure.getString(getBaseContext().getContentResolver(),
				Secure.ANDROID_ID);
		new sendData().execute();

	}

	class sendData extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject json = new UserFunction().registerUser(nameuser, email,
					uid);
			try {
				if (json.getString(KEY_SUCCESS) != null) {
					String res = json.getString(KEY_SUCCESS);
					if (Integer.parseInt(res) == 1) {
						db = new DatabaseHandler(
								getApplicationContext());
						JSONObject json_user = json.getJSONObject("user");
						db.addUser(json_user.getString(KEY_NAME),
								json_user.getString(KEY_EMAIL),
								json.getString(KEY_UID));
					}
				}else{
					Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			finish();
			
		}

	}
}
