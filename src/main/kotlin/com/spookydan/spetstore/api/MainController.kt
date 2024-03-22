package com.spookydan.spetstore.api

import com.spookydan.spetstore.model.*
import com.spookydan.spetstore.service.AccountService
import com.spookydan.spetstore.service.CartService
import com.spookydan.spetstore.service.CatalogService
import com.spookydan.spetstore.service.OrderService
import jakarta.servlet.http.HttpServletRequest
import nl.captcha.Captcha
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.logging.Logger


@Controller
class MainController {
    @GetMapping("/home")
    fun getHome(model: Model): String {
        model.addAttribute("account", null)
        return "home"
    }


    @Autowired
    private lateinit var catalogService: CatalogService

    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var cartService: CartService

    @Autowired
    private lateinit var orderService: OrderService

    companion object {

        private const val INPUT_RESULTS_STRING = "input_results"
        private const val SEARCH_RESULTS_STRING = "search_results"
        private const val SEARCH_RESULTS_NOT_FOUND_STRING = "search_results_not_found"

    }

    private var account: Account? = null

    private val logger = Logger.getLogger("MainController")

    @GetMapping("/store")
    fun getStore(model: Model): String {
        val categoryList = catalogService.categoryList
        val productList: MutableList<Product> = ArrayList()
        val itemList: MutableList<Item> = ArrayList()

        var fishItems: MutableList<Item> = mutableListOf<Item>()
        var dogsItems = mutableListOf<Item>()
        var catsItems = mutableListOf<Item>()
        var reptilesItems = mutableListOf<Item>()
        var birdsItems = mutableListOf<Item>()


        categoryList.let { list ->
            for (c in list) {
                val products = catalogService.getProductListByCategory(c.categoryId)
                products.let { products ->
                    for (p in products) {
                        catalogService.getItemListByProduct(p.productId)?.let {
                            itemList.addAll(it)
                        }
                    }
                    productList.addAll(products)
                }

                when (c.name) {
                    "Fish" -> {
                        val productList = catalogService.getProductListByCategory(c.categoryId)
                        productList?.forEach {
                            catalogService?.getItemListByProduct(it.productId)?.let { item ->
                                fishItems.addAll(item)
                            }
                        }
                    }

                    "Dogs" -> {

                        val productList = catalogService.getProductListByCategory(c.categoryId)
                        productList?.forEach {
                            catalogService?.getItemListByProduct(it.productId)?.let { item ->
                                dogsItems.addAll(item)
                            }
                        }

                    }

                    "Cats" -> {
                        val productList = catalogService.getProductListByCategory(c.categoryId)
                        productList?.forEach {
                            catalogService?.getItemListByProduct(it.productId)?.let { item ->
                                catsItems.addAll(item)
                            }
                        }
                    }

                    "Reptiles" -> {
                        val productList = catalogService.getProductListByCategory(c.categoryId)
                        productList?.forEach {
                            catalogService?.getItemListByProduct(it.productId)?.let { item ->
                                reptilesItems.addAll(item)
                            }
                        }
                    }

                    "Birds" -> {
                        val productList = catalogService?.getProductListByCategory(c.categoryId)
                        productList?.forEach {
                            catalogService?.getItemListByProduct(it.productId)?.let { item ->
                                birdsItems.addAll(item)
                            }
                        }
                    }
                }

            }
        }

        model.addAttribute("fishItems", fishItems)
        model.addAttribute("dogsItems", dogsItems)
        model.addAttribute("catsItems", catsItems)
        model.addAttribute("reptilesItems", reptilesItems)
        model.addAttribute("birdsItems", birdsItems)

        model.addAttribute("categoryList", categoryList)
        model.addAttribute("productList", productList)
        model.addAttribute("itemList", itemList)

        return "store"
    }


    @PostMapping("/store/search")
    fun postStoreSearch(
        @RequestParam(required = true) searchQuery: String,
        model: Model
    ): String? {
        var CONTENT_URL = ""

        var searchResults: List<Item?>

        searchResults = findResults(searchQuery)
        CONTENT_URL = if (searchResults.isEmpty()) SEARCH_RESULTS_NOT_FOUND_STRING else SEARCH_RESULTS_STRING

        model.addAttribute("searchItems", searchResults)

        // Return the view name to render
        return CONTENT_URL
    }

    @PostMapping("/store/input")
    fun postStoreInput(
        @RequestParam(required = false) input: String,
        model: Model
    ): String? {
        model.addAttribute("searchItems", deleteDuplicates(findResults(input)))
        return INPUT_RESULTS_STRING
    }

    private fun deleteDuplicates(original: List<Item>): List<Item> {
        val temp = mutableListOf<Item>()
        for (i in original) {
            var contains = false
            for (j in temp) {
                if (i.product?.name == j.product?.name) {
                    contains = true
                    break
                }
            }
            if (!contains) {
                temp.add(i)
            }
        }
        return temp
    }

    private fun findResults(searchQuery: String): List<Item> {

        val productList = catalogService.searchProductList(searchQuery)

        val itemList = mutableListOf<Item>()

        for (p in productList) {
            itemList.addAll(catalogService.getItemListByProduct(p.productId))
        }

        return itemList
    }

    @GetMapping("/item")
    fun getItemDetails(@RequestParam("itemId", required = true) itemId: String, model: Model): String? {
        val redirectUrl = "item_details"
        val item = catalogService?.getItem(itemId)
        return if (item != null) {
            model.addAttribute("item", item)
            redirectUrl
        } else {
            // Handle the case when item is not found
            "error" // Replace "error" with the appropriate error view name
        }
    }

    @GetMapping("/login")
    fun getLogin(): String {
        return "login"
    }

    @PostMapping("/login")
    fun postLogin(
        @RequestParam("answer") answer: String,
        @RequestParam("username") username: String,
        @RequestParam("password") password: String,
        model: Model
    ): ResponseEntity<String> {
        val captcha = model.getAttribute(Captcha.NAME) as Captcha?
//        if (!captcha?.isCorrect(answer)!!) {
//            val msg = "WRONG_CAPTCHA"
//            val json = "{\"message\": \"$msg\"}"
//            return flush(json, HttpStatus.OK)
//        }

        account?.let { return flush("already logged in", HttpStatus.BAD_REQUEST) }

        val loginAccount = accountService.getAccount(username, password)
        if (loginAccount == null) {
            val msg = "WRONG_CREDENTIALS"
            val json = "{\"message\": \"$msg\"}"
            return flush(json, HttpStatus.OK)
        } else {
            loginAccount.password = "null"


            if (loginAccount.listOption) {
                val myList = catalogService.getProductListByCategory(loginAccount.favouriteCategoryId)
                model.addAttribute("myList", myList)
            }

            account = loginAccount
            cartService.bind(loginAccount.username)

            val path = "/profile"
            val json = "{\"redirect\": \"$path\"}"

            return flush(json, HttpStatus.OK)
        }
    }

    @GetMapping("/register")
    fun getRegister(): String {
        return "register"
    }

    @PostMapping("/register/save")
    fun postRegSave(
        @RequestParam("firstName", required = true) firstName: String,
        @RequestParam("lastName", required = true) lastName: String,
        @RequestParam("username", required = true) username: String,
        @RequestParam("password", required = true) password: String,
        @RequestParam("emailAddress", required = true) emailAddress: String,
        @RequestParam("shippingAddress", required = true) shippingAddress: String,
        @RequestParam("shippingAddressTwo", required = true) shippingAddressTwo: String,
        @RequestParam("country", required = true) country: String,
        @RequestParam("zip", required = true) zip: String,
        @RequestParam("languagePreference", required = true) languagePreference: String,
        @RequestParam("favoriteCategory", required = true) favoriteCategory: String,
        @RequestParam("myBanner", required = true) myBanner: String,
        @RequestParam("myList", required = true) myList: String,
        model: Model
    ): ResponseEntity<String> {
        saveIntoDatabase(
            firstName,
            lastName,
            username,
            password,
            emailAddress,
            shippingAddress,
            shippingAddressTwo,
            country,
            zip,
            languagePreference,
            favoriteCategory,
            myBanner,
            myList
        )

        account = accountService.getAccount(username, password)

        model.addAttribute("loginAccount", account)

        val path = "/profile"
        val json = "{\"redirect\": \"$path\"}"
        return flush(json, HttpStatus.OK)
    }

    @PostMapping("/register/check")
    fun postRegCheck(
        @RequestParam("username", required = true) username: String
    ): ResponseEntity<String> {
        val account = accountService.getAccountByUsername(username)
        logger.info("account is not null" + account?.username)
        val msg = if (accountService.getAccountByUsername(username) != null) {
            "USER_EXISTS"
        } else {
            "ALLOWED"
        }
        val json = "{\"message\": \"$msg\"}"
        return flush(json, HttpStatus.OK)
    }

    private fun saveIntoDatabase(
        name: String,
        lastName: String,
        username: String,
        password: String,
        email: String,
        shipping1: String,
        shipping2: String,
        country: String,
        zip: String,
        language: String,
        favouriteCategory: String,
        myBanner: String,
        myList: String
    ) {
        val account = Account().apply {
            this.firstName = name
            this.lastName = lastName
            this.username = username
            this.password = password
            this.email = email
            this.address1 = shipping1
            this.address2 = shipping2
            this.country = country
            this.zip = zip
            this.languagePreference = language
            this.favouriteCategoryId = favouriteCategory
            this.bannerName = myBanner
        }

        accountService.insertAccount(account)
    }

    @GetMapping("/profile")
    fun getProfile(model: Model, redirectAttributes: RedirectAttributes): String {

        return if (account != null) {
            model.addAttribute("loginAccount", account)
            account?.let {
                val orderList = orderService.getOrdersByUsername(it.username)

                model.addAttribute("orderList", orderList)
            }
            "profile"
        } else {
            redirectAttributes.addFlashAttribute("redirectedFromProfile", true)
            "redirect:/login"
        }
    }

    @PostMapping("/update")
    fun updateProfile(
        @RequestParam("newPassword", required = true) newPassword: String,
        @RequestParam("newAddress", required = true) newAddress: String,
        @RequestParam("newAddressTwo", required = true) newAddressTwo: String,
        @RequestParam("newEmailAddress", required = true) newEmailAddress: String,
        @RequestParam("newPhone", required = true) newPhone: String,
        @RequestParam("newZip", required = true) newZip: String,
        model: Model
    ): ResponseEntity<String> {
        val account = model.getAttribute("loginAccount") as Account?
        account?.let {


            logger.info("$newPassword $newAddress $newAddressTwo $newEmailAddress $newPhone $newZip")

            account.password = newPassword
            account.phone = newPhone
            account.address1 = newAddress
            account.address2 = newAddressTwo
            account.email = newEmailAddress
            account.zip = newZip

            accountService.updateAccount(account)

            model.addAttribute("loginAccount", account)
            val msg = "SAVED"
            val json = "{\"message\": \"$msg\"}"
            return flush(json, HttpStatus.OK)
        }
        return flush("null", HttpStatus.OK)
    }

    @PostMapping("/invalidate")
    fun invalidateProfile(model: Model): ResponseEntity<String> {

        model.addAttribute("loginAccount", null)
        val path = "/login"
        val json = "{\"redirect\": \"$path\"}"
        return flush(json, HttpStatus.OK)
    }

    @GetMapping("/cart/size")
    fun getCartSize(): ResponseEntity<String> {
        val size = cartService.itemList.size.toString()
        return flush(size, HttpStatus.OK)
    }

    @GetMapping("/cart")
    fun getCart(model: Model): String {
        model.addAttribute("cartItems", cartService.itemList)
        model.addAttribute("loginAccount", account)
        return "cart"
    }

    @PostMapping("/cart/add")
    fun postCartAdd(
        @RequestParam("itemId", required = true)
        itemId: String,
        model: Model
    ): ResponseEntity<String> {
        catalogService.getItem(itemId)?.let {

            var contains = false
            cartService.itemList.forEach {
                if (it.item.itemId == itemId) {
                    contains = true
                    cartService.incrementQuantity(itemId)
                }
            }

            if (!contains) {
                val cartItem = CartItem().apply {
                    this.item = it
                    this.quantity = 1
                    this.inStock = true
                    calculateTotal()
                }
                cartService.insert(cartItem)
            }
        }

        return flush(cartService.itemList.size.toString(), HttpStatus.OK)
    }

    @PostMapping("/cart/increment", "cart/decrement")
    fun postCartQuantity(
        @RequestParam("itemId", required = true)
        itemId: String,
        model: Model,
        request: HttpServletRequest
    ): ResponseEntity<String> {

        val requestURL = request.requestURL.toString()

        if (requestURL.contains("increment")) {
            cartService.incrementQuantity(itemId, account?.username)
        } else if (requestURL.contains("decrement")) {
            cartService.decrementQuantity(itemId, account?.username)
        }
        cartService.itemList.forEach {
            if (it.item.itemId == itemId) {
                val msg = it.quantity.toString()
                val total = it.total.toString()
                val subTotal = cartService.calculateSubTotal().toString()
                val json =
                    "{\"message\": \"$msg\", \"itemId\": \"$itemId\", \"total\": \"$total\", \"subTotal\": \"$subTotal\"}"
                return flush(json, HttpStatus.OK)
            }
        }
        return flush("error", HttpStatus.OK)
    }

    @GetMapping("/cart/subtotal")
    fun getCartSubTotal(): ResponseEntity<String> {
        val subTotal = cartService.calculateSubTotal().toString()
        val json = "{\"message\": \"$subTotal\"}"
        return flush(json, HttpStatus.OK)
    }


    @PostMapping("/cart/remove")
    fun postCartRemove(
        @RequestParam("itemId", required = true)
        itemId: String
    ): ResponseEntity<String> {
        cartService.removeBy(itemId, account?.username)
        val subTotal = cartService.calculateSubTotal().toString()
        val json = "{\"message\": \"$subTotal\"}"
        return flush(json, HttpStatus.OK)
    }

    @PostMapping("/order/insert")
    fun postOrder(model: Model): ResponseEntity<String> {

        var path = "null"
        //TODO check on whether the cart is empty or not
        if (cartService.itemList.isNotEmpty()) {
            account?.let {
                val order = orderService.initOrder(it, cartService.calculateSubTotal())
                orderService.insertOrder(order)
                cartService.clear(account?.username)
                path = "/profile"
            }
        }

        val json = "{\"redirect\": \"$path\"}"

        return flush(json, HttpStatus.OK)
    }


    private fun flush(msg: String, status: HttpStatus): ResponseEntity<String> {
        return ResponseEntity.status(status)
            .contentType(MediaType.APPLICATION_JSON)
            .body(msg)
    }


}