<div>
    criminal rec



    <a href="/police/${citizen.getId()}/add-criminal-record">Add Record</a>

    <#list criminalRecords as criminalRecord>

        ${criminalRecord.getPoliceStation().getName()}
        ${criminalRecord.getTitle()}
        ${criminalRecord.getDate()}
        ${criminalRecord.getLocation()}
        ${criminalRecord.getDescription()}

    </#list>
</div>