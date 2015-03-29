package com.mariondepuydt.musei;

import android.util.Log;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    Statement state;
    private String url = "jdbc:sqlite:comment.db";
    private static Connection conn;
    // Singleton class
    private Connect(){
        try {
            Log.i("Connect", "first line");
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver O.K.");
            Log.i("Connect", "drive ok");
            //String user = "postgres";
            //String passwd = "postgres";
            conn = DriverManager.getConnection(url);
            System.out.println("Connexion effective !");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Connect", "drive not ok");
        }
    }

    public static Connection getInstance() {
        Log.i("GetInstance", "in");
        if(conn == null) {
            new Connect();
        }
        return conn;
    }

    public Statement onConnect() {
        //Création d'un objet Statement
        try {
            state = Connect.getInstance().createStatement();
            //state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    public void connectEnd() {
        try {
            onConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}