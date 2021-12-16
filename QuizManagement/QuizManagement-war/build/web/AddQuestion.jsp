<%@ page language="java" import="com.java.Entity.*" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Quiz - Code Quiz - Test Your Programming Knowledge!</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>

    </head>
    <br/>
    <body>

        <div id='navbar'>
            <ul>
                <li class='active'><a href='${pageContext.request.contextPath}/LoginServlet'><span>Home</span></a></li>
                <li><c:if test='${not empty sessionScope.userName}'>
                        <a href='${pageContext.request.contextPath}/StudentResults'>
                        </c:if> 
                        <c:if test='${empty sessionScope.userName}'>
                            <a href='${pageContext.request.contextPath}/LoginServlet'>
                            </c:if> 

                            <span>Student Results</span></a></li>
                <li><a href=''><span>Feedback</span></a></li>
                <li><a href=''
                       target="_blank"><span>Contact us</span></a></li>
                <li><a href='${pageContext.request.contextPath}/LogoutServlet'><span>Logout</span></a></li>

            </ul>
        </div>



        <div style="font-weight:bold;position:absolute;left:100px;top:100px">

            Add question to the Quiz
        </div>


        <div style="background-color:white;position:absolute;width:800px;padding:25px;
             border: 1px solid green ;left:100px;top:130px">

            <form action="AdminAction" method="post"  >

                <div class="form-group">
                    <label for="question">Question</label>
                    <input type="text" class="form-control" name="question" id="question" />
                </div>
                <div class="form-group">
                    <label for="option1">Option 1</label>
                    <input type="text" class="form-control" name="option1" id="option1" />
                </div>  
                <div class="form-group">
                    <label for="option2">Option 2</label>
                    <input type="text" class="form-control" name="option2" id="option2" />
                </div>

                <div class="form-group">
                    <label for="option3">Option 3</label>
                    <input type="text" class="form-control" name="option3" id="option3" />
                </div>
                <div class="form-group">
                    <label for="option4">Option 4</label>
                    <input type="text" class="form-control" name="option4" id="option4" />
                </div>
                <div class="form-group">
                    <label for="correct">Correct Answer</label>
                    <input type="text" class="form-control" name="correct" id="correct" placeholder="Please use index of the option" />
                </div>
                <!--  <button type="submit" class="btn btn-primary"> Add and Finish </button>
                  <button type="submit" class="btn btn-primary"> Add More Questions </button>-->
                <input class="button quizbutton" type="submit" name="adminAction" value="Add More Questions" />
                <input class="button finish" type="submit" name="adminAction" value="Add and Finish" />




            </form>
        </div>


    </body>
</html>