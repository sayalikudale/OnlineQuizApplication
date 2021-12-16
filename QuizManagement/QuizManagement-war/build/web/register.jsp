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
                    <h3>Student/Admin Register</h3>
                    <form action="Register" method="post" style="width:300px">
                        <div class="form-group">
                            <label for="fullName">Full Name </label>
                            <input type="text" class="form-control" name="fullname" id="userName" placeholder="Full Name"/>
                        </div>

                        <div class="form-group">
                            <label for="userName">User Name</label>
                            <input type="text" class="form-control" name="userName" id="userName" placeholder="User Name"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Password"/>
                        </div>  

                        <div class="form-group">
                            <label for="admin"> Register as Admin ? </label>
                            <select name="admin" id="admin">
                                <option value="no">No</option>
                                <option value="yes">Yes</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary" name="register" value="registered">Register</button>
                    </form>

                </div>

            </div>
        </div>

        <script src="jquery.min.js"></script>
        <script src="bootstrap.min.js"></script>
    </body>
</html>