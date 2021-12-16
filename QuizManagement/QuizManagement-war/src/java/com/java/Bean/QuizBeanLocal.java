/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Bean;

import com.java.Entity.Quiz;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author sayali
 */
@Local
public interface QuizBeanLocal {

    public Quiz getQuizData(Quiz q);

    public int getResult(String userName, Quiz quiz, String selectedExam);

    public Map<String, Integer> getAllResultsForUser(String userName);

}
