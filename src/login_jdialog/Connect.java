/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_jdialog;

import java.sql.*;

/**
 *
 * @author admin
 */
public class Connect {

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/test";
            Connection con = DriverManager.getConnection(url, "root", "");
            System.out.println("connect succesful");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*public static void main(String[] args) {
        ConnectDB();
    }*/
}
