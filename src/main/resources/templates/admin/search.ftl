<#include "*/fragments/head-nav.ftl">

<#setting date_format="yyyy-MM-dd">

<#include "*/fragments/admin-dashboard-block.ftl">

<div class="contact-clean" style="background-color:rgb(255,255,255);">
<div class="container">
    <div class="row">
        <div class="col-md-12">

            <form class="search-form" style="margin-top: 0" target="_self" action="/admin/search-action">
                <div class="input-group">
                    <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search"
                                                                                       style="color:rgb(23,162,185);font-size:18px;"></i></span>
                    </div>
                    <input id="ad-search-bar" class="form-control" type="text" name="search"
                           placeholder="Search  . . .">
                    <div class="input-group-append">

                        <button id="ad-search" class="btn btn-info btn-sm" type="submit">Search</button>
                    </div>

                </div>
                <br>
                <input type="radio" name="type" value="citizen" required>Citizen &nbsp;
                <input type="radio" name="type" value="hospital" >Hospital &nbsp;
                <input type="radio" name="type" value="police">Police Station<br>
            </form>
        </div>
    </div>
    <#if searchedList?? >
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
            <#list searchedList as item>
                <tr>
                    <td>${item.getId()}</td>
                    <td>
                        <#if item.getRole() == 'CITIZEN'>
                            <a id="med-rec-link" href="/admin/edit-citizen/${item.getId()}">${item.getName()}</a>
                        <#elseif item.getRole() == 'HOSPITAL'>
                            <a id="med-rec-link" href="/admin/edit-hospital/${item.getId()}">${item.getName()}</a>
                        <#elseif item.getRole() == 'POLICE'>
                            <a id="med-rec-link" href="/admin/edit-police-station/${item.getId()}">${item.getName()}</a>
                        </#if>
                    </td>
                    <td>${item.getAddress()}</td>
                    <td>${item.getEmail()}</td>
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

<#include "*/fragments/footer.ftl">