<#include "*/fragments/head-nav.ftl">

<div id="search-bar-hos">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>Medical Records</h2><br>
                <form class="search-form" target="_self" action="/hospital/hospitals-records" >
                    <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-search" style="color:rgb(23,162,185);font-size:22px;"></i></span></div><input class="form-control" type="text" name="search" placeholder="Search for Patient  . . .">
                        <div class="input-group-append"><button class="btn btn-info" type="submit">Search </button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div>
    <div id="hos-pol-rec-con" class="container">
        <div class="row">
            <div class="col-md-12">
                <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Record Title</th>
                        <th>Doctor</th>
                        <th>Patient</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if medicalRecords?? >
                        <#list medicalRecords as medicalrecord>
                            <tr>
                                <td>${medicalrecord.getDate()}</td>
                                <td>
                                    <a id="med-rec-link" href="/hospital/edit-medical-record/${medicalrecord.getId()}/${medicalrecord.getCitizen().getId()}">${medicalrecord.getTitle()}</a>
                                </td>
                                <td>${medicalrecord.getDoctor()}</td>
                                <td><a id="med-rec-link" href="/${medicalrecord.citizen.getId()}">${medicalrecord.citizen.getName()}</a></td>
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