const toastTrigger = document.getElementsByClassName('btn-purchase')
const toastLiveExample = document.getElementById('liveToast')


setToastListener(toastTrigger)
updateCart()

function setToastListener(trigger) {
    if (trigger) {
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
        for (let i = 0; i < trigger.length; i++) {
            trigger[i].addEventListener('click', () => {
                toastBootstrap.show();
                updateCart()
            });
        }
    }
}

function updateCart() {
    // Make an AJAX request to the server
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
}

document.getElementById("backToTop").addEventListener("click", function (event) {
    event.preventDefault(); // Pr  event the default link behavior
    document.getElementById("album-categories").scrollTo(
        {
            top: 0,
            behavior: "smooth"
        });
});


const btnClose = document.getElementById('button-close')
const searchContainer = document.getElementById('container-search');
const fishContainer = document.getElementById('container-fish');
const dogsContainer = document.getElementById('container-dogs');
const catsContainer = document.getElementById('container-cats');
const reptilesContainer = document.getElementById('container-reptiles');
const birdsContainer = document.getElementById('container-birds');
const searchInput = document.getElementById('search-input');

btnClose.addEventListener("click", function () {
    if (searchContainer.style.display === 'none') {
        searchContainer.style.display = 'block';
    } else {
        fishContainer.style.display = 'block';
        dogsContainer.style.display = 'block';
        catsContainer.style.display = 'block';
        reptilesContainer.style.display = 'block';
        birdsContainer.style.display = 'block';
        searchContainer.style.display = 'none';
        searchInput.value = '';
    }
});

const progressBar = document.getElementById('progress-bar');


$(document).ready(function () {
    // Handle form submission
    $("#search-form").submit(function (event) {
        // Prevent the default form submission
        event.preventDefault();
        progressBar.style.display = 'block'


        // Get the search query from the input field
        const searchQuery = $("#search-input").val();
        $("#container-search").show();
        $("#container-fish").hide();
        $("#container-dogs").hide();
        $("#container-cats").hide();
        $("#container-reptiles").hide();
        $("#container-birds").hide();


        // Make an AJAX request to the server
        $.ajax({
            url: "/store/search",
            type: "POST",
            data: {searchQuery: searchQuery},
            dataType: "text",

            success: function (response) {
                // Update the search results container with the response HTML
                $("#cards-search").html(response);

                const btnPurchase = $("#cards-search .btn-purchase");


                setToastListener(btnPurchase)


                progressBar.style.display = 'none'
            },
            error: (error) => {
                console.log(JSON.stringify(error));
                progressBar.style.display = 'none'
            }
        });
    });


});


$(document).ready(function () {
    const cards = document.getElementsByClassName('card');
    console.log(cards.length);
    for (let i = 0; i < cards.length; i++) {
        const card = cards[i];
        const viewButton = card.querySelector('.btn-view');

        // if (viewButton) {
        //     viewButton.addEventListener('click', function () {
        //         const itemId = card.getElementsByClassName('item-id');
        //         const itemIdText = itemId.item(0).textContent;
        //         console.log('Item ID:', itemId[0].textContent);
        //         // Perform any additional actions with the item ID
        //         $.ajax({
        //             url: "/JPetStore_war/item",
        //             type: "GET",
        //             data: {itemId: itemIdText},
        //             dataType: "json",
        //
        //             success: function (response) {
        //                 // Update the search results container with the response HTML
        //                 window.open('')
        //             },
        //             error: (error) => {
        //                 console.log(JSON.stringify(error));
        //             }
        //         });
        //     });
        // }


    }
});


// Get the search input and dropdown menu elements
const dropdownList = document.getElementById('search-list');
const dropdownMenu = document.getElementById('dropdown-search')


// Add click event listener to the dropdown menu items

setDropdownListener(dropdownMenu)

function setDropdownListener(dropdownMenu) {
    dropdownMenu.addEventListener('click', (event) => {

        // Check if the clicked element is a dropdown item
        if (event.target.classList.contains('dropdown-item')) {
            // Update the search input value with the selected item
            searchInput.value = event.target.textContent;
            dropdownList.style.display = 'none';

        }
    });
}


// Add input event listener to the search input
searchInput.addEventListener('input', () => {
    const searchText = searchInput.value.trim();

    if (searchText.length > 0) {
        sendSearchRequest(searchText);

        // Show the dropdown menu
        dropdownList.style.display = 'block';
    } else {
        // Hide the dropdown menu
        dropdownList.style.display = 'none';
    }
});

// Add click event listener to the document to hide the dropdown when clicking outside
document.addEventListener('click', (event) => {
    // Check if the clicked element is inside the dropdown menu or search input
    if (!dropdownMenu.contains(event.target) && event.target !== searchInput) {
        // Hide the dropdown menu
        dropdownList.style.display = 'none';
    }
});

function sendSearchRequest(input) {
    $.ajax({
        url: "/store/input",
        type: "POST",
        data: {input: input},
        dataType: "text",

        success: function (response) {
            // Update the search results container with the response HTML
            $("#dropdown-search").html(response);


        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}

// Get all the buttons with class "btn-purchase"
const purchaseButtons = document.querySelectorAll('.btn-purchase');

// Add click event listener to each button
purchaseButtons.forEach(function(button) {

    button.addEventListener('click', function(event) {
        event.stopPropagation();
        // Get the value of the clicked button
        const itemId = event.target.value;
        // Display the value in the console window


        sendPurchaseRequest(itemId);
    });
});

function sendPurchaseRequest(itemId) {
    $.ajax({
        url: "/cart/add",
        type: "POST",
        data: {itemId: itemId},
        dataType: "text",

        success: function (response) {
            // Update the search results container with the response HTML
            updateCart();

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}











