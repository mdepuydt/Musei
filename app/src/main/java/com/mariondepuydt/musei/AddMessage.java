package com.mariondepuydt.musei;

<<<<<<< HEAD
=======
import android.app.AlertDialog;
>>>>>>> 0cb118152762e51c48d8adb2fcbfc38eaad09a38
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
<<<<<<< HEAD
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

=======
>>>>>>> 0cb118152762e51c48d8adb2fcbfc38eaad09a38

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.TrackingValues;
import com.metaio.sdk.jni.TrackingValuesVector;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


public class AddMessage extends ARViewActivity {
    public final static String EXTRA_MESSAGE = "com.mariondepuydt.musei.MESSAGE";
    ArrayAdapter<String> list;
    ListView listView = null;
    EditText editText;
    String message;
    String uniqueId = UUID.randomUUID().toString();
    private static final String TAG_COMMENTS = "comments";
    private static final String TAG_MESSAGE = "message";
    //private static String url = "http://10.0.2.2:3000/db";
    //private static String url = "http://172.25.22.16:3000/db";
    //private static String url = "http://localhost:3000/db";
<<<<<<< HEAD
    private static String url = "http://172.20.10.2:3000/db";

    // contacts JSONArray
    JSONArray comments = null;
=======
    private static String url = "http://192.168.43.33:3000/db";

    private MetaioSDKCallbackHandler mCallbackHandler;
    private AddMessage mThis;
    private AlertDialog mAlert;
>>>>>>> 0cb118152762e51c48d8adb2fcbfc38eaad09a38

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbackHandler = new MetaioSDKCallbackHandler();

        // Set QRCode tracking configuration
        metaioSDK.setTrackingConfiguration("QRCODE");
        mThis = this;
        mAlert = null;

        setContentView(R.layout.activity_add_message);
        list = new ArrayAdapter<String>(this, R.layout.list_message, R.id.message_1);
        listView = (ListView) findViewById(R.id.list_message);
        new GetMessages().execute();
<<<<<<< HEAD
=======
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCallbackHandler.delete();
        mCallbackHandler = null;
    }

    @Override
    protected int getGUILayout() {
        return R.layout.activity_add_message;
    }

    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
        return mCallbackHandler;
    }

    final class MetaioSDKCallbackHandler extends IMetaioSDKCallback {

        private String[] tokens;

        @Override
        public void onTrackingEvent(TrackingValuesVector trackingValues) {
            for (int i = 0; i < trackingValues.size(); i++) {
                final TrackingValues v = trackingValues.get(i);
                if (v.isTrackingState()) {
                    // reading the code
                    tokens = v.getAdditionalValues().split("::");
                    if (tokens.length > 1) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (mAlert == null) {
                                    mAlert = new AlertDialog.Builder(mThis)
                                            .setTitle("Scanned QR-Code")
                                            .setMessage(tokens[1])
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            })
                                            .create();
                                }
                                if (!mAlert.isShowing()) {
                                    mAlert.setMessage(tokens[1]);
                                    mAlert.show();
                                }
                            }
                        });
                    }
                }

            }
        }

    }

    public void onButtonClick(View v)
    {
        finish();
>>>>>>> 0cb118152762e51c48d8adb2fcbfc38eaad09a38
    }


    // contacts JSONArray
    JSONArray comments = null;


    @Override
    protected void loadContents() {

    }

    @Override
    protected void onGeometryTouched(IGeometry geometry) {

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
        editText = (EditText) findViewById(R.id.edit_message);
        message = editText.getText().toString();
        list.add(message);
        listView.setAdapter(list);
        new PostMessage().execute();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.setText("");
    }

<<<<<<< HEAD
=======
    /** Called when the user clicks the Info Button */
    public void infoArt(View view) {
        // Do something in response to button
        Log.i("Press Button","working");
        //unOnUiThread(new Runnable());

    }

>>>>>>> 0cb118152762e51c48d8adb2fcbfc38eaad09a38
    private class PostMessage extends AsyncTask<Void, Void, Void> {

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
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String dat = dateFormat.format(currentDate);
            // Building post parameters, key and value pair
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("message", message));
            nameValuePair.add(new BasicNameValuePair("date", dat.toString()));
            nameValuePair.add(new BasicNameValuePair("author", uniqueId));
            // Making a request to url and getting response
<<<<<<< HEAD
            url = "http://172.20.10.2:3000/comments";
=======
            url = "http://192.168.43.33:3000/comments";
>>>>>>> 0cb118152762e51c48d8adb2fcbfc38eaad09a38
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, nameValuePair);
            Log.d("Response: ", "> " + jsonStr);
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
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    Log.i("jsonobj", jsonObj.toString());
                    // Getting JSON Array node
                    //comments = jsonObj.getJSONObject(TAG_COMMENTS);
                    comments = jsonObj.getJSONArray(TAG_COMMENTS);
                    for(int i = 0; i < comments.length(); i++){
                        JSONObject c = comments.getJSONObject(i);
                        String message = c.getString(TAG_MESSAGE);
                        list.add(message);
                    }

                    //list.add(jsonObj.toString());
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
