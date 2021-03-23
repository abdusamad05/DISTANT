<#import "parts/common.ftl" as c>

<@c.page>

<form method="get" action="/">
    <input type="text" class="form-control" name="filter" value="${filter?if_exists}">
    <button type="submit" class="btn btn-primary" >Найти</button>
</form>
    <div>Список статей</div>

    <#list lessons as lesson>
        <div>
            <table border="1" width="100%" cellpadding="5">
                <tr>
                    <th width="10%">Автор</th>
                    <th width="50%">Название</th>
                    <th width="10%">Офлайн</th>
                    <th width="30%">Категория</th>
                </tr>


                <tr>
                    <td width="10%">${lesson.authorName}</td>
                    <td width="50%"> <a href="/lesson/${lesson.id}">${lesson.lessonname}</a></td>
                    <td width="10%"> <#if lesson.filename??>
                    <a href="/doc/${lesson.filename}" download>Скачать</a>

                    </#if></td>
                    <td width="30%"><i>${lesson.lessoncategory}</i></td>
                </tr>
            </table>








        </div>
    <#else>
        No message
    </#list>
</@c.page>