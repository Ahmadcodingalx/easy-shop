package org.example.marketeasy.IDBConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    //Ici c'est pour lier la base de données au projet
    static String host = "localhost";
    static String port = "3306";
    static String user = "root";
    static String password = "";
    static String database = "easy_shop";
    static String URL = "jdbc:mysql://"+host+":"+port+"/"+database;

    //création de la methode de connexion qu'on aurra à utiliser plus tard dans le code
    public static Connection shop_connectDB() {
        try {
            return DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
