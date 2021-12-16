<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/styles.css">

        <title>Code Quiz - Test Your Programming Knowledge!</title>
    </head>
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

        <c:if test='${not empty sessionScope.user}'>

            <a href='${pageContext.request.contextPath}/userPanel'>
                <div class="button nameuser">Logged as, ${sessionScope.user}</div>
            </a>

            <a href='${pageContext.request.contextPath}/logout'>
                <div class="button logout">Logout</div>
            </a>
        </c:if>

        <div style="position: absolute; left: 5%; top: 15%;">
            <table cellpadding="0" cellspacing="50">

                <tr>
                    <td class="pic"><a href="addExam?test=java">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/java.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title">Java Quiz!</h1>
                                    <p class="caption__overlay__content">Add questions to Java Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                    <td class="pic"><a href="addExam?test=javascript">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/javascript.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title">JavaScript quiz!</h1>
                                    <p class="caption__overlay__content">Add questions to JavaScript Quiz...
                                    </p>
                                </div>
                            </div>
                        </a></td>
                    <td class="pic"><a href="addExam?test=sequel">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/sql-logo.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title"> SQL quiz</h1>
                                    <p class="caption__overlay__content">Add questions to SQL Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                    <td class="pic"><a href="addExam?test=python">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/python.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title">Python quiz!</h1>
                                    <p class="caption__overlay__content">Add questions to Python Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                </tr>

                <tr>
                    <td class="pic"><a href="addExam?test=css">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/css.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title"> CSS Quiz!</h1>
                                    <p class="caption__overlay__content">Add questions to CSS Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                    <td class="pic"><a href="addExam?test=php">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/php-logo.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title"> PHP quiz!</h1>
                                    <p class="caption__overlay__content">Add questions to PHP Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                    <td class="pic"><a href="addExam?test=c">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/c-logo.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title"> C quiz?</h1>
                                    <p class="caption__overlay__content">Add questions to C language Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                    <td class="pic"><a href="addExam?test=ruby">
                            <div class="caption">
                                <img class="caption__media" width="200" height="200"
                                     src="${pageContext.request.contextPath}/images/ruby.jpg" />
                                <div class="caption__overlay">
                                    <h1 class="caption__overlay__title">Ruby quiz !</h1>
                                    <p class="caption__overlay__content">Add questions to Ruby Quiz...</p>
                                </div>
                            </div>
                        </a></td>
                </tr>

            </table>
        </div>


    </body>
</html>
