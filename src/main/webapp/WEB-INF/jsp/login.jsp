<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=2">
    <title>JPetStore Home</title>
    <link rel="stylesheet" th:href="@{/css/login.css}">
    <link rel="stylesheet" th:href="@{/css/style.css}">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>


<nav class="navbar navbar-expand-xl fixed-top shadow-sm">
    <div class="container-fluid">
        <img th:src="@{/images/jpetstore.png}" alt="" width="32" height="32" class="me-2">

        <a class="navbar-brand mb-0 h1" href="#">JPetStore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav align-content-end me-auto mb-2 mb-lg-0">

                <button class="btn btn-outline-secondary rounded-5 m-1" type="button" id="button-home"
                        onclick="window.location.href='/home'">
                    <svg class="bi pe-none me-2" width="16" height="16">
                        <use xlink:href="#home"/>
                    </svg>
                    Home</a>
                </button>
                <button class="btn btn-outline-secondary rounded-5 m-1 position-relative"
                        onclick="window.open('/cart', '_blank')" type="button"
                        id="button-cart">
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" id="cart-size-span">
                        0
                        <span class="visually-hidden">unread messages</span>
                    </span>
                    <svg class="bi pe-none me-2" width="16" height="16">
                        <use xlink:href="#cart"/>
                    </svg>
                    Cart</a>
                </button>
                <button class="btn btn-outline-secondary rounded-5 m-1" type="button" id="button-store"
                        onclick="window.open('/store', '_blank')">
                    <svg class="bi pe-none me-2" width="16" height="16">
                        <use xlink:href="#store"/>
                    </svg>
                    Store</a>
                </button>


            </ul>


            <div class="dropdown">
                <a href="#" class="d-flex align-items-center link-dark rounded-5 text-decoration-none dropdown-toggle"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-globe-americas me-2" viewBox="0 0 16 16">
                        <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0ZM2.04 4.326c.325 1.329 2.532 2.54 3.717 3.19.48.263.793.434.743.484-.08.08-.162.158-.242.234-.416.396-.787.749-.758 1.266.035.634.618.824 1.214 1.017.577.188 1.168.38 1.286.983.082.417-.075.988-.22 1.52-.215.782-.406 1.48.22 1.48 1.5-.5 3.798-3.186 4-5 .138-1.243-2-2-3.5-2.5-.478-.16-.755.081-.99.284-.172.15-.322.279-.51.216-.445-.148-2.5-2-1.5-2.5.78-.39.952-.171 1.227.182.078.099.163.208.273.318.609.304.662-.132.723-.633.039-.322.081-.671.277-.867.434-.434 1.265-.791 2.028-1.12.712-.306 1.365-.587 1.579-.88A7 7 0 1 1 2.04 4.327Z"/>
                    </svg>
                    English
                </a>
                <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                    <li><a class="dropdown-item" href="#">English</a></li>
                    <li><a class="dropdown-item" href="#">汉语</a></li>
                </ul>
            </div>
        </div>


    </div>
</nav>


<main class="form-signin w-100 m-auto">
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">


        <symbol id="home" viewBox="0 0 16 16">
            <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
        </symbol>


        <symbol id="cart" viewBox="0 0 16 16">
            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>

        <symbol id="store" viewBox="0 0 16 16">
            <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5z"/>
        </symbol>
    </svg>


    <div class="container needs-validation" id="container-login" novalidate>


        <form id="form-login">
            <img class="mb-4" th:src="@{/images/jpetstore.png}" alt="" width="57"
                 height="57">
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>


            <div class="form-floating" id="form-username">
                <input type="text" class="form-control" name="username" id="floatingUsername" placeholder="Username"
                       required>
                <label for="floatingUsername">Username</label>
            </div>


            <div class="form-floating">
                <input type="password" class="form-control" name="password" id="floatingPassword" placeholder="Password"
                       required>
                <label for="floatingPassword">Password</label>
            </div>


            <div class="container d-flex m-0 p-0 align-content-center justify-content-center">
                <img id="image-captcha" th:src="@{/stickyImg}"/>
            </div>

            <div class="form-floating mt-2 mb-4">
                <input type="text" class="form-control" id="floatingCaptcha" placeholder="Captcha" name="answer"
                       required>
                <label for="floatingCaptcha">Captcha</label>
            </div>


            <button class="btn btn-primary rounded-5 w-100 py-2 text-uppercase" id="submitButton" type="submit">Sign in</button>
            <button class="btn btn-outline-secondary mt-3 rounded-5 w-100 py-2 text-uppercase" id="createAccountButton" type="button"
                    onclick="window.location.href='/register'">Register
            </button>
        </form>
    </div>

</main>

<div id="container-footer" class="row-cols-1 align-bottom">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 mt-4 border-top">
        <div class="col-auto d-flex align-items-center">
            <a href="/" class="mb-3 me-2 mb-md-0 text-body-secondary text-decoration-none lh-1">
                <svg class="bi" width="30" height="24"><img th:src="@{/images/jpetstore.png}" alt="" width="32" height="32" class="me-2"></svg>
            </a>
            <span class="mb-3 mb-md-0 text-body-secondary">&copy; 2023 JPetstore, Inc</span>

        </div>


    </footer>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script defer th:src="@{/js/login.js}"></script>


</body>
</html>
