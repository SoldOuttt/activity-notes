<#include "../security.ftlh">
<#import "../macros/tokens.ftlh" as tokens>

<#macro page>
    <!DOCTYPE HTML>
    <html lang="en">
    <head>
        <title>Activity Notes</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg" style="background-color: lightblue">
        <div class="container-fluid ms-5">
            <a class="navbar-brand ms-5" href="/">
                <img src="https://getbootstrap.com/docs/5.2/assets/brand/bootstrap-logo.svg" alt="" width="30"
                     height="24" class="d-inline-block align-text-top ms-5">
                <i style="color: black">About</i>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav ms-4">
                    <#if isAdmin>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/user/all">
                                <i style="color: black">Users</i>
                            </a>
                        </li>
                    </#if>
                    <#if isUser>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">
                                <i style="color: black">Profile</i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/subject/all">
                                <i style="color: black">Subjects</i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/activity/all">
                                <i style="color: black">Activities</i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/statistics/day">
                                <i style="color: black">Statistics</i>
                            </a>
                        </li>
                        <li class="nav-item ms-5">
                            <div>
                                <form method="post" action="/logout">
                                    <button type="submit"
                                            style="background-color: wheat"
                                            class="btn btn-light">
                                        <i>Sign Out</i>
                                    </button>
                                    <@tokens.csrf_token/>
                                </form>
                            </div>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="btn btn-light"
                               style="background-color: wheat"
                               aria-current="page"
                               href="/login">
                                <i>Sign In</i>
                            </a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container ms-5">
        <div class="container ms-5">
            <div class="container ms-4">
                <#nested>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous">
    </script>
    </body>
    </html>
</#macro>