package com.example.mipt_3praktikosdarbas
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

const val TEST_INPUT = "Sveiki. Viso 123,gero."
@RunWith(AndroidJUnit4::class)
class WordCounterTestUI {
    //https://developer.android.com/develop/ui/compose/testing
    //https://medium.com/@kashifakhtar/testing-jetpack-compose-uis-with-kotest-a-beginners-guide-to-writing-clean-and-idiomatic-tests-in-e628963e5245
    //labai cia komplikuotai pradzioje atrode, bet pasirodo ganetinai paprasta
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    //testuojame ar veikia sakiniu skaiciavimas per ui
    @Test
    fun uiSentencesTest() {
        //surandame elementa ivedimo ir ivedame savo sakini
        rule.onNodeWithText("Įveskite tekstą").performTextInput(TEST_INPUT)
        //paspaudziame skaiciuoti kai ivedame teksta
        rule.onNodeWithText("Apskaičiuokite").performClick()
        //tikrinam rezultata, visada pirmas pasirinkimas yra skaicuoti pagal sakinius todel nesirenkam jokiu variantu
        rule.onNodeWithText("Rezultatas: 2").assertIsDisplayed()

        }
    @Test
    fun uiDigitsTest() {
        rule.onNodeWithText("Įveskite tekstą").performTextInput(TEST_INPUT)
        //isskleidziam meniu
        rule.onNodeWithText("Sakinius").performClick()
        rule.onNodeWithText("Skaiciu kieki").performClick()

        rule.onNodeWithText("Apskaičiuokite").performClick()
        //tikriname ar tikrai surado 3 skaitmenis
        rule.onNodeWithText("Rezultatas: 3").assertIsDisplayed()
    }
    @Test
    fun uiWordsTest() {
        rule.onNodeWithText("Įveskite tekstą").performTextInput(TEST_INPUT)
        //isskleidziam meniu
        rule.onNodeWithText("Sakinius").performClick()
        rule.onNodeWithText("Zodzius").performClick()

        rule.onNodeWithText("Apskaičiuokite").performClick()
        //tikriname ar tikrai surado 3 zodzius
        rule.onNodeWithText("Rezultatas: 3").assertIsDisplayed()
    }
    }
