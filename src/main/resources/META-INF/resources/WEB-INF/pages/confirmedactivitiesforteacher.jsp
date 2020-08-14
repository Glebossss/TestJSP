<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<form role="form" class="form-horizontal" action="/confirmedactivitiesforteacher" method="post">

    <nav class="navbar navbar-default" style="background-color: #1fa67b;">
        <div class="container-fluid">
            <div class="" navbar-header>
                <a href="/teacher" class="navbar-brand">Teacher Site</a>
            </div>

            <div>
                <ul class="nav navbar-nav">
                    <li><a href="/teacher">Home</a></li>
                    <li><a href="/allstudent">All Student</a></li>
                    <li><a href="/unconfirmedactivitiesforteacher">Unconfirm Activities</a></li>
                    <li class="active"><a href="#">Confirm Activities</a></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>

            </div>
        </div>
    </nav>

    <h2>All Record</h2>
    <table class="table table-striped">
        <thread>
            <tr>
                <th>
                    Email Student
                </th>
                <th>
                    Email TEacher
                </th>
                <th>
                    Data Start
                </th>
                <th>
                    Data End
                </th>
                <th>
                    Time
                </th>
                <th>
                    Price
                </th>
            </tr>
        </thread>
        <c:forEach items="${confirmedactivitiesforteacher}" var="confirmedactivitiesforteacher">
            <tbody>
            <tr>
                <th value="${confirmedactivitiesforteacher.id}">${confirmedactivitiesforteacher.studentEntity.getEmail()}</th>
                <th value="${confirmedactivitiesforteacher.id}">${confirmedactivitiesforteacher.teacherEntity.getEmail()}</th>
                <th value="${confirmedactivitiesforteacher.id}">${confirmedactivitiesforteacher.getDateStart()}</th>
                <th value="${confirmedactivitiesforteacher.id}">${confirmedactivitiesforteacher.getDataEnd()}</th>
                <th value="${confirmedactivitiesforteacher.id}">${confirmedactivitiesforteacher.getTime()}</th>
                <th value="${confirmedactivitiesforteacher.id}">${confirmedactivitiesforteacher.getMoney()}</th>
            </tr>
            </tbody>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${count ne null}">
                <c:forEach var="i" begin="1" end="${count}">
                    <li><a href="/confirmedactivitiesforteacher?pageCount=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>


    <footer>

        <nav class="navbar navbar-light" style="background-color: #1fa67b;">
            <div class="container-fluid">
                <p class="text-center text-muted">020 Gleb Developer</p>
            </div>
        </nav>
    </footer>

</body>
</html>
