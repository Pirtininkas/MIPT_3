package com.example.mipt_3praktikosdarbas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mipt_3praktikosdarbas.ui.theme.MIPT_3PraktikosdarbasTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MIPT_3PraktikosdarbasTheme {

                val sentences = stringResource(R.string.sentences)
                val words = stringResource(R.string.words)
                val punctuation = stringResource(R.string.punctuation)
                val numbers = stringResource(R.string.numbers)
                val options = listOf(sentences,words,punctuation,numbers)
                val result = stringResource(R.string.result)

                //cia kad prisimintu visa info ir galima butu ja naudoti kitose funkcijose
                var inputText by remember { mutableStateOf("") }
                var selectedOption by remember { mutableStateOf(sentences) }
                var resultText by remember { mutableStateOf("") }
                val context = LocalContext.current

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            modifier = Modifier.padding(innerPadding)
                        )
                        TextInput(
                            text = inputText,
                            onTextChange = {inputText = it} //atnaujina input text i nauja ivesta verte
                        )
                        CalcDropdown(
                            options = options,
                            selectedOption = selectedOption,
                            onOptionSelected = {selectedOption = it}
                        )
                        Row{
                            CalcButton(
                                onClick = {
                                    //https://medium.com/@nicholausmichael51/displaying-toasts-in-jetpack-compose-76a52b773999
                                    if (inputText.isBlank()){
                                        Toast.makeText(context,getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
                                        resultText = ""
                                    }
                                    else {
                                        val count = when (selectedOption) {
                                            sentences -> TextCounter.countSentences(inputText)
                                            words -> TextCounter.countWords(inputText)
                                            punctuation -> TextCounter.countPunctuation(inputText)
                                            numbers -> TextCounter.countDigits(inputText)
                                            else -> 0
                                        }
                                        resultText = "$result $count"
                                    }
                                }
                            )
                        }
                        if(resultText.isNotBlank()) {
                            Text(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                text = resultText,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }


                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(
            text = stringResource(R.string.title),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            modifier = modifier.background(Color(0xFFD695E6)),
        )
    }
}

@Composable
fun TextInput(text: String, onTextChange: (String) -> Unit){

    Column {
        TextField(
            onValueChange = onTextChange,
            value = text,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            minLines = 5,
            maxLines = 15,
            placeholder = { Text(stringResource(R.string.input)) }

        )
    }
}

@Composable
fun CalcButton(onClick:() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onClick) {
            Text(stringResource(R.string.calculate))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcDropdown(options: List<String>,selectedOption: String, onOptionSelected: (String)-> Unit) {
// State to track the expanded state and selected option
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.choose)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                TextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, color = Color.DarkGray )},
                            onClick = {
                                onOptionSelected(option)
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
    }
}


