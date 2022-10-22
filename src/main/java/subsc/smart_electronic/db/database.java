/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.db;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class database {

    public static Connection ConnectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
