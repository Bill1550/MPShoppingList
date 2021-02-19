package com.loneoaktech.tests.shared

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Temp {

    suspend fun letsDelay() {
        delay(10)
    }

    fun run() {
        GlobalScope.launch {
            
        }
    }
}