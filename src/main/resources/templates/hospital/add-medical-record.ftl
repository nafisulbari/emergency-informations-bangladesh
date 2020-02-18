<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>
</head>
<body>


<#if flag??>

    <p style="color: red">${flag}</p>

</#if>


<form action="/hospital/${citizenId}/add-medical-record-action" enctype="multipart/form-data" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


    <label for="date">Birthday</label>
    <input type="date" name="date">

    <label for="doctor">Doctor</label><br>
    <input type="text" name="name" placeholder="Name" ><br>

    <label for="title">Title</label><br>
    <input type="text" name="title" placeholder="title" ><br>

    <label for="email">Description</label><br>
    <input type="text" name="description" placeholder="description" ><br>



    <br>

    <input type="submit" value="Add Record">
</form>








</body>
</html>