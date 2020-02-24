<#include "*/fragments/head-nav.ftl">

<div id="search-bar-hos">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>Criminal Records</h2><br>
                <form class="search-form" target="_self" action="/police/police-stations-records" >
                    <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search" style="color:rgb(23,162,185);font-size:22px;"></i></span></div><input class="form-control" type="text" name="search" placeholder="Search by Criminal  . . .">
                        <div class="input-group-append"><button class="btn btn-info" type="submit">Search </button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div id="table-block">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Record Title</th>
                        <th>Location</th>
                        <th>Criminal</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if criminalRecords?? >
                        <#list criminalRecords as criminalRecord>
                            <tr>
                                <td>${criminalRecord.getDate()}</td>
                                <td>
                                    <a id="med-rec-link" href="/police/edit-criminal-record/${criminalRecord.getId()}/${criminalRecord.getCitizen().getId()}">${criminalRecord.getTitle()}</a>
                                </td>
                                <td>${criminalRecord.getLocation()}</td>
                                <td><a id="med-rec-link" href="/${criminalRecord.citizen.getId()}">${criminalRecord.citizen.getName()}</a></td>
                            </tr>

                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>







<#include "*/fragments/footer.ftl">