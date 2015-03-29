package com.mariondepuydt.musei;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class AddMessage extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.mariondepuydt.musei.MESSAGE";
    ArrayAdapter<String> list;
    ListView listView = null;
    private static final String TAG_COMMENTS = "comments";
    private static final String TAG_MESSAGE = "message";
    private static String url = "http://10.0.2.2:3000/db";
    // contacts JSONArray
    JSONObject comments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        list = new ArrayAdapter<String>(this, R.layout.list_message, R.id.message_1);
        listView = (ListView) findViewById(R.id.list_message);
        new GetMessages().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_message, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Log.i("Press Button","working");
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        list.add(message);
        listView.setAdapter(list);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.setText("");
    }

    private class GetMessages extends AsyncTask<Void, Void, Void> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AddMessage.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    Log.i("jsonstr", jsonStr);
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    Log.i("jsonobj", jsonObj.toString());
                    // Getting JSON Array node
                    //comments = jsonObj.getJSONObject(TAG_COMMENTS);
                    //comments = jsonObj.getJSONArray(TAG_COMMENTS);
                    //String message = comments.getString(TAG_MESSAGE);
                     // adding contact to contact list
                     //list.add(message);
                    list.add(jsonObj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            listView.setAdapter(list);
        }

    }
}
