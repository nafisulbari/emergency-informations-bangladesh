<#if errorMessage??>
    <p style="color: red">${ errorMessage}</p>
<#else>



    <#if authUserRole =='HOSPITAL' || authUserRole == 'POLICE' || authUserRole == 'ADMIN' || authUserEmail == citizen.getEmail()>

        <#include "fragments/head-nav.ftl">

        <div id="block-info">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h1 id="citizen-heading">Citizen Information
                            <#if authUserRole == "ADMIN">
                                <a class="btn btn-warning" role="button"
                                   href="/admin/edit-citizen/${citizen.getId()}">Edit</a>
                            </#if>
                        </h1>
                    </div>
                </div>
                <div class="row" id="info-block">
                    <div class="col-md-4">
                        <div><img src="/citizen-records/${citizen.getId()}/${citizen.getImageUrl()}" id="profile-img"
                                  alt="${citizen.getName()}"></div>
                    </div>
                    <div class="col-md-4">
                        <div>
                            <h2 id="name">${citizen.getName()}</h2>
                            <p style="color:rgb(77,77,77);">Sex: ${citizen.getSex()}</p>
                            <p style="color:rgb(77,77,77);">DOB:&nbsp;${citizen.getBirthDate()}<br></p>
                            <p style="color:rgb(77,77,77);">Mobile: ${citizen.getMobile()}</p>
                            <p style="color:rgb(77,77,77);">NID:&nbsp;${citizen.getNid()}<br></p>
                            <p style="color:rgb(77,77,77);">Address:&nbsp;${citizen.getAddress()}<br></p>
                            <p style="color:rgb(77,77,77);">Citizen Points: ${citizen.getCitizenPoint()}<br></p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div id="emergency">
                            <h3 class="text-warning" id="e-info-text">Emergency Info &nbsp;
                                <#if authUserEmail == citizen.getEmail()>
                                    <a class="btn btn-warning btn-sm" role="button"
                                       href="/citizen/request-update">Edit</a>
                                </#if>
                            </h3>
                            <p style="color:rgb(77,77,77);">ID: ${citizen.getId()}<br></p>
                            <p style="color:rgb(77,77,77);">Blood Group: ${citizen.getBloodGroup()}<br></p>
                            <p style="color:rgb(77,77,77);">Emergency Contact: ${citizen.getEmergencyRelation()}<br></p>
                            <p style="color:rgb(77,77,77);">Contact Mobile: ${citizen.getEmergencyMobile()}<br></p>
                            <#if authUserEmail == citizen.getEmail()>
                                <a class="btn btn-info" role="button"
                                   href="/citizen-records/${citizen.getId()}/${citizen.getId()}.png" download>Download
                                    QR Code</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <#if authUserRole =='ADMIN' >
            <#include "fragments/medical-records.ftl">
            <#include "fragments/criminal-records.ftl">
        <#elseif authUserRole =='HOSPITAL' >
            <#include "fragments/medical-records.ftl">
        <#elseif authUserRole =='POLICE' >
            <#include "fragments/criminal-records.ftl">
        <#elseif authUserEmail == citizen.getEmail()>
            <#include "fragments/medical-records.ftl">
            <#include "fragments/criminal-records.ftl">

        <#else>
        </#if>

        <#include "fragments/footer.ftl">


    <#else>
        ${citizen.getId()}
        ${citizen.getName()}
        ${citizen.getBloodGroup()}
        ${citizen.getEmergencyRelation()}
        ${citizen.getEmergencyMobile()}


    </#if>


</#if>











