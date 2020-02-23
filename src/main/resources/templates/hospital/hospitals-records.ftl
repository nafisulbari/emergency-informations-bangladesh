<#include "*/fragments/head-nav.ftl">

<div id="table-block">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Title</th>
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
                                <td>${medicalrecord.getCitizen().getName()}</td>
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