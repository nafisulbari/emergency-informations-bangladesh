
<div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div>
                    <h1 id="med-rec-headding">Medical Records<a class="btn btn-warning float-right" role="button" href="/hospital/${citizen.id}/add-medical-record" id="add-record-btn">Add Record</a></h1>
                </div>
            </div>
        </div>
    </div>
</div>

<#list medicalRecords as medicalRecord>

<div id="record-list-block">
    <div class="container">
        <div class="row">
            <div class="col" id="record-list">
                <div>
                    <div class="col-md-12">
                        <h2>${medicalRecord.getTitle()}</h2>
                        <div class="row">
                            <div class="col-md-6">
                                <h4>${medicalRecord.getHospital().getName()}</h4>
                            </div>
                            <div class="col-md-6">
                                <h4>${medicalRecord.getDate()}<br></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</#list>






