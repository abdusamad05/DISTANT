<#import "parts/common.ftl" as c>

<@c.page>

List of users

    <table>
        <thead>
        <tr>
            <th>
                Ид
            </th>
            <th>
                Имя
            </th>
            <th>роль</th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
<tr>
    <td>${user.user_id}</td>
    <td>${user.username}</td>
    <td><#list user.roles as role>${role}<#sep>, </#list></td>
    <td><a href="/user/${user.user_id}">edit</a></td>
</tr>
    </#list>
        </tbody>
    </table>

</@c.page>