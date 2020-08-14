<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>TaskMenedger</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <style>
            body {
                padding: 30px;
            }
        </style>
    </head>
<body>

<nav class="navbar navbar-default" style="background-color: #FFFFFF;">

    <div class="container-fluid">
        <div class="" navbar-header>
            <a href="#" class="navbar-brand">Admin Site</a>
        </div>

        <div>
            <ul class="nav navbar-nav">
                <li><a href="/admin">Home</a></li>
                <li class="active"><a href="#">All User</a></li>
                <li><a href="/addsubject">Add Subject</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>

        </div>
    </div>
</nav>

<form role="form" class="form-horizontal" action="/alluser" method="post">
    <div>
        <h2>Hello Admin</h2>
        <table class="table table-striped">
            <thread>
                <tr>
                    <th>
                        Email
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Role
                    </th>
                    <th>
                       Change Role
                    </th>
                </tr>
            </thread>
            <c:forEach items="${alluser}" var="alluser">
                <tbody>
                <tr>
                    <th value="${alluser.id}">${alluser.email}</th>
                    <th value="${alluser.id}">${alluser.name}</th>
                    <th value="${alluser.id}">${alluser.role}</th>
                    <th value="${alluser.id}"><BUTTON type="submit" class="btn btn-primary" name="userEmail" value="${alluser.email}">Change</BUTTON></th>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <input type="submit" class="btn btn-primary" value="Upload">

        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${count ne null}">
                    <c:forEach var="i" begin="1" end="${count}">
                        <li><a href="/alluser?pageCount=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>

    <nav class="navbar navbar-light" style="background-color: #FFFFFF;">
        <div class="container-fluid">
            <p class="text-center text-muted">020 Gleb Developer</p>
        </div>
    </nav>
    </footer>

</body>
</html>

