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
<#if (hospital.id)??>
<form action="/admin/edit-hospital-action/${hospital.id}" enctype="multipart/form-data" method="post">
<#else>
<form action="/admin/add-hospital-action" enctype="multipart/form-data" method="post">
</#if>


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label for="name">Hospital Name</label><br>
    <input type="text" name="name" placeholder="Name" value="<#if (hospital.name)??>${hospital.name}</#if>" required><br>

    <label for="address">Address</label><br>
    <input type="text" name="address" placeholder="address" value="<#if (hospital.address)??>${hospital.address}</#if>" required><br>

    <label for="email">Email</label><br>
    <input type="text" name="email" placeholder="Email" value="<#if (hospital.email)??>${hospital.email}</#if>" required><br>

    <label for="password">Password</label><br>
    <input type="password" name="password" placeholder="Passowrd" required><br>

    <br>

    <#if (hospital.id)??>
        <input type="submit" value="Update Hospital">
    <#else>
        <input type="submit" value="Add Hospital">
    </#if>
</form>





</body>
</html>