

const loginButton = document.getElementById('login-button');

if (loginButton) {
    loginButton.addEventListener('click', function() {
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 1));
        console.log(contextPath);
        window.location.href=contextPath + '/login'

    });
}



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