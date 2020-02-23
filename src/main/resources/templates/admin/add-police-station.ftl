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


<#if (policeStation.id)??>
<form action="/admin/edit-police-station-action/${policeStation.id}" enctype="multipart/form-data" method="post">
    <#else>
    <form action="/admin/add-police-station-action" enctype="multipart/form-data" method="post">
        </#if>



        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="name">Police Station Name</label><br>
        <input type="text" name="name" placeholder="Name" value="<#if (policeStation.name)??>${policeStation.name}</#if>" required><br>

        <label for="address">Address</label><br>
        <input type="text" name="address" placeholder="address" value="<#if (policeStation.address)??>${policeStation.address}</#if>" required><br>

        <label for="email">Email</label><br>
        <input type="text" name="email" placeholder="Email" value="<#if (policeStation.email)??>${policeStation.email}</#if>" required><br>

        <label for="password">Password</label><br>
        <input type="password" name="password" placeholder="Passowrd" required><br>

        <br>

        <#if (policeStation.id)??>
            <input type="submit" value="Update Police Station">
        <#else>
            <input type="submit" value="Add Police Station">
        </#if>
    </form>







</body>
</html>