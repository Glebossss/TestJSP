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
<form role="form" class="form-horizontal" action="/teacher/add" method="post">

    <nav class="navbar navbar-default" style="background-color: #1fa67b;">
        <div class="container-fluid">
            <div class="" navbar-header>
                <a href="/teacher" class="navbar-brand">Teacher Site</a>
            </div>

            <div>
                <ul class="nav navbar-nav">
                    <li><a href="/teacher">Home</a></li>
                    <li  class="active"><a href="#">All Student</a></li>
                    <li><a href="/unconfirmedactivitiesforteacher">Unconfirm Activities</a></li>
                    <li><a href="/confirmedactivitiesforteacher">Confirm Activities</a></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>

            </div>
        </div>
    </nav>

    <table class="table table-striped">
        <thread>
    <div>
        <h2>All Stuent</h2>

        <tr>
            <th>
                Email
            </th>
            <th>
                Name
            </th>
        </tr>
        </thread>
        <c:forEach items="${allstudent}" var="allstudent">
        <tbody>
        <tr>
            <th value="${allstudent.id}">${allstudent.email}</th>
            <th value="${allstudent.id}">${allstudent.name}</th>
        </tr>
        </tbody>
        </c:forEach>
        </table>


        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${count ne null}">
                    <c:forEach var="i" begin="1" end="${count}">
                        <li><a href="/allstudent?pageCount=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>

    </div>


    <footer>

        <nav class="navbar navbar-light" style="background-color: #1fa67b;">
            <div class="container-fluid">
                <p class="text-center text-muted">020 Gleb Developer</p>
            </div>
        </nav>
    </footer>

</body>
</html>

