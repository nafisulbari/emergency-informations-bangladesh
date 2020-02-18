<div>

    med rec

    <a href="/hospital/${citizen.getId()}/add-medical-record">Add Record</a>

    <#list medicalRecords as medicalRecord>

        ${medicalRecord.getHospital().getName()}
        ${medicalRecord.getDate()}
        ${medicalRecord.getTitle()}
        ${medicalRecord.getDescription()}

    </#list>

</div>