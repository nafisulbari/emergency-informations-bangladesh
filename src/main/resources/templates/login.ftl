<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Emergency Informations Bangladesh - Log In</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/assets/css/dh-row-text-image-right-responsive.css">
    <link rel="stylesheet" href="/static/assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/static/assets/css/Navigation-with-Search.css">
    <link rel="stylesheet" href="/static/assets/css/styles.css">
    <link rel="stylesheet" href="/static/assets/css/Login-Form-Clean.css">

</head>
<body>
<nav class="navbar navbar-light navbar-expand-md navigation-clean-search" id="nav-eib"
     style="background-color:#f4f5ec;">
    <div class="container"><a class="navbar-brand" href="#"><img src="/static/assets/img/bd-govt-logo%20copy.png"
                                                                 id="logo"></a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                    class="navbar-toggler-icon"></span></button>
        <div
                class="collapse navbar-collapse" id="navcol-1">
            <ul class="nav navbar-nav">
                <li class="nav-item" role="presentation"><a class="nav-link" href="/">Home</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#"></a></li>
            </ul>
            <form class="form-inline mr-auto" target="_self" action="/">
                <div class="form-group"><label for="search-field"><i class="fa fa-search"
                                                                     style="font-size:25px;color:rgb(253,191,15);"></i></label><input
                            class="form-control search-field" type="search" name="search" placeholder="Search..."
                            id="search-field" style="color:rgb(67,67,67);"></div>
            </form>

        </div>
    </div>
</nav>
<div class="content-login">

<div id="login-form-div" class="login-clean" style="background-color:rgb(255,255,255);">

    <form role="form" action="/login" method="post">
        <h2 class="sr-only">Login Form</h2>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
        <div class="form-group">
            <input class="form-control" type="email" name="email" id="email" placeholder="Email" required autofocus>
        </div>

        <div class="form-group">
            <input class="form-control" type="password" name="password" id="password" placeholder="Password" required>
        </div>

        <#-- FIX THE TOMCAT ERROR    <div>-->
        <#--        <label for="remember-me">Remember me</label>-->
        <#--        <input type="checkbox" name="remember-me" id="remember-me">-->
        <#--    </div>-->



        <div class="form-group">
            <button class="btn btn-info btn-block" type="submit">Log In</button>
        </div>
    </form>
</div>


</div>


<div id="footer" class="footer-basic" style="background-color:#f4f5ec;">
    <footer>
        <ul class="list-inline footer">
            <li class="list-inline-item"><a href="#">Home</a></li>
            <li class="list-inline-item"><a href="#">Services</a></li>
            <li class="list-inline-item"><a href="#">About</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
        </ul>
        <p class="copyright"><br><strong>©&nbsp;2020,&nbsp;Ministry of Social Welfare</strong></p>
    </footer>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
</body>

</html>