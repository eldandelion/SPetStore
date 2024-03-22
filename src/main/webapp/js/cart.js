const loginButton = document.getElementById('login-button');
const subtotal = document.getElementById('subtotal');
const subtotalSidebar = document.getElementById('subtotalSidebar')
const rowCardItems = document.querySelectorAll('.row-card-item')
const removeButtons = document.querySelectorAll('.text-remove')
const buttonSubmit = document.getElementById('buttonSubmit')


getSubTotal()
if (buttonSubmit) {
    buttonSubmit.addEventListener('click', () => {
        $.ajax({
            url: "/order/insert",
            type: "POST",
            dataType: "json",

            success: function (data) {
                // Update the search results container with the response HTML

                console.info(data)
                window.location.href = data.redirect;

            },
            error: (error) => {
                console.log(JSON.stringify(error));
            }
        });
    })
}


removeButtons.forEach(function (button) {
    button.addEventListener('click', function () {
        // Handle button click event here
        const itemId = button.dataset.id;
        console.info(itemId)

        rowCardItems.forEach(function (element) {
            if (itemId === element.dataset.id) {
                removeItem(itemId)
                element.style.display = 'none';


            }
        })
        // Perform additional actions as needed
    });
});

function removeItem(itemId) {
    $.ajax({
        url: "/cart/remove",
        type: "POST",
        data: {
            itemId: itemId
        },
        dataType: "json",

        success: function () {

            getSubTotal()

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });



}

if (loginButton) {
    loginButton.addEventListener('click', function () {
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
        console.log(contextPath);
        window.location.href = contextPath + '/login'

    });
}

function getSubTotal() {
    $.ajax({
        url: "/cart/subtotal",
        type: "GET",
        dataType: "json",

        success: function (data) {
            // Update the search results container with the response HTML
            setSubtotal(data.message)

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}

function setSubtotal(value) {
    const formatter = new Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD', minimumFractionDigits: 2});
    const formattedNumber = formatter.format(value);
    subtotal.textContent = formattedNumber;
    subtotalSidebar.textContent = formattedNumber;
}


function updateNumberElement(group) {
    var numberElement = document.getElementById('number-' + group);
    numberElement.innerText = numbers[group];
}

const pageItemsDecrement = document.querySelectorAll('.page-link-decrement');
const pageItemsIncrement = document.querySelectorAll('.page-link-increment');
const pageItemsQuantity = document.querySelectorAll('.page-link-quantity');

const badgeTotal = document.querySelectorAll('.badge-total');

// Add click event listener to each button
pageItemsDecrement.forEach(function (button) {

    button.addEventListener('click', function (event) {
        // Get the value of the clicked button
        const itemId = event.target.value;
        // Display the value in the console window

        decrementQuantity(itemId);

    });
});

function updateTotal(total, itemId) {
    badgeTotal.forEach(function (button) {

        console.info(button.dataset.value)


        if (button.dataset.value === itemId) {

            const formatter = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
                minimumFractionDigits: 2
            });
            const formattedNumber = formatter.format(total);
            button.textContent = formattedNumber;
            console.info("text is set")
        }


    });
}


pageItemsIncrement.forEach(function (button) {

    button.addEventListener('click', function (event) {
        // Get the value of the clicked button
        const itemId = event.target.value;
        // Display the value in the console window
        console.info(itemId)

        incrementQuantity(itemId);


    });
});

function updateQuantity(quantity, itemId) {

    pageItemsQuantity.forEach(function (button) {
        console.info(button.value)
        console.info(quantity)
        if (button.value === itemId) {
            button.textContent = quantity;
        }
    });
}

function incrementQuantity(itemId) {
    $.ajax({
        url: "/cart/increment",
        type: "POST",
        data: {
            itemId: itemId
        },
        dataType: "json",

        success: function (data) {
            console.info(data.total)
            // Update the search results container with the response HTML
            console.info(data)
            updateQuantity(data.message, data.itemId)
            updateTotal(data.total, data.itemId)
            setSubtotal(data.subTotal)


        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}

function decrementQuantity(itemId) {
    $.ajax({
        url: "/cart/decrement",
        type: "POST",
        data: {
            itemId: itemId
        },
        dataType: "json",

        success: function (data) {
            // Update the search results container with the response HTML
            console.info(data)
            updateQuantity(data.message, data.itemId)
            updateTotal(data.total, data.itemId)
            setSubtotal(data.subTotal)

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}


const loginButtonTwo = document.getElementById('loginButton');

if (loginButtonTwo) {
    loginButtonTwo.addEventListener('click', function () {
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
        console.log(contextPath);
        window.location.href = contextPath + '/login'

    });
}