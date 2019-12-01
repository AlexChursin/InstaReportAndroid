package com.example.instareport.utils

class SessionManager(cookies: String) {
    private var cookies = ""

    init {
        this.cookies = cookies
    }

    fun getCookies():String{
        return cookies
    }
}