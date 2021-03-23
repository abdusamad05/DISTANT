<#import "parts/common.ftl" as c>

<@c.page>




<div>
    <script type="text/javascript" src="/static/tinymce/tinymce.min.js"></script>
    <script type="text/javascript">

        tinymce.init({
            selector: 'textarea',
            plugins: "link,image,media,imagetools,autolink,code"
        });

    </script>
</div>
<div>
    <a href="http://mega.nz" title="Загрузить файл на mega.nz" rel="nofollow" target="_blank">Файл на mega.nz</a>
    <a href="" rel="nofollow" onclick="window.open('http://upload.uploadpics.ru','upp','scrollbars=1,top=0,left=0,resizable=1,width=680,height=350');return false;">Вставить картинку</a></div>
<div class="form-group">
    <form method="post" enctype="multipart/form-data">

        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
               value="<#if message??>${lesson.text}</#if>" name="lessonname" placeholder="Введите название">
        <#if textError??>
            ${textError}

        <div class="invalid-feedback">

        </div>
        </#if>



        <select size="1" class="form-control" name="lessoncategory">

            <#list categories as category>

                <option name="category">${category.categoryname}</option>
            </#list>
        </select>
        <div class="form-group">
            <div class="custom-file">
        <input type="file" name="file" id="customFile" placeholder="Выберите файл">
        <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
        </div>
        <textarea name="text" class="form-control" placeholder="Ведите текст" ></textarea>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>
<div>Список уроков</div>
<form method="get" action="/main">
    <input type="text" class="form-control" name="filter" value="${filter}">
    <button type="submit" class="btn btn-primary">Найти</button>
</form>
<#list lessons as lesson>
    <div>
        <b>${lesson.id}</b>
        <i>${lesson.authorName}</i>
        <i>${lesson.lessonname}</i>
        <i>${lesson.lessoncategory}</i>
        <i>${lesson.lessoncategory}</i>
    </div>
<#else>
    No message
</#list>

</@c.page>