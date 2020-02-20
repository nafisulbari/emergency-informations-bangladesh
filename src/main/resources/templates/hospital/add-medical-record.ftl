<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Emergency Information Bangladesh</title>

    <script src="https://cdn.tiny.cloud/1/0mfdsilngywfygs9tbl0b40723kmi9tidztyrtr534whm93a/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>


</head>
<body>
<#setting date_format="yyyy-MM-dd">

<#if flag??>

    <p style="color: red">${flag}</p>

</#if>

<#if (medicalRecord.id)??>
<form action="/hospital/edit-medical-record-action/${citizenId}/${medicalRecord.hospital.id}/${medicalRecord.id}" enctype="multipart/form-data" method="post">
    <#else>
    <form action="/hospital/${citizenId}/add-medical-record-action" enctype="multipart/form-data" method="post">
        </#if>

        <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="title">Title</label><br>
        <input type="text" name="title" placeholder="title"
               value="<#if (medicalRecord.title)??>${medicalRecord.title}</#if>" required><br>

        <label for="date">Date</label><br>
        <input type="date" name="date" value="<#if (medicalRecord.date)??>${medicalRecord.date?date}</#if>" required><br>

        <label for="doctor">Doctor</label><br>
        <input type="text" name="doctor" placeholder="doctor"
               value="<#if (medicalRecord.doctor)??>${medicalRecord.doctor}</#if>" required><br>

        <label for="description">Description</label><br>
        <textarea name="description" required><#if (medicalRecord.description)??>${medicalRecord.description}</#if></textarea>


        <br>
        <#if (medicalRecord.id)??>
            <input type="submit" value="Update Record">
        <#else>
            <input type="submit" value="Add Record">
        </#if>
</form>







<script>


    tinymce.init({
        selector: 'textarea',
        toolbar_drawer: 'floating',
        plugins: 'image code',
        toolbar: 'bold italic underline strikethrough ' +
            'alignleft aligncenter alignright alignjustify styleselect ' +
            'bullist numlist outdent indent ' +
            'undo redo removeformat subscript superscript | image code',
        images_upload_credentials: true,
        relative_urls: false,
        convert_urls: false,
        remove_script_host : false,

        images_upload_handler: function (blobInfo, success, failure) {
            var xhr, formData;
            xhr = new XMLHttpRequest();
            xhr.withCredentials = false;
            xhr.open('POST', '/hospital/images/${citizenId}');
            var token = document.getElementById("csrf").value;
            xhr.setRequestHeader("X-CSRF-Token", token);
            xhr.onload = function() {
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
</script>
</body>
</html>