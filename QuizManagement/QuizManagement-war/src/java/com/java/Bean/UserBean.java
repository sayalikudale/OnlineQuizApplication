/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Bean;

import com.java.Entity.User;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author sayali
 */
@Stateless
public class UserBean implements UserBeanLocal {

    String url = "jdbc:mysql://azure-mysql-sayali.mysql.database.azure.com:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String dbusername = "sayaliUser@azure-mysql-sayali";
    String dbpassword = "Sayali@123";

    
    /**
     * get the login data of username
     *
     * @param String user
     * @throws SQLException if SQL error
     */
    public boolean login(User user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Quiz.login");

            if (!rs.next()) {
                return false;
            } else {
                do {

                    String username = rs.getString(2);
                    String password = rs.getString(3);
                    String isAdmin = rs.getString(4);
                    if (user.getUName().equals(username) && user.getPassword().equals(password)) {
                        if (isAdmin.equals("1")) {
                            user.setIsAdmin(true);
                        } else {
                            user.setIsAdmin(false);
                        }

                        return true;
                    }

                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
     /**
     * add new user to the database
     * @param String user
     * @throws SQLException if SQL error
     */
    public boolean register(User user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            String query = " insert into Quiz.login(username, password, isAdmin)"
                    + " values(?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, user.getUName());
            preparedStmt.setString(2, user.getPassword());
            preparedStmt.setInt(3, user.getIsAdmin() == true ? 1 : 0);

            preparedStmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
