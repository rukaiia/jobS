<#import "../layout.ftlh" as layout>
<@layout.layout>

    <h2>Forgot Password</h2>

    <form action="/users/forgot_password" method="post">
        <div class="form-group">
            <label for="email">Enter your email</label>
            <input type="email" class="form-control" id="email" name="email" required/>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</@layout.layout>
