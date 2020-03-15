<#include "*/fragments/head-nav.ftl">

<#setting date_format="yyyy-MM-dd">

<#include "*/fragments/admin-dashboard-block.ftl">

<div class="contact-clean" style="background-color:rgb(255,255,255);">

    <#if (policeStation.id)??>
    <form action="/admin/edit-police-station-action/${policeStation.id}" enctype="multipart/form-data" method="post">
        <h2 class="text-center">Update Information</h2>
        <#else>
        <form action="/admin/add-police-station-action" enctype="multipart/form-data" method="post">
            <h2 class="text-center">Police Station Registration</h2>
            </#if>

            <#if flag??>
                <p style="color: red">${flag}</p>
            </#if>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <label>Police Station Name</label><br>
            <div class="form-group"><input class="form-control" type="text" name="name" placeholder="Name"
                                           value="<#if (policeStation.name)??>${policeStation.name}</#if>" required>
            </div>

            <label>Address</label><br>
            <div class="form-group"><input class="form-control" type="text" name="address" placeholder="address"
                                           value="<#if (policeStation.address)??>${policeStation.address}</#if>"
                                           required></div>

            <label>Email</label><br>
            <div class="form-group"><input class="form-control" type="text" name="email" placeholder="Email"
                                           value="<#if (policeStation.email)??>${policeStation.email}</#if>" required>
            </div>

            <label>Password</label><br>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Passowrd"
                                           required></div>

            <br>

            <#if (policeStation.id)??>
                <div class="form-group text-center">
                    <button id="btn-war" class="btn btn-warning" role="button" type="submit">Update</button>
                </div>
            <#else>
                <div class="form-group text-center">
                    <button class="btn btn-primary" role="button" type="submit">Add Police Station</button>
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