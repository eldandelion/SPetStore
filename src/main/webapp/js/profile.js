const btnLogout = document.getElementById('btn-sign-out')


btnLogout.addEventListener('click', () => {
    logOut()
})

function logOut() {

    $.ajax({
        url: "/profile/invalidate",
        type: "GET",
        dataType: "json",

        success: function (response) {
            window.location.href = response.redirect;

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}


// Make an AJAX request to the server
$.ajax({
    url: "/JPetStore_war/cart",
    type: "GET",
    data: {purpose: "GET_SIZE"},
    dataType: "json",

    success: function (response) {
        console.info(response)
        const cartSpan = document.getElementById("cart-size-span")
        cartSpan.innerText = response

    },
    error: (error) => {
        console.log(JSON.stringify(error));
    }
});


const editButtonAddress = document.getElementById('button-edit-address');
const addressInput = document.getElementById('address');

const editButtonAddressTwo = document.getElementById('button-edit-address-two');
const addressTwoInput = document.getElementById('address2');

const editButtonPassword = document.getElementById('button-edit-password')
const passwordInput = document.getElementById('password');

const editButtonEmail = document.getElementById('button-edit-email')
const emailInput = document.getElementById('email');

const editButtonPhone = document.getElementById('button-edit-phone')
const phoneInput = document.getElementById('phone');

const editButtonZip = document.getElementById('button-edit-zip')
const zipInput = document.getElementById('zip')

const saveButton = document.getElementById('button-save')
// Add a click event listener to the edit button
editButtonAddress.addEventListener('click', function () {
    // Enable the input field
    addressInput.disabled = false;
    activateSaveButton()
});

editButtonAddressTwo.addEventListener('click', function () {
    // Enable the input field
    addressTwoInput.disabled = false;
    activateSaveButton()
});

editButtonPassword.addEventListener('click', function () {
    // Enable the input field
    passwordInput.disabled = false;
    activateSaveButton()
});

editButtonEmail.addEventListener('click', function () {
    // Enable the input field
    emailInput.disabled = false;
    activateSaveButton()
});

editButtonPhone.addEventListener('click', function () {
    // Enable the input field
    phoneInput.disabled = false;
    activateSaveButton()
});

editButtonZip.addEventListener('click', function () {
    zipInput.disabled = false;
    activateSaveButton()
})

function activateSaveButton() {
    saveButton.disabled = false
}


saveButton.addEventListener('click', () => {
    saveChanges();
})


function saveChanges() {
    const newPassword = passwordInput.value.trim()
    const newAddress = addressInput.value.trim()
    const newAddressTwo = addressTwoInput.value.trim()
    const newEmailAddress = emailInput.value.trim()
    const newPhoneWhoDis = phoneInput.value.trim()
    const newZip = zipInput.value.trim()

    $.ajax({
        url: "/JPetStore_war/profile",
        type: "POST",
        data: {
            operation: "update",
            newPassword: newPassword,
            newAddress: newAddress,
            newAddressTwo: newAddressTwo,
            newEmailAddress: newEmailAddress,
            newPhone: newPhoneWhoDis,
            newZip: newZip
        },
        dataType: "json",

        success: function (data, textStatus) {
            console.log(data)

            if (data.message === "SAVED") {
                disableInputs();
                showToast();
                disableSaveButton();

            } else if (data.message === "NOT_SAVED") {
                //well, sucks to be you
            }
        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });


}


function disableSaveButton() {
    saveButton.disabled = true
}

function disableInputs() {
    emailInput.disabled = true
    phoneInput.disabled = true
    zipInput.disabled = true
    passwordInput.disabled = true
    addressInput.disabled = true
    addressTwoInput.disabled = true
}


function showToast() {
    // Get the toast element by its ID
    const toast = document.getElementById('liveToast');
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toast);
    toastBootstrap.show();

}

const navLinkProfile = document.getElementById('nav-link-profile')
const navLinkOrders = document.getElementById('nav-link-orders')
const navItems = document.getElementsByClassName('nav-item');

const containerProfile = document.getElementById('container-profile')
const containerOrders = document.getElementById('container-order-history')
navLinkProfile.addEventListener('click', () => {

    containerProfile.style.display = 'block';
    containerOrders.style.display = 'none';
    navLinkOrders.classList.remove('active');
    navLinkProfile.classList.add('active');

});

navLinkOrders.addEventListener('click', () => {
    for (let i = 0; i < navItems.length; i++) {
        navItems[i].classList.remove('active');
    }
    containerProfile.style.display = 'none';
    containerOrders.style.display = 'block';

    navLinkProfile.classList.remove('active');
    navLinkOrders.classList.add('active');

});


