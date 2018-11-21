/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ggupt
 */
public class dbConnection {
    private static final String CONN = "jdbc:sqlite:Database.db";
    
    public static Connection getConnection() throws SQLException{
       try{
           Class.forName("org.sqlite.JDBC");
           return DriverManager.getConnection(CONN);
       }       
       catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
