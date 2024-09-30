@file:OptIn(DelicateCoroutinesApi::class)

package com.reco1l.toolkt.kotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Run a task on asynchronous using global scope.
 */
fun async(block: CoroutineScope.() -> Unit) = GlobalScope.launch {
    block()
}

/**
 * Run a delayed task on asynchronous using global scope.
 */
fun delayed(time: Long, block: CoroutineScope.() -> Unit) = GlobalScope.launch {
    delay(time)
    block()
}


// Scope functions

/**
 * Executes the block if the boolean equals `true`.
 */
fun Boolean.then(block: () -> Unit): Boolean {
    if (this) {
        block()
    }
    return this
}