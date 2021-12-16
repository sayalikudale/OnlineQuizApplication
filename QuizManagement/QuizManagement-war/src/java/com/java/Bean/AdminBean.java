/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Bean;

import com.java.Entity.Question;
import com.java.Entity.Quiz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author sayali
 * 
 * AdminBean is a session bean which performs the business logic for all admin activities
 * 
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    String url = "jdbc:mysql://azure-mysql-sayali.mysql.database.azure.com:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String dbusername = "sayaliUser@azure-mysql-sayali";
    String dbpassword = "Sayali@123";

  
    /**
     * Add the questions to the database table question
     *
     * @param Quiz quiz
     * @throws SQLException if SQL error 
     */
    public Boolean addQuizData(Quiz quiz) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement st = conn.createStatement();

            String query1 = "SELECT Id FROM Quiz.subject where Name='" + quiz.getSubject() + "'";

            ResultSet rs = st.executeQuery(query1);

            if (rs.next()) {

                for (Question q : quiz.getQuestionList()) {

                    String query = " insert into Quiz.question(Sub_id, Title, Option1,Option2,Option3,Option4,Correct)"
                            + " values(?, ?, ?,?,?,?,?)";

                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, rs.getInt("Id"));
                    preparedStmt.setString(2, q.getQuestion());
                    String[] options = q.getQuestionOptions();
                    preparedStmt.setString(3, options[0]);
                    preparedStmt.setString(4, options[1]);
                    preparedStmt.setString(5, options[2]);
                    preparedStmt.setString(6, options[3]);
                    preparedStmt.setInt(7, q.getCorrectOptionIndex());

                    preparedStmt.execute();

                }

            }
            try {
                conn.close();
            } catch (SQLException se) {
                System.out.println("Error : While Closing Connection");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }
    
     /**
     * 
     *gets the result information of all the students
     * @throws SQLException if SQL error 
     */

    public Map<String, Map<String, Integer>> getStudentResults() {

        Map<String, Map<String, Integer>> resultsData = new HashMap<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT UserId,TestId,Result FROM Quiz.UserResult order by TestId, Result desc");

            int i = 1;
            if (!rs.next()) {
                return resultsData;
            } else {
                do {
                    String TestId = rs.getString("TestId");
                    String UserId = rs.getString("UserId");
                    int result = rs.getInt("Result");
                    if (resultsData.containsKey(TestId)) {

                        Map<String, Integer> userRes = resultsData.get(TestId);

                        if (userRes.containsKey(UserId)) {
                            userRes.put(UserId, result);

                        } else {
                            userRes.put(UserId, result);
                        }

                        resultsData.put(TestId, userRes);

                    } else {

                        Map<String, Integer> userRes = new HashMap<>();
                        userRes.put(UserId, result);
                        resultsData.put(TestId, userRes);

                    }

                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultsData;
    }

}
