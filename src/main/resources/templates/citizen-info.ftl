<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>
</head>
<body>

<h2>Hi </h2>

<#if authUserRole =='HOSPITAL' >
    ${citizen.getName()}
<#elseif authUserRole =='POLICE' >
    ${citizen.getName()}
<#else>

    ${citizen.getName()}
    ${citizen.getBloodGroup()}
    ${citizen.getEmergencyRelation()}
    ${citizen.getEmergencyMobile()}

</#if>











</body>
</html>