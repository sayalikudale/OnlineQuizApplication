/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Entity;

/**
 *
 * @author sayali
 */
public class Question {

    int questionNumber;
    String question;
    String questionOptions[];
    int correctOptionIndex;

    public Question() {

    }

    public Question(String question, String[] options, int correctOption) {

        this.question = question;
        this.questionOptions = options;
        this.correctOptionIndex = correctOption;

    }

    public String getQuestion() {
        return question;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int i) {
        questionNumber = i;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public String[] getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestion(String s) {
        question = s;
    }

    public void setCorrectOptionIndex(int i) {
        correctOptionIndex = i;
    }

    public void setQuestionOptions(String[] s) {
        questionOptions = s;
    }
}
