package com.example.tejas.extensions

import java.util.regex.Pattern


public fun String.removeMonthAndDate(): String {

    this.let {
        if (contains("-")) {
            return Pattern.compile("-").split(this)[0]
        }
    }
    return this
}