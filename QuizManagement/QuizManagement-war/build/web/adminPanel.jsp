<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <title>User Panel - Code Quiz - Test Your Programming Knowledge!</title>
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/styles.css"/> 

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/style.css">
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

                            <span>My Results</span></a></li>
                <li><a href='#'><span>Feedback</span></a></li>
                <li><a href='#'
                       target="_blank"><span>Contact us</span></a></li>
                <li><a href='${pageContext.request.contextPath}/LogoutServlet'><span>Logout</span></a></li>

            </ul>
        </div>

        <!--  If user is not logged in -->
        <c:if test='${empty sessionScope.userName}'>
            <div id="wrapper">
                <form name="login-form" class="login-form">
                    <div class="header" style="height: 60px;">
                        <img style="float: left;" height="30" width="30"
                             src="${pageContext.request.contextPath}/images/warn.png" />
                        <h1 style="float: right;">Please Register or Login..</h1>
                    </div>
                </form>
            </div>
        </c:if>

        <c:if test='${not empty sessionScope.userName}'>

            <a href='${pageContext.request.contextPath}/adminPanel'>
                <div class="button nameuser">Logged as, ${sessionScope.userName}</div>
            </a>

            <div class="login-form" style="margin-top: 5%;">
                <h2 align="center"
                    style="color: white; background-color: blue; border-radius: 5px;">Test
                    Results of all the Students </h2>
                <div class="results">

                    <c:forEach items="${studentResults}" var="studentResult">

                        <c:forEach items="${studentResult.value}" var="result">
                            <td>
                            <tr>
                                <c:if test="${result.value < 5}">
                                <div style="color: red;">
                                </c:if>
                                <c:if test="${result.value >= 5}">
                                    <div style="color: green;">
                                    </c:if>
                                    <c:out value="${studentResult.key} : ${result.key} : ${result.value}" />

                                    </tr>
                                    </td>
                                </c:forEach>
                            </c:forEach>

                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
