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
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="/allstudent">All Student</a></li>
                    <li><a href="/unconfirmedactivitiesforteacher">Unconfirm Activities</a></li>
                    <li><a href="/confirmedactivitiesforteacher">Confirm Activities</a></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>

            </div>
        </div>
    </nav>

    <div><p> Info about ${teacherDTO.name}</p></div>
    <div>
        <p>Subject:
            <select class="selectpicker form-control form-group" name="subject">
                <option value="${teacherDTO.subjectEntity.id}"> ${teacherDTO.subjectEntity}</option>
                <c:forEach items="${subject}" var="subject">
                    <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>
        </p>


        <p>Pay:
            <input type="text" class="form-control" name="money" placeholder="money" value="${teacherDTO.price}">
        </p>

    </div>

    <input type="submit" class="btn btn-primary" value="Add">


    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

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

            </tr>
            </tbody>
        </c:forEach>
    </table>


    <ul class="pagination">
        <c:if test="${count ne null}">
            <c:forEach var="i" begin="1" end="${count}">
                <li><a href="/teacher?pageCount=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
            </c:forEach>
        </c:if>
    </ul>
    </nav>

    <br>
    <br>
    <br>
    <div id="addModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Add Lesons</h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tbody>
                        <tr>
                            <td>
                                Start: <input type="datetime-local" name="startDate" value="startDate" placeholder="startDate"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <select name="endDate" placeholder="endDate">
                                    <option>15</option>
                                    <option>30</option>
                                    <option>60</option>
                                    <option>90</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" id="submitButton" value="Add"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <a class="nav-link" href="#" data-toggle="modal" data-target="#addModal">Add</a>



    <footer>

        <nav class="navbar navbar-light" style="background-color: #1fa67b;">
            <div class="container-fluid">
                <p class="text-center text-muted">020 Gleb Developer</p>
            </div>
        </nav>
    </footer>

</body>
</html>

