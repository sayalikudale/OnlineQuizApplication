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
public interface AdminBeanLocal {

    public Boolean addQuizData(Quiz quiz);

    public Map<String, Map<String, Integer>> getStudentResults();

}
