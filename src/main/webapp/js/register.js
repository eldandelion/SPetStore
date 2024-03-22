// Get the form and submit button elements
const form = document.getElementById('registration-form');
const submitButton = document.getElementById('buttonSubmit');

const firstNameInput = document.getElementById('firstName');
const lastNameInput = document.getElementById('lastName');

const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');

const usernameInput = document.getElementById('username');
const emailInput = document.getElementById('email');

const zipInput = document.getElementById('zip');

const addressOneInput = document.getElementById('address');
const addressTwoInput = document.getElementById('address2');

const countrySpinner = document.getElementById('country');
const stateSpinner = document.getElementById('state');

const languageSpinner = document.getElementById('language-preference');
const favCategorySpinner = document.getElementById('favorite-category');

const enableMyList = document.getElementById('enable-mylist');
const enableMyBanner = document.getElementById('enable-mybanner');

const captcha = document.getElementById('captcha');
// Add click event listener to the submit button

firstNameInput.addEventListener('input', () => {
    if (!/^[a-zA-Z]+$/.test(firstNameInput.value)) {

        firstNameInput.classList.remove('is-valid'); // Add "is-invalid" class to show validation error
        firstNameInput.classList.add('is-invalid');
    } else {
        firstNameInput.classList.remove('is-invalid'); // Remove "is-invalid" class if valid
    }
})

lastNameInput.addEventListener('input', () => {
    if (!/^[a-zA-Z]+$/.test(lastNameInput.value)) {

        lastNameInput.classList.remove('is-valid');
        lastNameInput.classList.add('is-invalid'); // Add "is-invalid" class to show validation error
    } else {
        lastNameInput.classList.remove('is-invalid'); // Remove "is-invalid" class if valid
    }
})

confirmPasswordInput.addEventListener('input', () => {
    if (passwordInput.value !== confirmPasswordInput.value) {
        event.preventDefault(); // Prevent form submission
        confirmPasswordInput.classList.add('is-invalid'); // Add "is-invalid" class to show validation error
    } else {
        confirmPasswordInput.classList.remove('is-invalid'); // Remove "is-invalid" class if valid
    }
})

usernameInput.addEventListener('input', () => {

    const inputValue = username.value.trim()

    $.ajax({
        url: "/register/check",
        type: "POST",
        data: {
            username: inputValue
        },
        dataType: "json",

        success: function (data, textStatus) {
            console.log(data)
            if (data.message === "USER_EXISTS") {
                usernameInput.classList.add('is-invalid');
            } else if (data.message === "ALLOWED") {
                usernameInput.classList.remove('is-invalid');
            }

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
})

emailInput.addEventListener('input', () => {

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (emailInput.value === "") {
        emailInput.classList.remove('is-invalid');
        return
    }

    if (!emailRegex.test(emailInput.value.trim())) {
        emailInput.classList.add('is-invalid');
    } else {
        emailInput.classList.remove('is-invalid');
    }
})


zipInput.addEventListener('input', () => {
    const zipValue = zipInput.value.trim();
    const numericRegex = /^\d+$/;

    if (zipValue === "") {
        // Field is empty
        zipInput.classList.add("is-invalid");
    } else if (!numericRegex.test(zipValue)) {
        // Field does not contain a numeric value
        zipInput.classList.add("is-invalid");
    } else {
        // Field contains a valid numeric ZIP code
        zipInput.classList.remove("is-invalid");
    }
})



$(document).ready(function () {
    // Handle form submission
    $("#registration-form").submit(function (event) {
        // Prevent the default form submission
        event.preventDefault()
        event.stopPropagation()

        if (!this.checkValidity()) {



        } else {
            // Check if the first name contains symbols other than letters
            if (!/^[a-zA-Z]+$/.test(firstNameInput.value)) {
                console.info('removed')

                firstNameInput.classList.remove('is-valid'); // Add "is-invalid" class to show validation error
                firstNameInput.classList.add('is-invalid');
            } else {
                firstNameInput.classList.remove('is-invalid'); // Remove "is-invalid" class if valid
            }

            // Check if the last name contains symbols other than letters
            if (!/^[a-zA-Z]+$/.test(lastNameInput.value)) {

                lastNameInput.classList.remove('is-valid');
                lastNameInput.classList.add('is-invalid'); // Add "is-invalid" class to show validation error
            } else {
                lastNameInput.classList.remove('is-invalid'); // Remove "is-invalid" class if valid
            }

            //avengers assemble!

            const firstName = firstNameInput.value.trim()
            const lastName = lastNameInput.value.trim()
            const username = this.username.value.trim()
            const password = passwordInput.value.trim()
            const emailAddress = emailInput.value.trim()
            const shippingAddress = addressOneInput.value.trim()
            const shippingAddressTwo = addressTwoInput.value.trim()
            const country = countrySpinner.value
            const state = stateSpinner.value
            const zip = zipInput.value.trim()
            const languagePreference = languageSpinner.value
            const favoriteCategory = favCategorySpinner.value
            const myBanner = enableMyBanner.value
            const myList = enableMyList.value

            const captcha = this.captcha.value.trim()


            submitData(firstName, lastName, username, password, emailAddress, shippingAddress, shippingAddressTwo, country, state, zip, languagePreference, favoriteCategory, myBanner, myList, captcha)

        }

       // this.classList.add('was-validated')
    })
})

function submitData(firstName,
                    lastName,
                    username,
                    password,
                    emailAddress,
                    shippingAddress,
                    shippingAddressTwo,
                    country,
                    state,
                    zip,
                    languagePreference,
                    favoriteCategory,
                    myBanner,
                    myList,
                    captcha) {
    $.ajax({
        url: "/register/save",
        type: "POST",
        data: {
            firstName: firstName,
            lastName: lastName,
            username: username,
            password: password,
            emailAddress: emailAddress,
            shippingAddress: shippingAddress,
            shippingAddressTwo: shippingAddressTwo,
            country: country,
            state: state,
            zip: zip,
            languagePreference: languagePreference,
            favoriteCategory: favoriteCategory,
            myBanner: myBanner,
            myList: myList,
            captcha: captcha
        },
        dataType: "json",

        success: function (data, textStatus) {
            console.log(data)
            if (data.redirect) {
                console.log(data.redirect)
                window.location.href = data.redirect;
            }
            if (data.message === "USER_EXISTS") {
                usernameInput.classList.add('is-invalid');
            } else if (data.message === "ALLOWED") {
                usernameInput.classList.remove('is-invalid');
            }
        },

        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}
