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

const itemId = document.getElementById('item-id')


function incrementNumber() {

    $.ajax({
        url: "/item",
        type: "POST",
        data: {itemId: itemId.innerText,
        operation: "increment"},
        dataType: "json",

        success: function (response) {
            console.info('data received')

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}

function decrementNumber(group) {
    $.ajax({
        url: "/item",
        type: "POST",
        data: {itemId: itemId.innerText,
            operation: "decrement"},
        dataType: "json",

        success: function (response) {
            console.info('data received')

        },
        error: (error) => {
            console.log(JSON.stringify(error));
        }
    });
}