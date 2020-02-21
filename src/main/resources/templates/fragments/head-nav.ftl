<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>eib</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/assets/css/Data-Table.css">
    <link rel="stylesheet" href="/static/assets/css/dh-row-text-image-right-responsive.css">
    <link rel="stylesheet" href="/static/assets/css/Features-Clean.css">
    <link rel="stylesheet" href="/static/assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="/static/assets/css/Highlight-Phone.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/static/assets/css/Navigation-with-Search.css">
    <link rel="stylesheet" href="/static/assets/css/Testimonials.css">
    <link rel="stylesheet" href="/static/assets/css/Contact-Form-Clean.css">
    <link rel="stylesheet" href="/static/assets/css/styles.css">

</head>

<body>

<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>


<nav class="navbar navbar-light navbar-expand-md navigation-clean-search" id="nav-eib"
     style="background-color:#f4f5ec;">
    <div class="container" id="navbar-hov"><a class="navbar-brand" href="/"><img src="/static/assets/img/bd-govt-logo copy.png"
                                                                 id="logo"></a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                    class="navbar-toggler-icon"></span></button>
        <div
                class="collapse navbar-collapse" id="navcol-1">
            <ul class="nav navbar-nav">
                <li class="nav-item" role="presentation"><a class="nav-link" href="/">Home</a></li>

                <@sec.authorize access="hasRole('CITIZEN')">
                    <li class="nav-item" role="presentation"><a class="nav-link"
                                                                href="/<@sec.authentication property="principal.id"/>">Profile</a>
                    </li>
                </@sec.authorize>

                <li class="nav-item" role="presentation"><a class="nav-link" href="#"></a></li>
            </ul>
            <form class="form-inline mr-auto" target="_self" action="/">
                <div class="form-group"><label for="search-field"><i class="fa fa-search"
                                                                     style="font-size:25px;color:rgb(253,191,15);"></i></label><input
                            class="form-control search-field" type="search" name="search" placeholder="Search..."
                            id="search-field" style="color:rgb(67,67,67);"></div>
            </form>
            <p class="navbar-text" id="welcome-name">
                <@sec.authorize access="isAuthenticated()">
                    Welcome, <@sec.authentication property="principal.name"/>
                </@sec.authorize>
            </p>
            <@sec.authorize access="!isAuthenticated()">
                <a class="btn btn-light action-button" role="button" href="/login">Login</a>
            </@sec.authorize>

            <@sec.authorize access="isAuthenticated()">
                <a class="btn btn-danger active action-button" role="button" href="/logout">Logout</a>
            </@sec.authorize>

        </div>
    </div>
</nav>

