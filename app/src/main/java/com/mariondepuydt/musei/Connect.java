package com.mariondepuydt.musei;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    Statement state;
    static Connection conn;

public Connect(){}

public void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:postgresql://localhost:5432/MarieChatelin";
            String user = "postgres";
            String passwd = "postgres";

            conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

public Statement onConnect() {
    //Création d'un objet Statement
    try {
        state = conn.createStatement();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return state;
}

public static void connectEnd() {
    try {
        onConnect().close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}