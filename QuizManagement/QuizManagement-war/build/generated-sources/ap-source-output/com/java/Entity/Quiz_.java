package com.java.Entity;

import java.util.ArrayList;
import java.util.Map;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-04T22:50:48")
@StaticMetamodel(Quiz.class)
public class Quiz_ { 

    public static volatile SingularAttribute<Quiz, Map> selections;
    public static volatile SingularAttribute<Quiz, String> subject;
    public static volatile SingularAttribute<Quiz, Integer> numberOfQuestions;
    public static volatile SingularAttribute<Quiz, Integer> currentQuestion;
    public static volatile SingularAttribute<Quiz, ArrayList> questionList;

}