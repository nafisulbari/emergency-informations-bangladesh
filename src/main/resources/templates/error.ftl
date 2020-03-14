<#include "fragments/head-nav.ftl">

<div id="block-info">
    <div class="container">
        <div class="row">
            <div class="col-md-6">

                <#if errorMessage??>
                    <h3 style="color: red">${errorMessage}</h3>
                </#if>
                <#if message??>
                    <h3 style="color: red">${message}</h3>
                </#if>
                <#if flag??>
                    <h3 style="color: red">${flag}</h3>
                </#if>

                <div id="push"></div>

            </div>
        </div>
    </div>
</div>

<#include "fragments/footer.ftl">