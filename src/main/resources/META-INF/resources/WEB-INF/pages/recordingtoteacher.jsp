<%--
  Created by IntelliJ IDEA.
  User: Gleb
  Date: 05.08.2020
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
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
<form role="form" class="form-horizontal" action="/unconfirmedactivitiesforstudent" method="post">
    <nav class="navbar navbar-default" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand ">Student</a>
            </div>

            <div>
                <ul class="nav navbar-nav">
                    <li ><a href="/student"><p class="text-muted">Home</p></a></li>
                    <li><a href="/allteacher"><p class="text-muted">All Teacher</p></a></li>
                    <li><a href="/subject"><p class="text-muted">All Subject</p></a></li>
                    <li><a href="/unconfirmedactivitiesforstudent"><p class="text-muted">Unconfirmed Activities</p></a></li>
                    <li class="active"><a href="#"><p class="text-muted">Confirmed Activities</p></a></li>
                    <li><a href="/logout"><p class="text-muted">Logout</p></a></li>

                </ul>
            </div>
        </div>
    </nav>

    <p>Teacher Email: ${teacher.email}</p>
    <p>Student Email: ${student}</p>


    <table class="table table-striped">
        <thread>
            <tr>
                <th>
                    Email Teacher
                </th>
                <th>
                    Data start
                </th>
                <th>
                    Data end
                </th>
                <th>
                    Time
                </th>
                <th>
                    Mony
                </th>
                <th>
                    Record
                </th>
            </tr>
        </thread>
        <c:forEach items="${calendar}" var="calendar">
            <tbody>
            <tr>
                <th value="${calendar.id}">${calendar.getTeacherEntity().getEmail()}</th>
                <th value="${calendar.id}">${calendar.getDateStart()}</th>
                <th value="${calendar.id}">${calendar.getDataEnd()}</th>
                <th value="${calendar.id}">${calendar.getMoney()}</th>
                <th value="${calendar.id}">${calendar.getTime()}</th>
                <th>
                    <BUTTON type="submit" class="btn btn-primary" name="id" value="${calendar.id}">Record</BUTTON>
                </th>
            </tr>
            </tbody>
        </c:forEach>
    </table>


    <ul class="pagination">
        <c:if test="${count ne null}">
            <c:forEach var="i" begin="1" end="${count}">
                <li><a href="/recordingtoteacher?pageCount=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
            </c:forEach>
        </c:if>
    </ul>
    </nav>



            <footer>

                <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
                    <div class="container-fluid">
                        <p class="text-center text-muted">020 Gleb Developer</p>
                    </div>
                </nav>
            </footer>
</body>
</html>

