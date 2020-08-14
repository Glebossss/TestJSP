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

<nav class="navbar navbar-default" style="background-color: #FFFFFF;">

        <div class="container-fluid">
            <div class="" navbar-header>
                <a href="#" class="navbar-brand">Admin Site</a>
            </div>

            <div>
                <ul class="nav navbar-nav">
                    <li><a href="/admin">Home</a></li>
                    <li><a href="/alluser">All User</a></li>
                    <li  class="active"><a href="#">Add Subject</a></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>

            </div>
        </div>
    </nav>

                <h2>Add Subject</h2>


    </div>

    <form role="form" class="form-horizontal" action="/subject/add" method="post">
        <p>Name subject:
            <input type="text" class="form-control" name="subject" placeholder="subject">
        </p>
    <input type="submit" class="btn btn-primary" value="Add">

        <footer>

            <nav class="navbar navbar-light" style="background-color: #FFFFFF;">
                <div class="container-fluid">
                    <p class="text-center text-muted">020 Gleb Developer</p>
                </div>
            </nav>
        </footer>

</body>
</html>

