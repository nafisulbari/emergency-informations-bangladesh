

<#setting date_format="yyyy-MM-dd">

<#if flag??>
    <p style="color: red">${flag}</p>
</#if>


<div id="form-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form action="<#if (medicalRecord.id)??>/hospital/edit-medical-record-action/${citizenId}/${medicalRecord.hospital.id}/${medicalRecord.id}<#else>/hospital/${citizenId}/add-medical-record-action</#if>" enctype="multipart/form-data" method="post">

                    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <h2 class="text-left">Add Medical Record<button class="btn btn-success float-right" type="submit">Add Record</button></h2>

                    <div class="form-group"><input class="form-control" type="text" name="title" placeholder="title" value="<#if (medicalRecord.title)??>${medicalRecord.title}</#if>" required></div>

                    <div class="form-group"><input type="date" name="date" value="<#if (medicalRecord.date)??>${medicalRecord.date?date}</#if>" required></div>

                    <div class="form-group"><input class="form-control " type="text" name="doctor" placeholder="Doctor" value="<#if (medicalRecord.doctor)??>${medicalRecord.doctor}</#if>" required></div>

                    <div class="form-group"><textarea class="form-control" rows="14" name="description" placeholder="Message" id="textarea-tiny" required><#if (medicalRecord.description)??>${medicalRecord.description}</#if></textarea></div>

                    <div class="form-group"></div>

                </form>
            </div>
        </div>
    </div>
</div>






    <label for="description">Description</label><br>
    <textarea name="description"



    <br><br>
    <input type="submit" id="submitBt" value="<#if (medicalRecord.id)??>Update Record<#else>Add Record</#if>">
</form>


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
                xhr.open('POST', '/hospital/images/${citizenId}');
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
