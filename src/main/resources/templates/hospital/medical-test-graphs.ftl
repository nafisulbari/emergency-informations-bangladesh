<#include "*/fragments/head-nav.ftl">

<#setting date_format="yyyy-MM-dd">

<div id="block-info">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1 id="citizen-heading">Citizen Information</h1>
            </div>
        </div>
        <div class="row" id="info-block">
            <div class="col-md-4">
                <div><img src="/citizen-records/${citizen.getId()}/${citizen.getImageUrl()}" id="profile-img"
                          alt="${citizen.getName()}"></div>
            </div>
            <div class="col-md-4">
                <div>
                    <a href="/${citizen.getId()}"><h2 id="name">${citizen.getName()}</h2></a>
                    <p style="color:rgb(77,77,77);">Sex: ${citizen.getSex()}</p>
                    <p style="color:rgb(77,77,77);">DOB:&nbsp;${citizen.getBirthDate()}<br></p>
                    <p style="color:rgb(77,77,77);">Mobile: ${citizen.getMobile()}</p>
                    <p style="color:rgb(77,77,77);">NID:&nbsp;${citizen.getNid()}<br></p>
                    <p style="color:rgb(77,77,77);">Address:&nbsp;${citizen.getAddress()}<br></p>
                    <p style="color:rgb(77,77,77);">Citizen Points: ${citizen.getCitizenPoint()}<br></p>
                </div>
            </div>
            <div class="col-md-4">
                <div id="emergency">
                    <h3 class="text-warning" id="e-info-text">Emergency Info</h3>
                    <p style="color:rgb(77,77,77);">ID: ${citizen.getId()}<br></p>
                    <p style="color:rgb(77,77,77);">Blood Group: ${citizen.getBloodGroup()}<br></p>
                    <p style="color:rgb(77,77,77);">Emergency Contact: ${citizen.getEmergencyRelation()}<br></p>
                    <p style="color:rgb(77,77,77);">Contact Mobile: ${citizen.getEmergencyMobile()}<br></p>


                </div>
            </div>
        </div>
    </div>
</div>


<div id="form-div">

    <div class="container">
        <h1 id="test-results-h1">Previous Tests Results</h1>
    </div>
    <div id="test-results" class="container">

        <h3>Basic</h3>
        <div class="row">
            <div class="col-md-6">
                <canvas id="testCBC"></canvas>
            </div>

            <div class="col-md-6">
                <canvas id="testBP"></canvas>
            </div>
        </div>
    </div>

    <div id="test-results" class="container">
        <h3>Heart</h3>
        <div class="row">
            <div class="col-md-6">
                <canvas id="testBS"></canvas>
            </div>
            <div class="col-md-6">
                <canvas id="testLP"></canvas>
            </div>
        </div>
    </div>

    <div id="test-results" class="container">
        <h3>Advance</h3>
        <div class="row">
            <div class="col-md-6">
                <canvas id="testECG"></canvas>
            </div>
            <div class="col-md-6">
                <canvas id="testURINE"></canvas>
            </div>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
<script>
    var ctx = document.getElementById('testCBC').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                <#list medicalRecords as medicalRecord>
                <#if medicalRecord.getTestCBC()!=''>
                '${medicalRecord.getDate()}',
                </#if>
                </#list>
            ],
            datasets: [{
                label: 'Complete Blood Count',
                data: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestCBC()!=''>
                    '${medicalRecord.getTestCBC()}',
                    </#if>
                    </#list>
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('testBP').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                <#list medicalRecords as medicalRecord>
                <#if medicalRecord.getTestBP()!=''>
                '${medicalRecord.getDate()}',
                </#if>
                </#list>
            ],
            datasets: [{
                label: 'Blood Pressure',
                data: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestBP()!=''>
                    '${medicalRecord.getTestBP()}',
                    </#if>
                    </#list>
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('testBS').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                <#list medicalRecords as medicalRecord>
                <#if medicalRecord.getTestBS()!=''>
                '${medicalRecord.getDate()}',
                </#if>
                </#list>
            ],
            datasets: [{
                label: 'Blood Sugar',
                data: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestBS()!=''>
                    '${medicalRecord.getTestBS()}',
                    </#if>
                    </#list>
                ],
                backgroundColor: [
                    'rgba(229,255,253,0.2)'
                ],
                borderColor: [
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('testLP').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <#list medicalRecords as medicalRecord>
                <#if medicalRecord.getTestLP()!=''>
                '${medicalRecord.getDate()}',
                </#if>
                </#list>
            ],
            datasets: [{
                label: 'Lipid Profile',
                data: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestLP()!=''>
                    '${medicalRecord.getTestLP()}',
                    </#if>
                    </#list>
                ],

                borderColor: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestLP()!=''>
                    'rgba(255, 159, 64, 1)',
                    </#if>
                    </#list>

                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('testECG').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <#list medicalRecords as medicalRecord>
                <#if medicalRecord.getTestECG()!=''>
                '${medicalRecord.getDate()}',
                </#if>
                </#list>
            ],
            datasets: [{
                label: 'ECG',
                data: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestECG()!=''>
                    '${medicalRecord.getTestECG()}',
                    </#if>
                    </#list>
                ],
                borderColor: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestECG()!=''>
                    'rgb(255,13,30)',
                    </#if>
                    </#list>

                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<script>
    var ctx = document.getElementById('testURINE').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                <#list medicalRecords as medicalRecord>
                <#if medicalRecord.getTestURINE()!=''>
                '${medicalRecord.getDate()}',
                </#if>
                </#list>
            ],
            datasets: [{
                label: 'Urine Test',
                data: [
                    <#list medicalRecords as medicalRecord>
                    <#if medicalRecord.getTestURINE()!=''>
                    '${medicalRecord.getTestURINE()}',
                    </#if>
                    </#list>
                ],
                backgroundColor: [
                    'rgba(255,255,48,0.2)'
                ],
                borderColor: [
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
<#include "*/fragments/footer.ftl">