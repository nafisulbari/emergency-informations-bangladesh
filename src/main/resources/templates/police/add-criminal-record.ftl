<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>

    <script src="https://cdn.tiny.cloud/1/0mfdsilngywfygs9tbl0b40723kmi9tidztyrtr534whm93a/tinymce/5/tinymce.min.js"
            referrerpolicy="origin"></script>


</head>
<body>

<#setting date_format="yyyy-MM-dd">

<#if flag??>
    <p style="color: red">${flag}</p>
</#if>


<form action="<#if (criminalRecord.id)??>/police/edit-criminal-record-action/${citizenId}/${criminalRecord.policeStation.id}/${criminalRecord.id}<#else>/police/${citizenId}/add-criminal-record-action</#if>"
      enctype="multipart/form-data" method="post">


    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label for="title">Title</label><br>
    <input type="text" name="title" placeholder="title"
           value="<#if (criminalRecord.title)??>${criminalRecord.title}</#if>" required><br>

    <label for="date">Date</label><br>
    <input type="date" name="date" value="<#if (criminalRecord.date)??>${criminalRecord.date?date}</#if>"
           required><br>

    <label for="location">Location</label><br>
    <input type="text" name="location" placeholder="location"
           value="<#if (criminalRecord.location)??>${criminalRecord.location}</#if>" required><br>


    <label for="description">Description</label><br>
    <textarea name="description" id="textarea-tiny"
              required><#if (criminalRecord.description)??>${criminalRecord.description}</#if></textarea>


    <br><br>
    <input type="submit" id="submitBt" value="<#if (criminalRecord.id)??>Update Record<#else>Add Record</#if>">
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
                xhr.open('POST', '/police/images/${citizenId}');
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
</body>
</html>