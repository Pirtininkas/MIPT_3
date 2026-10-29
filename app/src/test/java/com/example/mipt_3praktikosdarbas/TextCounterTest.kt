package com.example.mipt_3praktikosdarbas
import org.junit.Test
import org.junit.Assert.*

//UNIT TESTAI!!!
class TextCounterTest {

    //https://developer.android.com/training/testing/local-tests
    //https://medium.com/@vptarasov/unit-testing-in-kotlin-multiplatform-fb3ca7d5c869

//words test----------------------------------------------------------------------------------------
    @Test
    fun countWords_correctCount() {
        val input = "Vienas    trys trys septyni geriu arbata valgau sumustini"
        val result = TextCounter.countWords(input)
        assertEquals(8, result)
    }
    @Test
    fun countWords_empty() {
        val input = ""
        val result = TextCounter.countWords(input)
        assertEquals(0, result)
    }

//sentences test------------------------------------------------------------------------------------
    @Test
    fun countSentences_correctCount() {
        val input = "Valgau sumustini! Jis pagamintas su meile."
        val result = TextCounter.countSentences(input)
        assertEquals(2, result)
    }
    @Test
    fun countSentences_empty() {
        val input = "   "
        val result = TextCounter.countSentences(input)
        assertEquals(0, result)
    }

//punctuation test----------------------------------------------------------------------------------
    @Test
    fun countPunctuation_correctCount() {
        val input = "Sveikas, mano brolau, ar tu dar gyvas?"
        val result = TextCounter.countPunctuation(input)
        assertEquals(8, result)//tarpai irgi skaiciuojami
    }
    @Test
    fun countPunctuation_empty() {
        val input = ""
        val result = TextCounter.countPunctuation(input)
        assertEquals(0, result)
    }

//digits test---------------------------------------------------------------------------------------
    @Test
    fun countDigits_correctCount() {
        val input = "Lietuvos vardas pirmą kartą buvo paminėtas 1009 metais"
        val result = TextCounter.countDigits(input)
        assertEquals(4, result)
    }
    @Test
    fun countDigits_noNumbers() {
        val input = "Maximoje pirkau kalafijorą."
        val result = TextCounter.countDigits(input)
        assertEquals(0, result)
    }
}