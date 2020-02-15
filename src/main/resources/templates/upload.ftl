<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>
</head>
<body>

<h1>upload test</h1>


<form action="/uploadFile" enctype="multipart/form-data" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="file" name="file" required/><br/><br/>
    <input type="submit" value="Submit" />
</form>




</body>
</html>