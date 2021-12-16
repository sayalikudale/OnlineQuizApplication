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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author sayali
 *
 * QuizBean is a session bean which handles business logic related to quiz
 * questions
 */
@Stateless
public class QuizBean implements QuizBeanLocal {

    String url = "jdbc:mysql://azure-mysql-sayali.mysql.database.azure.com:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String dbusername = "sayaliUser@azure-mysql-sayali";
    String dbpassword = "Sayali@123";

    /**
     * get the quiz questions for given subjectId
     *
     * @param Quiz quiz
     * @throws SQLException if SQL error
     */
    public Quiz getQuizData(Quiz quiz) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT  * FROM Quiz.Question where Sub_id= (select id from Quiz.subject where name = '" + quiz.getSubject() + "') order by id desc LIMIT 10;");

            int i = 1;
            if (!rs.next()) {
                return quiz;
            } else {
                do {

                    String title = rs.getString(3);
                    String opt1 = rs.getString(4);
                    String opt2 = rs.getString(5);
                    String opt3 = rs.getString(6);
                    String opt4 = rs.getString(7);
                    int correct = rs.getInt(8);

                    Question q = new Question();
                    q.setQuestion(title);
                    q.setQuestionNumber(i);
                    q.setQuestionOptions(new String[]{opt1, opt2, opt3, opt4});
                    q.setCorrectOptionIndex(correct);

                    quiz.addQuestion(q);
                    i++;
                } while (rs.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quiz;
    }

    /**
     * perform the business logic to calculate the score of the quiz
     *
     * @param String user
     * @param Quiz quiz
     * @param String selectedExam
     * @throws SQLException if SQL error
     */
    public int getResult(String user, Quiz quiz, String selectedExam) {

        int totalCorrect = 0;
        Map<Integer, Integer> userSelectionsMap = quiz.selections;
        List<Integer> userSelectionsList = new ArrayList<Integer>(10);
        for (Map.Entry<Integer, Integer> entry : userSelectionsMap.entrySet()) {
            userSelectionsList.add(entry.getValue());
        }
        List<Question> questionList = quiz.questionList;
        List<Integer> correctAnswersList = new ArrayList<Integer>(10);
        for (Question question : questionList) {
            correctAnswersList.add(question.getCorrectOptionIndex());
        }

        for (int i = 0; i < userSelectionsMap.size(); i++) {
            if ((userSelectionsList.get(i) - 1) == correctAnswersList.get(i)) {
                totalCorrect++;
            }
        }

        saveResult(user, totalCorrect, selectedExam);
        return totalCorrect;

    }

    /**
     * save the result data of username and testId
     *
     * @param String user
     * @param int result
     * @param String selectedExam
     * @throws SQLException if SQL error
     */
    private void saveResult(String user, int result, String selectedExam) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement st = conn.createStatement();

            String query1 = "SELECT * FROM Quiz.UserResult where userId='" + user + "' and TestId = '" + selectedExam + "'";

            ResultSet rs = st.executeQuery(query1);

            if (!rs.next()) {

                String query = " insert into Quiz.UserResult(UserId, TestId, Result)"
                        + " values(?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, user);
                preparedStmt.setString(2, selectedExam);
                preparedStmt.setInt(3, result);

                preparedStmt.execute();

            } else {

                String sql = "UPDATE  Quiz.UserResult SET  Result= " + result + " WHERE UserId = '" + user + "' and TestId = '" + selectedExam + "'";
                st.executeUpdate(sql);

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

    }

    /**
     * get the result data of username
     *
     * @param String user
     * @throws SQLException if SQL error
     */
    public Map<String, Integer> getAllResultsForUser(String userName) {
        Map<String, Integer> resultsData = new HashMap<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbusername, dbpassword);

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT TestId,Result FROM Quiz.UserResult where UserId ='" + userName + "';");

            int i = 1;
            if (!rs.next()) {
                return resultsData;
            } else {
                do {
                    resultsData.put(rs.getString("TestId"), rs.getInt("Result"));

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
