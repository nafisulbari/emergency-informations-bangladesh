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
    ${citizen.getSex()}
    ${citizen.getBirthDate()}
    ${citizen.getMobile()}
    ${citizen.getBirthDate()}
    ${citizen.getAddress()}
    ${citizen.getNid()}
    ${citizen.getBloodGroup()}
    ${citizen.getEmergencyRelation()}
    ${citizen.getEmergencyMobile()}

<#elseif authUserRole =='POLICE' >

    <img src="/citizen-images/${citizen.getImageUrl()}" alt="${citizen.getName()}">
    ${citizen.getName()}
    ${citizen.getSex()}
    ${citizen.getBirthDate()}
    ${citizen.getMobile()}
    ${citizen.getBirthDate()}
    ${citizen.getAddress()}
    ${citizen.getNid()}
    ${citizen.getBloodGroup()}
    ${citizen.getEmergencyRelation()}
    ${citizen.getEmergencyMobile()}

<#else>

    ${citizen.getName()}
    ${citizen.getBloodGroup()}
    ${citizen.getEmergencyRelation()}
    ${citizen.getEmergencyMobile()}

</#if>


</body>
</html>