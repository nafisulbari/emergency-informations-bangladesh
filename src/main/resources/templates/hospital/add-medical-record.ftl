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
                    <h2 id="name">${citizen.getName()}</h2>
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
        <div class="row">
            <div class="col-md-12">
                <form action="<#if (medicalRecord.id)??>/hospital/edit-medical-record-action/${citizen.getId()}/${medicalRecord.hospital.id}/${medicalRecord.id}<#else>/hospital/${citizen.getId()}/add-medical-record-action</#if>"
                      enctype="multipart/form-data" method="post">
                    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <h2 class="text-left" id="text-left">Medical Record
                        <#if !medicalRecord?? || authUserEmail==medicalRecord.hospital.email>
                            <button class="btn btn-success float-right" id="submitBt"
                                    type="submit"><#if (medicalRecord.id)??>Update Record<#else>Add Record</#if></button>
                        </#if>
                        <#if flag??>
                            <p style="color: red">${flag}</p>
                        </#if></h2>

                    <#if flagCp??>
                        <p style="color: red">${flagCp}</p>
                    </#if>



                    <#if (medicalRecord.id)??>
                    <#else>

                        <div class="form-group"><input class="form-control" type="text" name="citizenPoint"
                                                       placeholder="Give citizen point to"></div>

                    </#if>


                    <div class="form-group"><input class="form-control" type="text" name="title" placeholder="Title"
                                                   value="<#if (medicalRecord.title)??>${medicalRecord.title}</#if>"
                                                   required
                                <#if !medicalRecord?? || authUserEmail==medicalRecord.hospital.email>

                                <#else>
                                    disabled
                                </#if>
                        ></div>


                    <div class="form-group"><input class="form-control" type="date" name="date"
                                                   value="<#if (medicalRecord.date)??>${medicalRecord.date?date}</#if>"
                                                   required
                                <#if !medicalRecord?? || authUserEmail ==medicalRecord.hospital.email>
                                <#else>
                                    disabled
                                </#if>
                        ></div>


                    <div class="form-group"><input class="form-control" type="text" name="doctor" placeholder="Doctor"
                                                   value="<#if (medicalRecord.doctor)??>${medicalRecord.doctor}</#if>"
                                                   required
                                <#if !medicalRecord?? || authUserEmail==medicalRecord.hospital.email>
                                <#else>
                                    disabled
                                </#if>
                        ></div>


                    <#if !medicalRecord?? || authUserEmail==medicalRecord.hospital.email>

                        <div class="form-group"><textarea class="form-control" name="description" id="textarea-tiny"
                                                          placeholder="Medical record details and images"
                                                          required
                            ><#if (medicalRecord.description)??>${medicalRecord.description}</#if></textarea>
                        </div>


                    <#else>
                        <div id="record-description" class="form-group form-control">${medicalRecord.description}</div>
                    </#if>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.tiny.cloud/1/0mfdsilngywfygs9tbl0b40723kmi9tidztyrtr534whm93a/tinymce/5/tinymce.min.js"
        referrerpolicy="origin"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    $(document).ready(new function () {
        tinymce.init({
            selector: '#textarea-tiny',
            toolbar_drawer: 'floating',
            plugins: 'image code',
            toolbar: 'bold italic underline strikethrough ' +
                'alignleft aligncenter alignright alignjustify styleselect ' +
                'bullist numlist outdent indent ' +
                'undo redo removeformat subscript superscript | image code',
            images_upload_credentials: true,
            relative_urls: false,
            convert_urls: false,
            remove_script_host: false,

            images_upload_handler: function (blobInfo, success, failure) {
                var xhr, formData;
                xhr = new XMLHttpRequest();
                xhr.withCredentials = false;
                xhr.open('POST', '/hospital/images/${citizen.getId()}');
                var token = document.getElementById("csrf").value;
                xhr.setRequestHeader("X-CSRF-Token", token);
                xhr.onload = function () {
                    var json;
                    if (xhr.status != 200) {
                        failure('HTTP Error: ' + xhr.status);
                        return;
                    }
                    json = JSON.parse(xhr.responseText);

                    if (!json || typeof json.location != 'string') {
                        failure('Invalid JSON: ' + xhr.responseText);
                        return;
                    }
                    success(json.location);
                };
                formData = new FormData();
                formData.append('files', blobInfo.blob(), blobInfo.filename());
                xhr.send(formData);
            }
        });

        $(document).on('click', '#submitBt', function (e) {

            var content = tinymce.activeEditor.getContent();

            $("#textarea-tiny").val(content);
        });
    });
</script>

<#include "*/fragments/footer.ftl">