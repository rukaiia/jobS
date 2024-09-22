
<#import "../layout.ftlh" as main>
<@main.layout;spring>
    <div class="w-50 p-4 mx-auto border rounded-2 mb-4">
        <form action="/users/register" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="account_type" class="form-label"><@spring.message "form.account_type.label"/></label>
                <select name="accountType" id="account_type" class="form-select" required>
                    <option selected><@spring.message "form.account_type.option"/></option>
                    <option value="EMPLOYEE"><@spring.message "form.account_type.employee"/></option>
                    <option value="EMPLOYER"><@spring.message "form.account_type.employer"/></option>
                </select>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label"><@spring.message "form.name.label"/></label>
                <input name="name" type="text" class="form-control" id="name" placeholder="<@spring.message "form.name.placeholder"/>" required>
            </div>
            <div class="mb-3">
                <label for="surname" class="form-label"><@spring.message "form.surname.label"/></label>
                <input name="surname" type="text" class="form-control" id="surname" placeholder="<@spring.message "form.surname.placeholder"/>">
            </div>
            <div class="mb-3">
                <label for="age" class="form-label"><@spring.message "form.age.label"/></label>
                <input name="age" type="number" class="form-control" id="age" placeholder="<@spring.message "form.age.placeholder"/>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label"><@spring.message "form.email.label"/></label>
                <input name="email" type="email" class="form-control" id="email" placeholder="<@spring.message "form.email.placeholder"/>" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"><@spring.message "form.password.label"/></label>
                <input name="password" type="password" class="form-control" id="password" required>
                <div id="passwordHelp" class="form-text"><@spring.message "form.password.help"/></div>
            </div>
            <div class="mb-3">
                <label for="phone_number" class="form-label"><@spring.message "form.phone_number.label"/></label>
                <input name="phoneNumber" type="text" class="form-control" id="phone_number" placeholder="<@spring.message "form.phone_number.placeholder"/>" required>
            </div>
            <div class="mb-3">
                <label for="formFile" class="form-label"><@spring.message "form.file.label"/></label>
                <input name="file" class="form-control" type="file" id="formFile">
            </div>
            <button type="submit" class="btn btn-primary"><@spring.message "form.submit.button"/></button>
        </form>
    </div>
</@main.layout>
