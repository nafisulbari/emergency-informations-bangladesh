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


<form action="/admin/add-police-station-action" enctype="multipart/form-data" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label for="name">Police Station Name</label><br>
    <input type="text" name="name" placeholder="Name" required><br>

    <label for="address">Address</label><br>
    <input type="text" name="address" placeholder="address" required><br>

    <label for="email">Email</label><br>
    <input type="text" name="email" placeholder="Email" required><br>

    <label for="password">Password</label><br>
    <input type="password" name="password" placeholder="Passowrd" required><br>

    <br>

    <input type="submit" value="Add User">
</form>








</body>
</html>