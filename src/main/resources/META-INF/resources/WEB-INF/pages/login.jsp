<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <title>Google Authentication sample</title>

    <link rel="stylesheet" href="css/login.css">
    <script src="js/goto.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js"
            integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
    <style>
        body{
            padding: 30px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand ">MySite</a>
        </div>

        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="/about">About</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="conteiner-fluid ">
    <div class="row" >
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 conteiner-fluid btn-group-vertical"><img src="https://www.gettingsmart.com/wp-content/uploads/2018/02/High-school-student-writing.jpg" class="img-responsive center-block">
            <hr>
            <button type="button" id="starter-template-google" class="btn btn-danger" onclick="goToURL('oauth2/authorization/google');">Login
                Google
            </button>
            <button type="button" id="starter-template-facebook" class="btn btn-primary" onclick="goToURL('oauth2/authorization/facebook');">Login
                Facebook
            </button>
        </div>
    </div>
</div>

</br>
</br>
</br>

<script>
    $('#starter-template-google').click(function goToURL(url) {
        window.location.href='/oauth2/authorization/google';
    });

    $('#starter-template-facebook').click(function goToURL(url) {
        window.location.href='oauth2/authorization/facebook';
    });
</script>


<footer>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <p class="text-center text-muted">2020 Gleb Developer</p>
        </div>
    </nav>
</footer>
</body>
</html>
