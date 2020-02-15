<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>
</head>
<body>

<h2>Hi </h2>

<#if authUserRole =='HOSPITAL' >
    <img src="/citizen-images/${citizen.getImageUrl()}" alt="${citizen.getName()}">
    ${citizen.getName()}
<#elseif authUserRole =='POLICE' >
    <img src="/citizen-images/${citizen.getImageUrl()}" alt="${citizen.getName()}">
    ${citizen.getName()}
<#else>

    ${citizen.getName()}
    ${citizen.getBloodGroup()}
    ${citizen.getEmergencyRelation()}
    ${citizen.getEmergencyMobile()}

</#if>


</body>
</html>