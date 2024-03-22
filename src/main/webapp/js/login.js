

const usernameInput = document.getElementById("floatingUsername");
const passwordInput = document.getElementById("floatingPassword");
const captchaInput = document.getElementById("floatingCaptcha");
const form = document.getElementById("form-login");

const formUsername = document.getElementById("form-username")

captchaInput.addEventListener("input", function () {
    captchaInput.classList.remove("is-invalid")
});

usernameInput.addEventListener("input", function () {
    usernameInput.classList.remove("is-invalid")
});

passwordInput.addEventListener("input", function () {
    passwordInput.classList.remove("is-invalid")
});

$(document).ready(function () {
    // Handle form submission
    $("#form-login").submit(function (event) {
        // Prevent the default form submission
        event.preventDefault()
        event.stopPropagation()

        if (this.checkValidity()) {


            const username = usernameInput.value;
            const password = passwordInput.value;
            const answer = captchaInput.value;


            $.ajax({
                url: "/login",
                type: "POST",
                data: {
                    username: username,
                    password: password,
                    answer: answer
                },
                dataType: "json",

                success: function (data, textStatus) {
                    console.log(data)
                    if (data.redirect) {
                        // data.redirect contains the string URL to redirect to
                        window.location.href = data.redirect;
                    } else if (data.message === "WRONG_CAPTCHA") {
                        //form.classList.remove('was-validated')
                        captchaInput.classList.add("is-invalid")


                    } else if (data.message === "WRONG_CREDENTIALS") {
                        usernameInput.classList.add("is-invalid")
                        passwordInput.classList.add("is-invalid")
                    }


                },
                error: (error) => {
                    console.log(JSON.stringify(error));
                }
            });
        }

        //this.classList.add('was-validated')




    });
});


$.ajax({
    url: "/cart/size",
    type: "GET",
    dataType: "json",

    success: function (response) {
        const cartSpan = document.getElementById("cart-size-span")
        cartSpan.innerText = response

    },
    error: (error) => {
        console.log(JSON.stringify(error));
    }
});