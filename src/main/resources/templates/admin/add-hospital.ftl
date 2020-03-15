<#include "*/fragments/head-nav.ftl">

<#setting date_format="yyyy-MM-dd">

<#include "*/fragments/admin-dashboard-block.ftl">

<div class="contact-clean" style="background-color:rgb(255,255,255);">

    <#if (hospital.id)??>
    <form action="/admin/edit-hospital-action/${hospital.id}" enctype="multipart/form-data" method="post">
        <h2 class="text-center">Update Information</h2>
        <#else>
        <form action="/admin/add-hospital-action" enctype="multipart/form-data" method="post">
            <h2 class="text-center">Hospital Registration</h2>
            </#if>
            <#if flag??>
                <p style="color: red">${flag}</p>
            </#if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <label>Hospital Name</label><br>
            <div class="form-group"><input class="form-control" type="text" name="name" placeholder="Name"
                                           value="<#if (hospital.name)??>${hospital.name}</#if>" required></div>

            <label>Address</label><br>
            <div class="form-group"><input class="form-control" type="text" name="address" placeholder="address"
                                           value="<#if (hospital.address)??>${hospital.address}</#if>" required></div>

            <label>Email</label><br>
            <div class="form-group"><input class="form-control" type="text" name="email" placeholder="Email"
                                           value="<#if (hospital.email)??>${hospital.email}</#if>" required></div>

            <label>Password</label><br>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Passowrd"
                                           required></div>

            <br>

            <#if (hospital.id)??>
                <div class="form-group text-center">
                    <button id="btn-war" class="btn btn-warning" role="button" type="submit">Update</button>
                </div>
            <#else>
                <div class="form-group text-center">
                    <button class="btn btn-primary" role="button" type="submit">Add Hospital</button>
                </div>
            </#if>
        </form>

</div>
</div>
</div>
</div>
</div>
</div>







<#include "*/fragments/footer.ftl">