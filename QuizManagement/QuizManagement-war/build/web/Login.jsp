<%-- 
    Document   : index
    Created on : May 28, 2021, 12:55:08 AM
    Author     : sayali
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Online Quiz Portal</title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/styles.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    </head>
    <body>

        <div id='navbar'>
            <ul>
                <li><a href='${pageContext.request.contextPath}/LoginServlet'><span> Online Quiz Portal</span></a></li>

                <li><a href=''><span>Feedback</span></a></li>
                <li><a href=''
                       target="_blank"><span>Contact us</span></a></li>
                <li><a href='${pageContext.request.contextPath}/LogoutServlet'><span>Logout</span></a></li>

            </ul>
        </div>

        <div class="container-fluid">
            <h1>Online Quiz Portal</h1>

            <div class="row">
                <div class="col-md-6">
                    <h3>Student/Admin Login</h3>
                    <form action="LoginServlet" method="post" style="width:300px">
                        <div class="form-group">
                            <label for="userName">User Name</label>
                            <input type="text" class="form-control" name="userName" id="userName" placeholder="User Name"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password"/>
                        </div>  
                        <button type="submit" class="btn btn-primary" name="LoginRegister" value="Login">Login</button>
                        <button type="submit" class="btn btn-primary" name="LoginRegister" value="Register">Register</button>
                    </form>

                </div>

            </div>
        </div>

        <script src="jquery.min.js"></script>
        <script src="bootstrap.min.js"></script>
    </body>
</html>