<#include "*/fragments/head-nav.ftl">


<div id="table-block">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>NID</th>
                        <th>Accept</th>
                        <th>Decline</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#if citizenRequests?? >
                        <#list citizenRequests as citizenRequest>
                            <tr>
                                <td>${citizenRequest.getCitizen().getId()}</td>
                                <td>${citizenRequest.getCitizen().getName()}</td>
                                <td>${citizenRequest.getCitizen().getNid()}</td>
                                <td>
                                    <form action="/admin/citizen-update-request-accept/${citizenRequest.getId()}" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <button class="btn btn-success" type="submit">Accept</button>
                                    </form>

                                </td>
                                <td>
                                    <form action="/admin/citizen-update-request-decline/${citizenRequest.getId()}" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <button class="btn btn-danger" type="submit">Decline</button>
                                    </form>
                                </td>
                            </tr>

                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<#include "*/fragments/footer.ftl">