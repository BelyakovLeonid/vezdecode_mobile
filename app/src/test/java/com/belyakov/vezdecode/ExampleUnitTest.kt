package com.belyakov.vezdecode

import com.belyakov.vezdecode.utils.toStringWithSpaces
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun makeCurrencyStringTest() {
        val long = 123456L
        assertEquals("123 456", long.toStringWithSpaces())
    }

    @Test
    fun makeCurrencyStringTest_mediumNumber() {
        val long = 1234L
        assertEquals("1 234", long.toStringWithSpaces())
    }

    @Test
    fun makeCurrencyStringTest_shortNumber() {
        val long = 12L
        assertEquals("12", long.toStringWithSpaces())
    }
}