package com.example.staggeredgridviewdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

import com.example.staggeredgridviewdemo.database.DatabaseHandler;
import com.example.staggeredgridviewdemo.jsonparser.JSONParser;

public class UserFunction {
	
	private JSONParser jsonparser;
	
	private static String registerURL = "";
	private static String register_tag = "register";
	
	public UserFunction()
	{
		jsonparser = new JSONParser();
	}
	public JSONObject registerUser(String name,String email,String uid)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("device_id", uid));
         
        // getting JSON Object
        JSONObject json = jsonparser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
		
	}
	public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
     

}
