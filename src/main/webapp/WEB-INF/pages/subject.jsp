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
        body{
            padding: 30px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand ">Student</a>
        </div>

        <div>
            <ul class="nav navbar-nav">
                <li><a href="/student"><p class="text-muted">Home</p></a></li>
                <li><a href="/allteacher"><p class="text-muted">All Teacher</p></a></li>
                <li class="active"><a href="#"><p class="text-muted">All Subject</p></a></li>
                <li><a href="/unconfirmedactivitiesforstudent"><p class="text-muted">Unconfirmed Activities</p></a></li>
                <li><a href="/confirmedactivitiesforstudent"><p class="text-muted">Confirmed Activities</p></a></li>
                <li><a href="/logout"><p class="text-muted">Logout</p></a></li>
            </ul>
        </div>

</nav>


<div>
    <h2>All Subject</h2>
    <table class="table table-striped table-dark">
        <thread>
            <tr scope="col">
                <th>
                    Name
                </th>

            </tr>
        </thread>
        <c:forEach items="${subject}" var="subject">
            <tbody>
            <tr>
                <td value="${subject.id}">${subject.name}</td>
            </tr>
            </tbody>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${count ne null}">
                <c:forEach var="i" begin="1" end="${count}">
                    <li><a href="/subject?pageCount=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
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
