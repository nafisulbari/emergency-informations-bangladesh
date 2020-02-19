<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <title>Emergency Information Bangladesh</title>
</head>
<body>
<h2>Hi</h2>
<#if errorMessage??>
    <p style="color: red">${ errorMessage}</p>
<#else>


    <#if authUserRole =='HOSPITAL' >


        <img src="/citizen-records/${citizen.getId()}/${citizen.getImageUrl()}" alt="${citizen.getName()}">
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

        <#include "fragments/medical-records.ftl">

    <#elseif authUserRole =='POLICE' >

        <img src="/citizen-records/${citizen.getId()}/${citizen.getImageUrl()}" alt="${citizen.getName()}">
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

        <#include "fragments/criminal-records.ftl">

    <#elseif authUserEmail == citizen.getEmail() >

        <img src="/citizen-records/${citizen.getId()}/${citizen.getImageUrl()}" alt="${citizen.getName()}">

        <a href="/citizen-records/${citizen.getId()}/${citizen.getId()}.png" download>Download QR</a>

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
        <img src="/citizen-records/${citizen.getId()}/${citizen.getImageUrl()}" alt="${citizen.getName()}">
        ${citizen.getName()}
        ${citizen.getBloodGroup()}
        ${citizen.getEmergencyRelation()}
        ${citizen.getEmergencyMobile()}


    </#if>





</#if>


</body>
</html>