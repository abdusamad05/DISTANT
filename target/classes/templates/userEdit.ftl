<#import "parts/common.ftl" as c>

<@c.page>

    User editor
    <form action="/user" method="post">
        <label>
            имя
            <input type="text" class="form-control" name="username" value="${user.username}">
        </label>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.user_id}" name="user_id">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</@c.page>