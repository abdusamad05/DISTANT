<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>



<div>
    <form method="post">
        <input type="text" name="categoryname" class="form-control" placeholder="Название категории" />
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>
<#list categories as category>
    <div>

        <span>${category.categoryname}</span>

    </div>
</#list>

</@c.page>