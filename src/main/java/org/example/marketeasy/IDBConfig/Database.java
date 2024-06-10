package org.example.marketeasy.IDBConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    static String host = "localhost";
    static String port = "3306";
    static String user = "root";
    static String password = "";
    static String database = "market_easy";
    static String URL = "jdbc:mysql://"+host+":"+port+"/"+database;


    public static Connection shop_connectDB() {
        try {
            return DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
