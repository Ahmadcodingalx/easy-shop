//package org.example.marketeasy.models;
//
//import org.example.marketeasy.IDBConfig.Database;
//import org.example.marketeasy.interfaces.HistInterface;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class History implements HistInterface {
//
//    @Override
//    public void historiques(String recents) throws SQLException {
//        Connection connection = Database.shop_connectDB();
//        String req = "INSERT INTO hist_calc (Historique) VALUES (?)";
//        assert connection != null;
//        PreparedStatement preparedStatement = connection.prepareStatement(req);
//        preparedStatement.setString(1, recents);
//        preparedStatement.executeUpdate();
////
////        int row = preparedStatement.executeUpdate();
////
////        if (row == 0) {
////            System.out.println("La creation du copmpte n'a pas abouti. Veuillez vérifier les information saisies");
////        } else {
////            System.out.println(String.valueOf(row));
//        }
//
//        //System.out.println(String.valueOf(row));
//        preparedStatement.close();
//        connection.close();
//    }
//}
