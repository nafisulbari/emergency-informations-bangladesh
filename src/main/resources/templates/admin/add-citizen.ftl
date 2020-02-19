<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>
</head>
<body>

<#setting date_format="yyyy-MM-dd">



<#if flag??>

    <p style="color: red">${flag}</p>

</#if>
<h1></h1>

<#if (citizen.id)??>
<form action="/admin/edit-citizen-action/${citizen.id}" enctype="multipart/form-data" method="post">
    <#else>
    <form action="/admin/add-citizen-action" enctype="multipart/form-data" method="post">
        </#if>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="name">Name</label><br>
        <input type="text" name="name" placeholder="Name" value="<#if (citizen.name)??>${citizen.name}</#if>"
               required><br>

        <label for="birthDate">Birthday</label><br>
        <input type="date" name="birthDate" value="<#if (citizen.birthDate)??>${citizen.birthDate?date}</#if>" required><br>

        <label for="sex">Sex</label><br>
        <input type="radio" name="sex" value="Male"
               <#if  (citizen.sex)??><#if (citizen.sex) == 'Male'>checked</#if></#if> >Male<br>
        <input type="radio" name="sex" value="Female"
               <#if  (citizen.sex)??><#if (citizen.sex) == 'Female'>checked</#if></#if> >Female<br>

        <input type="radio" name="sex" value="Other"
               <#if  (citizen.sex)??><#if (citizen.sex) == 'Other'>checked</#if></#if> >Other<br>

        <label for="nid">NID</label><br>
        <input type="text" name="nid" placeholder="nid"
               value="<#if (citizen.nid)??>${citizen.nid?replace(",","")}</#if>" required><br>

        <label for="address">Address</label><br>
        <input type="text" name="address" placeholder="address"
               value="<#if (citizen.address)??>${citizen.address}</#if>"
               required><br>

        <label for="mobile">Mobile</label><br>
        <input type="text" name="mobile" placeholder="mobile"
               value="<#if (citizen.mobile)??>${citizen.mobile?replace(",","")}</#if>"
               required><br>

        <label for="bloodGroup">Blood Group</label><br>
        <input type="text" name="bloodGroup" placeholder="bloodGroup"
               value="<#if (citizen.bloodGroup)??>${citizen.bloodGroup}</#if>" required><br>

        <label for="emergencyRelation">Emergency Contact Relation</label><br>
        <input type="text" name="emergencyRelation" placeholder="emergencyRelation"
               value="<#if (citizen.emergencyRelation)??>${citizen.emergencyRelation}</#if>" required><br>

        <label for="emergencyMobile">Emergency Contact Mobile</label><br>
        <input type="text" name="emergencyMobile" placeholder="emergencyMobile"
               value="<#if (citizen.emergencyMobile)??>${citizen.emergencyMobile}</#if>" required><br>

        <label for="email">Email</label><br>
        <input type="text" name="email" placeholder="Email" value="<#if (citizen.email)??>${citizen.email}</#if>"
               required><br>

        <label for="password">Password</label><br>
        <input type="password" name="password" placeholder="Password" required><br>


        <#if (citizen.id)??>
            <br>
            <input type="file" name="file"/><br/><br/>

            <input type="submit" value="Update Citizen">
            <#else>
                <br>
                <input type="file" name="file" required/><br/><br/>

                <input type="submit" value="Add Citizen">
                </#if>

    </form>


</body>
</html>