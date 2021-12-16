/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sayali
 */
@Entity
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int currentQuestion = 0;

    public int numberOfQuestions = 0;

    public ArrayList<Question> questionList = new ArrayList<Question>();
    public Map<Integer, Integer> selections = new LinkedHashMap<Integer, Integer>();

    private String subject;

    public Quiz(String subject) {
        this.subject = subject;
    }

    public ArrayList<Question> getQuestionList() {
        return this.questionList;
    }

    public void addQuestion(Question q) {
        this.questionList.add(q);
    }

    public String getSubject() {
        return this.subject;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setNumberofQuestions() {
        this.numberOfQuestions = questionList.size();
    }

    public int getNumberofQuestions() {
        return questionList.size();
    }

}
