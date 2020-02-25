<#include "*/fragments/head-nav.ftl">



<#include "*/fragments/admin-dashboard-block.ftl">

<div id="table-block">


    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>RECORD NO</th>
            <th>Name</th>
            <th>TITLE</th>
            <th>POLICE STATION</th>


        </tr>
        </thead>
        <tbody>
        <#if criminalRecords?? >
            <#list criminalRecords as criminalRecord>
                <tr>
                    <td>${criminalRecord.getId()}</td>
                    <td>${criminalRecord.getCitizen().getName()}</td>
                    <td>
                        <a id="med-rec-link"
                           href="/admin/criminal-record-review/${criminalRecord.getId()}">${criminalRecord.getTitle()}</a>
                    </td>
                    <td>${criminalRecord.getPoliceStation().getName()}</td>

                </tr>

            </#list>
        </#if>
        </tbody>
    </table>


</div>


</div>
</div>
</div>
</div>






<#include "*/fragments/footer.ftl">