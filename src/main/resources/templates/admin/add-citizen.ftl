<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>
</head>
<body>




<h1>hi there all fellaas</h1>




<form action="/admin/add-citizen-action" enctype="multipart/form-data" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label for="name">Name</label><br>
    <input type="text" name="name" placeholder="Name" required><br>

    <label for="sex">Sex</label><br>
    <input type="radio" name="sex" value="Male">Male<br>
    <input type="radio" name="sex" value="Female">Female<br>
    <input type="radio" name="sex" value="Other">Other<br>

    <label for="email">Email</label><br>
    <input type="text" name="email" placeholder="Email" required><br>

    <label for="password">Password</label><br>
    <input type="password" name="password" placeholder="Passowrd" required><br>

    <label for="nid">NID</label><br>
    <input type="text" name="nid" placeholder="nid"><br>

    <label for="address">Address</label><br>
    <input type="text" name="address" placeholder="address" required><br>

    <label for="mobile">Mobile</label><br>
    <input type="text" name="mobile" placeholder="mobile" required><br>

    <label for="bloodGroup">Blood Group</label><br>
    <input type="text" name="bloodGroup" placeholder="bloodGroup" required><br>

    <label for="emergencyRelation">Emergency Contact Relation</label><br>
    <input type="text" name="emergencyRelation" placeholder="emergencyRelation" required><br>

    <label for="emergencyMobile">Emergency Contact Mobile</label><br>
    <input type="text" name="emergencyMobile" placeholder="emergencyMobile" required><br>

    <br>
    <input type="file" name="file"/><br/><br/>

    <input type="submit" value="Add User">
</form>








</body>
</html>