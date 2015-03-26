package com.mariondepuydt.musei;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class DisplayMessageActivity extends ActionBarActivity {
    int myQRCode = 1;
    String myAuthor = "Quentin";
    String myDate = "20-03-2015";
    Connect myConnect = new Connect();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Attention : la ca risque d'affichier tous les messages a chaque fois qu'on rajoute un message
        onDisplayPreviousMessages();

        //Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(AddMessage.EXTRA_MESSAGE);
        //Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        //Display it
        setContentView(textView);
        onSaveMessage(message);
    }

    public void onDisplayPreviousMessages() {
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = null;
        try {
            result = myConnect.onConnect().executeQuery("SELECT Message FROM Messages WHERE QRCode = myQRCode");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //On récupère les MetaData
        ResultSetMetaData resultMeta = null;
        try {
            resultMeta = result.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while(result.next()){
                for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                    String message = result.getObject(i).toString();
                    //Create the text view
                    TextView textView = new TextView(this);
                    textView.setTextSize(40);
                    //Display with another color
                    textView.setHighlightColor(1);
                    textView.setText(message);
                    //Display it
                    setContentView(textView);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connect.connectEnd();
    }

    public void onSaveMessage(String message) {
        String myMessage = message;
        try {
            ResultSet result = myConnect.onConnect().executeQuery("INSERT INTO Messages VALUES ('myQRCode', 'myMessage', 'myAuthor', 'myDate'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myConnect.connectEnd();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

