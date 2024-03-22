<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=2">
    <title>JPetStore Store</title>

    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" th:href="@{/css/style.css}">
    <link rel="stylesheet" type="text/css" th:href="@{/css/profile.css}">
    <link rel="stylesheet" type="text/css" th:href="@{/css/sidebars.css}">
</head>

<body>

<!--navigation bar is the bar on top of the screen, it contains JPetStore logo, name, Home button and Cart button, search view and so on -->
<nav class="navbar navbar-expand-xl fixed-top shadow-sm">
    <div class="container-fluid">
        <img th:src="@{/images/jpetstore.png}" alt="" width="32" height="32" class="me-2">

        <a class="navbar-brand mb-0 h1" href="#">JPetStore</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <button class="btn btn-outline-secondary rounded-5 m-1" type="button" id="button-home"
                        onclick="window.location.href='/home'">
                    <svg class="bi pe-none me-2" width="16" height="16">
                        <use xlink:href="#home"/>
                    </svg>
                    Home</a>
                </button>


                <button class="btn btn-outline-secondary rounded-5 m-1 position-relative" type="submit" id="button-cart"
                        onclick="window.location.href='/cart'">
                    <svg class="bi pe-none me-2" width="16" height="16">
                        <use xlink:href="#cart"/>
                    </svg>
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                          id="cart-size-span">
                        0
                        <span class="visually-hidden">unread messages</span>
                    </span>
                    Cart</a>
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

<main class="d-flex flex-nowrap">

    <!--    Icons for navigation bars buttons Home button and Cart button-->
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
        <symbol id="home" viewBox="0 0 16 16">
            <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
        </symbol>


        <symbol id="cart" viewBox="0 0 16 16">
            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>

        <symbol id="profile" viewBox="0 0 16 16">
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
            <path fill-rule="evenodd"
                  d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
        </symbol>

        <symbol id="orders" viewBox="0 0 16 16">
            <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
            <path d="M7 5.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0zM7 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm-1.496-.854a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
        </symbol>

        <symbol id="settings" viewBox="0 0 16 16">

            <path d="M7.068.727c.243-.97 1.62-.97 1.864 0l.071.286a.96.96 0 0 0 1.622.434l.205-.211c.695-.719 1.888-.03 1.613.931l-.08.284a.96.96 0 0 0 1.187 1.187l.283-.081c.96-.275 1.65.918.931 1.613l-.211.205a.96.96 0 0 0 .434 1.622l.286.071c.97.243.97 1.62 0 1.864l-.286.071a.96.96 0 0 0-.434 1.622l.211.205c.719.695.03 1.888-.931 1.613l-.284-.08a.96.96 0 0 0-1.187 1.187l.081.283c.275.96-.918 1.65-1.613.931l-.205-.211a.96.96 0 0 0-1.622.434l-.071.286c-.243.97-1.62.97-1.864 0l-.071-.286a.96.96 0 0 0-1.622-.434l-.205.211c-.695.719-1.888.03-1.613-.931l.08-.284a.96.96 0 0 0-1.186-1.187l-.284.081c-.96.275-1.65-.918-.931-1.613l.211-.205a.96.96 0 0 0-.434-1.622l-.286-.071c-.97-.243-.97-1.62 0-1.864l.286-.071a.96.96 0 0 0 .434-1.622l-.211-.205c-.719-.695-.03-1.888.931-1.613l.284.08a.96.96 0 0 0 1.187-1.186l-.081-.284c-.275-.96.918-1.65 1.613-.931l.205.211a.96.96 0 0 0 1.622-.434l.071-.286zM12.973 8.5H8.25l-2.834 3.779A4.998 4.998 0 0 0 12.973 8.5zm0-1a4.998 4.998 0 0 0-7.557-3.779l2.834 3.78h4.723zM5.048 3.967c-.03.021-.058.043-.087.065l.087-.065zm-.431.355A4.984 4.984 0 0 0 3.002 8c0 1.455.622 2.765 1.615 3.678L7.375 8 4.617 4.322zm.344 7.646.087.065-.087-.065z"/>
        </symbol>

    </svg>


    <!--    Sidebar is the element on the left side of the screen which contains list of categories -->
    <div id="sidebar" class="d-flex flex-column flex-shrink-0 p-3 sidebar">
        <ul class="nav nav-pills flex-column mb-auto">


            <li class="nav-item">
                <a class="nav-link rounded-5 link-body-emphasis active" id="nav-link-profile" aria-current="page">
                    <div class="row justify-content-between align-items-center">
                        <div class="col-auto name">
                            <svg class="bi pe-none me-2" width="22" height="22">
                                <use xlink:href="#profile"/>
                            </svg>
                            Personal Info
                        </div>
                    </div>


                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link rounded-5 link-body-emphasis" id="nav-link-orders" aria-current="page">
                    <div class="row justify-content-between align-items-center">
                        <div class="col-auto name">
                            <svg class="bi pe-none me-2" width="22" height="22">
                                <use xlink:href="#orders"/>
                            </svg>
                            My Orders
                        </div>
                    </div>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link rounded-5 link-body-emphasis" aria-current="page">
                    <div class="row justify-content-between align-items-center">
                        <div class="col-auto name">
                            <svg class="bi pe-none me-2" width="22" height="22">
                                <use xlink:href="#settings"/>
                            </svg>
                            Settings
                        </div>
                    </div>
                </a>
            </li>
        </ul>

        <hr>
        <span class="badge d-flex align-items-center p-1 pe-2 text-primary-emphasis bg-primary-subtle border border-primary-subtle rounded-pill badge-login">
        <div class="dropdown m-0 p-0">
    <a href="#" id="profile-badge-text"
       class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle"
       data-bs-toggle="dropdown" aria-expanded="false">
        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
             class="bi bi-person-circle me-2" viewBox="0 0 16 16">
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
            <path fill-rule="evenodd"
                  d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
        </svg>
        <!-- <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2"> -->
        <span th:text="${loginAccount.firstName}"></span>
    </a>
    <ul class="dropdown-menu rounded-4 dropdown-menu-dark text-small shadow">
        <li><a class="dropdown-item" href="#">Settings</a></li>
        <li>
            <hr class="dropdown-divider">
        </li>
        <li><a class="dropdown-item" href="#" id="btn-sign-out">Sign out</a></li>
    </ul>
</div>
        </span>
    </div>

    <div data-bs-spy="scroll" data-target="#sidebar" id="album-categories" data-bs-smooth-scroll="true"
         class="album py-5 px-4 pb-0 bg-body-tertiary">

        <div class="container" id="container-profile">

            <div class="row justify-content-between align-items-center">
                <div class="col-auto">
                    <h1 id="profile-name" class="display-1 h1-category-name">
                        <span th:text="${loginAccount.firstName}"></span>
                    </h1>
                </div>


            </div>

            <div class="row m-0 p-0 g-3">

                <div class="col mt-0 p-0">


                    <div class="row p-4 mt-0 mb-1 m-2" id="container-info">

                        <div class="col-6">
                            <h4 class="mb-3" id="text-account-info">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                     class="bi bi-person-fill me-2" viewBox="0 0 16 16">
                                    <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                </svg>
                                Account information
                            </h4>
                            <hr>
                        </div>
                        <div class="col-6">
                            <h4 class="mb-3" id="text-shipping">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                     class="bi bi-rocket-takeoff me-2" viewBox="0 0 16 16">
                                    <path d="M9.752 6.193c.599.6 1.73.437 2.528-.362.798-.799.96-1.932.362-2.531-.599-.6-1.73-.438-2.528.361-.798.8-.96 1.933-.362 2.532"/>
                                    <path d="M15.811 3.312c-.363 1.534-1.334 3.626-3.64 6.218l-.24 2.408a2.56 2.56 0 0 1-.732 1.526L8.817 15.85a.51.51 0 0 1-.867-.434l.27-1.899c.04-.28-.013-.593-.131-.956a9.42 9.42 0 0 0-.249-.657l-.082-.202c-.815-.197-1.578-.662-2.191-1.277-.614-.615-1.079-1.379-1.275-2.195l-.203-.083a9.556 9.556 0 0 0-.655-.248c-.363-.119-.675-.172-.955-.132l-1.896.27A.51.51 0 0 1 .15 7.17l2.382-2.386c.41-.41.947-.67 1.524-.734h.006l2.4-.238C9.005 1.55 11.087.582 12.623.208c.89-.217 1.59-.232 2.08-.188.244.023.435.06.57.093.067.017.12.033.16.045.184.06.279.13.351.295l.029.073a3.475 3.475 0 0 1 .157.721c.055.485.051 1.178-.159 2.065Zm-4.828 7.475.04-.04-.107 1.081a1.536 1.536 0 0 1-.44.913l-1.298 1.3.054-.38c.072-.506-.034-.993-.172-1.418a8.548 8.548 0 0 0-.164-.45c.738-.065 1.462-.38 2.087-1.006ZM5.205 5c-.625.626-.94 1.351-1.004 2.09a8.497 8.497 0 0 0-.45-.164c-.424-.138-.91-.244-1.416-.172l-.38.054 1.3-1.3c.245-.246.566-.401.91-.44l1.08-.107-.04.039Zm9.406-3.961c-.38-.034-.967-.027-1.746.163-1.558.38-3.917 1.496-6.937 4.521-.62.62-.799 1.34-.687 2.051.107.676.483 1.362 1.048 1.928.564.565 1.25.941 1.924 1.049.71.112 1.429-.067 2.048-.688 3.079-3.083 4.192-5.444 4.556-6.987.183-.771.18-1.345.138-1.713a2.835 2.835 0 0 0-.045-.283 3.078 3.078 0 0 0-.3-.041Z"/>
                                    <path d="M7.009 12.139a7.632 7.632 0 0 1-1.804-1.352A7.568 7.568 0 0 1 3.794 8.86c-1.102.992-1.965 5.054-1.839 5.18.125.126 3.936-.896 5.054-1.902Z"/>
                                </svg>
                                Shipping
                            </h4>
                            <hr>
                        </div>

                        <div class="col-sm-3">
                            <label for="firstName" class="form-label text-secondary">First name</label>
                            <input disabled type="text" class="form-control rounded-3" id="firstName"
                                   th:value="${loginAccount.firstName}"/>

                        </div>

                        <div class="col-sm-3">
                            <label for="lastName" class="form-label text-secondary">Last name</label>
                            <input disabled type="text" class="form-control rounded-3" id="lastName"
                                   th:value="${loginAccount.lastName}"/>

                        </div>

                        <div class="col-6">
                            <label for="address" class="form-label text-secondary">Address</label>
                            <div class="input-group has-validation">
                                <input disabled type="text" class="form-control rounded-3" id="address"
                                       th:value="${loginAccount.address1}" placeholder=""/>
                                <button class="btn text-secondary rounded-3 btn-edit" type="button"
                                        id="button-edit-address">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </div>

                        </div>

                        <div class="col-3 mt-2">
                            <label for="username" class="form-label text-secondary">Username</label>
                            <div class="input-group has-validation">
                                <input disabled type="text" class="form-control rounded-3" id="username"
                                       th:value="${loginAccount.username}"/>

                            </div>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="password" class="form-label text-secondary">Password</label>
                            <div class="input-group has-validation">
                                <input type="text" disabled class="form-control rounded-3" id="password"
                                       placeholder="Password">
                                <button class="btn text-secondary rounded-3 btn-edit" type="button"
                                        id="button-edit-password">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div class="col-6 mt-2">
                            <label for="address2" class="form-label text-secondary">Address 2 <span
                                    class="text-secondary">(Optional)</span></label>
                            <div class="input-group has-validation">
                                <input disabled type="text" class="form-control rounded-3" id="address2"
                                       th:value="${loginAccount.address2}" placeholder=""/>
                                <button class="btn text-secondary rounded-3 btn-edit" type="button"
                                        id="button-edit-address-two">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="email" class="form-label text-secondary">Email</label>
                            <div class="input-group has-validation">
                                <input type="email" disabled class="form-control rounded-3" id="email"
                                       th:value="${loginAccount.email}"/>
                                <button class="btn text-secondary rounded-3 btn-edit" type="button"
                                        id="button-edit-email">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="phone" class="form-label text-secondary">Phone Number</label>
                            <div class="input-group has-validation">
                                <input type="tel" disabled class="form-control rounded-3" id="phone"
                                       placeholder="" th:value="${loginAccount.phone}"/>
                                <button class="btn text-secondary rounded-3 btn-edit" type="button"
                                        id="button-edit-phone">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>


                        <div class="col-3 mt-2">
                            <label for="country" class="form-label text-secondary">Country</label>
                            <select class="form-select rounded-3" id="country">
                                <option value="">Choose...</option>
                                <option>United States</option>
                            </select>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="state" class="form-label text-secondary">State</label>
                            <select class="form-select rounded-3" id="state">
                                <option value="">Choose...</option>
                                <option>California</option>
                            </select>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="state" class="form-label text-secondary">Favorite category</label>
                            <select class="form-select rounded-3" id="favorite-category">
                                <option th:value="${loginAccount.favouriteCategoryId}"
                                        text=" ${loginAccount.favouriteCategoryId}">

                                </option>
                                <option>Fish</option>
                                <option>Dogs</option>
                                <option>Reptiles</option>
                                <option>Cats</option>
                                <option>Birds</option>
                            </select>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="state" class="form-label text-secondary">Language preference</label>
                            <select class="form-select rounded-3" id="language-preference">
                                <option th:value="${loginAccount.languagePreference}"
                                        th:text="${loginAccount.languagePreference}">

                                </option>
                                <option>English</option>
                                <option>Chinese</option>
                            </select>
                        </div>

                        <div class="col-3 mt-2">
                            <label for="zip" class="form-label text-secondary">Zip</label>
                            <div class="input-group has-validation">
                                <input disabled type="text" class="form-control rounded-3" id="zip"
                                       th:value="${loginAccount.zip}"/>
                                <button class="btn text-secondary rounded-3 btn-edit" type="button"
                                        id="button-edit-zip">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                            </div>
                            <div class="invalid-feedback">
                                Zip code required.
                            </div>

                        </div>
                        <div class="col-3 d-flex flex-column align-items-center">
                            <div class="flex-grow-1"></div>
                            <button disabled class="w-100 btn btn-primary btn-lg rounded-5 text-uppercase"
                                    id="button-save"
                                    type="submit">Save changes
                            </button>
                        </div>
                    </div>
                </div>


            </div>
        </div>

        <div class="container" id="container-order-history">

            <div class="row justify-content-between align-items-center">
                <div class="col-auto">
                    <h1 id="container-name"
                        class="display-1 h1-category-name">Order History
                    </h1>
                </div>


            </div>

            <div class="row m-1 p-0 g-3" id="row-order-history">

                <div class="col mt-0 p-0">
                    <div class="accordion rounded-5" id="accordionExample">

                        <div th:each="item : ${orderList}">
                            <div class="accordion-item rounded-4 mt-4">
                                <h2 class="accordion-header">
                                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseOne" aria-expanded="true"
                                            aria-controls="collapseOne" th:text="'Order №' + ${item.orderId} + ' ' + ${#calendars.format(item.orderDate, 'yyyy-MM-dd')}">
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse show"
                                     data-bs-parent="#accordionExample">
                                    <div class="row collapse-row d-flex m-0">

                                        <div class="col p-0 m-4">

                                            <div class="container mt-0">


                                                <dt class="col-sm-12 text-uppercase ms-2 mb-3">Shipping Address</dt>
                                                <hr>
                                                <div class="row">
                                                    <div class="col-sm-3 mt-2">First name:</div>
                                                    <div class="col-sm-9 mt-2"
                                                         th:text="${item.shipToFirstName}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">Last name:</div>
                                                    <div class="col-sm-9" th:text="${item.shipToLastName}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">Address 1:</div>
                                                    <div class="col-sm-9" th:text="${item.shipAddress1}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">Address 2:</div>
                                                    <div class="col-sm-9" th:text="${item.shipAddress2}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">City:</div>
                                                    <div class="col-sm-9" th:text="${item.shipCity}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">State:</div>
                                                    <div class="col-sm-9" th:text="${item.shipState}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">Zip:</div>
                                                    <div class="col-sm-9" th:text="${item.shipZip}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">Country:</div>
                                                    <div class="col-sm-9" th:text="${item.shipCountry}"></div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-3">Courier:</div>
                                                    <div class="col-sm-9" th:text="${item.courier}"></div>
                                                </div>

                                            </div>


                                        </div>
                                        <div class="col-12 mt-0">


                                            <div class="container mt-0" id="container-items-list">
                                                <div class="row mt-0 pt-3 m-2 p-2" id="list-heading">
                                                    <div class="col-4 text-uppercase">
                                                        <strong>Product details</strong>
                                                    </div>
                                                    <div class="col-3 text-center text-uppercase">
                                                        <strong>Quantity</strong>
                                                    </div>
                                                    <div class="col-3 text-center text-uppercase">
                                                        <strong>Price</strong>
                                                    </div>
                                                    <div class="col-2 text-end text-uppercase">
                                                        <strong>Total</strong>
                                                    </div>

                                                </div>


                                                <div th:each="lineItem : ${item.lineItems}">
                                                    <div class="row m-2 ps-2 pe-2">
                                                        <hr>
                                                        <div class="col-4">
                                                            <div class="container p-0 m-0">
                                                                <div class="row">
                                                                    <div class="col-6 d-flex flex-column justify-content-center align-content-center">
                                                                        <img class="bd-placeholder-img rounded-circle card-img-top image-holder crop-image"
                                                                             th:src="${lineItem.item.attribute2}"
                                                                             width="130" height="140">
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <div class="container p-0 m-0">
                                                                            <div class="row">
                                                                                <div class="col-12 d-flex flex-column justify-content-center">
                                                                                    <h6 class="my-0"
                                                                                        th:text="${lineItem.item.product.name}"></h6>
                                                                                    <small class="text-body-secondary"
                                                                                           th:text="${lineItem.item.product.description}"></small>
                                                                                    <small class="text-body-secondary"
                                                                                           th:text="'Product ID: ' + ${lineItem.item.product.productId}"></small>
                                                                                    <small class="text-body-secondary"
                                                                                           th:text="'Item ID: ' + ${lineItem.item.itemId}"></small>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-3 text-center">
                                                            <span class="badge rounded-pill badge-counter"
                                                                  th:text="${lineItem.quantity}"></span>
                                                        </div>
                                                        <div class="col-3 text-center">
                                                            <span class="badge rounded-pill badge-counter"
                                                                  th:text="'$' + ${lineItem.unitPrice}"></span>
                                                        </div>
                                                        <div class="col-2 text-end">
                                                            <span class="badge rounded-pill badge-counter"
                                                                  th:text="'$' + ${lineItem.total}"></span>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="row p-2 ms-2 me-2" id="list-bottom">
                                                    <hr>
                                                    <div class="col-6">
                                                        <span>Total (USD)</span>


                                                    </div>
                                                    <div class="col-6 text-end">
                                                        <strong th:text="'$' + ${item.subTotal}"></strong>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <div id="container-footer" class="row-cols-1 align-bottom">
            <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 mt-4">
                <div class="col-auto d-flex align-items-center">
                    <a href="/" class="mb-3 me-2 mb-md-0 text-body-secondary text-decoration-none lh-1">
                        <svg class="bi" width="30" height="24"><img
                                th:src="@{/images/jpetstore.png}" alt="" width="32"
                                height="32" class="me-2"></svg>
                    </a>
                    <span class="mb-3 mb-md-0 text-body-secondary">&copy; 2023 JPetstore, Inc</span>

                </div>


            </footer>


        </div>
    </div>


</main>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <strong class="me-auto">Meow!</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
            <div class="row mx-auto">
                <div class="col-8  justify-content-center align-content-center">The changes have been saved to the
                    server
                </div>
            </div>
        </div>
    </div>
</div>


</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script defer th:src="@{/js/profile.js}"></script>
</html>
