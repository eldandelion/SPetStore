
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=2">
    <title>JPetStore Home</title>
    <link rel="stylesheet" th:href="@{/css/register.css}">
    <link rel="stylesheet" th:href="@{/css/style.css}">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<main class="d-flex flex-nowrap">


    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
        <symbol id="home" viewBox="0 0 16 16">
            <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
        </symbol>
    </svg>

    <nav class="navbar navbar-expand-xl fixed-top shadow-sm">
        <div class="container-fluid">
            <img th:src="@{/images/jpetstore.png}" alt="" width="32" height="32"
                 class="me-2">

            <a class="navbar-brand mb-0 h1" href="#">JPetStore</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav align-content-end me-auto mb-2 mb-lg-0">

                    <button class="btn btn-outline-secondary rounded-5 m-1" type="button" id="button-cart"
                            onclick="window.location.href='/home'">
                        <svg class="bi pe-none me-2" width="16" height="16">
                            <use xlink:href="#home"/>
                        </svg>
                        Home</a>
                    </button>


                </ul>


                <div class="dropdown">
                    <a href="#"
                       class="d-flex align-items-center link-dark rounded-5 text-decoration-none dropdown-toggle"
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

    <div class="container w-75" id="container-form">
        <main>
            <div class="py-5 text-center">
                <img class="d-block mx-auto mb-4" th:src="@{/images/jpetstore.png}" alt=""
                     width="57" height="57">
                <h2 id="text-header">Registration form</h2>
                <p class="lead">Please input all the data carefully</p>
            </div>

            <div class="row">

                <div class="col-md-7 col-lg-8 mx-auto">
                    <h4 class="mb-3" id="text-account-info"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                                                 class="bi bi-person-fill me-2" viewBox="0 0 16 16">
                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                    </svg>Basic information</h4>
                    <form id="registration-form" class="needs-validation">


                        <div class="row g-3">


                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">First name</label>
                                <input type="text" class="form-control" id="firstName" placeholder="Jake" value=""
                                       required>
                                <div class="invalid-feedback">
                                     Name cannot contain symbols, that are not letters.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Last name</label>
                                <input type="text" class="form-control" id="lastName" placeholder="Paul" value=""
                                       required>
                                <div class="invalid-feedback">
                                    Last name cannot contain symbols, that are not letters.
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="username" class="form-label">Username</label>
                                <div class="input-group has-validation">
                                    <span class="input-group-text">@</span>
                                    <input type="text" class="form-control" id="username" placeholder="jakepaul"
                                           >
                                    <div class="invalid-feedback">
                                        Username already exists
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="username" class="form-label">Password</label>
                                <div class="input-group has-validation">
                                    <input type="password" class="form-control" id="password" placeholder="********"
                                           required>
                                    <div class="invalid-feedback">
                                        Your password is required.
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="username" class="form-label">Confirm password</label>
                                <div class="input-group has-validation">
                                    <input type="password" class="form-control" id="confirm-password" placeholder="********"
                                           required>
                                    <div class="invalid-feedback">
                                        Confirm the password
                                    </div>
                                </div>
                            </div>


                            <div class="col-12">
                                <label for="email" class="form-label">Email <span
                                        class="text-body-secondary">(Optional)</span></label>
                                <input type="email" class="form-control" id="email" placeholder="you@example.com">
                                <div class="invalid-feedback">
                                    Please enter a valid email address for shipping updates.
                                </div>
                            </div>

                            <hr class="my-4">
                            <h4 class="mb-3 mt-0" id="text-shipping-info">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                     class="bi bi-rocket-takeoff me-2" viewBox="0 0 16 16">
                                    <path d="M9.752 6.193c.599.6 1.73.437 2.528-.362.798-.799.96-1.932.362-2.531-.599-.6-1.73-.438-2.528.361-.798.8-.96 1.933-.362 2.532"/>
                                    <path d="M15.811 3.312c-.363 1.534-1.334 3.626-3.64 6.218l-.24 2.408a2.56 2.56 0 0 1-.732 1.526L8.817 15.85a.51.51 0 0 1-.867-.434l.27-1.899c.04-.28-.013-.593-.131-.956a9.42 9.42 0 0 0-.249-.657l-.082-.202c-.815-.197-1.578-.662-2.191-1.277-.614-.615-1.079-1.379-1.275-2.195l-.203-.083a9.556 9.556 0 0 0-.655-.248c-.363-.119-.675-.172-.955-.132l-1.896.27A.51.51 0 0 1 .15 7.17l2.382-2.386c.41-.41.947-.67 1.524-.734h.006l2.4-.238C9.005 1.55 11.087.582 12.623.208c.89-.217 1.59-.232 2.08-.188.244.023.435.06.57.093.067.017.12.033.16.045.184.06.279.13.351.295l.029.073a3.475 3.475 0 0 1 .157.721c.055.485.051 1.178-.159 2.065Zm-4.828 7.475.04-.04-.107 1.081a1.536 1.536 0 0 1-.44.913l-1.298 1.3.054-.38c.072-.506-.034-.993-.172-1.418a8.548 8.548 0 0 0-.164-.45c.738-.065 1.462-.38 2.087-1.006ZM5.205 5c-.625.626-.94 1.351-1.004 2.09a8.497 8.497 0 0 0-.45-.164c-.424-.138-.91-.244-1.416-.172l-.38.054 1.3-1.3c.245-.246.566-.401.91-.44l1.08-.107-.04.039Zm9.406-3.961c-.38-.034-.967-.027-1.746.163-1.558.38-3.917 1.496-6.937 4.521-.62.62-.799 1.34-.687 2.051.107.676.483 1.362 1.048 1.928.564.565 1.25.941 1.924 1.049.71.112 1.429-.067 2.048-.688 3.079-3.083 4.192-5.444 4.556-6.987.183-.771.18-1.345.138-1.713a2.835 2.835 0 0 0-.045-.283 3.078 3.078 0 0 0-.3-.041Z"/>
                                    <path d="M7.009 12.139a7.632 7.632 0 0 1-1.804-1.352A7.568 7.568 0 0 1 3.794 8.86c-1.102.992-1.965 5.054-1.839 5.18.125.126 3.936-.896 5.054-1.902Z"/>
                                </svg>Shipping</h4>

                            <div class="col-12 mt-0">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" placeholder="1234 Main St"
                                       >
                                <div class="invalid-feedback">
                                    Please enter your shipping address.
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="address2" class="form-label">Address 2 <span class="text-body-secondary">(Optional)</span></label>
                                <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
                            </div>

                            <div class="col-md-5">
                                <label for="country" class="form-label">Country</label>
                                <select class="form-select" id="country">
                                    <option value="">Choose...</option>
                                    <option>United States</option>
                                </select>
                                <div class="invalid-feedback">
                                    Please select a valid country.
                                </div>
                            </div>

                            <div class="col-md-4">
                                <label for="state" class="form-label">State</label>
                                <select class="form-select" id="state">
                                    <option value="">Choose...</option>
                                    <option>California</option>
                                </select>
                                <div class="invalid-feedback">
                                    Please provide a valid state.
                                </div>
                            </div>

                            <div class="col-md-3">
                                <label for="zip" class="form-label">Zip</label>
                                <input type="text" class="form-control" id="zip" placeholder="">
                                <div class="invalid-feedback">
                                    Zip code is not valid.
                                </div>
                            </div>
                        </div>


                        <hr class="my-4">

                        <h4 class="mb-3" id="text-profile-info"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-person-lines-fill me-2" viewBox="0 0 16 16">
                            <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5m.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1z"/>
                        </svg>Profile details</h4>

                        <div class="row g-3">

                            <div class="col-md-4">
                                <label for="state" class="form-label">Language preference</label>
                                <select class="form-select" id="language-preference">
                                    <option value="">Choose...</option>
                                    <option>English</option>
                                    <option>Chinese</option>
                                </select>
                                <div class="invalid-feedback">
                                    Please provide a language preference.
                                </div>
                            </div>
                            <div class="col-md-8">
                                <label for="state" class="form-label">Favorite category</label>
                                <select class="form-select" id="favorite-category">
                                    <option value="">Choose...</option>
                                    <option value="FISH">Fish</option>
                                    <option value="DOGS">Dogs</option>
                                    <option value="REPTILES">Reptiles</option>
                                    <option value="CATS">Cats</option>
                                    <option value="BIRDS">Birds</option>
                                </select>
                                <div class="invalid-feedback">
                                    Please provide a favorite category.
                                </div>
                            </div>
                        </div>

                        <div class="form-check mt-2">
                            <input type="checkbox" class="form-check-input" id="enable-mylist">
                            <label class="form-check-label" for="enable-mylist">Enable MyList</label>
                        </div>


                        <div class="form-check mt-2">
                            <input type="checkbox" class="form-check-input" id="enable-mybanner">
                            <label class="form-check-label" for="enable-mybanner">Enable MyBanner</label>
                        </div>

                        <hr  class="my-4">

                        <div class="container d-flex m-0 p-0 align-content-center justify-content-center">
                            <img id="image-captcha" th:src="@{/stickyImg}"/>
                        </div>



                        <div class="mt-2 mb-4">

                            <label for="username" class="form-label">Captcha</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="captcha" placeholder="Captcha" name="answer"
                                       >
                                <div class="invalid-feedback">
                                    Please enter correct captcha.
                                </div>
                            </div>
                        </div>



                        <button class="w-100 btn mb-5 btn-primary btn-lg rounded-5 text-uppercase" id="buttonSubmit" type="submit">
                            Continue
                        </button>
                    </form>
                </div>
            </div>
        </main>


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
<script defer th:src="@{/js/register.js}"></script>

</body>
</html>
