/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginApp;

import dbUtil.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ggupt
 */
public class LoginModel {
    Connection connection;
    
    public LoginModel(){
        try{
            this.connection = dbConnection.getConnection();
        }
        catch (SQLException ex){
        }
        if(this.connection == null){
            System.exit(1);
        }
    }
    public boolean isDatabaseConnected(){
        return this.connection != null;
    }
    public boolean isLogin(String username, String password) throws Exception{
        PreparedStatement pt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Login where Username = ? and Password = ?";
        try{
            pt = this.connection.prepareStatement(sql);
            pt.setString(1, username);
            pt.setString(2, password);
            rs = pt.executeQuery();
            
            boolean bool1;
            
            if (rs.next()){
                return true;
            }
            return false;
        }
        catch(SQLException ex){
            return false;
        }
        
        finally {
            {
                pt.close();
                rs.close();
                      
            }
        }
    }
    
}

