<#include "*/fragments/head-nav.ftl">

<#setting date_format="yyyy-MM-dd">


<div class="contact-clean" style="background-color:rgb(255,255,255);">
    <form action="/citizen/request-update-action" enctype="multipart/form-data" method="post">


        <#if flag??>
            <h3 style="color: green" class="text-center">${flag}</h3>
        <#else>
            <h2 class="text-center">Update Information</h2>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>



            <label>Address</label><br>
            <div class="form-group"><input class="form-control" type="text" name="address"
                                           value="<#if (citizen.address)??>${citizen.address}</#if>"
                                           required></div>

            <label>Mobile</label><br>
            <div class="form-group"><input class="form-control" type="text" name="mobile"
                                           value="<#if (citizen.mobile)??>${citizen.mobile?replace(",","")}</#if>"
                                           required></div>

            <label>Emergency Contact Relation</label><br>
            <div class="form-group"><input class="form-control" type="text" name="emergencyRelation"
                                           value="<#if (citizen.emergencyRelation)??>${citizen.emergencyRelation}</#if>"
                                           required></div>

            <label>Emergency Contact Mobile</label><br>
            <div class="form-group"><input class="form-control" type="text" name="emergencyMobile"
                                           value="<#if (citizen.emergencyMobile)??>${citizen.emergencyMobile}</#if>"
                                           required></div>

            <label>Email</label><br>
            <div class="form-group"><input class="form-control" type="text" name="email"
                                           value="<#if (citizen.email)??>${citizen.email}</#if>"
                                           required></div>

            <#if flagUserExists??>
                <h3 style="color: red">${flagUserExists}</h3>
            </#if>



            <label>Password</label><br>
            <div class="form-group"><input class="form-control" type="password" name="password" required></div>

            <label>Image size should be less than 500KB, square image is recommended</label>
            <br>
            <input type="file" name="file"/><br/><br/>
            <div class="form-group text-center">
                <button class="btn btn-primary" role="button" type="submit">Request Update</button>
            </div>

        </#if>
    </form>


</div>


<#include "*/fragments/footer.ftl">