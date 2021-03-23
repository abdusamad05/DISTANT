<#include "security.ftl">
<#macro login path isRegisterForm>
    <form action="${path}" method="post">


        <div class="form-group">
            <label class="col-sm-2 col-form-label">Логин</label>
            <div class="col-sm-6">
            <input type="text" name="username"  class="form-control ${(usernameError??)?string('is-invalid', '')}"/>
                <#if usernameError??>

                    <div class="invalid-feedback">
                        Введите логин.
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                <#if passwordError??>

                    <div class="invalid-feedback">
                        Введите пароль.
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>

            <div class="form-group">
                <label class="col-sm-2 col-form-label">Повторите пароль</label>
                <div class="col-sm-6">
                    <input type="password" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"/>
                    <#if password2Error??>

                        <div class="invalid-feedback">
                            Введите пароль.
                        </div>
                    </#if>
                </div>
            </div>

        <div class="form-group">
            <label class="col-sm-2 col-form-label">E-mail</label>
            <div class="col-sm-6">
                 <input type="email" name="email"
                        class="form-control ${(emailError??)?string('is-invalid', '')}"/>
                <#if emailError??>

                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        </#if>
            <#if !isRegisterForm>
        <a href="/registration">Регистрация</a>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button class="btn btn-primary" type="submit" ><#if isRegisterForm>Создать<#else>Войти</#if></button>
    </form>
</#macro>

<#macro logout>

    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><#if user??>Выйти<#else>Войти</#if></button>
    </form>
</#macro>