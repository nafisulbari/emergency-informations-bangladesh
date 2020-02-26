<#include "*/fragments/head-nav.ftl">

<#setting date_format="yyyy-MM-dd">



<#include "*/fragments/admin-dashboard-block.ftl">

<div  class="contact-clean" style="background-color:rgb(255,255,255);">

    <#if (citizen.id)??>
    <form action="/admin/edit-citizen-action/${citizen.id}" enctype="multipart/form-data" method="post">
        <h2 class="text-center">Update Information</h2>
        <#else>
        <form action="/admin/add-citizen-action" enctype="multipart/form-data" method="post">
            <h2 class="text-center">Citizen Registration</h2>
            </#if>
            <#if flag??>
                <p style="color: red">${flag}</p>
            </#if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <label>Name</label><br>
            <div class="form-group"><input class="form-control" type="text" name="name"
                                           value="<#if (citizen.name)??>${citizen.name}</#if>"
                                           required></div>

            <label>Birth Date</label><br>
            <div class="form-group"><input class="form-control" type="date" name="birthDate"
                                           value="<#if (citizen.birthDate)??>${citizen.birthDate?date}</#if>" required>
            </div>

            <label for="sex">Sex</label><br>
            <input type="radio" name="sex" value="Male"
                   <#if  (citizen.sex)??><#if (citizen.sex) == 'Male'>checked</#if></#if> >Male &nbsp;
            <input type="radio" name="sex" value="Female"
                   <#if  (citizen.sex)??><#if (citizen.sex) == 'Female'>checked</#if></#if> >Female &nbsp;
            <input type="radio" name="sex" value="Other"
                   <#if  (citizen.sex)??><#if (citizen.sex) == 'Other'>checked</#if></#if> >Other<br><br>


            <label>NID</label><br>
            <div class="form-group"><input class="form-control" type="number" name="nid"
                                           value="<#if (citizen.nid)??>${citizen.nid?replace(",","")}</#if>" required>
            </div>

            <label>Address</label><br>
            <div class="form-group"><input class="form-control" type="text" name="address"
                                           value="<#if (citizen.address)??>${citizen.address}</#if>"
                                           required></div>

            <label>Mobile</label><br>
            <div class="form-group"><input class="form-control" type="text" name="mobile"
                                           value="<#if (citizen.mobile)??>${citizen.mobile?replace(",","")}</#if>"
                                           required></div>

            <label>Blood Group</label><br>
            <div class="form-group"><input class="form-control" type="text" name="bloodGroup"
                                           value="<#if (citizen.bloodGroup)??>${citizen.bloodGroup}</#if>" required>
            </div>

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

            <label>Password</label><br>
            <div class="form-group"><input class="form-control" type="password" name="password" required></div>

            <label>Image size should be less than 500KB, square image is recommended</label>

            <#if (citizen.id)??>
                <br>
                <input type="file" name="file"/><br/><br/>
                <div class="form-group text-center"><button id="btn-war" class="btn btn-warning" role="button" type="submit">Update</button></div>
            <#else>
                <br>
                <input type="file" name="file" required/><br/><br/>
                <div class="form-group text-center"><button class="btn btn-primary" role="button" type="submit">Add Citizen</button></div>
            </#if>

        </form>


</div>


</div>
</div>
</div>
</div>




<#include "*/fragments/footer.ftl">