package org.example.marketeasy.controllers;

import org.example.marketeasy.IDBConfig.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataCalcule {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public String totalProdByCat(String catId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String T_P_B_C = "";


//        Integer total_prodoct = 0;
//        Integer prodoct_red = 0;

        String sql = "SELECT SUM(p.quantite) FROM produits p " +
                "JOIN categories c ON p.categories_id = c.id WHERE categories_id = ?";

        connection = Database.shop_connectDB();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, catId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString(1) == null) {
                    T_P_B_C = "0";
                } else {
                    T_P_B_C = resultSet.getString(1);
                }
                // total_prodoct = Integer.parseInt(resultSet.getString(1));
                // prodoct_red = Integer.parseInt(resultSet.getString(2));
                // System.out.println("Total : " + total_prodoct + " Rouges : " + prodoct_red);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return T_P_B_C;
    }

}
