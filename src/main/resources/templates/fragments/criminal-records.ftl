<div>
    criminal rec



    <a href="/police/${citizen.id}/add-criminal-record">Add Record</a>

    <#list criminalRecords as criminalRecord>

        ${criminalRecord.getPoliceStation().getName()}
        ${criminalRecord.getTitle()}
        ${criminalRecord.getDate()}
        ${criminalRecord.getLocation()}
        ${criminalRecord.getDescription()}


        <#if authUserEmail==criminalRecord.policeStation.email>
            <a href="/police/edit-criminal-record/${criminalRecord.getId()}/${citizen.getId()}">Edit</a>
        </#if>


    </#list>
</div>