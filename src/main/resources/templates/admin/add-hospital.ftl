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
                    <button class="btn btn-primary"role="button" type="submit">Add Hospital</button>
                </div>
            </#if>
        </form>

            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <form class="search-form" target="_self" action="/admin/search-hospital" >
                            <div class="input-group">
                                <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search" style="color:rgb(23,162,185);font-size:18px;"></i></span></div><input id="ad-search-bar" class="form-control" type="text" name="search" placeholder="Search hospital  . . .">
                                <div class="input-group-append"><button id="ad-search" class="btn btn-info btn-sm" type="submit">Search </button></div>
                            </div>
                        </form>
                    </div>
                </div>
                <#if searchedHospitals?? >
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Email</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list searchedHospitals as hos>
                            <tr>
                                <td>${hos.getId()}</td>
                                <td>
                                    <a id="med-rec-link" href="/admin/edit-hospital/${hos.getId()}">${hos.getName()}</a>
                                </td>
                                <td>${hos.getAddress()}</td>
                                <td>${hos.getEmail()}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>

                </#if>
            </div>



        </div>


</div>



</div>
</div>
</div>
</div>







<#include "*/fragments/footer.ftl">