package com.example.mipt_3praktikosdarbas

class TextCounter {
    companion object {
        //https://stackoverflow.com/questions/69567859/calling-method-without-creating-an-instance
        //buciau cia naudojes object o ne class, taciau uzduotis prase class.
        //kaip suprantu companion object yra atitikmuo @JvmStatic java kalboje

        //https://stackoverflow.com/questions/61749642/kotlin-count-no-of-occurrences-of-string-in-string
        fun countSentences(text: String): Int {
            if (text.isBlank()) return 0
            return text.split(".", "!", "?").count {it.isNotBlank()}
        }

        //https://stackoverflow.com/questions/55426910/how-to-count-the-number-of-words-in-kotlin-android
        fun countWords(text: String): Int {
            if (text.isBlank()) return 0
            val words = text.trim()
            return words.split("\\s+".toRegex()).size
        }

        //https://stackoverflow.com/questions/71104286/count-the-number-of-punctuation-symbols-in-a-string-kotlin
        fun countPunctuation(text: String): Int {
            if (text.isBlank()) return 0
            val punctuationSymbols = setOf('.', ',', ' ')

            return text.count{char -> char in punctuationSymbols}
        }

        //https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/is-digit.html
        fun countDigits(text: String): Int {
            if (text.isBlank()) return 0
            return text.count {it.isDigit()}
        }//manau cia skaitmenys
    }
}