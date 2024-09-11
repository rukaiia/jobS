<#import  "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Job search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
                crossorigin="anonymous"></script>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg bg-body-tertiary mb-3" data-bs-theme="dark">
        <div class="container">
            <a class="navbar-brand" href="/">JobSearch</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <li>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        <@spring.message "layout.lang"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" href="?lang=en"> <@spring.message "layout.lang3"/></a></li>
                        <li><a class="dropdown-item" href="?lang=ru"> <@spring.message "layout.lang2"/></a></li>
                        <li><a class="dropdown-item" href="?lang=ky"> <@spring.message "layout.lang4"/></a></li>
                    </ul>
                </div>
            </li>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav d-flex flex-fill">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/users/profile"><@spring.message "layout.profile"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/employee/resumes/1/vacancy/2"><@spring.message "layout.messages"/></a>
                    </li>
                    <li class="nav-item ms-lg-auto">
                        <a class="nav-link active" href="/login"><@spring.message "layout.login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/users/register"><@spring.message "layout.register"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/logout"><@spring.message "layout.logout"/></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">

        <#nested spring>

    </div>

    </body>

    </html>

</#macro>


