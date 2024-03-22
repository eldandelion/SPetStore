package com.spookydan.spetstore.model

import java.io.Serializable

data class Account(
    var username: String,
    var password: String,
    var email: String,
    var firstName: String,
    var lastName: String,
    var status: String,
    var address1: String,
    var address2: String,
    var city: String,
    var state: String,
    var zip: String,
    var country: String,
    var phone: String,
    var favouriteCategoryId: String,
    var languagePreference: String,
    var listOption: Boolean,
    var bannerOption: Boolean,
    var bannerName: String
) : Serializable {
    //well that is stupid
    constructor() : this(
        "", "", "", "", "", "", "", "", "", "", "", "", "",
        "", "", false, false, ""
    )
}
