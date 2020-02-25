<div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div>
                    <h1 id="med-rec-headding">Criminal Records
                        <@sec.authorize access="hasRole('POLICE')">
                            <a class="btn btn-warning float-right" role="button" href="/police/${citizen.id}/add-criminal-record" id="add-record-btn">Add Record</a>
                        </@sec.authorize>
                    </h1>
                </div>
            </div>
        </div>
    </div>
</div>

<#if criminalRecords?? >
    <#list criminalRecords as criminalRecord>

        <div id="record-list-block">
            <div class="container">
                <div class="row">
                    <div class="col" id="record-list">
                        <div>
                            <div class="col-md-12">
                                <a id="med-rec-title" href="<#if authUserEmail==criminalRecord.getCitizen().getEmail()>
                                                    /citizen/criminal-record/${criminalRecord.getId()}
                                                   <#elseif authUserRole =='ADMIN'>
                                                   /admin/view-criminal-record/${criminalRecord.getId()}
                                                   <#else>
                                                      /police/edit-criminal-record/${criminalRecord.getId()}/${citizen.getId()}
                                                   </#if>">
                                    <h2>${criminalRecord.getTitle()}</h2></a>
                                <div class="row">
                                    <div class="col-md-6">
                                        <h4>${criminalRecord.getPoliceStation().getName()}</h4>
                                    </div>
                                    <div class="col-md-6">
                                        <h4>${criminalRecord.getDate()}<br></h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </#list>
</#if>


