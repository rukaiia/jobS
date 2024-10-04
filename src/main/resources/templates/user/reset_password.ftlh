
<#import "../layout.ftlh" as layout>
<@layout.layout>
    <h2>Reset Password</h2>

    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <#if message??>
        <div class="alert alert-success">${message}</div>
    </#if>

    <form method="post" action="/users/reset_password">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <input type="hidden" name="token" value="${token!''}" />

        <label for="password">New Password</label>
        <input type="password" id="password" name="password" required />

        <button type="submit">Reset Password</button>
    </form>

</@layout.layout>
