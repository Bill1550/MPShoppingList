package com.loneoaktech.tests.shared

import Platform


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
